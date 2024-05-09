package 算法.动态规划_贪心.动态规划.其他;

public class _91解码方法 {
    /*
    一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：

    'A' -> "1"
    'B' -> "2"
    ...
    'Z' -> "26"
    要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：

    "AAJF" ，将消息分组为 (1 1 10 6)
    "KJF" ，将消息分组为 (11 10 6)
    注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。

    给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。

    题目数据保证答案肯定是一个 32 位 的整数。
     */

    /**
     dp[i]:使用前i个字符的解码总数<br>
     对于dp[i]:<br>
     1. 如果第i个字符单独使用,则dp[i]=dp[i-1],(s[i]!=0)<br>
     2. 如果第i个字符与i-1结合使用,则dp[i]=dp[i-2],(s[i-1]与s[i]拼接有效)<br>
     所以dp[i]=dp[i-1]+dp[i-2]
     */
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;//空串视为1种解码方式
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) != '0') {//单独使用,不能为0
                dp[i] += dp[i - 1];
            }
            //结合使用:需要有前一位,前一位不能为0,与前一位的结合需要合法(不能超过26)
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }
}
