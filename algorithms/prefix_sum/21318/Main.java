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
        int a[] = new int[n+1];
        int pre[] = new int[n+1];
        a[1] = Integer.parseInt(next());
        for (int i = 1; i < n; i++) {
            int input = Integer.parseInt(next());
            if( a[i] > input){
                pre[i] = 1+ pre[i-1];
            }
            else{
                pre[i] = pre[i-1];
            }
            a[i+1] = input;
        }

        int q = Integer.parseInt(next());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int x = Integer.parseInt(next());
            int y = Integer.parseInt(next());
            
            // if (x==y){
            //     sb.append(0).append('\n');
            //     continue;
            // }
            int sum = pre[y-1] - pre[x-1] ;
            sb.append(sum).append('\n');

        }
        System.out.println(sb.toString());
    }
}