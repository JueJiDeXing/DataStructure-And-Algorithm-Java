package 算法.数学.组合数学.容斥原理;

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
     <h1>二分+容斥</h1>
     二分金额m,检查小于等于m的是否有k个
     <p>
     下界: 如果金额为1, 第k个金额为k, k-1一定不是最终答案
     上界: 如果最小值为min, 则小于等于min*k的一定有k个值
     */
    public long findKthSmallest(int[] coins, int k) {
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
     <br>
     <b>容斥</b><br>
     ① 只看其中一个值,  coins[i]的倍数 <= m, 则 有 m/coins[i]个
     <p>
     ② 但是如果一个数同时是coins[i]和coins[j]的倍数, 在①会加两遍, 需要减掉一个
     <p>
     ③ 如果一个数同时是coins中三个数的倍数, 在①中会加3次, 在②中会减C(3,2)=3次, 需要加1个
     <p>
     ....
     <br>
     <b>二项式定理</b>: (1 + x)^n = sum_{k=0}^{n} C(n,k) x^k<br>
     当x=-1时,  sum_{k=0}^{n} C(n,k) (-1)^k = 0<br>
     sum_{k=1}^{n} C(n,k) (-1)^k = -1<br>
     sum_{k=1}^{n} C(n,k) (-1)^(k+1) = 1<br>
     即: C(n,1) - C(n,2) + C(n,3) - ... = 1<br>
     奇加偶减, 恰好使同时是coins中n个数的倍数的数只被计入一次<br>
     <br>
     枚举coins的子集,求其lcm
     子集大小为奇数时,答案 +m/lcm, 否则 -m/lcm
     */
    static boolean check(long m, int[] coins, int k) {
        long cnt = 0;
        next:
        for (int i = 1; i < (1 << coins.length); i++) {//枚举coins子集
            long lcm = 1;
            for (int j = 0; j < coins.length; j++) {
                if (((i >> j) & 1) == 1) {
                    lcm = lcm(lcm, coins[j]);
                    if (lcm > m) { // 太大了, m/lcm = 0
                        continue next;
                    }
                }
            }
            long t = m / lcm;
            cnt += (Integer.bitCount(i) & 1) == 1 ? t : -t;// 奇加偶减
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
