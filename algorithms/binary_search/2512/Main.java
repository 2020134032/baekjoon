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
        long n = nextLong();
        long[] a = new long[(int)n];
        long sum =0;
        long max =0;
        for (int i = 0; i < n; i++) {
            long t = nextLong();
            if(max < t) max =t;
            a[i] = t;
            sum +=t;
        }

        long m = nextLong();
        if( m >= sum ){
            System.out.println(max);
            return;
        }

        long l = 1,r=max;
        long ans =1;
        while( l<=r){
            long mid = (r-l)/2  +l;
            long sum2=0;
            for (long o : a) {
                if( o> mid) sum2+=mid;
                else sum2 +=o;
            }

            if( sum2 <= m){
                ans = mid;
                l = mid+1;
            }
            else{
                r =mid-1;
            }
        }
        System.out.println(ans);

    }
}