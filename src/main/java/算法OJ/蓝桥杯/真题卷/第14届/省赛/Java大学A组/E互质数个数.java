package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学A组;

import java.util.Scanner;

/**
 已AC
 */
public class E互质数个数 {
    /*

    求数k在[1,k]上与k互质的个数,E(k)
    1. E(a^b) = E(a) * a^(b-1)
    2. E(k) = k * ∏(1 - 1/pi) 其中pi是k的质因子
    3. E(N) = E(N/pi) * (pi-1) 其中pi是N的某个质因子
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong(), b = sc.nextLong();// k=a^b
        System.out.println(E(a) * pow(a, b - 1) % MOD);  //E(a^b)=E(a)*a^(b-1)
    }

    static long E(long a) {  // E(a) = a * ∏(1 - 1/pi)
        long p = a;
        long res = a;
        int sq = (int) Math.sqrt(a);
        for (int i = 2; i <= sq; i++) {
            if (p % i == 0) {//i是a的质因数
                res = res - res / i;//E(a) = a * ∏(1 - 1/pi)
                while (p % i == 0) {//将a中i的倍数都筛掉
                    p /= i;
                }
            }
        }
        if (p != 1) res -= res / p;//a中大于sqrt(a)的质数
        return res;
    }

    static int MOD = 998244353;

    static long pow(long x, long n) { //x^n
        long ans = 1;
        while (n != 0) {
            if ((n & 1) == 1) ans = (ans * x) % MOD;
            x = (x * x) % MOD;
            n >>= 1;
        }
        return ans;
    }
}
