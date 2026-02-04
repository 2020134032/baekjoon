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
        int m = nextInt();
        int[] a = new int[n];
        long r=0;
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
            r+= a[i];
        }

        long ans = 0;
        long l=0;

        while (l<=r) {
            long mid = (r-l)/2 +l;
            long min=a[0],max=a[0];
            int counter=0;
            for (int i = 0; i < n; i++) {
                long t = a[i];
                if (t<min) min = t;
                if (t>max) max = t;
                if ( max-min > mid ){

                    counter++;
                    min = max = t;
                    if(counter > m-1)
                        break;
                }
            }

            if (counter <= m-1){
                ans = mid;
                r = mid-1;
            }
            else{
                l = mid+1;
            }
        }
        System.out.println(ans);
    }
}