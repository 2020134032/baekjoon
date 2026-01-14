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
    public static void main(String argv[]) throws IOException{
        long n = Long.parseLong(next());

        long l=0,r= Long.MAX_VALUE;

        while(l<r){
            long m = (r-l)/2 + l;
            if( Math.pow(m, 2)<n ){
                l = m+1;
            }
            else{
                r=m;
            }
        }
        System.out.println(l);
    }
}