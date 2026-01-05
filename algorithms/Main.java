import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
        int n = Integer.parseInt(next());
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(next());
        }
       
        Arrays.sort(a);
        int l =0;
        int r = n-1;
        int m = 1;
        
        int ans[] = new int[]{l,m,r};
        long answer = (long)a[l] + a[m]+ a[r];
        for( m =1 ; m< n-1; m++)
        {
            l=0;
            r=n-1;
            while(l<m && m<r)
            {
                long sum = (long)a[l] + a[m]+ a[r];
            
                if (sum == 0){
                    System.out.println(a[l] + " "+ a[m]+" "+ a[r]);
                    return;
                }
                if (Math.abs(answer) > Math.abs(sum)){
                    answer = sum;
                    ans[0] = l;
                    ans[1] = m;
                    ans[2] = r;
                }
    
                if( sum > 0 ){
                    r--;
                }
                else{
                    l++;
                }
            }
        }
        System.out.println(a[ans[0]] + " " + a[ans[1]]+ " " + a[ans[2]] );
     }
}