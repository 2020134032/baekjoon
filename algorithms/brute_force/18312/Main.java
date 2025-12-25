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
       int N = Integer.parseInt(next());
       String K = next();
        
       int count=0;
        for( ; N>=0; N-- ) {
            String s = String.valueOf(N);
            if (N<10) s = "0" + s;
        
            for (int i = 0; i < 60; i++) {
                    String minute = String.valueOf(i);
                    if (i<10) minute = "0" + minute;
                    for (int j = 0; j < 60; j++) {
                        String sec = String.valueOf(j);
                        if (j<10) sec = "0" + sec;

                        if(s.contains(K) || minute.contains(K) || sec.contains(K)) count++;
                    }
            }
        }   
      

       System.out.println(count);
    }
}