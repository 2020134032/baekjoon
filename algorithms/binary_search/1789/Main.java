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
    static long nextLong() throws IOException {
        return Long.parseLong(next());
    }
    public static void main(String argv[]) throws IOException{
        long s = nextLong();
        long sum = 0;
        
        /* 1 =1 
           2 = 2
           3 = 1 + 2
           4 = 1 + 3
           5 = 1 + 4
           6 = 1 + 2 + 3
           7 = 1 + 2 + 4
           8 = 1 + 2 + 5
           9 = 1 + 2 + 6
           10 = 1 + 2 + 3 + 4
           11 = 1 + 2 + 3 + 5
        */

        for( long i = 1; i <= s ; i++) {
            sum += i;
            if ( s == sum || s-sum <= i ){ // s == sum no need
                System.out.println(i); // answer
                break;
            }
        }
    }
}