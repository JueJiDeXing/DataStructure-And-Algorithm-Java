package 算法OJ.蓝桥杯.其他;

import java.util.*;

/**
 不会 2AC 2WA 14TLE
 */
public class 奇怪的数 {
    /*
    n位数字,连续5位不大于m,奇偶交错
    求方案数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int ans = 0;
        int[] pre = new int[4];
        for (int i = 1; i <= 9; i += 2) {
            pre[0] = i;
            ans = (ans + f(n, m, 1, pre));
        }
        System.out.println(ans);
    }

    static int MOD = 998244353;

    /**
     @param n    n位数字
     @param m    任意连续的5位数字之和不大于m
     @param curr 当前枚举的数位
     @param pre  前面的四个数位
     */
    public static int f(int n, int m, int curr, int[] pre) {
        if (curr == n) {
            return 1;
        }
        boolean isOdd = curr % 2 != 0;
        int sum = pre[0] + pre[1] + pre[2] + pre[3];
        int up = Math.min(9, m - sum);
        int ans = 0;
        for (int i = isOdd ? 0 : 1; i <= up; i++) {
            int[] p;
            if (curr >= 4) {
                p = new int[]{pre[1], pre[2], pre[3], i};
            } else {
                p = pre.clone();
                p[curr] = i;
            }
            ans = (ans + f(n, m, curr + 1, p)) % MOD;
        }
        return ans;
    }
}
