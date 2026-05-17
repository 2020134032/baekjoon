#!/usr/bin/env python3
"""Scrape Baekjoon problems referenced in algorithms/*/README.md or a workbook.

Output:
    problems/<algorithm_name>/<problem_number>.md  (per algorithms/*/README.md)
    problems/workbook_<id>_<slug>/<problem_number>.md  (per --workbook)

Run:
    scripts/.venv/bin/python3 scripts/scrape_problems.py
    scripts/.venv/bin/python3 scripts/scrape_problems.py --only backtracking
    scripts/.venv/bin/python3 scripts/scrape_problems.py --workbook 1152
    scripts/.venv/bin/python3 scripts/scrape_problems.py --workbook https://www.acmicpc.net/workbook/view/1152
    scripts/.venv/bin/python3 scripts/scrape_problems.py --validate-only
"""
from __future__ import annotations

import argparse
import os
import re
import sys
import time
from pathlib import Path
from typing import Iterable

import requests
from bs4 import BeautifulSoup
from markdownify import markdownify as md

REPO_ROOT = Path(__file__).resolve().parent.parent
ALGORITHMS_DIR = REPO_ROOT / "algorithms"
PROBLEMS_DIR = REPO_ROOT / "problems"

HEADERS = {
    "User-Agent": (
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) "
        "AppleWebKit/537.36 (KHTML, like Gecko) "
        "Chrome/124.0.0.0 Safari/537.36"
    ),
    "Accept-Language": "ko,en;q=0.9",
}

PROBLEM_LINK_RE = re.compile(r"acmicpc\.net/problem/(\d+)")
WORKBOOK_URL_RE = re.compile(r"acmicpc\.net/workbook/view/(\d+)")
MIN_VALID_LEN = 200  # bytes — anything smaller is almost certainly an error


def find_problem_numbers(readme_path: Path) -> list[str]:
    """Return ordered, de-duplicated list of problem numbers in a README."""
    text = readme_path.read_text(encoding="utf-8")
    seen: set[str] = set()
    ordered: list[str] = []
    for match in PROBLEM_LINK_RE.finditer(text):
        num = match.group(1)
        if num not in seen:
            seen.add(num)
            ordered.append(num)
    return ordered


def all_categories() -> list[tuple[str, Path]]:
    """Return (category_name, readme_path) for every algorithms/*/README.md."""
    out = []
    for child in sorted(ALGORITHMS_DIR.iterdir()):
        if child.is_dir():
            readme = child / "README.md"
            if readme.exists():
                out.append((child.name, readme))
    return out


def is_valid_problem_md(path: Path) -> bool:
    """Quick TDD-style sanity check on a saved problem markdown file."""
    if not path.exists():
        return False
    try:
        text = path.read_text(encoding="utf-8")
    except OSError:
        return False
    if len(text) < MIN_VALID_LEN:
        return False
    # Must contain canonical headers we always emit.
    if "## 문제 설명" not in text:
        return False
    # Must reference original link.
    if "acmicpc.net/problem/" not in text:
        return False
    return True


def fetch_problem_html(problem_number: str, session: requests.Session) -> str | None:
    """GET the problem page with retries. Returns HTML or None."""
    url = f"https://www.acmicpc.net/problem/{problem_number}"
    for attempt in range(4):
        try:
            res = session.get(url, headers=HEADERS, timeout=20)
        except requests.RequestException as exc:
            print(f"    network err on {problem_number} attempt {attempt+1}: {exc}")
            time.sleep(2 + attempt * 2)
            continue
        if res.status_code == 200:
            return res.text
        if res.status_code in (502, 503, 504, 429):
            print(f"    status {res.status_code} on {problem_number}, retrying…")
            time.sleep(3 + attempt * 2)
            continue
        print(f"    status {res.status_code} on {problem_number}, abort")
        return None
    return None


def section_md(soup: BeautifulSoup, css_id: str) -> str:
    node = soup.select_one(f"#{css_id}")
    if not node:
        return ""
    return md(node.decode_contents(), heading_style="ATX").strip()


def extract_samples(soup: BeautifulSoup) -> str:
    out_chunks: list[str] = []
    idx = 1
    while True:
        sin = soup.select_one(f"#sample-input-{idx}")
        sout = soup.select_one(f"#sample-output-{idx}")
        if not sin and not sout:
            break
        if sin:
            out_chunks.append(
                f"### 예제 입력 {idx}\n```\n{sin.get_text().rstrip()}\n```"
            )
        if sout:
            out_chunks.append(
                f"### 예제 출력 {idx}\n```\n{sout.get_text().rstrip()}\n```"
            )
        idx += 1
    return "\n\n".join(out_chunks)


