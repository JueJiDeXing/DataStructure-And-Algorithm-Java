package 算法OJ.Codeforces.构造题单;

import java.util.*;
/**
 已AC
 */
public class _4最长有效括号序列 {
    /*
    长度为n的括号序列
    输出最长的括号序列长度,并且如果只有1个或没有输出1,如果有多个输出其个数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char[] s = str.toCharArray();
        int n = s.length;
        int[] dp = new int[n];//dp[i]:以s[i]结尾的最长括号序列长度
        int maxLen = 0;
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            char cur = s[i], pre = s[i - 1];
            if (cur == '(') continue;//左括号不可能是结尾
            if (pre == '(') {//cur与pre配对
                int preLen = i >= 2 ? dp[i - 2] : 0;// .... pre( cur)
                dp[i] = 2 + preLen;
            } else {
                //前面一位是右括号
                int len = dp[i - 1];//pre与前面的最大匹配长度
                int preIdx = i - len;//pre与前面的匹配位置
                if (len != 0 && preIdx - 1 >= 0 && s[preIdx - 1] == '(') {
                    int preLen = preIdx - 2 >= 0 ? dp[preIdx - 2] : 0; // .... pre( xxx cur)
                    dp[i] = 2 + len+preLen;
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                cnt = 1;
            } else if (dp[i] == maxLen) {
                cnt++;
            }
        }
        if (maxLen == 0) {
            System.out.println("0 1");
        } else {
            System.out.println(maxLen + " " + cnt);
        }
    }
}
