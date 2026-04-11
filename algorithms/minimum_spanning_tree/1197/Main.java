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

    // what does union find need:
    // find its group
    // put a node to a group(and set group)
    static int[] parent;

    static int find(int a) {
        while (parent[a] != a) {
            a = parent[a];
        }
        return a;
    }

    static void union(int a, int b) {
        int p_a = find(a);
        int p_b = find(b);
        if (p_a == p_b)
            return;
        // compress
        while (b != parent[b]) {
            int temp = parent[b];
            parent[b] = p_a;
            b = temp;
        }
        parent[b] = p_a;
    }

    public static void main(String[] args) throws IOException {
        int v = nextInt();
        int e = nextInt();

        // need to store vertices ..
        int[][] weight = new int[e][3];
        // makeset
        parent = new int[v];
        for (int i = 0; i < v; i++) {
            parent[i] = i;
        }
        long answer = 0;

        for (int i = 0; i < weight.length; i++) {
            int a = nextInt();
            int b = nextInt();
            int w = nextInt();
            int[] info = weight[i];
            info[0] = w;
            info[1] = a;
            info[2] = b;
        }

        Arrays.sort(weight, (a, b) -> Integer.compare(a[0], b[0]));

        int mstVertice = weight[0][0];
        for (int i = 0; i < v - 1; i++) {
            int v1 = weight[i][0];
            int v2 = weight[i][1];
            if (find(v1) == mstVertice && find(v2) == mstVertice) {
                // its a cycle.
                continue;
            } else {
                union(mstVertice, v1);
                union(mstVertice, v2);
                answer += weight[i][2];
            }
        }
        System.out.println(answer);
    }
}

/*
 * 그래프가 주어졌을 때, 그 그래프의 최소 스패닝 트리를 구하는 프로그램을 작성하시오.
 * 
 * 최소 스패닝 트리는, 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리를 말한다.
 * 
 * 입력
 * 첫째 줄에 정점의 개수 V(1 ≤ V ≤ 10,000)와 간선의 개수 E(1 ≤ E ≤ 100,000)가 주어진다. 다음 E개의 줄에는 각
 * 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다. 이는 A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다는
 * 의미이다. C는 음수일 수도 있으며, 절댓값이 1,000,000을 넘지 않는다.
 * 
 * 그래프의 정점은 1번부터 V번까지 번호가 매겨져 있고, 임의의 두 정점 사이에 경로가 있다. 최소 스패닝 트리의 가중치가
 * -2,147,483,648보다 크거나 같고, 2,147,483,647보다 작거나 같은 데이터만 입력으로 주어진다.
 */