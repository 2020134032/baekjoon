import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        int T = nextInt();
        HashMap<Integer, HashMap<Integer,Integer>> map = new HashMap<>(n*2);
        
        for (int i = 0; i < n; i++) {
            int x = nextInt();
            int y = nextInt();
            map.computeIfAbsent(x, k -> new HashMap<>()).put(y, 1);

        }

        Queue<int[]> q = new ArrayDeque<int[]>();

        q.add(new int[]{0,0,0}); // {x,y,move}

        int min = -1;
        while (!q.isEmpty()) {
            int[] coord = q.poll();
            int x = coord[0];
            int y = coord[1];
            int move = coord[2];
            
            if(y ==T ){
                min = move;
                break;
            }
            for(int dx = -2 ; dx<=2 ; dx++){
                int nx = x+dx;
                if(nx <0 || nx >1000000) continue;
                HashMap<Integer,Integer> inner = map.get(nx) ;
                if( inner==null ) 
                    continue;
                for( int dy = -2 ; dy <=2; dy++){
                    int ny = y+dy;
                    if(ny <0 || ny >T) continue;
                    Integer v= inner.remove(ny);
                    if( v ==null) continue;

                    q.add(new int[]{nx,ny, move+1});
                    
                } 

                if(inner.isEmpty())
                map.remove(nx);
            }
        }
        System.out.println(min);
        
    }
}