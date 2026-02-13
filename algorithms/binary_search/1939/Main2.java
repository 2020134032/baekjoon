import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {

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
        int m = nextInt();
        HashMap<Integer, HashMap<Integer, Long>> a = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int from = nextInt();
            int to = nextInt();
            long cap = nextLong();
            a.computeIfAbsent(from, k->new HashMap<>()).merge(to, cap, ( max , val  )-> Math.max(max,val) );
            a.computeIfAbsent(to, k->new HashMap<>()).merge(from, cap, ( max , val  )-> Math.max(max,val) );
            
        }
        int start = nextInt();
        int end = nextInt();
        // select node (start)
        // for every edge, calculate the min between edge weight and current node
        // if it is bigger than next node of edge, update
        // after update, select biggest node 
        // repeat
        class Node{
            int next;
            long weight;
            Node(int next, long weight){
                this.next=next;
                this.weight=weight;
            }
        }

        // 1. select node ( from heap) 
        PriorityQueue<Node> pq = new PriorityQueue<>( (n1, n2) -> Long.compare(n2.weight,n1.weight) );
        boolean visited[] = new boolean[n+1];
        long[] node_weights = new long[n+1];
        pq.add(new Node(start, Long.MAX_VALUE));


        while(!pq.isEmpty()){
            Node node = pq.poll();
            int cur = node.next;
            long cur_weight = node.weight;

            if (visited[cur]) continue;
            visited[cur] = true;
            if(cur == end) {
                System.out.println(cur_weight);
                break;
            }

            HashMap<Integer, Long> adj = a.get(cur);
            for ( Map.Entry<Integer,Long> e : adj.entrySet()) {
                int next = e.getKey();
                long edge_weight = e.getValue();

                if(visited[next]) continue;
                long new_weight = Math.min(cur_weight,edge_weight);
                if ( new_weight > node_weights[next] ){
                    node_weights[next] = new_weight;
                    pq.add(new Node(next, new_weight));
                }
            }

        }
    }
}