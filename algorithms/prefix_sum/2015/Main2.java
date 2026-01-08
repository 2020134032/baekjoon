 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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
    public static void main(String argv[]) throws IOException{
        int n = nextInt();
        int k = nextInt();

        HashMap<Integer, List<Integer>> map = new HashMap<>();


        int[] a = new int[n+1];
        map.put(0, new ArrayList<>());
        map.get(0).add(0);
        for (int i = 1; i < n+1; i++) {
            int input = nextInt();
            int key = input +a[i-1];
            a[i] = key;
            List<Integer> list = map.getOrDefault( key , new ArrayList<>());
            list.add(i);
            map.put( key, list);
        }

        long count = 0;

        for (int i = 1; i < n+1; i++) {
            int key = -(k - a[i]);
            List<Integer> list = map.getOrDefault(key, null);
            if( list == null) continue;

            // list.sort( Comparator.naturalOrder());
            int l=0, r=list.size();
            int target = i;
            while (l<r) {
                int m = (l+r) /2;
                int val = list.get(m);
                if (val < target ){
                    l = m+1;
                }
                else{
                    r= m;
                }
            }

            count += l;
            
        }
        System.out.println(count);
    }
 }