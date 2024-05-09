package 算法.动态规划_贪心.动态规划.数位dp;

import java.util.Arrays;

/**
 第 121 场双周赛 Q4
 难度分:2351
 */
public class _2999统计强大整数的数目 {


    /*
    给出整数start,finish,limit 和 s(字符串表示)
    强大整数:正整数x的末尾部分是s,且x中的每个数位最多是limit,则x是强大的
    求[start,finish]上强大整数的数目
     */
    public long numberOfPowerfulInt(long start, long finish, int l, String s) {
        S = s;
        limit = l;
        low = String.valueOf(start);
        high = String.valueOf(finish);
        n = high.length();
        low = repeat("0", n - low.length()) + low;
        diff = n - s.length();//前面的数位可以随便填,后面的数位只能填s

        memo = new long[n][4];
        for (long[] m : memo) Arrays.fill(m, -1);
        return dfs(0, true, true);
    }

    String repeat(String s, int n) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; ++i) ans.append(s);
        return ans.toString();
    }

    String low, high, S;
    long[][] memo;
    int diff, limit, n;

    long dfs(int i, boolean limit_low, boolean limit_high) {
        if (i == n) return 1;
        int idx = limit_low ? 1 : 0;
        idx = idx | (limit_high ? 2 : 0);

        if (memo[i][idx] != -1) return memo[i][idx];
        int down = limit_low ? low.charAt(i) - '0' : 0;
        int up = limit_high ? high.charAt(i) - '0' : 9;
        long ans = 0;
        if (i < diff) {//前面的随便枚举
            for (int d = down; d <= Math.min(up, limit); d++) {
                ans += dfs(i + 1, limit_low && d == down,
                        limit_high && d == up);
            }
        } else {//后面的必须填S[i-diff]
            int x = S.charAt(i - diff) - '0';
            if (down <= x && x <= Math.min(up, limit)) {
                ans = dfs(i + 1, limit_low && x == down,
                        limit_high && x == up);
            }
        }
        return memo[i][idx] = ans;
    }
}
