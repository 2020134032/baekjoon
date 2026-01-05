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

    
    public static void main(String argv[]) throws IOException{
        int n = Integer.parseInt(next());
        int[] a = new int[n+1];
        a[0] = 0;
        long[] ps = new long[n+1];
        ps[0] = 0;
        for (int i = 1; i <= n; i++) {
            int input = Integer.parseInt(next());
            a[i] = input;
            ps[i] = ps[i-1] + input; 
        }
        long sum = 0;

        for (int i = 1; i <= n; i++) {
            sum += a[i] * (ps[n]- ps[i]);
        }

        System.out.println(sum);
    }
}