package 算法.数学.组合数学;

public class 组合数 {
    public static void main(String[] args) {
        组合数 test = new 组合数();
        System.out.println(test.C3(11, 5));
        System.out.println(test.C(11, 5));
    }

    /**
     C(n, m) = C(n - 1, m) + C(n - 1, m - 1)
     右式含义:对于某个项考虑选出的组合是否包含它
     */
    public long C(int n, int m) {
        if (m > n || n < 0) return 0;
        if (n == m) return 1;
        return C(n - 1, m) + C(n - 1, m - 1);
    }

    public long C2(int n, int m) {
        int[][] c = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {//C_{n}^{1}=n
            c[i][1] = i;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= m; j++) {
                c[i][j] = c[i - 1][j] + c[i - 1][j - 1];
            }
        }
        return c[n][m];
    }

    public long C3(int n, int m) {
        int[] curr = new int[m + 1];
        curr[0] = 1;
        for (int i = 0; i <= n; i++) {
            for (int j = Math.min(i, m); j > 0; j--) {//j倒序遍历,因为curr[j]需要使用curr[j-1]
                curr[j] = curr[j] + curr[j - 1];
            }
        }
        return curr[m];
    }


    /**
     <h1>模组合数</h1>
     C(n,m)%MOD
     组合数C(n,m) = n! / ((n-m)! m!)
     */
    public int C4(int n, int m) {
        return factorial[n] * inverse[m] % MOD * inverse[n - m] % MOD;
    }

    int N = 1000001, MOD = 998244353;
    int[] factorial = new int[N], inverse = new int[N];//fac[i]=i!, facinv[i]为i!在模p下的逆元

    /**
     取模性质对除法不适用,所以需要逆元,用乘法代替除法
     预处理出阶乘数组和乘法逆元数组
     */
    void init(int n) {
        for (int i = 1; i <= 2 * n; i++) {//求阶乘
            factorial[i] = factorial[i - 1] * i % MOD;
        }
        inverse[2 * n] = fastPow(factorial[2 * n]);
        for (int i = 2 * n; i > 0; i--) {//倒序递推求逆元
            inverse[i - 1] = inverse[i] * i % MOD;
        }
    }

    /**
     快速幂,base^(MOD-2)
     */
    int fastPow(int base) {
        int ans = 1;
        int p = MOD - 2;
        while (p > 0) {
            if ((p & 1) == 1) ans = ans * base % MOD;
            base = base * base % MOD;
            p >>= 1;
        }
        return ans;
    }
}
