package 算法OJ.蓝桥杯.题单.第15届省赛冲刺营;

import java.util.*;
/**
 已AC
 */
public class I_GCD王国和LCM王国 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long ans = sc.nextInt();
        for (int i = 0; i < n - 1; i++) {
            int a = sc.nextInt();
            ans = gcd(a, ans);
        }
        System.out.println(ans);
    }

    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

}
