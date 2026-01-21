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
        long m = nextLong();
        long[] t = new long[n];
        for (int i = 0; i < n; i++) {
            t[i] = nextLong();
        }
        long l=1, r = (long)Math.pow(10, 18);
        long ans = 0;
        while( l <=r){
            long mid = (r-l)/2 +l;
            long sum = 0;
            for (long o : t) {
                sum += mid/o;
                if (sum >= m) break;
            }
            if ( sum >= m){
                ans = mid;
                r = mid-1;
            }
            else{
                l = mid +1;
            }

        }
        System.out.println(ans);
    }
}