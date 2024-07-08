package 算法OJ.ICPC.江西2024;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class L校园 {
    /*
    n个节点,m条边, 第i个节点上有a[i]个游客, 游客的速度为无穷大
    n个节点中,有k个节点为门, 第i个门在[l[i],r[i]]时间段开启
    问1~T的每一刻, 游客从门离开的距离之和为多少
     */
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static String S() throws IOException {
        String res = bf.readLine();
        while (res.isEmpty()) res = bf.readLine();
        return res;
    }

    public static void main(String[] args) throws Exception {
        int n = I(), m = I(), k = I(), T = I();
        int[] a = new int[n + 1];// a[i]:第i个节点上的人数
        for (int i = 1; i <= n; i++) a[i] = I();
        int[][] LR = new int[k][3];
        for (int i = 0; i < k; i++) {
            int p = I(), l = I(), r = I();
            LR[i] = new int[]{p, l, r};
        }
        List<int[]>[] graph = new List[n + 1];
        Arrays.setAll(graph, e -> new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int u = I(), v = I(), w = I();
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }
    }

}
