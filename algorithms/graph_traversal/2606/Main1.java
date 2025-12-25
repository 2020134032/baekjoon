import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;


public class Main1{

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
    static void visit(int y, int x){
        mat[y][x] = 2 ;
        mat[x][y] = 2 ;
    }
    static int computer, pair;
    static int[][] mat = new int[101][101];
    static int[] visit = new int[101];
    public static void main(String argv[]) throws IOException{
        computer = Integer.parseInt(next());
        pair = Integer.parseInt(next());
        // if y -> x
        for(int i = 0 ; i < pair ; i++){
            int y = nextInt();
            int x = nextInt();
            mat[y][x] = 1;
            mat[x][y] = 1;
        }

        Deque<Integer> q = new ArrayDeque<>();
        // 1번 노드의 모든 연결된 노드 큐에 추가.x
        // 1번 만 넣고 돌림
        int count=0;
        q.add(1);
        // count++; 1 빼고
        visit[1] = 1;
        while(!q.isEmpty()){
            int startnode = q.poll();
            for(int i = 1 ; i<= 100 ; i++){
                if( visit[i] == 0 && mat[startnode][i] == 1  ) {
                    q.add(i); count++; visit[i] =1 ;
                }
            }
        }
        System.out.println(count);

    }
}