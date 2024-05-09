package 算法.动态规划_贪心.动态规划.其他;

public class 斐波那契 {

    /*
    动态规划入门
    f(n)=f(n-1)+f(n-2)
     */
    public static int fibonacci(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static int fibonacci2(int n) {
        if (n <= 1) return 1;
        int a = 0;//降维 , 一维数组->两个变量
        int b = 1;
        for (int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    /**
     矩阵快速幂优化
     f(n)=f(n-1)+f(n-2)
     [ f(n-1)  f(n-2) ] * X = [ f(n)  f(n-1) ]
     X:
     ┌          ┐
     │  1    1  │
     │  1    0  │
     └          ┘
     [ f(2) f(1) ] * X^(n-2) = [ f(n) f(n-1) ]
     f(1)=f(2)=1
     */
    public static int fibonacci3(int n) {
        if (n <= 2) return 1;
        long[][] pow_X = matrixPow(new long[][]{{1, 1}, {1, 0}}, n - 2);
        return (int) (pow_X[0][0] + pow_X[1][0]);// 最后与[f(2) f(1)]相乘,他们是1,所以直接算第一列即可
    }

    static long[][] matrixPow(long[][] mat, int n) {
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

    /**
     矩阵乘法
     */
    static long[][] multiply(long[][] m1, long[][] m2) {
        int mod = 10_0000_0007;
        int p = m1[0].length;//m1的列数==m2的行数
        if (p != m2.length) throw new RuntimeException("这两个矩阵无法相乘");
        int row1 = m1.length, col2 = m2[0].length;
        long[][] ans = new long[row1][col2];
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col2; j++) {
                for (int k = 0; k < p; k++) {
                    ans[i][j] = (ans[i][j] + m1[i][k] * m2[k][j] % mod) % mod;
                }
            }
        }
        return ans;
    }
}
