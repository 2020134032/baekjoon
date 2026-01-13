import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
    public static void main(String argv[]) throws IOException{
        int n = nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(nextInt());
        }
        Collections.sort(list);
        n = nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            
            int next = nextInt();
            int l = 0, r=list.size();

            while(l<r){
                int m = l + (r-l) /2;
                int result = list.get(m);
                if (result<next){
                    l=m +1;
                }
                else{
                    r = m;
                }
            }

            if( l < list.size() && list.get(l) == next){
                sb.append(1).append('\n');
            }
            else{
                sb.append(0).append('\n');
            }
            
        }
        System.out.println(sb.toString());
        
    }
}