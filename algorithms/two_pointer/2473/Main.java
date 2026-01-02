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
    static int bs(int l, int r, int target, int[] a){
        int s = l, e = r;
        int m =(s+e)/2;
        while(s < e){
            m = (s+e)/2;
            if(target < a[m]){
                e=m;
            }
            else if( target == a[m]){
                break;
            }
            else{
                s= m+1;
            }
        }
        return m;
    }

    public static void main(String argv[]) throws IOException{
        int n = Integer.parseInt(next());
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(next());
        }
        // bad impl
        Arrays.sort(a);
        int l =0;
        int r = n-1;
        int m = 1;
        
        int ans[] = new int[]{l,m,r};
        int answer = a[l] + a[r]; //temp
        int target = -answer;
        m = bs(l+1, r-1, target, a);
        ans[1] = m;
        answer += a[m];
        while(l+1<r){
            m = bs(l+1, r-1, -(a[l] + a[r]), a);
            int sum = a[l] + a[m]+ a[r];
            if(m+1 < r){
                int sum2 = sum -a[m] + a[m+1];
                if( Math.abs(sum) > Math.abs(sum2)){
                    sum = sum2;
                    m++;
                }
            }
            if (sum == 0){
                System.out.println(a[l] + " "+ a[m]+" "+ a[r]);
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
        System.out.println(a[ans[0]] + " " + a[ans[1]]+ " " + a[ans[2]] );
     }
}