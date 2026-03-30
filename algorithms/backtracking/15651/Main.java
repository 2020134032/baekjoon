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
    static int[] a;
    static boolean[] visited;
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
            a[depth] = i;
            backtrack(depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        a = new int[m + 1];
        visited = new boolean[n + 1];

        backtrack(0);
        System.out.println(sb);
    }
}