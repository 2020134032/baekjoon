 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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
        int k = nextInt();
        int[] pre = new int[n+1];

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        long count = 0;
        int pref = 0;
        for (int i = 1; i < n+1; i++) {
            pref += nextInt();
            // pre[i] - key (where index less than i) == k
            count += map.getOrDefault(pref -k, 0);
            
            map.put( pref , map.getOrDefault(pref, 0) + 1);
        }

        
        
        System.out.println(count);
    }
 }