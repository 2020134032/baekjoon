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
        int[] dp = new int[5001];

        dp[0]=0;
        dp[1]=0;
        dp[2]=0;
        dp[3]=1;
        dp[4]=0;
        dp[5]=1;
        for(int i = 6 ; i <= n ; i++){
            int d3 = dp[i-3];
            int d5 = dp[i-5];
            if (d3 == 0 && d5 ==0)
                {
                    dp[i] = 0;
                    continue;
                } 
            if (d3 == 0){
                dp[i] = d5 +1;
                continue;
            }
            if (d5 == 0){
                dp[i] = d3 +1;
                continue;
            }
            
            dp[i] = Math.min(d3, d5) +1;
        }
        if(dp[n]==0)System.out.println(-1);
        else System.out.println(dp[n]);

    }
}