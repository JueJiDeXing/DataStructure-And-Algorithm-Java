package 算法OJ.洛谷.普及down;

import java.util.*;
import java.io.*;

/**
 已AC
 */
public class P1002过河卒 {
    /*
    左上角(0,0),右下角(n,m)
    从左上角可以向下或向右,求到右下角的路径数
    棋盘(x,y)处有一个马,马能到达的位置不能走(马固定不动)
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static int[][] direction = new int[][]{{1, 2}, {1, -2}, {2, 1}, {2, -1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
    static int n, m;
    static List<int[]> horse = new ArrayList<>();

    static boolean isVaild(int x, int y) {//坐标是否越界
        return 0 <= x && x <= n && 0 <= y && y <= m;
    }

    static boolean contain(int x, int y) {//坐标是否是马
        for (int[] p : horse) {
            if (p[0] == x && p[1] == y) return true;
        }
        return false;
    }

    /**
     dp[i][j]:从(0,0)到达(i,j)的方案数
     如果(i,j)位置是马,dp[i][j]=0
     否则,(i,j)可由(i-1,j)和(i,j-1)转移,dp[i][j]=dp[i-1][j]+dp[i][j-1]
     */
    public static void main(String[] args) {
        n = Int();
        m = Int();
        int x = Int(), y = Int();
        horse.add(new int[]{x, y});
        for (int[] dir : direction) {
            int nx = x + dir[0], ny = y + dir[1];
            if (isVaild(nx, ny)) horse.add(new int[]{nx, ny});
        }

        solve2();//dp
        //solve1();//dfs+记忆化搜索
    }

    private static void solve2() {
        long[][] dp = new long[n + 2][m + 2];
        dp[0][1] = 1;
        for (int i = 1; i <= n + 1; i++) {
            for (int j = 1; j <= m + 1; j++) {
                if (contain(i - 1, j - 1)) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        System.out.println(dp[n + 1][m + 1]);
    }


    private static void solve1() {
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1);
        }
        System.out.println(dfs(0, 0));
    }


    static long[][] memo = new long[25][25];

    /**
     @param x,y 当前位置
     */
    static long dfs(int x, int y) {
        if (x == n && y == m) {
            return 1;
        }
        if (memo[x][y] != -1) return memo[x][y];
        long count = 0;
        if (isVaild(x + 1, y) && !contain(x + 1, y)) {
            count += dfs(x + 1, y);
        }
        if (isVaild(x, y + 1) && !contain(x, y + 1)) {
            count += dfs(x, y + 1);
        }
        return memo[x][y] = count;
    }


}
