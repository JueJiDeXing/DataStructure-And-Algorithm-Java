package 算法OJ.牛客.小白月赛.小白月赛92;

import java.util.Arrays;
import java.util.Scanner;
/**
 已AC
 */
public class E烧铁矿 {
    /*
    你厌倦了探索的日子，在之前你采掘了许多矿石，现在你需要烧炼矿石。
    一枚煤炭可以在熔炉内燃烧 y 秒融化至多 x 单位的铁矿石。
    而一枚暗物质可以在熔炉内燃烧 y/2 秒融化至多 2x 单位的铁矿石。
    同一时刻，熔炉只能燃烧一枚燃料。燃料均不可重复利用。
    燃料燃烧完之前，你不可以获取熔炉中的矿物。
    你有一个神奇的魔法，可以将一枚煤炭升级成暗物质，这个魔法至多只能使用一次。
    现在你有 1 个熔炉， n 枚煤炭和 m 单位铁矿石，问烧炼 m 单位铁矿石至少需要多长时间？
         */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        // dp[i][j][use] 前i个煤,烧至少j块铁的最少时间
        // dp[i][j][use]=min{ dp[i-1][j][use], dp[i-1][j-yi][use]+yi }
        // dp[i][j][1]=min{ dp[i-1][j-2xi][0] + yi/2 }
        long[][][] dp = new long[n + 1][m + 1][2];
        int[] X = new int[n + 1], Time = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int x = sc.nextInt(), y = sc.nextInt();
            X[i] = x;
            Time[i] = y;
        }
        for (long[][] a : dp) for (long[] b : a) Arrays.fill(b, Integer.MAX_VALUE);
        dp[0][0][0] = dp[0][0][1] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                //i-1不用
                dp[i][j][0] = dp[i - 1][j][0];
                dp[i][j][1] = dp[i - 1][j][1];
                //i-1用
                int max = Math.max(0, j - X[i]);
                dp[i][j][0] = Math.min(dp[i][j][0], dp[i - 1][max][0] + Time[i]);
                dp[i][j][1] = Math.min(dp[i][j][1], dp[i - 1][max][1] + Time[i]);

                //使用魔法
                max = Math.max(0, j - X[i] * 2);
                dp[i][j][1] = Math.min(dp[i][j][1], dp[i - 1][max][0] + Time[i] / 2);

            }
        }

        System.out.println(dp[n][m][1]);
    }


}
