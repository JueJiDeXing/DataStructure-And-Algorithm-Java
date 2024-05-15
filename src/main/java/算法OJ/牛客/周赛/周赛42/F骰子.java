package 算法OJ.牛客.周赛.周赛42;

import java.util.Scanner;

/**
 已AC
 */
public class F骰子 {
    /*
    初始p=1
    每次骰子, 1、2、3等概率, p * 这个数字
    当p第一次是x的倍数时,需要扔的次数期望是多少？
     */
    static int MOD = 10_0000_0007;
    static int inv2 = 5_0000_0004;

    /**
     x 一定需要被分解为 2^a * 3^b的形式, 如果不能,说明x里有其他因子,p不可能成为x的倍数
     dp[a][b] 表示 投a个2,b个3的期望次数
     dp[a][b] = 1 + 1/3( dp[a][b] + dp[a-1][b] + dp[a][b-1] )
     => dp[a][b] = ( 3 +  dp[a-1][b] + dp[a][b-1] ) / 2
     dp[0][b] = 3b, dp[a][0]= 3a
     */
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        long x = sc.nextLong();
        int cnt2 = 0, cnt3 = 0;
        for (; x % 2 == 0; cnt2++, x /= 2) ;
        for (; x % 3 == 0; cnt3++, x /= 3) ;
        if (x != 1) {
            System.out.println(-1);
            return;
        }

        long[][] dp = new long[cnt2 + 1][cnt3 + 1];
        for (int a = 0; a <= cnt2; a++) dp[a][0] = 3L * a % MOD;
        for (int b = 0; b <= cnt3; b++) dp[0][b] = 3L * b % MOD;

        for (int a = 1; a <= cnt2; a++) {
            for (int b = 1; b <= cnt3; b++) {
                dp[a][b] = (3 + dp[a - 1][b] + dp[a][b - 1]) * inv2 % MOD;
            }
        }
        System.out.println(dp[cnt2][cnt3]);
    }
}
