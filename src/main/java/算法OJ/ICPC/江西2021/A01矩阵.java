package 算法OJ.ICPC.江西2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 已AC(dp好写,优化难)
 */
public class A01矩阵 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static int MOD = 998244353;
    static int[][] map = new int[501][501];
    static int n, m;


    public static void main(String[] args) throws Exception {
        n = I();
        m = I();
        int p = I(), q = I();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                map[i][j] = I();
            }
        }
        long[][] dp = new long[m + 1][n + m];
        dp[1][map[1][1]] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == 1 && j == 1) continue;
                if (map[i][j] == 1) {
                    for (int k = i + j - 1; k > 0; k--) {
                        dp[j][k] = (dp[j - 1][k - 1] + dp[j][k - 1]) % MOD;
                    }
                    dp[j][0] = 0;
                } else {
                    for (int k = i + j - 1; k >= 0; k--) {
                        dp[j][k] = (dp[j - 1][k] + dp[j][k]) % MOD;
                    }
                }
            }
        }
        long ans = 0;
        for (int i = q; i <= n + m - 1 - p; i++) {
            ans = (dp[m][i] + ans) % MOD;
        }
        System.out.println(ans);
    }
}
