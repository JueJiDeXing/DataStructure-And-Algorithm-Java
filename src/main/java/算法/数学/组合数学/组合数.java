package 算法.数学.组合数学;

/**
 n个元素选m个的方案数
 */
public class 组合数 {

    /**
     <h2>阶乘式</h2>
     C(n, m) = A(n,n) / A(m,m) / A(n-m,n-m)
     <p>
     有两种水果,苹果m个和香蕉n-m个,求其排列数
     <p>
     将水果全部看作同一种,有n!种方案
     再将重复的方案去除:
     对于某一特定排列, 固定香蕉位置,苹果内部有m!种排列,同理,香蕉内部有n-m!种排列
     根据乘法原理, 重复数量为 m!(n-m)!种
     即: n! / m!(n-m)!

     <h2>递推式</h2>
     C(n, m) = C(n - 1, m) + C(n - 1, m - 1)
     <p>
     每个项有选和不选两种方案:
     如果不选, 剩余n-1个数,需要选m个, C(n-1,m)
     如果选, 剩余n-1个数,需要选m-1个, C(n-1,m-1)
     */
    public long C(int n, int m) {
        if (m > n || n < 0) return 0;
        if (n == m) return 1;
        return C(n - 1, m) + C(n - 1, m - 1);
    }

    public long C2(int n, int m) {
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) dp[i][1] = i;
        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= m; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
            }
        }
        return dp[n][m];
    }

    public long C3(int n, int m) {
        int[] dp = new int[m + 1];
        dp[0] = 1;
        for (int i = 0; i <= n; i++) {
            for (int j = Math.min(i, m); j > 0; j--) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[m];
    }


    /**
     <h1>模组合数</h1>
     C(n,m)%MOD
     组合数C(n,m) = n! / ((n-m)! m!)
     */
    public int C4(int n, int m) {
        return fact[n] * invFact[m] % MOD * invFact[n - m] % MOD;
    }

    int N = 1000001, MOD = 998244353;
    int[] fact = new int[N], invFact = new int[N];//fac[i]=i!, invFact[i]为i!在模p下的逆元

    /**
     取模性质对除法不适用,所以需要逆元,用乘法代替除法
     预处理出阶乘数组和乘法逆元数组
     */
    void init() {
        for (int i = 1; i < N; i++) {//求阶乘
            fact[i] = fact[i - 1] * i % MOD;
        }
        invFact[N - 1] = fastPow(fact[N - 1]);
        for (int i = N - 1; i > 0; i--) {//倒序递推求逆元
            invFact[i - 1] = invFact[i] * i % MOD;
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
