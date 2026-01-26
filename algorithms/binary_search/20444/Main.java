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
        // n번의 가위질로 k개의 색종이?
        int n = nextInt();
        long k = nextLong();

        int l = 0 , r= (n-1)/2 + 1;
        boolean ans = false;
        while( l<=r){
            int mid = (r-l)/2 + l;
            long calc = (mid +1) * (long)(n- mid+1);
            if( calc > k){
                r = mid -1;
            }
            else if (calc ==k){
                ans = true;
                break;
            }
            else{
                l = mid +1;
            }
        }

        if(ans) System.out.println("YES");
        else System.out.println("NO");

    }
}