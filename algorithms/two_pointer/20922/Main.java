import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
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
    static int max = 0;
    static HashMap<Integer, Integer> m = new HashMap<>();
    public static void main(String argv[]) throws IOException{
        int n = Integer.parseInt(next());
        int k = Integer.parseInt(next());

        int a[] = new int[n];

        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.parseInt(next());
        }

        int l=0, r=0;
        while(r<n){
            int nextInt = a[r];
            int occurs = m.getOrDefault(nextInt, 0 );
            if(occurs >= k){
                int t = m.get(a[l]);
                m.replace(a[l], t-1);
                l++;
                continue;
            }
            else{
                // increase the counter
                m.put(nextInt, occurs+1);
                r++;
                int length = r -l;
                if (length > max){ max = length;}
            }
        }
        System.out.println(max);
    }
}