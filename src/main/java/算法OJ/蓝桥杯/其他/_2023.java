package 算法OJ.蓝桥杯.其他;

import java.util.Arrays;
import java.util.Scanner;

/**
 不会
 */
public class _2023 {
    static int MOD = 998244353;

    /*
    n位数,恰好m个2023,前缀可以为0
    求有多少个这样的数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        solve2(n, m);
    }

    private static void solve2(int n, int m) {
        int max = Math.max(n / 4, m);
        int[][] dp = new int[n + 1][max + 1];//dp[i]][j]剩余i位需要放j个:
        for (int[] d : dp) {
            d[0] = 1;
        }
        //dp[i][j]=dp[i-1][j] 不放
        //dp[i][j]=dp[i-4][j-1]+1 放
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j <= max; j++) {
                if (i == 4 * j) {
                    dp[i][j] = 1;
                    continue;
                }
                if (4 * j > i) {//放不了这么多
                    break;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 4][j - 1];
                }
            }
        }
        int k = n - 4 * m;
        long ans = pow10[k] * dp[n][m];
        for (int i = m + 1; i <= max; i++) {
            ans = (ans - pow10[n - 4 * i] * dp[n][i] + MOD) % MOD;
        }
        System.out.println(ans);
    }

    static long[] pow10 = new long[10001];

    static {
        pow10[0] = 1;
        for (int i = 1; i <= 10000; i++) {
            pow10[i] = pow10[i - 1] * 10 % MOD;
        }
    }

    private static void solve1(int m, int n) {//6AC 14TLE
        memo = new int[m + 1][n + 1][5];
        for (int[][] mem : memo) {
            for (int[] me : mem) {
                Arrays.fill(me, -1);
            }
        }
        int ans = dfs(m, n, 0);
        System.out.println(ans);
    }

    //static Stack<Integer> stack = new Stack<>();
    static int[][][] memo;

    /**
     @param m      还需要的2023个数
     @param rem    剩余位数
     @param status 前面2023状态(匹配位数)
     */
    static int dfs(int m, int rem, int status) {
        if (m < 0) return 0;
        if (rem == 0 && m == 0) return 1;
        if (rem == 0) return 0;
        if (rem + status < 4 * m) return 0;
        if (memo[m][rem][status] != -1) return memo[m][rem][status];
        long ans = 0;
        for (int i : list) {
            int nextS = getNext(status, i);
            //stack.push(i);
            ans = (ans + dfs(nextS == 4 ? m - 1 : m, rem - 1, nextS == 4 ? 0 : nextS)) % MOD;
            //stack.pop();
        }
        ans = (ans + 7L * dfs(m, rem - 1, 0)) % MOD;
        return memo[m][rem][status] = (int) ans;
    }

    static int[] list = new int[]{0, 2, 3};

    /**
     null  --2-->  2  --0--> 20  --2--> 202 --3--> 2023
     */
    private static int getNext(int status, int i) {
        if (status == 0) {// 匹配0位
            if (i == 2) return 1;//2
            return 0;//?
        } else if (status == 1) {
            if (i == 0) return 2;// 20
            if (i == 2) return 1;// 22
            return 0;//2?
        } else if (status == 2) {
            if (i == 2) return 3;//202
            return 0; //20?
        } else {
            if (i == 3) return 4;//2023
            if (i == 0) return 2;//2020
            if (i == 2) return 1;//2022
            return 0;
        }
    }

}
