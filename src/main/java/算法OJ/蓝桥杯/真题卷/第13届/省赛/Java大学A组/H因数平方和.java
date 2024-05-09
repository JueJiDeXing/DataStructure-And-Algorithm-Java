package 算法OJ.蓝桥杯.真题卷.第13届.省赛.Java大学A组;

import java.util.Scanner;

/**
 已AC
 */
public class H因数平方和 {
    /*
    f(x) = x所有因数的平方和
    f(12) = 1^2 + 2^2 + 3^2 + 4^2 + 6^2 + 12^2
    给定一个正整数n, 求 Sum(f(i)) mod 1e+7 , 其中1<=i<=n, n<=1e9
     */


    /**
     Sum( floor(n/i) * i^2 )
     Sum(i^2) i∈[l,r] 是可以O(1)求解的
     根据数论分块知识:
     Sum( floor(n/i) * i^2 ) i∈[1,n]
     = Sum( floor(n/l) * ( s(r)-s(l-1) ) ) )
     其中:
     l∈[1,n]且l每次迭代为r+1 // 分块
     r=floor(n/floor(n/l)) // 块的右端点
     s(i)为i^2的前缀和
     */
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    long ans = 0;
    for (int l = 1; l <= n; ) {//处理所有块[l,r]
        int x = n / l, r = n / (n / l);
        //x = floor(n/l) ; s(r)-s(l-1) = sum(i^2) l<=i<=r
        ans = ((ans + x * (s(r) - s(l - 1))) % mod + mod) % mod;
        l = r + 1;
    }
    System.out.println(ans);
}

static int mod = 10_0000_0007;
static final int INV = 166666668;//6的乘法逆元

/**
 sum(i^2)  1<=i<=n
 */
static long s(long n) {
    return n * (n + 1) % mod * (2 * n + 1) % mod * INV % mod;
}


    private static void solve1(int n) {// 3AC 7TLE
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = (ans + ((long) n / i) * i * i) % mod;
        }
        System.out.println(ans);
    }
}
