package 算法.动态规划.题集.背包问题;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 已AC
 */
public class _3180执行操作可获得的最大总奖励 {
    /*
    给你一个整数数组 rewardValues，长度为 n，代表奖励的值。

    最初，你的总奖励 x 为 0，所有下标都是 未标记 的。你可以执行以下操作 任意次 ：

    从区间 [0, n - 1] 中选择一个 未标记 的下标 i。
    如果 rewardValues[i] 大于 你当前的总奖励 x，则将 rewardValues[i] 加到 x 上（即 x = x + rewardValues[i]），并 标记 下标 i。
    以整数形式返回执行最优操作能够获得的 最大 总奖励。
     */
    // 从一个升序数组中选择一个序列, 序列满足 sum{a[0~i]} < a[i+1]
    public int maxTotalReward(int[] rewardValues) {
        Arrays.sort(rewardValues);
        int m = rewardValues[rewardValues.length - 1];
        // dp[i]: 能否选出和为i
        boolean[] dp = new boolean[2 * m];// 最大2m-1
        dp[0] = true;
        for (int x : rewardValues) {
            // 选择x的条件: x大于当前总奖励
            // 设之前的总奖励为k-x, 加入x后变为k, 则 k-x>=0 && x > k-x
            for (int k = 2 * x - 1; k >= x; k--) {
                if (dp[k - x]) dp[k] = true;
            }
        }
        for (int i = dp.length - 1; i >= 0; i--) {
            if (dp[i]) return i;
        }
        return 0;
    }


    public int maxTotalReward2(int[] rewardValues) {
        int n = rewardValues.length;
        Arrays.sort(rewardValues);
        int m = rewardValues[n - 1]; // 最大值, 答案一定小于2m

        // 两数之和优化
        Set<Integer> set = new HashSet<>();
        for (int v : rewardValues) {
            if (v == m - 1) return m * 2 - 1; // 有m-1和m在, 一定选这两个就可以
            if (set.contains(v)) continue;
            if (set.contains(m - 1 - v)) return m * 2 - 1;// 有两个数能凑成m-1
            set.add(v);
        }

        /*01背包优化
        for v in sorted(set(rewardValues)):
            f |= (f & ((1 << v) - 1)) << v
        return f.bit_length() - 1
        */
        int pre = 0;
        BigInteger f = BigInteger.ONE;
        for (int v : rewardValues) {
            if (v == pre) continue;

            BigInteger mask = BigInteger.ONE.shiftLeft(v).subtract(BigInteger.ONE);
            f = f.or(f.and(mask).shiftLeft(v));
            pre = v;
        }
        return f.bitLength() - 1;
    }
}
