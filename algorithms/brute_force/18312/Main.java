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

    static int occurs(int n, int k){
        //  0-n
        // n = 10a+b
        int a = n/10;
        int b = n%10;
        // int ans = 0;
        // if( a > k){ // 30 2
        //     ans += 10;
        //     ans += a-1;
        //     if( b>= k) ans++;
        // }
        // else if ( a == k){ // 22 2
        //     ans += b+1;
        //     if(a == 0) {}
        //     else ans += a;
        // }
        // else{ // a<k  19 9
        //     ans += a;
        //     if( b>= k) ans++;
        // }
        // return ans;
        
        // 십의자리가 K인 HH
        int tens = 0;
        int ones = 0;
        if (a > k) 
            tens = 10;
        else if ( a==k) 
            tens = b+1;
        else 
            tens =0;
        // 일의자리가 K HH
        ones = a;
        if(b>=k) ones += 1;

        if ( n>= 10*k+k) ones -=1; // 중복 빼주기

        return tens + ones;
    }
    public static void main(String argv[]) throws IOException{
       int N = Integer.parseInt(next());
       int K = Integer.parseInt(next());
        // 00 01 02 03 04 05 06 07 08 09 10 11 12 
       int answer=0;
       int hours = occurs(N, K);

        // System.out.println(hours);
       answer = hours * 3600;

       int minutes = occurs(59, K);
       answer += (N+1-hours) * (minutes * 60);

       int sec = minutes;

       answer+= (N+1-hours) * (60-minutes) * sec ; 


       System.out.println(answer);
    }
}