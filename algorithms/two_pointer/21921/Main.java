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
    
    static int max = 0;
    static int count = 0;
    public static void main(String argv[]) throws IOException{
        int n = Integer.parseInt(next());
        int x = Integer.parseInt(next());

        int[] a = new int[n];

        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.parseInt(next());
            if( i < x){
                max += a[i];
            }
        }
        if(max > 0 )    count=1;
        int l =0, r=x;
        int temp=max;
        for(;r<n;l++,r++){
            temp += a[r];
            temp -= a[l];

            if(temp>=max){
                if(temp > max){
                    count =0;
                }
                max = temp;
                count++;
            }
        }

        if(max == 0){
            System.out.println("SAD");
        }
        else{
            System.out.println(max);    
            System.out.println(count);    
        }   
    }


    
    
}