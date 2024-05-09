package 算法OJ.蓝桥杯.算法赛.强者挑战赛.第9场;

import java.util.Scanner;

public class _6组合数 {
    /*
    给定a,b,n, 求sum{ C(n,ai+b) | 0 <= i <= (n-b)/a }
     */
    static Scanner sc = new Scanner(System.in);

    /**
     <h1>组合数转动态规划+矩阵快速幂优化dp</h1>
     <p>
     sum{ C(n,ai+b) | 0 <= i <= (n-b)/a }<br>
     = sum{ C(n,k) | 0<=k<=n && k%a==b }<br>
     问题等价于在n个球中选择k个球,k%a=b的方案数<br>
     <p><br>
     令dp[i][j]表示前i个球,选择了k个球,k%a=j的方案数<br>
     则 ans = dp[n][b]<br>
     <b>转移方程: </b><br>
     (1) dp[i][j] = dp[i-1][j] 第i个球不选<br>
     (2) dp[i][j] = dp[i-1][(j-1)%a] 第i个球选<br>
     则 dp[i][j] = dp[i - 1][j] + dp[i - 1][(j - 1) % a]<br>
     <b>初始状态:</b><br>
     dp[0][0]=1,0个球只能选择0个,余数为0<br>
     */
    public static void main(String[] args) {
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            solve2();
        }
    }

    static int MOD = 10_0000_0007;

    static void solve1() {// 30%AC 爆内存+超时
        int a = sc.nextInt(), b = sc.nextInt(), n = sc.nextInt();
        int[][] dp = new int[n + 1][a];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < a; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i - 1][(j - 1 + a) % a]) % MOD;
            }
        }
        System.out.println(dp[n][b]);
    }

    /**
     矩阵快速幂优化dp:<br>
     记F[i]为 [ f[i][0], ..., f[i][a-1] ]<br>
     则 ans = f[n][0] = F[n][0]<br>
     <p><br>
     令 F[i-1] * X = F[i]<br>
     则 F[0]*X^n=F[n]<br>
     F[0]已知: F[0] = [ f[0][0],f[0][1],...,f[0][a-1] ] = [ 1, 0, 0, ...,0]<br>
     所以求出X,再使用矩阵快速幂求X^n,即可求得F[n]<br>
     <p><br>
     根据之前的转移方程:<br>
     当 1 <= j <= a-1: f[i][j] = f[i - 1][j - 1] + f[i - 1][j]<br>
     当 j = 0 时: f[i][0] = f[i - 1][0] + f[i - 1][a - 1]<br>
     根据系数可写出X:<br>
     <pre>
     ┌                      ┐
     │  1  1  0  0  0 ... 0 │ 0
     │  0  1  1  0  0 ... 0 │ 1
     │  0  0  1  1  0 ... 0 │ 2
     │  0  0  0  1  1 ... 0 │ .
     │  0  0  0  0  1 ... 0 │ .
     │  ................. 1 │ .
     │  1  0  0  0  0 ... 1 │ a-1
     └                      ┘
     </pre>
     */
    static void solve2() {
        int a = sc.nextInt(), b = sc.nextInt();
        long n = sc.nextLong();

        long[][] X = new long[a][a];
        for (int i = 1; i < a; i++) {
            X[i][i] = 1;
            X[i - 1][i] = 1;
        }
        X[0][0] = 1;
        X[a - 1][0] = 1;
        if (a == 1) X[0][0] = 2;

        X = fastPow(X, n);
        long[][] F = new long[1][a];
        F[0][0] = 1;
        F = mul(F, X);
        System.out.println(F[0][b]);
    }

    static long[][] mul(long[][] A, long[][] B) {
        int n = A.length, m = A[0].length, p = B[0].length;
        long[][] res = new long[n][p];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < m; k++) {
                    res[i][j] = (res[i][j] + A[i][k] * B[k][j] % MOD) % MOD;
                }
            }
        }
        return res;
    }

    static long[][] fastPow(long[][] X, long n) {
        long[][] res = new long[X.length][X.length];
        for (int i = 0; i < X.length; i++) res[i][i] = 1;
        while (n > 0) {
            if ((n & 1) == 1) res = mul(res, X);
            X = mul(X, X);
            n >>= 1;
        }
        return res;
    }
}
