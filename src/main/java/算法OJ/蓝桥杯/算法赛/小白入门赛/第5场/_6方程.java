package 算法OJ.蓝桥杯.算法赛.小白入门赛.第5场;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 已AC
 */
public class _6方程 {
    /*
    T次询问
    每次询问给出n和k
    x + 1 / x = k,求 x^n + 1 / x^n
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static int mod = 10_0000_0007;

    /**
     <pre>
     x + 1 / x = k,求 x^n + 1 / x^n
     令 f(n) = x^n + 1 / x^n
     f(n-1) * f(1) = x^n + 1/x^n + x^n-2 + 1/x^n-2
     f(n-1) * f(1) = f(n) + f(n-2)
     f(n) = f(n-1) * f(1) - f(n-2)
     f(n) = kf(n-1) - f(n-2)

     ┌                ┐  ┌          ┐     ┌               ┐
     │ f(n-1)  f(n-2) │  │  k    1  │  =  │ f(n)  f(n-1)  │
     │                │  │ -1    0  │     │               │
     └                ┘  └          ┘     └               ┘
     ┌                ┐ ┌          ┐          ┌               ┐
     │  f(2)   f(1)   │ │  k    1  │^(n-2) =  │ f(n)  f(n-1)  │
     │                │ │ -1    0  │          │               │
     └                ┘ └          ┘          └               ┘
     f(1) = k
     f(2) = (x+1/x)^2 - 2 = k^2 - 2
     </pre>
     */
    public static void main(String[] args) {
        int T = Int();
        for (int i = 0; i < T; i++) {
            int n = Int(), k = Int();
            if (n == 1) {
                System.out.println(k);
                continue;
            }
            //long f1 = k;
            long f2 = ((long) k * k - 2) % mod;
            if (n == 2) {
                System.out.println(f2);
                continue;
            }
            long[][] a = {{k, 1}, {-1, 0}};
            long[][] mat = pow(a, n - 2);
            long ans = (mat[0][0] * f2 - mat[0][1] * (long) k) % mod;
            System.out.println((ans + mod) % mod);
        }
    }

    /**
     矩阵乘法
     */
    static long[][] multiply(long[][] m1, long[][] m2) {
        int p = m1[0].length;
        if (p != m2.length) throw new RuntimeException("这两个矩阵无法相乘");
        int m = m1.length, n = m2[0].length;
        long[][] ans = new long[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < p; k++) {
                    ans[i][j] = (ans[i][j] + m1[i][k] * m2[k][j] % mod) % mod;
                }
            }
        }
        return ans;
    }

    /**
     矩阵快速幂
     */
    static long[][] pow(long[][] mat, int n) {
        int len = mat.length;
        if (len != mat[0].length) throw new RuntimeException("矩阵必须为方阵");
        long[][] res = new long[len][len];
        for (int i = 0; i < len; i++) res[i][i] = 1;
        while (n > 0) {
            if ((n & 1) == 1) res = multiply(res, mat);
            mat = multiply(mat, mat);
            n >>= 1;
        }
        return res;
    }
}
