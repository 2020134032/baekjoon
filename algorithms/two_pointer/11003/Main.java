import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
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

    static ArrayDeque<Integer> deque = new ArrayDeque<>();
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public static void main(String argv[]) throws IOException{
        int n = Integer.parseInt(next());
        int l = Integer.parseInt(next());

        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(next());

            deque.offer(input);
            
            if(i >=l){
                pq.remove(deque.poll());
                
            }
            pq.offer(input);
            System.out.println(pq.peek());
        }
    }
}