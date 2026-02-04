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
    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
    static long nextLong() throws IOException {
        return Long.parseLong(next());
    }
    public static void main(String argv[]) throws IOException{
        int n = nextInt();
        int c = nextInt();

        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextLong();
        }
        Arrays.sort(a);
        c = c-1;

        long l = 1, r = a[n-1]-a[0];
        long ans = 0;
        while( l <=r){
            int routers_used =0;
            long last = a[0];
            long mid = (r-l)/2 + l;
            for (int i = 1; i < a.length; i++) {
                if ( a[i] - last >= mid ){
                    last = a[i];
                    routers_used++;
                    if(routers_used >=c) break;
                }
            }

            if( routers_used >= c){
                ans = mid;
                l = mid+1;
            }
            else{
                r=mid-1;
            }
            
        }

        System.out.println(ans);

    }
}