package 算法OJ.蓝桥杯.算法赛.小白入门赛.第1场;

import java.math.BigInteger;
import java.util.*;
/**
 已AC
 */
public class _4合并石子 {
    /*
    n个石子围成环,每次可以选择相邻的2堆合并,代价为2堆个数之积
    求合并为1堆的最小代价
     */

    /**
     三堆石子情况: a b c
     (1) 先合并ab: 代价为 ab + (a+b)c
     (2) 先合并bc: 代价为 bc + (b+c)a
     (3) 先合并ac: 代价为 ac + (a+c)b
     三种情况是相等的
     <p>
     对于初始的某堆石子A[k]
     它发生了x次合并,最后合并到了最后的一堆
     这个过程中它会与其他的所有石子都发生一次乘积贡献
     其总贡献与合并顺序无关
     例如:
     A[M,N]中含k,现在A[M,N]与A[P,Q]合并
     它的代价是 sum{A[M,N]}*sum{A[P,Q]}
     = sum{ A[i] * A[j] | M<=i<=N, P<=j<=Q }
     单独关注A[k]的贡献
     sum{ A[k]*A[j] | P<=j<=Q }
     每次A[k]参与合并时,就会产生乘积贡献
     因为全部的石子都会与A[k]合并一次,所以A[k]的贡献是sum{A[k]*A[j]|j!=k}
     <p>
     因为每堆石子的贡献都固定(每个乘积只计算一次哈)
     总贡献: sum( A[i]*A[j] | 1 <= i < j <= n }
     这个是n^2的算法
     换种思路:
     因为无论怎么合并都是一样的结果
     所以策略就是没有策略,从前往后一堆一堆模拟合并就是了
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        BigInteger ans = BigInteger.ZERO;
        BigInteger sum = BigInteger.valueOf(A[0]);
        for (int i = 1; i < n; i++) {
            ans = ans.add(sum.multiply(BigInteger.valueOf(A[i])));
            sum = sum.add(BigInteger.valueOf(A[i]));
        }
        System.out.println(ans);

    }

}
