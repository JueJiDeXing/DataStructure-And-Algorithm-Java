package 算法.动态规划_贪心.动态规划.其他;

/**
 难度:困难
 */
public class _44通配符匹配 {
    /*
    给你一个输入字符串 (s) 和一个字符模式 (p) ，请你实现一个支持 '?' 和 '*' 匹配规则的通配符匹配：
    '?' 可以匹配任何单个字符。
    '*' 可以匹配任意字符序列（包括空字符序列）。
    判定匹配成功的充要条件是：字符模式必须能够 完全匹配 输入字符串（而不是部分匹配）。
     */

    /**
     dp[i][j]:s的前i个与p的前j个能否匹配<br>
     如果p[j]是字母,那么dp[i][j] = dp[i-1][j-1] && s[i]==p[j]<br>
     如果p[j]是?,那么dp[i][j] = dp[i-1][j-1]<br>
     如果p[j]是*:<br>
     1.使用*号,dp[i][j] = dp[i-1][j]<br>
     2.不使用*号,dp[i][j] = dp[i][j-1]<br>
     */
    public boolean isMatch(String s, String p) {
        char[] str = s.toCharArray(), pattern = p.toCharArray();
        int m = str.length, n = pattern.length;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n && pattern[i - 1] == '*'; ++i) {
            dp[0][i] = true;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (pattern[j - 1] == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else if (pattern[j - 1] == '?' || str[i - 1] == pattern[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
