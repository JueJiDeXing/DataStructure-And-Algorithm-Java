package 算法.数学.数论.模;


/**
 {@link 算法.数学.数论.质数.欧拉函数}
 */
public class 欧拉降幂 {
    /*
    am互质: a^k mod m  =  a^( k % f(m) + f(m) ) mod m
    am不互质: a^k mod m  =  a^( k % f(m) ) mod m
    其中f(m)为欧拉函数,表示数m在[1,m]上与m互质的个数
     */

    /**
     例:
     求 2^(3^(4^(...^(2022^2023))) mod 2023
     */
    public static void main(String[] args) {
        int ans = pow(2, 3, 2023, 2023);
        System.out.println(ans);
    }

    static long gcd(long a, long b) {// 判断两数互质 gcd(a,b) ?= 1
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    /**
     pow(base,L,R,m)
     = base^(L...R) % m
     = base^(L...R % f_m + f_m ) % m
     <p>
     L...R % f_m = pow(L,L+1,R,f_m)
     */
    static int pow(int base, int L, int R, int m) {
        if (L == R) return fastPow(base, L, m);
        // 欧拉降幂
        int f_m = f(m);
        int n = pow(L, L + 1, R, f_m);
        if (gcd(base, f_m) == 1) n += f_m;
        return fastPow(base, n, m);
    }

    static int fastPow(int a, int n, int mod) {// 快速幂
        int ans = 1;
        while (n != 0) {
            if ((n & 1) == 1) ans = ans * a % mod;
            a = a * a % mod;
            n >>= 1;
        }
        return ans;
    }

    static int f(int n) {// 欧拉函数
        int res = n;
        for (int i = 2; i * i <= n; i++) {
            if (n % i != 0) continue;
            res = res / i * (i - 1);
            while (n % i == 0) n /= i;
        }
        if (n > 1) res = res * (n - 1) / n;
        return res;
    }
}
