package 算法.数学.数论.模.乘法逆元;

//
public class 组合数与乘法逆元预处理 {
    static int mod = 10_0000_0007;
    static int N = 100010;
    static int n;
    static int[] inv = new int[N], fact = new int[N];

    static int pow(int x, int n) {
        int res = 1;
        while (n > 0) {
            if ((n & 1) == 1) res = res * x % mod;
            x = x * x % mod;
            n >>= 1;
        }
        return res;
    }

    static int C(int a, int b) {
        return (fact[a] * inv[b] % mod) * inv[a - b] % mod;
    }

    static void Init() {
        fact[0] = inv[0] = 1;
        for (int i = 1; i <= n / 3; i++) {
            fact[i] = fact[i - 1] * i % mod;
            inv[i] = inv[i - 1] * pow(i, mod - 2) % mod;
        }
    }

    public static void main(String[] args) {

    }

}
