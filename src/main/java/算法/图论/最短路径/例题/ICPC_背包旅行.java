package 算法.图论.最短路径.例题;

import java.io.*;
import java.util.Arrays;

/**
 已AC
 */
public class ICPC_背包旅行 {
    /*
    n个城市, m个双向边, 边权为1
    q个询问, 从x到y, 最大预算为B
    如果背有k个包袱, 从 x->a1->a2->...->y 一共经过t条边, 花费为 k+k^2+k^3+...+k^t
     */


    public static void main(String[] args) throws IOException {
        int n = I(), m = I();
        int[][] graph = new int[n + 1][n + 1];
        int inf = 0x3f3f3f3f;
        for (int[] g : graph) Arrays.fill(g, inf);
        for (int i = 0; i < m; i++) {
            int u = I(), v = I();
            graph[u][v] = graph[v][u] = 1;
        }
        // Floyd求最短路
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][k] != inf && graph[k][j] != inf) {
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }
        }
        //询问
        int q = I();
        for (int i = 0; i < q; i++) {
            int x = I(), y = I(), B = I();
            int t = graph[x][y];// 背1个, 需要t的预算
            long ans = cal(t, B);
            pw.println(ans);
        }
        pw.flush();
        pw.close();
    }

    static long cal(int t, int B) {
        if (B < t) return 0;
        if (B == t) return 1;
        if (t == 1) return B;
        // k(k^t-1)/(k-1) <= B
        int left = 1, right = B + 1;//left:√
        while (left + 1 != right) {
            int k = (left + right) >>> 1;
            if (sum(k, t) > B) {
                right = k;
            } else {
                left = k;
            }
        }
        return left;

    }

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static long sum(int k, int t) {
        // k + k^2 + k^3 + ... + k^t
        // = k(k^t-1)/(k-1)
        return (long) (k * (Math.pow(k, t) - 1) / (k - 1));
    }
}
