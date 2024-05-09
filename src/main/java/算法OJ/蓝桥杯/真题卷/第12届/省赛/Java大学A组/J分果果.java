package 算法OJ.蓝桥杯.真题卷.第12届.省赛.Java大学A组;

import java.io.*;

/**
 这题动态规划我真写不了
 */
public class J分果果 {
    /*
    n包糖果分给m个人,每人至少一包
    糖果编号1~n,第i包糖果重w[i],每个人得到的糖果编号需要是连续的
    每包糖果可以有两份(必须分出一份出去,另一个可分可不分),但同一个人最多拿其中一份
    求分出的方案中最大重量与最小重量的差最小

    长度为n的数组分为m个长度大于0的子数组,求子数组和的最大值与最小值差最小为多少

     */
    /*
10 8
5 4 5 8 2 6 7 6 7 3
4

     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {

        int n = nextInt();
        int m = nextInt();
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = arr[i - 1] + nextInt();
        }


        int[][][] dp = new int[m + 1][n + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= j; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        dp[0][0][0] = 0;
        int ans = Integer.MAX_VALUE;
        for (int min = arr[n] * 2 / m; min > 0; min--) {
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    int trans2 = 0, trans3 = 0;
                    for (int k = 1; k <= j; k++) {
                        dp[i][j][k] = dp[i][j][k - 1];
                        while (true) {
                            if (trans2 >= k) break;
                            int diff = arr[j] - arr[trans2 + 1];
                            if (diff < min) break;
                            if (Math.max(dp[i - 1][trans2 + 1][trans2 + 1], diff) >
                                    Math.max(dp[i - 1][trans2][trans2], arr[j] - arr[trans2])) break;
                            trans2++;

                        }
                        if (arr[j] - arr[trans2] >= min) {
                            dp[i][j][k] = Math.min(dp[i][j][k], Math.max(dp[i - 1][trans2][trans2], arr[j] - arr[trans2]));
                        }
                        while (true) {
                            if (trans3 >= k) break;
                            int diff = arr[j] - arr[trans3 + 1];
                            if (diff < min) break;
                            if (Math.max(dp[i - 1][k][trans3 + 1], diff) >
                                    Math.max(dp[i - 1][k][trans3], arr[j] - arr[trans3])) break;
                            trans3++;
                        }
                        if (arr[j] - arr[trans3] >= min) {
                            dp[i][j][k] = Math.min(dp[i][j][k], Math.max(dp[i - 1][k][trans3], arr[j] - arr[trans3]));
                        }

                    }
                }
            }
            ans = Math.min(ans, dp[m][n][n] - min);
        }
        System.out.println(ans);
    }
}
