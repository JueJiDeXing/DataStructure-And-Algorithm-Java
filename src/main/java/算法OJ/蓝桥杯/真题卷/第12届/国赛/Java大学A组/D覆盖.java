package 算法OJ.蓝桥杯.真题卷.第12届.国赛.Java大学A组;

/**
 已AC
 */
public class D覆盖 {
    /*
    8*8棋盘,用32张1*2的纸片覆盖,求方案数
     */
static boolean[][] bool = new boolean[8][8];
static int count = 0;

public static void main(String[] args) {
    dfs(0, 0);
    System.out.println(count);//12988816
}

public static void dfs(int a, int b) {
    if (a == 8) {
        count++;
        return;
    }
    if (!bool[a][b]) {
        bool[a][b] = true;
        if (b != 7 && !bool[a][b + 1]) {//横向放置
            bool[a][b + 1] = true;
            dfs(a, b + 1);
            bool[a][b + 1] = false;
        }
        if (a != 7 && !bool[a + 1][b]) {//竖向放置
            bool[a + 1][b] = true;
            if (b == 7) dfs(a + 1, 0);
            else dfs(a, b + 1);
            bool[a + 1][b] = false;
        }
        bool[a][b] = false;
    } else {//不能放置
        if (b == 7) dfs(a + 1, 0);
        else dfs(a, b + 1);
    }
}


    private static void slove1() {
        int n = 8;
        boolean[] st = new boolean[1 << n];
        long[][] f = new long[n + 1][1 << n];//[i][j]表示已经将前i-1列摆好，且从第i-1列伸出到i列的所有方案
        for (int i = 0; i < 1 << n; i++) {
            int count = 0;
            st[i] = true;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    if ((count & 1) == 1) {
                        st[i] = false;
                    }
                    count = 0;
                } else {
                    count++;
                }
            }
            if ((count & 1) == 1) st[i] = false;
        }

        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 1 << n; j++) {
                for (int k = 0; k < 1 << n; k++) {
                    if (st[(j | k)] && (j & k) == 0) {
                        f[i][j] += f[i - 1][k];
                    }
                }
            }
        }
        System.out.println(f[n][0]);
    }

}


