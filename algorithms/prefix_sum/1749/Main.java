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
        int n = nextInt();
        int m = nextInt();

        int[][] a = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int input = nextInt();
                a[i][j] = input + a[i][j-1] ;
            }
        }

        int max = -10000;

        
        for(int m1=1;m1<=m;m1++){
            for(int m2=m1;m2<=m;m2++){
                int sum = 0;
                for(int i = 1; i<=n; i++){
                    int pref = a[i][m2] - a[i][m1-1];
                    sum = Math.max(sum+pref, pref);
                    max = Math.max(max, sum);
                }
            }
        }

            System.out.println(max);
        }

        
        
    
}