package 算法.数学.数论.莫比乌斯;

import java.util.*;

public class 二维lcm求和 {
    /*
    求 sum{ sum{ lcm(i,j) } }  1<=i<=n, 1<=j<=m

     */

    /**
     原式
     = sum{ sum{ i*j/gcd(i,j) } }  1<=i<=n, 1<=j<=m
     枚举最大公因数d
     = sum{ sum{ sum{ i*j/d } } }  1<=i<=n, 1<=j<=m, d=gcd(i,j)
     = sum{ d * sum{ sum{ [gcd(i,j)=1] i*j } } } 1<=d<=n, 1<=i<=n/d, 1<=j<=m/d
     记 F(n,m) = sum{ sum{ [gcd(i,j)=1] i*j } }  1<=i<=n, 1<=j<=m
     将[gcd(i,j)=1]写为E(gcd(i,j))
     再枚举约数
     则 F(n,m) = sum{ sum{ sum{ f(d)*i*j } } } 1<=d<=n, 1<=i<=n, 1<=j<=m, d整除i和j
     设i=i`*d,j=j`*d
     = sum{ f(d) * d^2 * sum{ sum{ i*j } } } 1<=d<=n, 1<=i<=n/d, 1<=j<=m/d
     前半段可以预处理前缀和, 后半段可以O(1)求解
     记G(n,m)=sum{i*j} 1<=i<=n, 1<=j<=m
     = n*(n+1)*m*(m+1)/4
     所以 F(n,m) = sum{ f(d)*d^2 *G(n/d,m/d) } 1<=d<=n
     则原式 = sum{ d * F(n/d,m/d) } 1<=d<=n
     <p>
     即 sum{ lcm(i,j) } -> sum{ d*F(n/d,m/d) } 1<=d<=n
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        long ans = solve(n, m);
        System.out.println(ans);

    }


    static int N = 1000_0000;
    static int mod = 20101009;
    static int n, m;
    static int[] mu = new int[N + 5], p = new int[N / 10 + 5];
    static long[] sum = new long[N + 5];
    static boolean[] flg = new boolean[N + 5];

    static long Sum(int x, int y) {
        return ((long) x * (x + 1) / 2 % mod) * ((long) y * (y + 1) / 2 % mod) % mod;
    }

    static long F(int x, int y) {
        long res = 0;
        int j;
        for (int i = 1; i <= Math.min(x, y); i = j + 1) {
            j = Math.min(x / (x / i), y / (y / i));
            res = (res + (sum[j] - sum[i - 1] + mod) * Sum(x / i, y / i) % mod) % mod;  //+mod防负数
        }
        return res;
    }

    static long solve(int x, int y) {
        init();//预处理前缀和供F函数使用
        long res = 0;
        int j;
        for (int i = 1; i <= Math.min(x, y); i = j + 1) {  // 整除分块处理
            j = Math.min(x / (x / i), y / (y / i));
            res = (res + (long) (j - i + 1) * (i + j) / 2 % mod * F(x / i, y / i) % mod) % mod;  // ！每步取模防爆
        }
        return res;
    }

    static void init() {
        mu[1] = 1;
        int tot = 0, k = Math.min(n, m);
        for (int i = 2; i <= k; ++i) {// 线性筛
            if (!flg[i]) {
                p[++tot] = i;
                mu[i] = -1;
            }
            for (int j = 1; j <= tot; ++j) {
                int mul = i * p[j];
                if (mul > k) break;
                flg[mul] = true;
                mu[mul] = -mu[i];
                if (i % p[j] == 0) {
                    mu[mul] = 0;
                    break;
                }
            }
        }
        for (int i = 1; i <= k; ++i) {
            sum[i] = (sum[i - 1] + (long) i * i % mod * (mu[i] + mod)) % mod;
        }
    }


}
