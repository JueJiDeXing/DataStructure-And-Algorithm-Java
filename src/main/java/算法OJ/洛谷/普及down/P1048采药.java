package 算法OJ.洛谷.普及down;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
/**
 已AC
 */
public class P1048采药 {
    /*
    第一行 T:总共能用来采药的时间; M:草药的数量
    下面M行,每行两个整数,采该株草药的时间,草药的价值
    求T时间内,可以采到的草药最大总价值
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int T = Int(), M = Int();
        int[] time = new int[M], value = new int[M];
        for (int i = 0; i < M; i++) {
            time[i] = Int();
            value[i] = Int();
        }
        solve1(M, T, time, value);
    }

    /**
     定义dp[i][j]:到第i个草药为止,花费j时间的最大总价值,则ans=dp[M][T]
     状态转移:
     如果采i,dp[i][j]=dp[i-1][j-time[i]]+value[i] // 需要j>=time[i]
     如果不采i,dp[i][j]=dp[i-1][j]
     dp[i][j]=max{dp[i-1][j],dp[i-1][j-time[i]]+value[i]}
     初始化:
     dp[0][j]=dp[i][0]=0 // 不采草药或不花时间
     */
    private static void solve1(int M, int T, int[] time, int[] value) {
        int[][] dp = new int[M + 1][T + 1];
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= T; j++) {
                if (j >= time[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - time[i - 1]] + value[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[M][T]);
    }

    /**
     降维
     */
    private static void solve2(int M, int T, int[] time, int[] value) {
        int[] dp = new int[T + 1];//dp[i][j]:到第i个草药为止,花费j时间的最大总价值
        //如果采i,dp[i][j]=dp[i-1][j-time[i]]+value[i] // 需要j>=time[i]
        //如果不采i,dp[i][j]=dp[i-1][j]
        //dp[i][j]=max{dp[i-1][j],dp[i-1][j-time[i]]+value[i]}
        //dp[0][j]=dp[i][0]=0 // 不采草药或不花时间
        for (int i = 1; i <= M; i++) {
            for (int j = T; j >= time[i - 1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - time[i - 1]] + value[i - 1]);
            }
        }
        System.out.println(dp[T]);
    }
}
