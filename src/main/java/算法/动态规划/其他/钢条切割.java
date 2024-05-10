package 算法.动态规划.其他;

import java.util.Arrays;

public class 钢条切割 {
    /*
    不同长度的钢条有不同价值
    输入:价值数组(索引表示长度,值表示价值),n(总长度)
    输出:求价值最高的切割价值
     */
    public static void main(String[] args) {
        System.out.println(cut2(new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20}, 4));
    }

    /**
     dp[i][j],尝试切i长度,切割总长度为j
     状态转移方程:
     如果放得下:比较这次的组合与上一次的价值 dp[i][j]=max(value+dp[i][j-weight],dp[i-1][j])
     如果放不下:取上一次(切i-1长度,总长度为j)价值  dp[i][j]=dp[i-1][j]
     */
    public static int cut(int[] values, int n) {
        int valLen = Math.min(n + 1, values.length);//价值长度超过总的可切割长度可以忽略
        int[][] dp = new int[valLen][n + 1];

        for (int i = 1; i < valLen; i++) {//索引表示长度
            for (int j = 1; j < n + 1; j++) {//总的可切割长度
                if (j >= i) {//能切割
                    dp[i][j] = Math.max(values[i] + dp[i][j - i], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
            System.out.println(Arrays.deepToString(dp));
        }
        return dp[valLen - 1][n];
    }

    /**
     降维
     */
    public static int cut2(int[] values, int n) {
        int valLen = Math.min(n + 1, values.length);//价值长度超过总的可切割长度可以忽略
        int[] dp = new int[n + 1];

        for (int i = 1; i < valLen; i++) {//索引表示长度
            for (int j = i; j < n + 1; j++) {//总的可切割长度
                //if(j>=i)
                dp[j] = Math.max(values[i] + dp[j - i], dp[j]);//能切割
                //不能切割,不变
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[n];
    }
}
