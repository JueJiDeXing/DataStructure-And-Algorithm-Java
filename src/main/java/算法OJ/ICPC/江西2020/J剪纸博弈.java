package 算法OJ.ICPC.江西2020;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;

/**
 已AC(sg函数)
 */
public class J剪纸博弈 {
    /*
    初始为一张n*m的纸, 每次可选择一张纸, 按水平或竖直线进行裁剪(裁剪后为两张纸)
    如果剪出的两张纸有1*1的, 则输了

    给出n,m, Alice先手, 问谁会赢

    例:
    n*m=1*2: Alice输
        Alice第一步只能剪成两张1*1的, 必输
    n*m=2*2: Alice赢
        Alice剪成2张1*2的纸
        无论Bob选择哪张,都会输
    n*m=2*3: Alice赢
        Alice剪成两张1*3     (如果剪成1*2和2*2的,1*2不能再剪,Bob先手剪2*2的,Bob赢)
        1*3不能剪,Bob输
    n*m=3*3: Alice输
        Alice剪成1*3和2*3
        1*3不能剪,Bob剪2*3为两张1*3的
        剩余3张1*3的,Alice输

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

     (n,m) -> 分为(i,m)和(n-i,m)两个子游戏
     现在轮到Bob先手

     如果(i,m)和(n-i,m)都是先手必胜的
        假设Bob先做(i,m), 那么最后一步轮到Alice, 无法再剪
        Alice需要去先手剪(n-i,m)的纸, 而(n-i,m)先手必胜, 则Bob必输
     如果(i,m)和(n-i,m)都是先手必输的
         假设Bob先做(i,m), 那么最后一步轮到Bob, 无法再剪
         Bob需要去先手剪(n-i,m)的纸, 而(n-i,m)先手必胜, 则Bob必输
     如果(i,m)先手必输,(n-i,m)先手必胜
         假设Bob先做(i,m), 那么最后一步轮到Bob, 无法再剪
         Bob需要去先手剪(n-i,m)的纸, 而(n-i,m)先手必胜, 则Bob必胜

        假设Bob先做(n-i,m), 那么最后一步轮到Alice, 无法再剪
        Alice需要去先手剪(n-i,m)的纸, 而(n-i,m)先手必胜, 则Bob必输

     */
    static void dfs(int n, int m) {
        if (sg[n][m] != -1) return;// 已求过
        BitSet set = new BitSet();
        for (int i = 1; i < n; i++) {
            if (m == 1 && (i == 1 || i == n - 1)) continue;
            dfs(i, m);
            dfs(n - i, m);
            set.set(sg[i][m] ^ sg[n - i][m]);
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
