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
        int answer = a[l] + a[r];
        int ans[] = new int[]{l,r};
        while(l<r){
            int sum = a[l] + a[r];
            if (sum == 0){
                System.out.println(a[l] + " " + a[r]);
            }
            if (Math.abs(answer) > Math.abs(sum)){
                answer = sum;
                ans[0] = l;
                ans[1] = r;
            }

            if( sum > 0 ){
                r--;
            }
            else{
                l++;
            }
        }
        System.out.println(a[ans[0]] + " " + a[ans[1]]);
    }
}