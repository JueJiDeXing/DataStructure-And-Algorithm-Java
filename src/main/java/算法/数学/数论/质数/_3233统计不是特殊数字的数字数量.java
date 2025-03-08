package 算法.数学.数论.质数;

import java.util.ArrayList;
import java.util.List;

/**
 难度:中等
 */
public class _3233统计不是特殊数字的数字数量 {
    /*
    给你两个 正整数 l 和 r。对于任何数字 x，x 的所有正因数（除了 x 本身）被称为 x 的 真因数。

    如果一个数字恰好仅有两个 真因数，则称该数字为 特殊数字。例如：

    数字 4 是 特殊数字，因为它的真因数为 1 和 2。
    数字 6 不是 特殊数字，因为它的真因数为 1、2 和 3。
    返回区间 [l, r] 内 不是 特殊数字 的数字数量。
     */

    /**
     <h1>思路</h1>
     设X是特殊数字
     显然X != 1
     则X = 1*X
     ∵ X 有除自身外的两个真因数
     ∴ X = p^2(p为质数)
     素数筛求出所有质数, 则[l,r]内的特殊数字的数量 = [l^2,r^2]内的质数平方数的数量
     = [\sqrt(l),\sqrt(r)]内质数数量 = 质数数量前缀和sum[r]-sum[l-1]
     */
    public int nonSpecialCount(int l, int r) {
        // 1. 素数筛求质数
        int N = (int) Math.sqrt(1e9 + 10);
        List<Integer> prims = new ArrayList<>();
        boolean[] notPrime = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            if (!notPrime[i]) prims.add(i);
            for (int j = 0; j < prims.size(); j++) {
                int p = prims.get(j);
                int mul = p * i;
                if (mul > N) break;
                notPrime[mul] = true;
                if (i % p == 0) break;
            }
        }
        // 2. 质数数量前缀和
        // (因为前面已经有不是质数的bool数组了,所以这里也求不是质数的数量前缀和)
        int[] notPrimeCnt = new int[N + 1];// 前缀和:notPrimeCnt[i]=\sum_{k=1}^{k=i}{notPrime[k]}
        notPrimeCnt[1] = 1;
        for (int i = 2; i <= N; i++) {
            notPrimeCnt[i] = notPrimeCnt[i - 1] + (notPrime[i] ? 1 : 0);
        }
        // 差分计算数量
        int sqrt_l = (int) Math.sqrt(l - 1), sqrt_r = (int) Math.sqrt(r);
        int primeNum = (sqrt_r - sqrt_l) - (notPrimeCnt[sqrt_r] - notPrimeCnt[sqrt_l]);
        return r - l + 1 - primeNum;
    }
}
