package 算法.数学.数学基础.线性代数;

import java.util.Arrays;

public class 矩阵乘法 {
    public static void main(String[] args) {
        double[][] m1 = new double[][]{
                {1, 2},
                {3, 4}
        };
        double[][] m2 = new double[][]{
                {5, 6},
                {7, 8}
        };
        double[][] ans = multiply(m1, m2);
        for (double[] a : ans) {
            System.out.println(Arrays.toString(a));
        }

    }

    /**
     矩阵乘法
     */
    static double[][] multiply(double[][] m1, double[][] m2) {
        int p = m1[0].length;
        if (p != m2.length) throw new RuntimeException("这两个矩阵无法相乘");
        int m = m1.length, n = m2[0].length;
        double[][] ans = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < p; k++) {
                    ans[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return ans;
    }

    /**
     矩阵乘法,取模
     */
    static long[][] multiply(long[][] m1, long[][] m2, int mod) {
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

}
