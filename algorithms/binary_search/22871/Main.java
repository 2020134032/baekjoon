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
    public static void main(String argv[]) throws IOException{
        int n = nextInt();
        long[] a = new long[n];
        long[] dp = new long[n];
        for (int i = 0; i < n; i++) {
            a[i]=nextLong();
        }
        for (int i = 1; i < n; i++) {
            dp[i] = Long.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                long k = (i-j) * (1+ Math.abs(a[i] - a[j]));
                dp[i] = Math.min(dp[i] , Math.max(dp[j] , k) );
            }
        }
        System.out.println(dp[n-1]);


    }
}