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
        int m = nextInt();
        int l = nextInt();

        int [] a = new int[n+2];

        for(int i=0;i<n;i++){
            a[i] = nextInt();
        }
        a[n]=0;
        a[n+1]=l;
        Arrays.sort(a);
        int ans = l;

        int f=1, e=l;
        while( f<=e){
           // System.out.println();
            int mid = (f+e)/2;
            long sum =0;
            // boolean ok =true;
            for (int i = 1; i <n+2 ; i++) {
                sum += ( a[i] - a[i-1]-1) / mid;
                if (sum>m) break;
            }
            if  ( sum <= m) {
                ans = mid;
              //  System.out.println("ans" + ans);
                e = mid-1;
            }
            else{
                
                f = mid+1;
            }
        }
        System.out.println(ans);
    }
}