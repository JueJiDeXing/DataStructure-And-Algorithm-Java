package 算法OJ.牛客.小白月赛._93;

import java.util.Scanner;

public class _5 {

    static Scanner sc = new Scanner(System.in);
    static int MOD = 998244353;

    public static void main(String[] args) {
        int n = sc.nextInt(), m = sc.nextInt();
        char[] seq = sc.next().toCharArray();
        long[][] dp = new long[n + 1][2];//dp[i][0]:前面的0到i位置的距离和; dp[i][1]同理
        long pre0 = 0, pre1 = 0;
        long cnt0 = 0, cnt1 = 0;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = pre0;
            dp[i][1] = pre1;
            if (seq[i - 1] == '0') {
                cnt0++;
            } else {
                cnt1++;
            }
            pre0 = (pre0 + cnt0) % MOD;
            pre1 = (pre1 + cnt1) % MOD;
        }
        long[] preSum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + (seq[i - 1] == '0' ? dp[i][1] : dp[i][0]) % MOD;
        }
        for (int i = 0; i < m; i++) {
            int l = sc.nextInt(), r = sc.nextInt();
            System.out.println((preSum[r] - preSum[l - 1] + MOD) % MOD);
        }
    }

}
