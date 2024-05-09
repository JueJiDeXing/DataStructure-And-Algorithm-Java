package 算法OJ.蓝桥杯.真题卷.第12届.省赛.Java大学A组;

import java.util.Arrays;

/**
 已AC
 */
public class D路径 {
    /*
    有[1,2021]这些节点
    节点i与节点[i-21,i+21]中的节点相连
     如果节点i与节点j相连,i与j的无向边权为lcm(a,b)
     求1到2021的最短路径长度
     */
    public static void main(String[] args) {
        int n = 2021;
        long[] dp = new long[n + 1]; //dp[i]:i与1的最短路径长度
        Arrays.fill(dp, Long.MAX_VALUE);
        for (int i = 1; i <= 21; i++) {
            dp[i] = i;
        }
        for (int i = 22; i <= n; i++) {
            for (int j = i - 21; j < i; j++) {
                dp[i] = Math.min(dp[i], dp[j] + lcm(i, j));
            }
        }
        System.out.println(dp[n]);//10266837
    }

    static long lcm(int a, int b) {
        return (long) a * b / gcd(a, b);
    }

    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }


}
