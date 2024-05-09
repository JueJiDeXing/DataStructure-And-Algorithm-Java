package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学A组;

import 算法.数学.组合数学.组合数;
import 算法.数学.组合数学.错排问题;

/**
 已AC
 */
public class B小蓝与钥匙 {
    /*
    28人,每人有一把钥匙,将钥匙随机打乱然后分发下去
    问,正好14个人的钥匙是自己的钥匙.另外14个人的钥匙都不是自己的钥匙的方案有多少种
     */
    /*
    14个人是自己的钥匙:C(28,14)
    另外14个人的钥匙都不是自己的钥匙:D(14) 其中D表示错排
     */
    public static void main(String[] args) {
        int n = 28, m = 14;
        System.out.println(C(n, m) * D(m));//1286583532342313400
    }

    /**
     组合数公式C(n,m)=C(n-1,m)+C(n-1,m-1)<br>
     公式推导:{@link 组合数}
     */
    private static long C(int n, int m) {
        if (m > n || n < 0) return 0;
        if (n == m) return 1;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n && i <= m; i++) {
            dp[i][i] = 1;
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
            }
        }
        return dp[n][m];
    }

    /**
     错排公式D(n)=(n-1)*(D(n-1)+D(n-2))<br>
     公式推导:{@link 错排问题}
     */
    private static long D(int n) {
        long[] dp = new long[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2]);
        }
        return dp[n];
    }
}
