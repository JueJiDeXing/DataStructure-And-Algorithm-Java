package 算法OJ.蓝桥杯.算法赛.小白入门赛.第3场;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 已AC
 */
public class _5奇怪的段 {
    /*
    长度为n的数组,将其划分为k个子数组(完全划分)
    每个子数组的值为子数组和,记为W[i]
    给定长度为k的系数数组p
    求 sum{ p[i]*W[i] } 的最大值
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int n = Int(), k = Int();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) A[i] = Int();
        int[] p = new int[k];
        for (int i = 0; i < k; i++) p[i] = Int();
        long[][] dp = new long[k + 1][n + 1];//dp[i][j]:前j个数,划分i个子数组的最大加权和值
        /*
        如果j与j-1分配为第i个子数组: dp[i][j] = dp[i][j-1] + p[i]*A[j]
        如果j是单开第i个子数组: dp[i][j] = dp[i-1][j-1] + p[i]*A[j]
         */
        long ps = 0;
        for (int j = 1; j <= n; j++) {
            ps += (long) p[0] * A[j - 1];
            dp[1][j] = ps;
        }
        for (int i = 2; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                if (j < i) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - 1]) + (long) p[i - 1] * A[j - 1];
            }
        }
        System.out.println(dp[k][n]);
    }
}