def extract_limits(soup: BeautifulSoup) -> str:
    """Pull the time/memory limits row from the info table."""
    table = soup.select_one("table#problem-info")
    if not table:
        return ""
    headers = [th.get_text(strip=True) for th in table.select("thead th")]
    cells = [td.get_text(" ", strip=True) for td in table.select("tbody td")]
    if not headers or not cells:
        return ""
    pairs = [f"- **{h}**: {c}" for h, c in zip(headers, cells)]
    return "\n".join(pairs)


def make_markdown(problem_number: str, html: str) -> str | None:
    """Convert raw HTML to our problem markdown, or None if page is unusable."""
    soup = BeautifulSoup(html, "html.parser")
    title_tag = soup.select_one("#problem_title")
    if not title_tag:
        return None
    title = title_tag.get_text(strip=True)

    desc_node = soup.select_one("#problem_description")
    if desc_node is None or not desc_node.get_text(strip=True):
        return None

    # Rewrite relative image URLs to absolute so the markdown stays portable.
    for img in desc_node.find_all("img"):
        src = img.get("src") or img.get("data-src") or ""
        if src.startswith("/"):
            img["src"] = "https://www.acmicpc.net" + src

    description_md = md(desc_node.decode_contents(), heading_style="ATX").strip()
    input_md = section_md(soup, "problem_input")
    output_md = section_md(soup, "problem_output")
    limits_md = extract_limits(soup)
    samples_md = extract_samples(soup)

    parts = [f"# {problem_number}번: {title}", ""]
    if limits_md:
        parts += ["## 제한", limits_md, ""]
    parts += ["## 문제 설명", description_md, ""]
    if input_md:
        parts += ["## 입력", input_md, ""]
    if output_md:
        parts += ["## 출력", output_md, ""]
    if samples_md:
        parts += ["## 예제", samples_md, ""]
    parts += [
        "---",
        f"*원본 링크: <https://www.acmicpc.net/problem/{problem_number}>*",
        "",
    ]
    return "\n".join(parts)


def scrape_one(problem_number: str, target: Path, session: requests.Session) -> bool:
    html = fetch_problem_html(problem_number, session)
    if html is None:
        return False
    body = make_markdown(problem_number, html)
    if body is None:
        print(f"    parse fail on {problem_number}")
        return False
    target.parent.mkdir(parents=True, exist_ok=True)
    target.write_text(body, encoding="utf-8")
    if not is_valid_problem_md(target):
        print(f"    validation fail on {problem_number}")
        return False
    return True


def slugify(text: str) -> str:
    """ASCII slug — keeps Korean by leaving non-ASCII out, falls back to 'workbook'."""
    text = text.strip().lower()
    # Drop the leading "문제집:" prefix some workbook <title>s carry.
    text = re.sub(r"^문제집[:\s]*", "", text)
    text = re.sub(r"\s+", "_", text)
    text = re.sub(r"[^0-9a-z_\-가-힣]", "", text)
    return text or "workbook"


def parse_workbook_arg(arg: str) -> str:
    """Accept either a workbook id or a full workbook URL; return the id."""
    m = WORKBOOK_URL_RE.search(arg)
    if m:
        return m.group(1)
    if arg.isdigit():
        return arg
    raise ValueError(f"unrecognized workbook arg: {arg}")


def fetch_workbook(workbook_id: str, session: requests.Session) -> tuple[str, list[str]]:
    """Return (workbook_title, ordered problem numbers) for a workbook id."""
    url = f"https://www.acmicpc.net/workbook/view/{workbook_id}"
    for attempt in range(4):
        try:
            res = session.get(url, headers=HEADERS, timeout=20)
        except requests.RequestException as exc:
            print(f"  workbook network err attempt {attempt+1}: {exc}")
            time.sleep(2 + attempt * 2)
            continue
        if res.status_code == 200:
            break
        if res.status_code in (502, 503, 504, 429):
            time.sleep(3 + attempt * 2)
            continue
        raise RuntimeError(f"workbook {workbook_id} status {res.status_code}")
    else:
        raise RuntimeError(f"workbook {workbook_id} failed after retries")

    soup = BeautifulSoup(res.text, "html.parser")
    h1 = soup.select_one("h1")
    title = h1.get_text(strip=True) if h1 else f"workbook_{workbook_id}"

    seen: set[str] = set()
    ordered: list[str] = []
    # Problem numbers live in the first column of the workbook table.
    for row in soup.select("table.table tbody tr"):
        cells = row.select("td")
        if not cells:
            continue
        first = cells[0].get_text(strip=True)
        if first.isdigit() and first not in seen:
            seen.add(first)
            ordered.append(first)
    return title, ordered


