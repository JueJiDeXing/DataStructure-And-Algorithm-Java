package 算法OJ.蓝桥杯.算法赛.算法双周赛.第3场;

import java.io.*;
import java.util.Arrays;
/**
 已AC
 */
public class _4迷宫逃脱 {
    /*
    n*m的矩阵,从左上角到右下角
    有Q把钥匙,每次从格子i走到相邻格子j,如果i和j的数互质,则需要消耗一把钥匙
    求走到右下角的路径最大和,如果无法到达则输出-1
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    static int n, m;
    static int[][] map;

    public static void main(String[] args) {
        n = nextInt();
        m = nextInt();
        int q = nextInt();
        map = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                map[i][j] = nextInt();
            }
        }
        long[][][] dp = new long[n + 1][m + 1][q + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        Arrays.fill(dp[1][1], 0);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 0; k <= q; k++) {
                    if (map[i - 1][j] != 0 && gcd(map[i - 1][j], map[i][j]) == 1) {
                        if (k < q) dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][k + 1]);//需要钥匙
                    } else {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][k]);//不需要钥匙
                    }
                    if (map[i][j - 1] != 0 && gcd(map[i][j - 1], map[i][j]) == 1) {
                        if (k < q) dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j - 1][k + 1]);
                    } else {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j - 1][k]);
                    }
                    if (dp[i][j][k] != -1) dp[i][j][k] += map[i][j];
                }
            }
        }
        long max = -1;
        for (long t : dp[n][m]) {
            max = Math.max(max, t);
        }
        System.out.println(max);
    }

    private static void solve1(int q) {//12AC 8TLE
        memo = new long[n][m][q + 1];
        for (long[][] mem : memo) {
            for (long[] me : mem) {
                Arrays.fill(me, -1);
            }
        }
        long max = dfs(0, 0, q);
        System.out.println(max);
    }

    static long[][][] memo;

    static long dfs(int x, int y, int q) {
        if (memo[x][y][q] != -1) return memo[x][y][q];
        if (x == n - 1 && y == m - 1) {
            return map[n - 1][m - 1];
        }
        long max = -1;
        if (isValid(x + 1, y)) {
            if (gcd(map[x][y], map[x + 1][y]) == 1) {
                if (q != 0) {
                    max = Math.max(max, dfs(x + 1, y, q - 1));
                }
            } else {
                max = Math.max(max, dfs(x + 1, y, q));
            }
        }
        if (isValid(x, y + 1)) {
            if (gcd(map[x][y], map[x][y + 1]) == 1) {
                if (q != 0) {
                    max = Math.max(max, dfs(x, y + 1, q - 1));
                }
            } else {
                max = Math.max(max, dfs(x, y + 1, q));
            }
        }
        return memo[x][y][q] = (max == -1 ? max : (max + map[x][y]));
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
