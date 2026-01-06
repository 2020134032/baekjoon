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

        int a[][] = new int[n+1][n+1];

        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                int input = Integer.parseInt(next());
                a[i][j] = input + a[i-1][j] + a[i][j-1] - a[i-1][j-1];
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int x1 = Integer.parseInt(next());
            int y1 = Integer.parseInt(next());
            int x2 = Integer.parseInt(next());
            int y2 = Integer.parseInt(next());
            int sum = a[x2][y2] - a[x2][y1-1] - a[x1-1][y2] + a[x1-1][y1-1];
            sb.append(sum).append('\n');
        }
        System.out.println(sb.toString());
    }
}