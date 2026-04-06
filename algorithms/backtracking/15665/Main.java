import java.io.*;
import java.util.*;

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

    static long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    static StringBuilder sb = new StringBuilder();
    static int m, n, a[];

    static void bt(List<Integer> list, int depth) {

        if (depth == m) {
            for (int i : a) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            int num = list.get(i);
            a[depth] = num;
            bt(list, depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        a = new int[m];

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(nextInt());
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);

        bt(list, 0);
        System.out.println(sb);
    }
}