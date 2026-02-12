import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    static boolean dfs(HashMap<Integer,HashMap<Integer,Long>> map , int cur, int dest, boolean[] visited, long mid){
        if(visited[cur]) return false;
        visited[cur] = true;
        if(cur == dest){
            return true;
        }

        HashMap<Integer, Long> adj = map.get(cur);
        for ( Map.Entry<Integer, Long> e: adj.entrySet()) {
            int k = e.getKey();
            long v = e.getValue();
            if( v >=mid) {
                if(dfs(map, k, dest, visited, mid))
                    return true;
            }    
        }
        return false;
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

        while (l<=r){
            long mid = (r-l)/2 +l;
            boolean[] visited = new boolean[n+1];
           

            if(dfs(adj, start, destination, visited, mid)){
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