package 算法.数学.数论;

import 算法OJ.蓝桥杯.算法赛.小白入门赛.第5场._6方程;
import 算法OJ.蓝桥杯.算法赛.强者挑战赛.第9场._6组合数;

public class 快速幂 {
    //直接for循环乘的次数为n次
    //使用快速幂,降低乘的次数

    /**
     <h1>奇偶快速幂</h1>
     1. n为奇数 a^n = a^(n/2) * a^(n/2) * a
     1. n为偶数 a^n = a^(n/2) * a^(n/2)
     */
    public double fastPow1(double a, int n) {
        long N = n;//这里使用long的转换,因为如果传入的是Integer.MIN_VALUE直接转为正数会溢出
        if (n < 0) {//兼容负数
            a = 1 / a;
            N = -N;
        }
        if (N == 0) return 1.0;//兼容n为0的情况
        double temp = fastPow1(a, (int) (N / 2));
        if (N % 2 == 0) return temp * temp;  // 偶数个a
        return temp * temp * a;// 奇数个a，此时也可以写为if(n&1)
    }

    /**
     <h1>简化写法</h1>
     */
    public double fastPow2(double x, int n) {
        long N = n;
        return N >= 0 ? quick(x, N) : 1.0 / quick(x, -N);
    }

    private double quick(double x, long N) {
        if (N == 0) return 1.0;
        double res = quick(x, N / 2);
        return N % 2 == 0 ? res * res : res * res * x;
    }

    /**
     <h1>二进制快速幂</h1>
     使用二进制对指数拆分<br>
     例如:a^13   13转二进制为[8,4,1] 1101  a^13 = a^8 * a^4 * a
     */
    public int fastPow3(int a, int n) {
        int res = 1;  // 用res返回结果
        while (n != 0) {
            if ((n & 1) == 1) res *= a; // 如果n的最后一位是1，表示这个地方需要乘
            a *= a; // 推算乘积：a² --> (a²)² -->((a²)²)² ...
            n >>= 1;//n去掉最后一位
        }
        return res;
    }

    /**
     <h1>快速幂取模</h1>
     计算a^n mod m<br>
     模运算性质: (a[+ - *]b) mod m = ( (a mod m) [+ - *] (b mod m) ) mod m<br>
     */
    public int fastPow4(int a, int n, int m) {
        int base = a; // 不用base直接用a也行
        int res = 1;  // 用res返回结果
        while (n != 0) {
            if ((n & 1) == 1) // 如果n的最后一位是1，表示这个地方需要乘
                res = (res * base) % m;
            base = (base * base) % m; // 推算乘积：a² --> (a²)² -->((a²)²)² ...
            n >>= 1;
        }
        return res;
    }

    /**
     <h1>矩阵快速幂</h1>
     例题: <br>{@link _6方程} <br> {@link _6组合数}
     */
    public long[][] matrixPow(long[][] mat, int n) {
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
    long[][] multiply(long[][] A, long[][] B) {
        int mod = 10_0000_0007;
        int p = A[0].length;
        if (p != B.length) throw new RuntimeException("这两个矩阵无法相乘");
        int m = A.length, n = B[0].length;
        long[][] ans = new long[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < p; k++) {
                    ans[i][j] = (ans[i][j] + A[i][k] * B[k][j] % mod) % mod;
                }
            }
        }
        return ans;
    }


}
