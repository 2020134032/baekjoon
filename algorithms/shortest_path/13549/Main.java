import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
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
        int k = nextInt();
        int limit = 100001;
        int dp[] = new int[limit];
        for (int i = 0; i < limit; i++) {
            dp[i] = Integer.MAX_VALUE; 
        }
        
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        dp[n] = 0;
        q1.offer(n);
        q2.offer(n);

        while(!q1.isEmpty() || !q2.isEmpty()){
            while(!q2.isEmpty()){
                int cur = q2.poll();
                int current_cost = dp[cur];
                if( 2*cur <= limit-1 ){
                    if(dp[2*cur] > current_cost){
                        dp[2*cur] = current_cost;
                        q2.add(2*cur);
                        q1.add(2*cur);
                    }
                }
            }
            while(!q1.isEmpty()){
                int cur = q1.poll();
                int current_cost = dp[cur];
                if( cur-1>=0 && current_cost +1 < dp[cur-1] ){
                    dp[cur-1] = current_cost+1;
                    q1.add(cur-1);
                    q2.add(cur-1);
                }
                if( cur+1<= limit-1 && current_cost +1 < dp[cur+1] ){
                    dp[cur+1] = current_cost+1;
                    q1.add(cur+1);
                    q2.add(cur+1);
                }

            }

            
        }
        System.out.println(dp[k]);

    }
}