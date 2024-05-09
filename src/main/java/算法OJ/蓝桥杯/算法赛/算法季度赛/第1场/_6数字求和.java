package 算法OJ.蓝桥杯.算法赛.算法季度赛.第1场;

import java.util.*;

/**
 已AC
 */
public class _6数字求和 {
    /*
    求满足数位和等于M的N位数的个数
     */
    static int MOD = 998244353;

    /**
     <h1>隔板法</h1>
     首先n位数首位不能为0,那么我们枚举首位1~9, 假设首位为x, 则现在剩下n-1位 以及 和为m-x
     那么现在要解决的问题是: n个空, [0,9]随便放, 空之和为m的方案数.
     那么我们求 0~inf 随便放,空之和为m的方案数, 再减去有空大于等于10, 空之和为m的方案数即可

     对于有i个空大于等于10的方案数:
     先选出i个空先分配10, 再对剩下的1进行隔板法C(n, i) * C(m + n - 1 - i * 10, n - 1)
     在分配i个空时, 方案有重复,它包含了1个i的方案 和 C(k,i)个k(k>i)的方案
     <pre>
     i  j->1  2  3  4  5  6
     ↓  ---------------------------
     1 |   1  2  3  4  5  6
     2 |      1  3  6  10 15
     3 |         1  4  10 20
     4 |            1  5  15
     5 |               1  6
     6 |                  1
     </pre>
     需要求的是j=1~n的方案数,系数应该为1
     在j=k时, 式子是 C(k,1), C(k,2), C(k,3), ...,C(k,k), 与二项式系数式相比只差C(k,0)项
     由二项式系数 奇数项之和 = 偶数项之和 = 2^(n-1)
     由于偶数项少了C(k,0)=1
     所以这里奇数项之和-偶数项之和恰好为1
     所以对i为奇数时进行加,偶数时进行减,即为有空大于等于10的方案数量
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        init(n, m); // 初始化阶乘逆元组合数
        long ans = 0;
        int up = Math.min(m, 9);
        for (int i = 1; i <= up; i++) {//枚举第一位(因为第一位不能为0)
            ans = (ans + count(n - 1, m - i)) % MOD;
        }
        System.out.println(ans);
    }


    static long[] fact, inv;

    static long pow(long x, long n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1) res = res * x % MOD;
            x = x * x % MOD;
            n >>= 1;
        }
        return res;
    }

    static long C(int n, int m) {  //组合数
        if (m < 0 || m > n) return 0;
        return fact[n] * inv[m] % MOD * inv[n - m] % MOD;
    }

    /**
     求n个空,每个空0~9随意放, n空之和为m的方案
     */
    static long count(int n, int m) {
        if (n == 0) return m == 0 ? 1 : 0;
        //隔板法,求0~inf随便放的方案数,因为可以为0,所以对m需要补n-1个位置
        long res = C(m + n - 1, n - 1);
        //减去方案中 有空大于等于10 的方案数量
        for (int i = 1; i <= n; i += 1) {
            //给i个空先分配10,剩余 m + n - 1 - i * 10个位置,再用隔板法求方案
            long countI = C(n, i) * C(m + n - 1 - i * 10, n - 1) % MOD;
            //在分配i个空时, 方案中包含了1个i的方案 和 C(k,i)个k(k>i)的方案
            // 对i为奇加,i为偶减
            res = (res + ((i & 1) == 1 ? -countI : countI) + MOD) % MOD;
        }
        return res;
    }

    static void init(int n, int m) {
        //求阶乘和逆元
        int M = Math.max(n, m) << 1;//组合数使用时的参数是m+n级别,所以要乘2
        fact = new long[M + 1];
        inv = new long[M + 1];
        fact[1] = 1;
        for (int i = 2; i <= M; i += 1) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        inv[M] = pow(fact[M], MOD - 2);
        for (int i = M; i > 0; i -= 1) {
            inv[i - 1] = inv[i] * i % MOD;
        }
    }

    private static void solve1(int m, int n) {
        long ans = 0;
        int up = Math.min(m, 9);
        for (int i = 1; i <= up; i++) {
            long dfs = dfs(n - 1, m - i);
            ans = (ans + dfs) % MOD;
        }
        System.out.println(ans);
    }


    static HashMap<Long, Long> map = new HashMap<>();

    static long dfs(int n, int m) {
        long key = n * 1000000L + m;
        if (map.containsKey(key)) return map.get(key);
        if (m > 9 * n) {
            map.put(key, 0L);
            return 0;
        }
        if (n == 1) {
            map.put(key, 1L);
            return 1;
        }
        long ans = 0;
        int up = Math.min(m, 9);
        for (int i = 0; i <= up; i++) {
            ans = (ans + dfs(n - 1, m - i)) % MOD;
        }
        map.put(key, ans);
        return ans;
    }
}
