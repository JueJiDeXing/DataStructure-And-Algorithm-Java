package 算法OJ.蓝桥杯.其他;

import java.util.*;

/**
 写不来
 */
public class 强化准备运气版本 {

    /*
    当前为0级装备,需要强化到n级
    1. 花1元升一级
    2. 花1元直接升到n级, 成功率为pi, 失败则退回到ai级
     */
    static class P {
        int p, a;

        public P(int p, int a) {
            this.p = p;
            this.a = a;
        }
    }

    /**
     dp[i]:从 i级 升到 n级 的期望花费
     (1) dp[i] = dp[i+1] + 1   花1元先升到 i+1级
     (2) dp[i] = pi + (1-pi) * dp[ai]   尝试直接升到 n级
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            int p = sc.nextInt(), a = sc.nextInt();
            ps[i] = new P(p, a);
        }
        System.out.println(n);
    }
}
