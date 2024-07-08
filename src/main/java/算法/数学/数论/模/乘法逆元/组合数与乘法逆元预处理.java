package 算法.数学.数论.模.乘法逆元;

//
public class 组合数与乘法逆元预处理 {
    static int MOD = 10_0000_0007;
    static int N = 100010;
    static int[] inv = new int[N], fact = new int[N];

    static int C(int a, int b) {
        return (fact[a] * inv[b] % MOD) * inv[a - b] % MOD;
    }

    static void Init(int n) {
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {//求阶乘
            fact[i] = fact[i - 1] * i % MOD;
        }
        inv[n] = pow(fact[n], MOD - 2);
        for (int i = n; i > 0; i--) {//倒序递推求逆元
            inv[i - 1] = inv[i] * i % MOD;
        }
    }

    static int pow(int x, int n) {
        int res = 1;
        while (n > 0) {
            if ((n & 1) == 1) res = res * x % MOD;
            x = x * x % MOD;
            n >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {

    }

}
