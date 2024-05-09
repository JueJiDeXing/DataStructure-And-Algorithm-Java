package 算法OJ.牛客.练习赛124_PLUS;

import java.util.Scanner;

/**
 已AC
 */
public class E_括号匹配 {
    static int MAXN = 1005;
    static long INF = 1L << 60;
    static long[][] dp = new long[MAXN][MAXN], c = new long[MAXN][2];//0:'(',1:')'
    static long[] dp2 = new long[MAXN], dp3 = new long[MAXN];
    static long[] v = new long[MAXN];
    static boolean[][] vis = new boolean[MAXN][MAXN];//记忆化
    static char[] s;

    static long val(int l, int r) {
        if (l > r) return 0;
        if (((r - l) & 1) == 0) return -INF;
        if (vis[l][r]) return dp[l][r];
        vis[l][r] = true;
        dp[l][r] = val(l + 1, r - 1) + c[l][0] + c[r][1] + (v[l] * v[r]);
        for (int i = l + 1; i < r; i += 2) {
            dp[l][r] = Math.max(dp[l][r], val(l, i) + val(i + 1, r));
        }
        return dp[l][r];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        s = (" " + sc.next()).toCharArray();
        for (int i = 1; i <= n; i++) v[i] = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            int op = s[i] == '(' ? 1 : 0;
            c[i][op] = -sc.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            dp2[i] = dp3[i] = -INF;
        }
        for (int i = 1; i <= n; i++) {
            dp2[i] = Math.max(dp2[i], dp2[i - 1] + c[i][1]);
            for (int j = i - 1; j >= 1; j -= 2) {
                dp2[i] = Math.max(dp2[i], dp2[j - 1] + val(j, i));
            }
        }
        for (int i = n; i > 0; i--) {
            dp3[i] = Math.max(dp3[i], dp3[i + 1] + c[i][0]);
            for (int j = i + 1; j <= n; j += 2) {
                dp3[i] = Math.max(dp3[i], dp3[j + 1] + val(i, j));
            }
        }
        long ans = -INF;
        for (int i = 1; i <= n + 1; ++i) {
            ans = Math.max(ans, dp2[i - 1] + dp3[i]);
        }
        System.out.println(ans);
    }


}
