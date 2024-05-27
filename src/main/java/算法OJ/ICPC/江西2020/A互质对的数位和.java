package 算法OJ.ICPC.江西2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/**
 已AC(容斥,难)
 */
public class A互质对的数位和 {
    /*
    求 sum{ sum{ F(j) | j=1->i } | i=1->n }
    其中F(j)表示j的数位和
     */

    /**
     贡献法, 计算i的贡献, 即F[i] * 数量
     数量 = i后面与i互质的数的个数 = (n-i) - i后面不与i互质的数个数
     <p>
     i后面不与i互质的数个数 可以 枚举i的质因子集
     假设枚举出的质因子集相乘为mul, 则 i~n上有 (n-i) / mul 个mul的倍数, 即有 (n-i) / mul 个数与i不互质
     但是枚举出来的是有重叠的, 需要使用容斥原理, 奇数个因子则计入"不互质", 偶数个因子则从"不互质"中减去
     */
    public static void main(String[] args) {
        long n = new Scanner(System.in).nextLong();
        init(n);// 预处理
        //对于每一个i, 找[i+1,n]里有多少个个数和它互质
        long ans = 1;// f(1)
        for (int i = 1; i < n; i++) {
            int m = p[i].size();// i的因数个数
            long cnt = 0;  // 与i不互质的数的个数
            for (int j = 1; j < (1 << m); j++) {// 枚举因数集
                long mul = 1;  //当前枚举状态的所有质因数的乘积
                for (int k = 0; k < m; k++) {
                    if ((j & (1 << k)) != 0) mul *= p[i].get(k);
                }
                long now = (n - i) / mul;  //mul的倍数个数
                int t = (Integer.bitCount(j) % 2 == 1) ? 1 : -1; //容斥,奇加偶减
                cnt += t * now;
            }
            ans += f[i] * (n - i - cnt);
        }
        System.out.println(ans);
    }

    static int N = 100010;
    static long[] f = new long[N];  //数位和
    static List<Integer> prime = new ArrayList<>();  //素数
    static List<Integer>[] p = new List[N];   // p[i]:i的质因子


    static int F(int x) {
        int sum = 0;
        while (x != 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }


    static void init(long n) {
        // 数位和
        for (int i = 1; i <= n; i++) f[i] = F(i);
        Arrays.setAll(p, i -> new ArrayList<>());
        //素数筛
        boolean[] isCom = new boolean[N];
        for (int i = 2; i < N; i++) {
            if (!isCom[i]) prime.add(i);
            for (int j = 0; j < prime.size(); j++) {
                int v = prime.get(j);
                if (i * v >= N) break;
                isCom[i * v] = true;
                if (i % v == 0) break;
            }
        }
        //质因子筛
        for (int i = 2; i <= n; i++) {
            int x = i;
            for (int j = 0; j < prime.size(); j++) {
                int v = prime.get(j);
                if (v * v > x) break;
                if (x % v == 0) {
                    p[i].add(v);
                    while (x % v == 0) x /= v;
                }
            }
            if (x != 1) p[i].add(x);
        }
    }
}
