import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    static String s, t;
    static boolean found = false;
    static void bruteforce(StringBuilder sb){
        
        int len = sb.length();
        if(found || len < s.length() ) 
            return;
        if ( len == s.length() ){
            if (sb.toString().equals(s))
                found = true;
            return;
        }


        // last is A:
        if (sb.charAt(len-1) == 'A' ){
            sb.setLength(len-1);
            bruteforce(sb);
            sb.append('A');
        }
        if (sb.charAt(0) == 'B'){
            sb.reverse();
            sb.setLength(len-1);
            bruteforce(sb);
            sb.append('B').reverse();
        }

    }
    public static void main(String argv[]) throws IOException{
        s = next();
        t = next();

        StringBuilder sb = new StringBuilder(t);
        bruteforce(sb);

        if (found) System.out.println(1);
        else System.out.println(0);
    }
}