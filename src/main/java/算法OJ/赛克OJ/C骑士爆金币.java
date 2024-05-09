package 算法OJ.赛克OJ;

import java.util.*;

public class C骑士爆金币 {
    static int mod = 10_0000_0007;
    static int N = 100000;
    static long[] inv = new long[N + 10], fact = new long[N + 10];

    static long pow(long x, int n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1) res = res * x % mod;
            x = x * x % mod;
            n >>= 1;
        }
        return res;
    }

    static long C(int a, int b) {
        if (b == a || b == 0) return 1;
        return (fact[a] * inv[b] % mod) * inv[a - b] % mod;
    }

    static {
        fact[0] = inv[0] = 1;
        for (int i = 1; i <= N; i++) {
            fact[i] = fact[i - 1] * i % mod;
            inv[i] = inv[i - 1] * pow(i, mod - 2) % mod;
            //inv[i] = pow(fact[i], mod - 2) % mod;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long ans = 0;
        for (int r = 0; r <= n - 2; r++) {
            long re = C(n - 2, r) * n * (r + 2) % mod;
            ans = (ans + re) % mod;
        }
        System.out.println(ans);
    }

}
