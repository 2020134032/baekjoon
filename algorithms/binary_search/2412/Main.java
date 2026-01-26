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
        HashMap<List<Integer>,Integer> map = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            int x = nextInt();
            int y = nextInt();
            List<Integer> list = new ArrayList<>();
            list.add(x);
            list.add(y);
            map.put(  list , 1);
        }

        Queue<int[]> q = new ArrayDeque<int[]>();

        q.add(new int[]{0,0,0}); // {x,y,move}
        List<Integer> list = new ArrayList<>();
            list.add(0);
            list.add(0);
            map.put(  list , 1);

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
                for( int dy = -2 ; dy <=2; dy++){
                    int ny = y+dy;
                    if(ny <0 || ny >T) continue;
                    list = new ArrayList<>();
                    list.add(nx);
                    list.add(ny);
                    Integer v= map.remove(list);
                    if( v ==null) continue;

                    q.add(new int[]{nx,ny, move+1});
                    
                } 
            }
        }
        System.out.println(min);
        
    }
}