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
        long[] a = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = nextLong();
        }

        Arrays.sort(a);

        int l=0, r=n-1;
        long best[] = { Long.MAX_VALUE ,0,n-1};
        while(l<r){
            long sum = a[l] + a[r];
            if (sum ==0 ){
                System.out.print(a[l] + " "+a[r]);
                return;
            }
            if (best[0] > Math.abs(sum) ){
                best[0] = Math.abs(sum);
                best[1] = a[l];
                best[2] = a[r];
            }
            if (sum > 0){
                r--;
            }
            else{
                l++;
            }
        }

        System.out.println( best[1] + " " + best[2]);
    }
}