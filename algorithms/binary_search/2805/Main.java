import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
    static long nextInt() throws IOException {
        return Long.parseLong(next());
    }
    public static void main(String argv[]) throws IOException{
        long n = nextInt();
        long m = nextInt();
        long[] a = new long[(int)n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
        Arrays.sort(a);
        long l=0 , r=a[(int)n-1];
        long ans=0;
        while( l <= r){
            long mid = (r-l) /2 +l;
            long sum=0;
            for (long o : a) {
                sum += Math.max(o-mid,0);
            }
            if( sum >=m){
                ans =mid;
                l = mid+1;
            }
            else{
                r=mid-1;
            }
        }

        System.out.println(ans);
    }
}