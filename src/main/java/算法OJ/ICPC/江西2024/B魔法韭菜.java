package 算法OJ.ICPC.江西2024;

import java.io.*;

public class B魔法韭菜 {
    /*
    n个韭菜排成环形, 初始第i颗高度为v[i], 每秒每颗韭菜都会长高k点
    初始在索引p位置, 每秒收走当前位置韭菜(在韭菜生长后), 再选择{不动,向左一步,向右一步}
    执行t0次操作, 求最大收益
     */
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }


    public static void main(String[] args) throws Exception {
        int T = I();
        while (T-- > 0) {
            solve();
        }
        pw.flush();
    }

    static void solve() throws IOException {
        int n = I(), p = I();
        int[] v = new int[n];
        for (int i = 0; i < n; i++) v[i] = I();
        long k = I(), t0 = I();
        if (t0 >= n) {
            long s = (t0 - n) * n * k + (n + 1L) * n / 2 * k;
            for (int i = 0; i < n; i++) {
                s += v[i];
            }
            pw.println(s);
            return;
        }
        int p1 = p - 1, p2 = p - 1;
        long sum1 = 0, sum2 = 0;
        for (int i = 0; i < t0; i++) {
            sum1 += v[p1];
            sum2 += v[p2];
            p1 = (p1 + 1) % n;
            p2 = (p2 - 1 + n) % n;
        }
        long s = (t0 + 1) * t0 / 2 * k;
        pw.print(s + Math.max(sum1, sum2));

    }
}
