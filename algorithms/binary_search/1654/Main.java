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
    public static void main(String argv[]) throws IOException{
        int k = nextInt();
        int n = nextInt();
        int a[] = new int[k];
        for (int i = 0; i < k; i++) {
            a[i] = nextInt();
        }
        Arrays.sort(a);
        int max = a[k-1];
        int l=0,r=max+1;
        while(l<r){
            int mid = (r-l)/2 + l;
            long sum =0;
            for (int i = 0; i < a.length; i++) {
                sum += a[i]/mid;
            }
            if( sum < n  ){
                r = mid;
            }
            else{
                l = mid+1;
            }

        }
        System.out.println(l-1);
    }
}