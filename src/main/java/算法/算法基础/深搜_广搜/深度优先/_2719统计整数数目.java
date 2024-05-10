package 算法.算法基础.深搜_广搜.深度优先;

import java.util.*;

public class _2719统计整数数目 {
    public static void main(String[] args) {
        _2719统计整数数目 t = new _2719统计整数数目();
        System.out.println(t.count("1", "12", 1, 8));
    }

    private static final int MOD = 1_000_000_007;

    /**
     统计 [num1,num2] 的好整数的个数
     好整数: 如果x的各位数之和位于[minSum,maxSum]则x是好整数
     */
    public int count(String num1, String num2, int minSum, int maxSum) {
        int ans = calc(num2, minSum, maxSum) - calc(num1, minSum, maxSum) + MOD; // 避免负数

        //检查num1的合法性
        int sum = 0;
        for (char c : num1.toCharArray()) sum += c - '0';
        if (minSum <= sum && sum <= maxSum) ans++; // num1 是合法的，补回来

        return ans % MOD;
    }

    /**
     返回[1,s]的好整数个数
     */
    private int calc(String s, int minSum, int maxSum) {
        int n = s.length();
        int[][] memo = new int[n][Math.min(9 * n, maxSum) + 1];
        for (int[] row : memo) Arrays.fill(row, -1);

        return dfs(0, 0, true, s.toCharArray(), minSum, maxSum, memo);
    }

    /**
     数位dp,对于字符串s,枚举1到s的所有整数

     @param currLen       当前枚举长度
     @param sum           当前枚举各位数之和
     @param isLimit       是否到达上限,前面所有位都是恰好等于字符串s的
     @param s             字符串,枚举上限
     @param minSum,maxSum 好整数上下界
     @param memo          记忆数组
     @return 返回长度为currLen, 各位数之和为sum的好整数数量
     */
    private int dfs(int currLen, int sum, boolean isLimit, char[] s, int minSum, int maxSum, int[][] memo) {
        if (sum > maxSum) { // 各位数之和已经超过上界,非法
            return 0;
        }
        if (currLen == s.length) {//枚举到完整数字
            return sum >= minSum ? 1 : 0;
        }
        if (!isLimit && memo[currLen][sum] != -1) {//不是上限,并且有记录
            return memo[currLen][sum];
        }
        //枚举数位d
        int up = isLimit ? s[currLen] - '0' : 9;
        int res = 0;
        for (int d = 0; d <= up; d++) {
            boolean limit = isLimit && (d == up);//如果上一位是limit,且这一位枚举d恰好等于s[currLen],则下一位是limit
            res = (res + dfs(currLen + 1, sum + d, limit, s, minSum, maxSum, memo)) % MOD;
        }
        if (!isLimit) {//如果不是上限,记录此次结果
            memo[currLen][sum] = res;
        }
        return res;
    }
}
