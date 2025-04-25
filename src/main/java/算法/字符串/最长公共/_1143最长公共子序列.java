package 算法.字符串.最长公共;

public class _1143最长公共子序列 {
    /*
    子序列:可以不连续,但必须保证相对位置(在abcde里,ace是子序列,aec不是子序列)
    "abcxyz", "abxyz"  --> result=len("abxyz")=5
     */

    /**
     <h1>动态规划</h1>
     dp[i][j]:第一个字符串的前i个与第二个字符串的前j个构成的最长公共子序列长度<br>
     状态转移方程:<br>
     如果相同:取两个字符串在该位置前面的子串的结果+1  dp[i][j]=dp[i-1][j-1]+1<br>
     如果不同:沿用左边和上面的最大值   dp[i][j]=max(dp[i-1][j],dp[i][j-1])<br>
     最后,右下角的值为最长公共子序列的长度<br>
     */
    public static int lcs(String a, String b) {
        // dp[i][j]:第一个字符串的前i个与第二个字符串的前j个构成的最长公共子序列长度
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {//如果相同:取两个字符串在该位置前面的子串的结果+1
                    dp[i][j] = dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {//如果不同:沿用左边和上面的最大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[a.length()][b.length()];
        /*
                 a   b   c   x   y   z
              0  0   0   0   0   0   0
           a  0  1   1   1   1   1   1
           b  0  1   2   2   2   2   2
           x  0  1   2   2   3   3   3
           y  0  1   2   2   3   4   4
           z  0  1   2   2   3   4   5
         */
    }
}

class _583字符串删除 {
    /*
    输入两个字符串
    每步删除一个字符串的一个字符,留下两个字符串的公共部分,求最少需要删多少次
     */

    /**
     本质上也是在求最长公共子序列长度<br>
     字符串a的删除次数为a.length-公共长度<br>
     字符串b的删除次数为b.length-公共长度<br>
     */
    public int minDistance(String a, String b) {
        int m = a.length();
        int n = b.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return m - dp[m][n] + n - dp[m][n];
    }

    //效率改进
    public int minDistance2(String a, String b) {
        int m = a.length();
        int n = b.length();
        char[] str1 = a.toCharArray();
        char[] str2 = b.toCharArray();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return m - dp[m][n] + n - dp[m][n];
    }
}
