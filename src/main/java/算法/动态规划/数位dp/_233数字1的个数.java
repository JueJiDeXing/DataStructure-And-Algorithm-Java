package 算法.动态规划.数位dp;

import java.util.Arrays;

public class _233数字1的个数 {
    /*
    给定非负整数n,求小于等于n的非负整数中,1的出现次数
    例:n=13
    输出6
    1,10,11,12,13
    其中11有2个1
     */
    public int countDigitOne(int n) {
        char[] s = Integer.toString(n).toCharArray();
        int m = s.length;
        int[][] memo = new int[m][m];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        return dfs(0, 0, true, s, memo);
    }

    int dfs(int i, int cnt1, boolean isLimit, char[] s, int[][] memo) {
        if (i == s.length) return cnt1;

        if (!isLimit && memo[i][cnt1] >= 0) { // 之前计算过
            return memo[i][cnt1];
        }
        int res = 0;
        int up = isLimit ? s[i] - '0' : 9;
        for (int d = 0; d <= up; d++) { // 枚举要填入的数字 d
            res += dfs(i + 1, cnt1 + (d == 1 ? 1 : 0), isLimit && d == up, s, memo);
        }
        if (!isLimit) {
            memo[i][cnt1] = res; // 记忆化
        }
        return res;
    }


}
