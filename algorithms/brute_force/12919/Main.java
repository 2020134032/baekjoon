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
    static String s, t;
    static boolean found = false;
    static char[] a;
    static boolean[][][] visited;

    static void dfs(int l, int r, boolean rev) {
        if(r-l +1 < s.length() || found ) return;
        int revint = rev ? 1:0;
        if(visited[l][r][revint]) return;
        visited[l][r][revint] =true;

        if( r-l +1 == s.length() ){
            // boolean match = true;
            for(int i =0  ; i< s.length() ; i++){
                if ( !rev && (s.charAt(i) != a[l+i]) ){
                    // match = false;
                    return;
                }
                if (rev && (s.charAt(i) != a[r-i]) ){
                    // match = false;
                    return;
                }
            }
            found = true;
            return;
        }   

        char start = a[l];
        char end = a[r];
        if(rev){
            start = a[r];
            end = a[l];
        }

        if ( end == 'A'){
            if(rev) dfs(l+1, r, rev);
            else dfs(l, r-1, rev);
        }
        if( start =='B'){
            if(rev) dfs(l, r-1, false);
            else dfs(l+1 , r, true);
        }   
    }
    
    public static void main(String argv[]) throws IOException{
        s = next();
        t = next();

        a = t.toCharArray();
        int l = 0, r = t.length()-1;
        visited = new boolean[r+1][r+1][2];

        dfs(l,r,false);


        if (found) System.out.println(1);
        else System.out.println(0);
    }
}