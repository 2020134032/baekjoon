import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    public static void main(String argv[]) throws IOException{
        String s = br.readLine();
        String[] strings = s.split(" ");
        int n = Integer.parseInt(strings[0]), m = Integer.parseInt( strings[1]);
        long[] a = new long[n], b = new long[m];
        strings = br.readLine().split(" ");
        
        for(int i=0; i<n;i++){
            a[i] = Long.parseLong(strings[i]);
        }
         strings = br.readLine().split(" ");
        for(int i=0; i<m;i++){
            b[i] = Long.parseLong(strings[i]);
        }

        StringBuilder sb = new StringBuilder();
        int p,q;
        for(p =0, q=0; p<n && q<m;){
            if(a[p] > b[q]) sb.append(b[q++]).append(" ");
            else if(a[p] <= b[q]) sb.append(a[p++]).append(" ");
        }
        if (p == n || q ==m) {
            if( p == n){
                p =q;
                a=b;
                n=m;
            }
            for( ; p<n;p++){
                sb.append(a[p]).append(" ");
            }

        }

        System.out.println(sb.toString());
        
    }
}