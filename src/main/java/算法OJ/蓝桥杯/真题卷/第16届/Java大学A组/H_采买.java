package 算法OJ.蓝桥杯.真题卷.第16届.Java大学A组;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 20% = 4分
 */
public class H_采买 {
    /*
     数轴上: 第i个店的价格为a[i],库存为b[i],位置在c[i]    c[i] > 0 且 c递增
     你在0位置, 你需要买m个物品
     如果到达第k个店停止, 那么路程上的花费为 o * c[k]
     */
    static int n, m, o;
    static int[] b;
    static long[] a, c;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        o = sc.nextInt();
        a = new long[n];
        b = new int[n];
        c = new long[n];
        long sumB = 0;
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLong();
            b[i] = sc.nextInt();
            c[i] = sc.nextLong();
            sumB += b[i];
        }
        if (sumB < m) {
            System.out.println(-1);
            return;
        }
        memo = new HashMap[n];
        Arrays.setAll(memo, k -> new HashMap<>());
        long ans = dfs(0, m);
        System.out.println(ans + o * c[0]);
    }

    static HashMap<Integer, Long>[] memo;

    static long dfs(int i, int need) {
        if (need == 0) return 0;
        if (i == n) return Long.MAX_VALUE / 2;
        if (memo[i].containsKey(need)) return memo[i].get(need);
        long minCost = Long.MAX_VALUE / 2;
        int maxBuy = Math.min(b[i], need);
        for (int buy = 0; buy <= maxBuy; buy++) {
            long cost = buy * a[i];
            if (need - buy > 0) {
                if (i + 1 == n) continue;
                cost += dfs(i + 1, need - buy) + o * (c[i + 1] - c[i]);
            }
            minCost = Math.min(minCost, cost);
        }
        memo[i].put(need, minCost);
        return minCost;
    }
}
