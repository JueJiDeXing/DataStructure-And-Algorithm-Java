package 算法.动态规划.其他;

/**
 难度:困难
 */
public class _10正则表达式匹配 {
    /*
    给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
    '.' 匹配任意单个字符
    '*' 匹配零个或多个前面的那一个元素
    所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

     示例:
        s       p       输出      注释
        aa      a       F       a匹配第一个a,第二个a不匹配
        ab      .*      T       .*匹配所有字符
        aa      a*      T       a*匹配aa
        aaab    a*      F       a*匹配aaa,b不匹配
        aab     c*a*b   T       c*匹配null a*匹配aa b匹配b

       此题与44通配符匹配非常相似
     */

    /**
     <h1>动态规划</h1>
     <ul>
     <li>
     思路:<br>
     dp[i][j]:s的前i个字符与p的前j个字符是否匹配
     </li>
     <li>
     初始化:<br>
     dp[i][0] null不能匹配s的字符; dp[0][0]=T null匹配null<br>
     </li>
     <li>
     状态转移方程:
     <ul>
     <li>如果p[j]为字母:<br>
     如果s[i]==p[j],dp[i][j]==dp[i-1][j-1]<br>
     如果s[i]!=p[j],dp[i][j]=F</li>
     <li>如果p[j]为'.':<br>
     一定匹配,dp[i][j]==dp[i-1][j-1]</li>
     <li>如果p[j]为'*':<br>
     如果s[i]匹配失败,去掉p[j]的字母+星号组合<br>
     如果匹配则继续匹配s的前一位,直到匹配失败,去掉p[j]的字母+星号组合<br>
     dp[i][j]=dp[i-1][j]||dp[i][j-2]</li>
     </ul>
     </li>
     </ul>
     */
    public boolean isMatch(String s, String p) {
        char[] ss = s.toCharArray(),pp = p.toCharArray();
        int len1 = ss.length,len2 = pp.length;
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];//留出一位
        dp[0][0] = true;//null匹配null
        for (int i = 0; i <= len1; i++) {//i从0开始,*号字符可以匹配null
            for (int j = 1; j <= len2; j++) {//j从1开始,null不能匹配s的字符
                if (pp[j - 1] == '*') {//是星号
                    dp[i][j] = dp[i][j - 2];//如果不使用字母+星号组合,是否能匹配
                    if (ismatch(ss, pp, i, j - 1)) {//匹配星号前一位的字母
                        dp[i][j] = dp[i][j] || dp[i - 1][j];//dp[i - 1][j]:扔掉s的最后一位继续匹配
                    }
                } else if (ismatch(ss, pp, i, j)) {//不是星号且该位能匹配
                    dp[i][j] = dp[i - 1][j - 1];
                } //不能匹配为false
            }
        }
        return dp[len1][len2];
    }

    public boolean ismatch(char[] ss, char[] pp, int i, int j) {
        if (i == 0) return false;
        if (pp[j - 1] == '.') return true;
        return ss[i - 1] == pp[j - 1];
    }

}
