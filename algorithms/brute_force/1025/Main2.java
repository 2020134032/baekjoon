import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main2
 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }
    static int N,M;
    static int nextInt() throws IOException{
        return Integer.parseInt(next());
    }
    static long answer=-1;
    static HashSet<Long> squared = new HashSet<>();
    static void check(long l ){
        if (squared.contains(l)){
            answer = l > answer ? l : answer;
        }
    }
    static int count = 0;
    public static void main(String argv[]) throws IOException{
        N = nextInt(); //y
        M = nextInt();//x

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String ss = next();
            for (int j = 0; j < M; j++) {
               arr[i][j] = ss.charAt(j)-'0';
            }
        }
        long limit = 1;
        for (int i = 0; i < Math.max(N, M) ; i++) {
            limit *= 10;
        }
       
        for(long i = 0L; i*i< limit; i++ ){
            squared.add(i*i);
        }
        //fill in 
        HashSet<Long> history = new HashSet<>();

        for (int sx=0; sx<M; sx++){
            for (int sy=0; sy<N; sy++){
                for(int dx = -M+1 ; dx < M; dx++ ){
                    for (int dy = -N+1; dy < N; dy++) {
                        int a = sx;
                        int b = sy;
                        // int c = dx;
                        // int d = dy;
                        long lookup = 0;
                        while ( 0 <= a && a < M && 0 <= b && b < N){
                            // System.out.println("a:" + a+" b:" + b);
                            lookup += arr[b][a];
                            
                            if(history.contains(lookup)){
                                // go to increment
                            }else{
                                history.add(lookup);
                                check(lookup);
                                
                            }
                            System.out.println(lookup);
                            count++;
                            lookup *= 10;
                            a += dx;
                            b += dy;
                            if ( dx == 0 && dy == 0) {
                                break;
                            }

                        }
                    }
                }
            }
        }



        System.out.println(answer);
        System.out.println("count: " + count);
    }// end
}