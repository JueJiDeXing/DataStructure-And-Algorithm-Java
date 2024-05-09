package 算法.字符串;

import java.util.Arrays;

public class 最长公共子串 {
    /*
    输入两个字符串,找最长的公共子串的长度
    例:"jjdx" "thema"
    len("the")>len("ma")
    result=3
     */
    public static void main(String[] args) {
        System.out.println(lcs("jjdx", "thema"));
    }
    /**
     <h1>动态规划</h1>
     dp[i][j]:第一个字符串的第i个字符,与第二个字符串的第j个字符
     状态转移方程:
     如果相同,则在前一行前一列的基础上加1
     如果不同,则为0
     最后dp里的最大值即为最长公共子串的长度
     */
    public static int lcs(String a, String b) {
        int[][] dp = new int[b.length()][a.length()];
        int max = 0;
        for (int i = 0; i < b.length(); i++) {
            for (int j = 0; j < a.length(); j++) {
                if (a.charAt(j) == b.charAt(i)) {
                    dp[i][j] = (i == 0 || j == 0) ? 1 : (dp[i - 1][j - 1] + 1);
                    max = Math.max(max, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
            System.out.println(Arrays.deepToString(dp));
        }
        return max;
         /*
                i   t   h   e   i   m   a
           t    0   1   0   0   0   0   0
           h    0   0   2   0   0   0   0
           e    0   0   0   3   0   0   0
           m    0   0   0   0   0   1   0
           a    0   0   0   0   0   0   2
        */
    }
}
