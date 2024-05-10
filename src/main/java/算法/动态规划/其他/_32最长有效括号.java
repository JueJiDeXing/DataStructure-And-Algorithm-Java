package 算法.动态规划.其他;

import 算法OJ.蓝桥杯.题单.动态规划基础30题.卖货;

import java.util.*;

public class _32最长有效括号 {
    /*
    给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
     */
    public int longestValidParentheses(String s) {
        int ans = 0, len = s.length();
        int[] dp = new int[len];//dp[i]:从i开始到前面i-dp[i]可以构成的最长有效括号子串长度
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == '(') continue;//dp[i],必须以右括号结尾才能是有效的括号子串
            if (s.charAt(i - 1) == '(') {//前一位为'(',当前位可以与前一位匹配
                int prevLen = i >= 2 ? dp[i - 2] : 0;
                dp[i] = prevLen + 2;
            } else {//前一位为')',当前位需要向前找未匹配的'('
                int prevIndex = i - dp[i - 1];//i-dp[i-1]为前一个匹配项的起始位置
                if (prevIndex > 0 && s.charAt(prevIndex - 1) == '(') {//前面需要有字符 且 前一个字符为左括号与当前位匹配
                    //(prevIndex >= 2 ? dp[prevIndex - 2] : 0)查看匹配后的前面是否为匹配项,能否进行拼接
                    dp[i] = dp[i - 1] + 2 + (prevIndex >= 2 ? dp[prevIndex - 2] : 0);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    /**
     统计合法连续括号序列的个数
     {@link 卖货}
     */
    public long count(String s) {
        int len = s.length();
        long[] dp = new long[len + 1];
        Stack<Integer> stack = new Stack<>();
        long ans = 0;
        for (int i = 1; i <= len; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {//1不可能是合法子序列的结尾
                stack.push(i);
            } else if (!stack.isEmpty()) {
                dp[i] = dp[stack.pop() - 1] + 1;//stack.pop()-1最近未匹配的1的前一位,dp[..]为前面有多少个能拼接的序列
            }
            ans += dp[i];
        }
        return ans;
    }
}
