import java.io.*;
import java.util.*;

// 0/1 knapsack dp 2d.
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

    public static void main(String args[]) throws IOException {
        int w = nextInt();
        int n = nextInt();
        int weights[] = new int[n];
        int values[] = new int[n];
        int dp[][] = new int[n + 1][w + 1];

        for (int i = 0; i < n; i++) {
            weights[i] = nextInt();
            values[i] = nextInt();
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= w; j++) {
                if (j >= weights[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];

                }
            }
        }

        //
        // what do we have to return. highest value
        System.out.println(dp[n][w]);
    }

}