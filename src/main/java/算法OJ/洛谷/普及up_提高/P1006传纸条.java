package 算法OJ.洛谷.普及up_提高;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 已AC
 */
public class P1006传纸条 {
    /*
    m*n的矩阵,除左上角和右下角为0外,其余格子都有一个数
    现在从左上角传纸条到右下角,再从右下角传到左上界,同一个格子不能走两次,求两次传递的路径上值的最大和
     */

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static int N, M;
    static int MAX = 50;
    static int[][] mat = new int[MAX + 1][MAX + 1];//M,N<=50

    /**
     左上角->右下角->左上角,等价于从左上角传递两次,只需要路径不重叠即可
     */
    public static void main(String[] args) {
        N = Int();
        M = Int();
        //将记录数组初始化成-1，因为可能出现取的数为0的情况，如果直接判断f[x][y][x2][y2]!=0（见dfs第一行）
        //可能出现死循环而导致超时，细节问题
        for (int a = 0; a <= MAX; a++) {
            for (int b = 0; b <= MAX; b++) {
                for (int c = 0; c <= MAX; c++) {
                    Arrays.fill(memo[a][b][c], -1);
                }
            }
        }
        for (int i = 1; i <= N; i++) {//读入
            for (int j = 1; j <= M; j++) {
                mat[i][j] = Int();
            }
        }
        //System.out.println(dfs(1, 1, 1, 1));
        /*
         dp[x1][y1][x2][y2]表示第一种方案目前位于(x1,y1),第二种方案目前位于(x2,y2)的最大和
         */
        int[][][][] dp = new int[MAX + 1][MAX + 1][MAX + 1][MAX + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                for (int k = 1; k <= N; k++) {
                    for (int l = 1; l <= M; l++) {
                        int num = max(dp[i - 1][j][k][l - 1], dp[i - 1][j][k - 1][l],
                                dp[i][j - 1][k][l - 1], dp[i][j - 1][k - 1][l]);
                        dp[i][j][k][l] = num + mat[i][j] + mat[k][l];
                        if (i == k && j == l) dp[i][j][k][l] -= mat[i][j];
                    }
                }
            }
        }
        System.out.println(dp[N][M][N][M]);
    }

    static int max(int a, int b, int c, int d) {
        int m1 = Math.max(a, b), m2 = Math.max(c, d);
        return Math.max(m1, m2);
    }

    static int[][][][] memo = new int[MAX + 1][MAX + 1][MAX + 1][MAX + 1];

    /**
     当第一种方案走到x,y,第二种方案走到x2,y2时 到终点可以取得的最大数
     */
    static int dfs(int x, int y, int x2, int y2) {//
        if (memo[x][y][x2][y2] != -1) return memo[x][y][x2][y2];//记忆化
        if (x == N && y == M && x2 == N && y2 == M) return 0;//如果两种方案都走到了终点，返回结束
        int ans = 0;
        if (x < N && x2 < N) {// case 1: 都往下走
            int res = dfs(x + 1, y, x2 + 1, y2) + mat[x + 1][y] + mat[x2 + 1][y2];
            int repeat = mat[x + 1][y] * (x + 1 == x2 + 1 && y == y2 ? 1 : 0);//如果走到了同一格,需要减去重复值
            ans = Math.max(ans, res - repeat);
        }
        if (x < N && y2 < N) {//case 2: 第一种向下,第二种向右
            int res = dfs(x + 1, y, x2, y2 + 1) + mat[x + 1][y] + mat[x2][y2 + 1];
            int repeat = mat[x + 1][y] * (x + 1 == x2 && y == y2 + 1 ? 1 : 0);
            ans = Math.max(ans, res - repeat);
        }
        if (y < N && x2 < N) {// case 3: 第一种向右,第二种向下
            int res = dfs(x, y + 1, x2 + 1, y2) + mat[x][y + 1] + mat[x2 + 1][y2];
            int repeat = mat[x][y + 1] * (x == x2 + 1 && y + 1 == y2 ? 1 : 0);
            ans = Math.max(ans, res - repeat);
        }

        if (y < N && y2 < N) { // case 4: 都向右走
            int res = dfs(x, y + 1, x2, y2 + 1) + mat[x][y + 1] + mat[x2][y2 + 1];
            int repeat = mat[x][y + 1] * (x == x2 && y + 1 == y2 + 1 ? 1 : 0);
            ans = Math.max(ans, res - repeat);
        }
        return memo[x][y][x2][y2] = ans;//返回最大值
    }
}
