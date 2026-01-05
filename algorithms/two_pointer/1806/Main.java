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
       int s = Integer.parseInt(next());
       int[] a = new int[n];
       for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(next());
       }

        int min = s+1;
        int l =0,r=0;
        int sum = a[l];
        while (l <=r && r<n) {
            if (sum >= s){
                if (r-l+1 < min){
                    min = r-l+1;
                }
                sum -= a[l];
                l++;
            }
            else{
                r++;
                if(r<n)
                sum += a[r];
            }


        }
        if(min ==s+1) min =0;
        System.out.println(min);

    }
}