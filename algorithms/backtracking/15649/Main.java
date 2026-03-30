import java.io.*;
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

    static int n, m;
    static boolean visited[];
    static int a[];
    static StringBuilder sb = new StringBuilder();

    static void backtrack(int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(a[i]).append(" ");
            }
            sb.append('\n');
            return;
        }
        for (int i = 1; i < n + 1; i++) {
            if (visited[i])
                continue;

            visited[i] = true;
            a[depth] = i;
            backtrack(depth + 1);
            visited[i] = false;

            // a[depth] = 0;actually don't need
        }

    }

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        visited = new boolean[n + 1];
        a = new int[m + 1];
        backtrack(0);

        System.out.print(sb);
    }
}