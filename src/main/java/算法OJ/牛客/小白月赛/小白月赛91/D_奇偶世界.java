package 算法OJ.牛客.小白月赛.小白月赛91;

import java.util.*;

/**
 已AC
 */
public class D_奇偶世界 {
    /*
    从长度为n的数串S中选择一个子序列,得到的子序列需要是一个无前导0的偶数,求方案数
     */
    static int MOD = 10_0000_0007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();
        char[] ss = s.toCharArray();
        long ans = 0;
        for (char c : ss) if ((c - '0') % 2 == 0) ans++;
        long[][] dp = new long[n + 1][2];
        /*
        dp[i][0]: 第一位为1~9,后面为任意数字的序列数
        dp[i][1]: 第一位为1~9,中间为任意数字,最后一位为偶数的序列数
        状态转移:
        dp[i][0]:
        (1)不要i,方案数为dp[i-1][0];
        (2)将i添加到前面的序列里,方案数为dp[i-1][0];
        (3)将i作为开头(不为0),方案数为1
        dp[i][1]:
        (1)不要i,方案数为dp[i-1][1]
        (2)要i(偶数),方案数为dp[i-1][0]
        */
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] * 2 % MOD;//选和不选
            if (ss[i - 1] != '0') dp[i][0] += 1;//空串的开头字符

            if (ss[i - 1] % 2 == 0) {
                dp[i][1] = (dp[i - 1][1] + dp[i - 1][0]) % MOD;
            } else {
                dp[i][1] = dp[i - 1][1];
            }
        }
        System.out.println(ans + dp[n][1]);
    }
}
