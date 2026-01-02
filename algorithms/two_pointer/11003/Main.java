import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
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

    static Deque<int[]> q = new ArrayDeque<>();
    public static void main(String argv[]) throws IOException{
        int n = Integer.parseInt(next());
        int l = Integer.parseInt(next());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if( !q.isEmpty() && q.peek()[1] == i -l ) q.poll();
            int[] input = {Integer.parseInt(next()) , i};
            while(!q.isEmpty() && (q.peekLast()[0] > input[0]) )
            {
                q.pollLast();
            }
            q.offer(input);
            

            sb.append(q.peek()[0]).append(" ");

            
        }

        System.out.println(sb.toString());
    }
}