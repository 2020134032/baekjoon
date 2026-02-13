import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
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
        int m = nextInt();
        
        HashMap<Integer, HashMap<Integer,Long>> adj = new HashMap<>();

        long r = 0;
        for (int i = 0; i < m; i++) {
            int a = nextInt();
            int b = nextInt();
            long t = nextLong();
            adj.computeIfAbsent(a, k->new HashMap<>()).merge(b, t, Math::max);
            adj.computeIfAbsent(b, k->new HashMap<>()).merge(a, t, Math::max);
            r = Math.max(r,t);
        }

        long l = 1;
        long ans=1;
        int start = nextInt();
        int destination = nextInt();

        class Edge{
            public int from;
            public int to;
            public long weight;
            Edge(int from, int to, long weight){
                this.from = from;
                this.to = to;
                this.weight = weight;
            }
        }

        while (l<=r){
            long mid = (r-l)/2 +l;
            boolean[] visited = new boolean[n+1];
           
            ArrayDeque<Integer> stack = new ArrayDeque<>();

            boolean good = false;

            visited[start] = true;
            stack.push(start);

            while(!stack.isEmpty()){
                int from_node = stack.pop();
                if(from_node == destination){
                    good =true;
                    break;
                }
                HashMap<Integer, Long> edges = adj.get(from_node);

                for (Map.Entry<Integer, Long> e : edges.entrySet()) {
                    int to_node = e.getKey();
                    long weight = e.getValue();
                    if(weight < mid) continue;
                    if( visited[to_node] )continue;
                    visited[to_node] = true;
                    stack.push(to_node);

                }
            }

            if(good){
                l= mid+1;
                ans = mid;
            }
            else{
                r = mid -1;
            }

        }
        System.out.println(ans);

    }
}