package 算法.数学.容斥原理;

/**
 第 393 场周赛 Q3
 难度分:约2400
 */
public class _100267单面值组合的第K小金额 {
    /*
    给你一个整数数组 coins 表示不同面额的硬币，另给你一个整数 k 。
    你有无限量的每种面额的硬币。但是，你 不能 组合使用不同面额的硬币。
    返回使用这些硬币能制造的 第 kth 小 金额。
     */


    /**
     二分m,检查小于等于m的是否有k个
     */
    public long findKthSmallest(int[] coins, int k) {
        //二分
        int min = Integer.MAX_VALUE;
        for (int x : coins) min = Math.min(min, x);
        long left = k - 1, right = (long) min * k;//left不满足,right满足
        while (left + 1 < right) {
            long mid = (left + right) / 2;
            if (check(mid, coins, k)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    /**
     检查coins中的组合金额小于等于m的是否有k个
     */
    static boolean check(long m, int[] coins, int k) {
        long cnt = 0;
        next:
        for (int i = 1; i < (1 << coins.length); i++) {//枚举coins子集
            long lcm = 1;
            for (int j = 0; j < coins.length; j++) {
                if (((i >> j) & 1) == 1) {
                    lcm = lcm(lcm, coins[j]);
                    if (lcm > m) { // 太大了
                        continue next;
                    }
                }
            }
            long t = m / lcm;
            cnt += (Integer.bitCount(i) & 1) == 1 ? t : -t;
        }
        return cnt >= k;
    }

    static long gcd(long a, long b) {

        if (b == 0) return a;
        return gcd(b, a % b);
    }

    static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

}
