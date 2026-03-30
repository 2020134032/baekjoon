import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }
    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
    static long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    static int n, l, r, x;
    static int[] a;
    static long answer = 0;

    // 백트래킹으로 모든 부분집합을 탐색하여 조건을 만족하는 경우의 수를 센다
    // index: 현재 고려하는 문제 인덱스
    // sum: 현재까지 선택한 문제들의 난이도 합
    // min: 현재까지 선택한 문제들의 최소 난이도
    // max: 현재까지 선택한 문제들의 최대 난이도
    // count: 현재까지 선택한 문제의 개수
    static void backtrack(int index, int sum, int min, int max, int count) {
        // Pruning: 합이 이미 R을 초과하면 이 경로는 탐색 중단
        if (sum > r) {
            return;
        }

        // 2개 이상의 문제를 선택했을 때, 모든 조건 검사
        if (count >= 2) {
            // 조건 1: L <= 합 <= R
            // 조건 2: 최대 난이도 - 최소 난이도 >= X
            if (sum >= l && max - min >= x) {
                answer++;
            }
        }

        // 모든 문제를 고려했으면 종료
        if (index == n) {
            return;
        }

        // 1. index번째 문제를 선택하는 경우
        if (count == 0) {
            // 첫 번째 선택일 때 min과 max를 같은 값으로 초기화
            backtrack(index + 1, sum + a[index], a[index], a[index], count + 1);
        } else {
            // 이후 선택일 때는 min/max 업데이트
            backtrack(index + 1, sum + a[index], Math.min(min, a[index]),
                    Math.max(max, a[index]), count + 1);
        }

        // 2. index번째 문제를 선택하지 않는 경우
        backtrack(index + 1, sum, min, max, count);
    }

    public static void main(String argv[]) throws IOException{
        n = nextInt();
        l = nextInt();
        r = nextInt();
        x = nextInt();

        a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = nextInt();
        }

        // 백트래킹 시작 (인덱스 0부터, 합 0, 최소값 0, 최대값 0, 선택 개수 0)
        backtrack(0, 0, 0, 0, 0);

        System.out.println(answer);
    }
}