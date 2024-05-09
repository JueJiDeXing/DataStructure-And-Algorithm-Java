package 算法OJ.蓝桥杯.算法赛.算法双周赛.第2场;

import java.util.*;
/**
 已AC
 */
public class _6神奇数 {
    /*
    神奇数: 前n-1位数位相加可以被最后一位整除
    不允许前导0,最后一位不允许为0

    求[L,R]上的神奇数个数,10<=L<=R<1e200
     */
    static String L, R;
    static int n;

    public static void main(String[] aaaaa) {
        Scanner sc = new Scanner(System.in);
        L = sc.next();
        R = sc.next();
        n = R.length();
        L = "0".repeat(n - L.length()) + L;
        long ans = 0;
        memo = new long[n + 1][10][10][2][2];
        for (long[][][][] mem : memo) {
            for (long[][][] me : mem) {
                for (long[][] m : me) {
                    for (long[] mmm : m) {
                        Arrays.fill(mmm, -1);
                    }
                }
            }
        }
        for (int mod = 1; mod <= 9; mod++) {
            ans = (ans + dfs(0, 0, mod, true, true)) % MOD;
        }
        System.out.println(ans);
    }

    static int MOD = 998244353;
    static long[][][][][] memo;

    /**
     @param i          当前是第i位要枚举的数位
     @param sum        前i-1位数的和
     @param mod        最后一位数
     @param isUpLimit  前面i-1位是否触达上限R
     @param isLowLimit 前面i-1位是否触达下限R
     @return 方案数
     */
    static long dfs(int i, int sum, int mod, boolean isUpLimit, boolean isLowLimit) {
        int i1 = to(isUpLimit), i2 = to(isLowLimit);
        if (memo[i][sum][mod][i1][i2] != -1) return memo[i][sum][mod][i1][i2];
        if (i == n - 1) {
            int ans = (sum % mod != 0) ||
                    (isLowLimit && mod < L.charAt(i) - '0') ||
                    (isUpLimit && mod > R.charAt(i) - '0') ? 0 : 1;
            return memo[i][sum][mod][i1][i2] = ans;
        }
        int low = isLowLimit ? L.charAt(i) - '0' : 0;
        int up = isUpLimit ? R.charAt(i) - '0' : 9;
        long ans = 0;
        for (int k = low; k <= up; k++) {
            ans = (ans + dfs(i + 1, (sum + k) % mod, mod, isUpLimit && k == up, isLowLimit && k == low)) % MOD;
        }
        return memo[i][sum][mod][i1][i2] = ans;
    }

    static int to(boolean a) {
        return a ? 1 : 0;
    }

}
