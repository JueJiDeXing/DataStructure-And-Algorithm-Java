package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学B组;

import java.util.*;

/**
 已AC
 */
public class C数组分割 {
    /*
    分配数组为两堆数,堆中数之和为偶数
    求分配方案
     */
    static int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] ans = new int[T];
        int[] pow = new int[1001];
        pow[0] = 1;
        for (int i = 1; i < 1001; i++) {
            pow[i] = (pow[i - 1] * 2) % MOD;
        }
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int countEven = 0;//偶数个数
            for (int j = 0; j < n; j++) {
                if (sc.nextInt() % 2 == 0) countEven++;
            }
            if ((n - countEven) % 2 == 0) {
                ans[i] = pow[countEven + (countEven == n ? 0 : n - countEven - 1)] % MOD;
            }
        }
        for (int e : ans) System.out.println(e);
    }

}
