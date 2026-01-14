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
    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    static int bs1(int target , int l, int r, int[] a){
        
        while(l<r){
            int mid = l+ (r-l)/2;
            if( a[mid] < target){
                l = mid+1;
            }
            else{
                r= mid;
            }

        }
        return l;
    }
    static int bs2(int target , int l, int r, int[] a){
        
        while(l<r){
            int mid = l+ (r-l)/2;
            if( a[mid] <= target){
                l = mid+1;
            }
            else{
                r= mid;
            }

        }
        return l;
    }
    public static void main(String argv[]) throws IOException{
        int n=nextInt(), m= nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
        Arrays.sort(a);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int start =nextInt(), end = nextInt();
            int count = bs2(end, 0, n, a) - bs1(start, 0, n, a);
            sb.append(count).append('\n');
        }
        System.out.print(sb);


    }
}