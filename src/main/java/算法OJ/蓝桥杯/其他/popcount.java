package 算法OJ.蓝桥杯.其他;

import java.util.Scanner;
/*
未解决
 */
public class popcount {
    /*
    popcount(i)表示二进制下,i的1个数

    给定L,R,p
    求x∈[L,R],  popcount(x)之积 (mod p)
     */

    /*
    此处为数位dp做法,但是超时了

    应该使用贡献法
     */
    static String L, R;
    static long p;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long l = sc.nextLong(), r = sc.nextLong();
        p = sc.nextLong();

        L = Long.toBinaryString(l);
        R = Long.toBinaryString(r);
        L = "0".repeat(R.length() - L.length()) + L;

        int len = L.length();
        int[] cnt = new int[len];
        for (int i = 2; i < len; i++) {// popcount(x)=i的x个数
            cnt[i] = dfs(0, true, true, i);
        }

        long ans = 1;
        for (int i = 2; i < len; i++) {// popcount = i 的数有cnt[i]个
            ans = ans * pow(i, cnt[i]) % p;
        }
        System.out.println(ans);
    }

    static int dfs(int i, boolean isLimitLow, boolean isLimitHigh, int cnt) {
        if (i == L.length()) {
            return cnt == 0 ? 1 : 0;
        }
        if (cnt == 0) {
            if (isLimitLow) {
                for (int j = i; j < L.length(); j++) {
                    if (L.charAt(j) == '1') return 0;
                }
            }
            return 1;
        }
        int ans = 0;
        boolean zero = !(isLimitLow && L.charAt(i) == '1'), one = !(isLimitHigh && R.charAt(i) == '0');
        if (zero) {
            // 填0
            // 对于low: 如果前面是limit,这位填0还是limit;如果不是,这位填0仍不是
            // 对于high: 如果前面是limit,这位填0,当是1时,不是limit;是0时,是limit
            ans = (ans + dfs(i + 1, isLimitLow, isLimitHigh && R.charAt(i) == '0', cnt));
        }
        if (one && cnt > 0) {
            // 填1
            // 对于low: 如果前面是limit,这位填1,当是1时是limit;是0时不再是limit
            // 对于high: 如果前面是limit,这位填1,仍是limit
            ans = (ans + dfs(i + 1, isLimitLow && L.charAt(i) == '1', isLimitHigh, cnt - 1));
        }
        return ans;
    }
     static long pow(long x, long n) {
        long ans = 1;
        while (n != 0) {
            if ((n & 1) == 1) ans = ans * x % p;
            x = x * x % p;
            n >>= 1;
        }
        return ans;
    }
}
