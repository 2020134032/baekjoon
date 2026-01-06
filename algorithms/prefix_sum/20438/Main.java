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
    static int nextInt() throws IOException{
        return Integer.parseInt(next());
    }
    static int n,k,q,m,s,e;
    public static void main(String argv[]) throws IOException{
        n = nextInt(); // 학생
        k = nextInt(); // sleep
        q = nextInt(); // 보낼 사람
        m = nextInt(); // 구간 개수

        int a[] = new int[n+3];

        for (int i = 0; i < k; i++) {
            a[nextInt()] = -1;
        }
        for (int i = 0; i < q; i++) {
            int input = nextInt();
            for(int j = input; j <= n+2; j += input){
                if ( a[j] != -1){
                    a[j] = 1;
                }
                else{
                    if(j==input) break;
                }
            }
        }

        int[] prefix = new int[n+3];

        for (int i = 3; i <= n+2; i++) {
            if(a[i] == -1) a[i] = 0;
            prefix[i] = prefix[i-1] + a[i];
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            s = nextInt();
            e = nextInt();
            int sum = e - s + 1 - (prefix[e] - prefix[s-1]);
            sb.append(sum).append('\n');
        }
        System.out.println(sb.toString());
    }
}