def iter_targets(only: str | None) -> Iterable[tuple[str, str, Path]]:
    """Yield (category, problem_number, output_path) tuples."""
    for category, readme in all_categories():
        if only and category != only:
            continue
        for num in find_problem_numbers(readme):
            yield category, num, PROBLEMS_DIR / category / f"{num}.md"


def cmd_validate(only: str | None) -> int:
    bad: list[Path] = []
    total = 0
    for _, num, target in iter_targets(only):
        total += 1
        if not is_valid_problem_md(target):
            bad.append(target)
    print(f"validated {total} expected files; invalid/missing: {len(bad)}")
    for p in bad[:30]:
        print(f"  - {p.relative_to(REPO_ROOT)}")
    if len(bad) > 30:
        print(f"  … and {len(bad) - 30} more")
    return 0 if not bad else 1


def cmd_scrape(only: str | None, sleep_s: float, force: bool) -> int:
    session = requests.Session()
    targets = list(iter_targets(only))
    print(f"plan: {len(targets)} problems")
    failures: list[str] = []
    skipped = 0
    fetched = 0
    for i, (category, num, target) in enumerate(targets, 1):
        if not force and is_valid_problem_md(target):
            skipped += 1
            continue
        print(f"[{i}/{len(targets)}] {category}/{num} → {target.relative_to(REPO_ROOT)}")
        ok = scrape_one(num, target, session)
        if ok:
            fetched += 1
        else:
            failures.append(f"{category}/{num}")
        time.sleep(sleep_s)
    print(
        f"done. fetched={fetched} skipped={skipped} failed={len(failures)} "
        f"out of {len(targets)}"
    )
    if failures:
        print("failures:")
        for f in failures:
            print(f"  - {f}")
        return 1
    return 0


def cmd_workbook(arg: str, sleep_s: float, force: bool) -> int:
    workbook_id = parse_workbook_arg(arg)
    session = requests.Session()
    title, numbers = fetch_workbook(workbook_id, session)
    slug = slugify(title)
    out_dir = PROBLEMS_DIR / f"workbook_{workbook_id}_{slug}"
    out_dir.mkdir(parents=True, exist_ok=True)

    # Drop a small index file so the workbook is self-describing on disk.
    index_path = out_dir / "README.md"
    index_lines = [
        f"# 문제집 {workbook_id}: {title}",
        "",
        f"원본: <https://www.acmicpc.net/workbook/view/{workbook_id}>",
        "",
        f"문제 수: {len(numbers)}",
        "",
    ]
    for n in numbers:
        index_lines.append(f"- [{n}]({n}.md) — <https://www.acmicpc.net/problem/{n}>")
    index_path.write_text("\n".join(index_lines) + "\n", encoding="utf-8")

    print(f"workbook {workbook_id} '{title}' → {out_dir.relative_to(REPO_ROOT)}")
    print(f"plan: {len(numbers)} problems")

    failures: list[str] = []
    fetched = skipped = 0
    for i, num in enumerate(numbers, 1):
        target = out_dir / f"{num}.md"
        if not force and is_valid_problem_md(target):
            skipped += 1
            continue
        print(f"[{i}/{len(numbers)}] {num} → {target.relative_to(REPO_ROOT)}")
        if scrape_one(num, target, session):
            fetched += 1
        else:
            failures.append(num)
        time.sleep(sleep_s)

    print(
        f"done. fetched={fetched} skipped={skipped} failed={len(failures)} "
        f"out of {len(numbers)}"
    )
    if failures:
        print("failures:", ", ".join(failures))
        return 1
    return 0


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument(
        "--only", help="limit to one category name (e.g. backtracking)", default=None
    )
    parser.add_argument(
        "--workbook",
        help="scrape a workbook by id or full URL (e.g. 1152)",
        default=None,
    )
    parser.add_argument(
        "--validate-only",
        action="store_true",
        help="check existing files without fetching",
    )
    parser.add_argument(
        "--force", action="store_true", help="re-fetch even if file already valid"
    )
    parser.add_argument(
        "--sleep", type=float, default=1.2, help="seconds between requests"
    )
    args = parser.parse_args()

    if args.workbook:
        return cmd_workbook(args.workbook, args.sleep, args.force)
    if args.validate_only:
        return cmd_validate(args.only)
    return cmd_scrape(args.only, args.sleep, args.force)


if __name__ == "__main__":
    sys.exit(main())
