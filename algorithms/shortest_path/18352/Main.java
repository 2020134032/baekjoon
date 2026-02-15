import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
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
        int m = nextInt();
        int k = nextInt();
        int x = nextInt();

        Map<Integer,List<Integer>> map = new HashMap<>(); 
        for (int i = 0; i < m; i++) {
            int start = nextInt();
            int to = nextInt();
            map.computeIfAbsent( start, key-> new ArrayList<Integer>() ).add(to);
        }

        boolean[] visited = new boolean[n+1];
        class Node{
            int num;
            int dist;
            Node(int num, int dist){
                this.num = num;
                this.dist = dist;
            }
        }
        PriorityQueue<Node> pq = new PriorityQueue<>( (n1,n2) -> Integer.compare(n1.dist,n2.dist) );
        int[] node_weights = new int[n+1];
        for (int i = 0; i < n+1; i++) {
            node_weights[i] = Integer.MAX_VALUE;
        }
        pq.add(new Node(x, 0));
        node_weights[x] = 0;

        List<Integer> ans = new ArrayList<>();

        while(!pq.isEmpty()){

            Node cur = pq.poll();

            int cur_dist = cur.dist;
            int cur_num = cur.num;

            if(visited[cur_num]) continue;
            visited[cur_num] =true;
            if(cur_dist == k){
                ans.add(cur_num);
            }

            List<Integer> adj = map.get(cur_num);
            if(adj == null) continue;
            for (Integer integer : adj) {
                // notice weight is all 1
                int next = integer;
                if ( node_weights[next] > cur_dist +1){
                    node_weights[next] = cur_dist+1;
                    pq.add(new Node(next, cur_dist+1));
                }
            }
            
        }

        ans.sort(null);
        if(ans.isEmpty()){
            System.out.println(-1);
        }
        else
        ans.forEach(System.out::println);
    }
}