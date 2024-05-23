package 算法OJ.ICPC.江西2020;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;
/**
 已AC(sg函数)
 */
public class J剪纸博弈 {
    /*
    n*m的纸, 每次可选择一条水平或竖直的先进行裁剪
    如果见出的两边纸有1*1的,则输了
    给出n,m,Alice先手,问谁会赢
     */
    public static void main(String[] args) {
        for (int[] s : sg) Arrays.fill(s, -1);
        sg[1][2] = sg[2][1] = sg[3][1] = sg[1][3] = 0;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt(), m = sc.nextInt();
            dfs(n, m);
            System.out.println(sg[n][m] != 0 ? "Alice" : "Bob");
        }
    }

    static int[][] sg = new int[200][200];

    /**
     (n,m)的情况下,先手能不能赢
     */
    static void dfs(int n, int m) {
        if (sg[n][m] != -1) return;
        BitSet set = new BitSet();
        for (int i = 1; i < n; i++) {
            if (m == 1 && (i == 1 || i == n - 1)) continue;
            dfs(i, m);
            dfs(n - i, m);
            set.set(sg[i][m] ^ sg[n - i][m]);// 子游戏的结果(是否先手必胜) 为 他俩的异或
        }
        for (int i = 1; i < m; i++) {
            if (n == 1 && (i == 1 || i == m - 1)) continue;
            dfs(n, i);
            dfs(n, m - i);
            set.set(sg[n][i] ^ sg[n][m - i]);
        }

        sg[n][m] = set.nextClearBit(0);
    }
}
