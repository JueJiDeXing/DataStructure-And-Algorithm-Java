package 算法OJ.蓝桥杯.算法赛.小白入门赛.第1场;

import java.util.*;
/**
 已AC
 */
public class _3小蓝的金牌梦 {
    /*
    求最大子数组和, 子数组长度需要是质数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] preSum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + sc.nextInt();
        }
        long ans = Long.MIN_VALUE;
        for (int len = 2; len <= n; len++) {
            if (!isPrime(len)) continue;
            for (int left = 1; left <= n; left++) {
                int right = left + len - 1;
                if (right > n) break;
                ans = Math.max(preSum[right] - preSum[left - 1], ans);
            }
        }
        System.out.println(ans);
    }

    static boolean isPrime(int n) {
        int sq = (int) Math.sqrt(n);
        for (int i = 2; i <= sq; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
