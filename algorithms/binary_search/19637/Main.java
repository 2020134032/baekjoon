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
        int n = nextInt(), m= nextInt();
        String[] name = new String[n];
        int[] strong = new int[n];
        for (int i = 0; i < n; i++) {
            name[i] = next();
            strong[i] = nextInt();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int target = nextInt();

            int l=0,r=n;
            while(l<r){
                int mid = (r-l)/2 +l;
                if(strong[mid] < target){
                    l = mid+1;
                }
                else{
                    r =mid;
                }
            }
            // index of l is the name
            sb.append(name[l]).append('\n');
        }

        System.out.println(sb.toString());
    }
}