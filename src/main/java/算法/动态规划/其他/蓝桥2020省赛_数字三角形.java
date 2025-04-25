package 算法.动态规划.其他;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class 蓝桥2020省赛_数字三角形 {
    /*
    给定一个N行的数字三角形, 第k行有k个数字
    从第1行第1个数字起, 每次可以向左下或右下走, 需要满足向左和向右走的步数相差不超过1
    到达第N行后, 统计路径之和, 求最大路径和
    示例:
    n = 5
        7
       3 8
      8 1 0
     2 7 4 4
    4 5 2 6 5
    输出:27
    7+3+8+7+2=27
     */

    /**
     (设下标从1开始)
     <p>
     若不考虑"向左和向右走的步数相差不超过1"的限制:
     dp[i][j]: 从(1,1)走到(i,j)的最大路径和
     则 ans = max(dp[n])
     <p>
     <p>
     (1)如果n为奇数:
     那么最后一定会走到a[n][n/2+1]的位置
     则ans=dp[n][n/2+1]
     (2)如果n为偶数:
     那么最后会走到a[n][n/2]或a[n][n/2+1]的位置
     而上一行一定是位于a[n-1][(n-1)/2+1]
     则ans = dp[n-1][(n-1)/2+1] + max(a[n][n/2],a[n][n/2+1])
     由于 max(a[n][n/2],a[n][n/2+1]) 已经在求dp时计算过了
     则 ans = max(dp[n][n/2],dp[n][n/2+1])
     <p>
     所以,只需要用原来的dp形式,最后取值时按照n的奇偶性,选择返回值即可
     */

    public static void main(String[] args) throws IOException {
        int n = I();
        int[][] a = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                a[i][j] = I();
            }
        }
        int[][] dp = new int[n + 1][n + 1];// dp[i][j]:从(0,0)到(i,j)的最大和
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = a[i][j] + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
            }
        }
        if (n % 2 == 0) {
            System.out.println(Math.max(dp[n][n / 2], dp[n][n / 2 + 1]));
        } else {
            System.out.println(dp[n][n / 2 + 1]);
        }
    }

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
