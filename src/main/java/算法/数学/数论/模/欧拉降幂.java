package 算法.数学.数论.模;

import 算法.数学.数论.质数.欧拉函数;

/**
 {@link 欧拉函数}
 */
public class 欧拉降幂 {
    /*
    a^k mod m  =  a^( k % f(m) + f(m) ) mod m
    其中f(m)为欧拉函数,表示数m在[1,m]上与m互质的个数
     */

    /**
     例:
     求 2^(3^(4^(...^(2022^2023))) mod 2023
     */
    public static void main(String[] args) {
        System.out.println(eular(23));
        f = eular(2023);
        int ans = pow(2, 3, 2023);
        System.out.println(ans);
    }

    /**
     base^(L...R) % 2023
     = base^(L...R % f + f ) % 2023
     L..R = pow(L,L+1,R)
     */
    static int pow(int base, int L, int R) {
        if (L == R) return fastPow(base, L);
        return fastPow(2, pow(L, L + 1, R) % f + f);
    }

    static int fastPow(int a, int n) {
        int ans = 1;
        while (n != 0) {
            if ((n & 1) == 1) ans = ans * a % 2023;
            a = a * a % 2023;
            n >>= 1;
        }
        return ans;
    }

    static int eular(int n) {
        int res = n;
        for (int i = 2; i * i <= n; i++) {
            if (n % i != 0) continue;
            res = res / i * (i - 1);
            while (n % i == 0) n /= i;
        }
        if (n > 1) res = res / n * (n - 1);
        return res;
    }

    static int f;

}
