package 算法OJ.蓝桥杯.算法赛.算法季度赛.第1场;

import java.io.*;
/**
 已AC
 */
public class _5异或与求和 {
    /*
    求 sum{ a[i1]^a[i2]+a[i3]^a[i4] | 1<=i1<i2<i3<i4<=n }
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static int MOD = 998244353;
    static long inv2 = 1;

    static {
        long x = 2, n = MOD - 2;
        while (n > 0) {
            if ((n & 1) == 1) inv2 = inv2 * x % MOD;
            x = x * x % MOD;
            n >>= 1;
        }
    }

    public static void main(String[] args) {
        int n = nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = nextInt();
        }

        long ans = 0;
        for (int i = 0; i < 32; i++) { // 计算a1与a2的异或和
            long cnt0 = 0, cnt1 = 0, curr;
            for (int j = 0; j < n; j++) {
                if (((A[j] >> i) & 1) != 0) {
                    cnt1++;
                    curr = cnt0;
                } else {
                    cnt0++;
                    curr = cnt1;
                }
                curr %= MOD;
                long res = (long) (n - j - 1) * (n - j - 2) / 2 % MOD;// a3与a4的组合数
                ans = (ans + ((curr * (1 << i)) % MOD) % MOD * res) % MOD;
            }
        }
        for (int i = 0; i < 32; i++) {
            long cnt0 = 0, cnt1 = 0, curr;
            for (int j = n - 1; j >= 0; j--) { // 计算a3与a4的异或和
                if (((A[j] >> i) & 1) != 0) {
                    cnt1++;
                    curr = cnt0;
                } else {
                    cnt0++;
                    curr = cnt1;
                }
                curr %= MOD;
                long res = (long) j * (j - 1) / 2 % MOD;// a1与a2的组合数
                ans = (ans + ((curr * (1 << i)) % MOD) % MOD * res) % MOD;
            }
        }
        System.out.println(ans);
    }

    private static void solve1(int n, int[] A) {//n^2 全部超时
        long ans = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                long count1 = (long) i * (i - 1) % MOD * inv2 % MOD;
                long count2 = (long) (n - j - 1) * (n - j - 2) % MOD * inv2 % MOD;
                ans = (ans + (A[i] ^ A[j]) * (count1 + count2) % MOD) % MOD;
            }
        }
        System.out.println(ans);
    }


}
