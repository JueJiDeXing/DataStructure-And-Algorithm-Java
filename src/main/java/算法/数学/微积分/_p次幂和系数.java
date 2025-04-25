package 算法.数学.微积分;

import java.util.Scanner;

public class _p次幂和系数 {
    /*
    给定 p 和 分数处理模数M
    求 1^p + 2^p + ... + n^p 对n的展开式系数

    例:
    p = 1
    1+2+...+n = n/2 + n^2/2
    n的1次系数为1/2, 2次系数为1/2
    输出inv(2,M) inv(2,M)

    p = 2
    1^2+2^2+...+n^2 = n/6 + n^2/2 + n^3/3
    n的1次系数为1/6, 2次系数为1/2, 3次系数为1/3
    输出inv(6,M) inv(2,M) inv(3,M)
     */
    /**
     ①将求和式推广到实数域
     定义连续函数f(p,n), f(p,0)=0, f(p,n)=f(p,n-1)+n^p
     <p>
     ②根据关于n的递推式求关于p的递推式
     f(p,n)=f(p,n-1)+n^p
     对两边同时积分：
     ∫_{0}^{n}f(p,t)dt=∫_{0}^{n}f(p,t-1)dt+∫_{0}^{n}t^pdt
     化简、移项、凑项:
     (p+1)[∫_{0}^{n}f(p,t)dt-n∫_{-1}^{0}f(p,t)]=(p+1)[∫_{0}^{n-1}f(p,t)dt-(n-1)∫_{-1}^{0}f(p,t)]+n^{p+1}
     <p>
     令 g(n) = (p+1)[∫_{0}^{n}f(p,t)dt-n∫_{-1}^{0}f(p,t)]
     则上式为 g(n) = g(n-1) +n^(p-1)
     对比f(p,n)的递推式, 可得 f(p+1,n)=g(n)
     所以 f(p+1,n) = (p+1)[∫_{0}^{n}f(p,t)dt-n∫_{-1}^{0}f(p,t)dt]
     <p>
     ③证明f(p,n)为p+1次的无常数项多项式
     数学归纳法,p=0时成立,p=k时可推出p=k+1时成立
     <p>
     ④根据函数递推式求系数递推式
     <p>
     由f(1,n)=n^2 / 2  +  n / 2
     可得a[1][1]=1/2, a[1][2]=1/2
     <p>
     根据③: f(p,n)=\sum_{i=1}^{p+1}{a[p][i]n^i}
     由f(p+1,n)=(p+1)[∫_{0}{n}f(p,t)dt-n∫_{-1}{0}f(p,t)dt]
     稍作变形：
     \sum_{i=0}^{p+1}{a[p+1][i+1]n^{i+1}}
     =\sum_{i=1}^{p+1}{\frac{p+1}{i+1}a[p][i]n^{i+1}}
     + n\sum_{i=1}^{p+1}{\frac{p+1}{i+1}a[p][i](-1)^{i+1}}
     <p>
     比对系数:
     对于i≥2的n^i^系数，有 a[p+1][i+1]=\frac{p+1}{q+1}a[p][i]
     <p>
     对于n的系数：
     f(p+1,1) = 1^{p+1} = 1
     f(p+1,1)=\sum_{i=1}^{p+2}{a[p+1][i] * 1^i} = \sum_{i=0}^{p+1}{a[p+1][i+1]}
     则 \sum_{i=0}^{p+1}{a[p+1][i+1]}=1
     抽出i=0的一项: a[p+1][1]+\sum_{i=1}^{p+1}{a[p+1][i+1]}=1
     可得：n的各次项系数之和为1
     所以对于1次项系数，只需要先算出其他系数，用1去减即可
     */
    static long M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int p = sc.nextInt();
        M = sc.nextLong();
        // 预处理乘法逆元
        long[] inv = new long[p + 2];
        inv[1] = 1;
        inv[2] = 500000004; // 2的乘法逆元,用快速幂,long型,mod=1e9+7的情况下恰好会会变成0
        for (int i = 3; i <= p + 1; i++) {
            inv[i] = inv(i);
        }

        long[][] dp = new long[p + 1][p + 2];
        dp[1][1] = inv[2];
        dp[1][2] = inv[2];
        for (int i = 2; i <= p; i++) {
            // dp[p][q] = dp[p-1][q-1]*p/q   1 < q <= p
            for (int j = 2; j <= i + 1; j++) {
                dp[i][j] = dp[i - 1][j - 1] * i % M * inv[j] % M;
            }
            // dp[p][1] = 1 - sum{ dp[p][q] | q>=2 }
            long sum = 0;
            for (int j = 2; j <= i + 1; j++) {
                sum = (sum + dp[i][j]) % M;
            }
            dp[i][1] = (1 - sum + M) % M;
        }

        for (int j = 1; j <= p + 1; j++) {
            System.out.print(dp[p][j] + " ");
        }
    }

    static long inv(long n) {
        return pow(n, M - 2);
    }

    static long pow(long x, long n) {
        x %= M;
        long ans = 1;
        while (n != 0) {
            if ((n & 1) == 1) ans = ans * x % M;
            x = x * x % M;
            n >>= 1;
        }
        return ans;
    }
}
