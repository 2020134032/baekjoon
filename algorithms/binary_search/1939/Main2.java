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
            // a.computeIfAbsent(from, k->new HashMap<>()).put(to, cap);
            

            // t1 = a.get(to);
            // if(t1 != null) {
            //     Long max = t1.get(from);
            //     if(max != null) {
            //         if (cap > max) t1.put(from,cap);
            //     }  
            //     else t1.put(from, cap);
            // } else{
            //     a.computeIfAbsent(to, (k-> new HashMap<>()).put(from,cap) );

            // }
            
        }
        int start = nextInt();
        int end = nextInt();
        class Node{
            long cap;
            int num; 
            public Node(long cap, int num){
                this.cap=cap;
                this.num=num;
            }
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((n1,n2) -> Long.compare(n2.cap,n1.cap));
        boolean visited[] = new boolean[n+1];

        long nodecap[] = new long[n+1];
        nodecap[start] = Long.MAX_VALUE;
        a.get(start).forEach((key, value) ->{
            pq.add(new Node(value, key));
            nodecap[key] =value;
        });

        while( !pq.isEmpty()){
            Node currentNode = pq.poll();
            if(visited[currentNode.num]) continue;
            visited[currentNode.num]=true;
            if(currentNode.num == end){
                System.out.println(nodecap[end]);
            }
            // long currentMax = nodecap[currentNode.num];
            long currentMax = currentNode.cap;
            a.get(currentNode.num).forEach(( destNodeNum,weight )->{
                if(visited[destNodeNum]) return;
                long minCap = Math.min(weight, currentMax);
                if(minCap > nodecap[destNodeNum] ){
                    nodecap[destNodeNum] = minCap; 
                    pq.add(new Node(minCap, destNodeNum));
                }
            });
        }





    }
}