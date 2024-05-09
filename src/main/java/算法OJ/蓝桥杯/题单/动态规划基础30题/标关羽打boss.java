package 算法OJ.蓝桥杯.题单.动态规划基础30题;

import java.util.*;
/**
 已AC
 */
public class 标关羽打boss {
    /*
    有a张杀,每张可造成1点伤害
    boss有b点血,如果受到1点伤害则有50%的概率损失1张手牌
    求击杀boss的概率
     */

    /**
     (b,a)  --50%-->  (b-1,a-1)
     --50%--> (b-1,a-2)
     dp[i][j]:i点血,j张牌的击杀概率
     dp[i][j] = ( dp[i-1][j-1]  + dp[i-1][j-2] ) * inv2
     dp[j] = ( dp[j-1]  + dp[j-2] ) * inv2
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt(), b = sc.nextInt();
        long[] dp = new long[a + 1];//dp[i][j]:i点血,j张牌的击杀概率;  dp[j]:j张牌的击杀概率
        Arrays.fill(dp, 1);//1点血时,只需要有牌就为1
        dp[0] = 0;//1点血,没有牌,击杀不了
        for (int i = 2; i <= b; i++) {//i点血
            for (int j = a; j >= 2; j--) {//j张牌,对于dp[j], = ( dp[j-1]  + dp[j-2] ) * inv2
                dp[j] = avg(dp[j - 1], dp[j - 2]);
            }
            dp[1] = 0;//对于dp[1], i大于等于2点血,1张牌击杀不了,为0
        }
        System.out.println(dp[a]);
    }

    static long avg(long a, long b) {
        return (a + b) * inv2 % MOD;
    }

    static int MOD = 998244353;
    static long inv2 = 1;//2的乘法逆元

    static {
        long n = MOD - 2;
        long x = 2;
        while (n != 0) {
            if ((n & 1) == 1) inv2 = inv2 * x % MOD;
            x = x * x % MOD;
            n >>= 1;
        }
    }

}
