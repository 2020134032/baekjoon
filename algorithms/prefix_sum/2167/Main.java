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
        int m = Integer.parseInt(next());
        int[][] a = new int[n+1][m+1];
        for(int i=0; i<n+1; i++){
            a[i][0] = 0;
        }
        for(int i=0; i<m+1; i++){
            a[0][i] = 0;
        }
       
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                a[i][j] = Integer.parseInt(next());
                a[i][j] += a[i-1][j] + a[i][j-1] -a[i-1][j-1];
            }
        }

        int k = Integer.parseInt(next());

        StringBuilder sb = new StringBuilder();
        
        for (int index = 0; index < k; index++) {
            int i = Integer.parseInt(next());
            int j = Integer.parseInt(next());
            int x = Integer.parseInt(next());
            int y = Integer.parseInt(next());
            int res = a[x][y] - a[i-1][y] - a[x][j-1] + a[i-1][j-1];
            sb.append(res).append('\n');
        }

        System.out.println(sb.toString());
    }
}