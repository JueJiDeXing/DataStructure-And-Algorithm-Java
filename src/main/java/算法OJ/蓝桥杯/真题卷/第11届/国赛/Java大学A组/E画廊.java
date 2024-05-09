package 算法OJ.蓝桥杯.真题卷.第11届.国赛.Java大学A组;

import 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学B组.H修路;

import java.io.*;

/**
 已AC
 */
public class E画廊 {
    /*
    左边L幅画,右边R幅画,相对起点坐标u1,u2,...uL 和 v1,v2,...vR
    画廊宽w,长d
    移动为直线
    求从起点中央经过所有点到终点中央的距离,保留两位小数
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static double inf = Double.POSITIVE_INFINITY;

    /**
    动态规划
     dp[i][j]:已走过A[0,i)和B[0,j)的最短距离,0表示现在在A[i],1表示现在在B[j]<br>
     dp[i][j][0]:<br>
     从左i-1转移: dp[i-1][j][0] + A[i] - A[i-1]<br>
     从右j-1转移: dp[i-1][j][1] + dis(B[j],A[i])<br>
     dp[i][j][1]:<br>
     从左i-1转移: dp[i][j-1][0] + dis(A[i],B[j])<br>
     从右j-1转移: dp[i][j-1][1] + B[j] - B[j-1]<br>
     dp[i][j][0] = Math.min(dp[i - 1][j][0] + A[i] - A[i - 1], dp[i - 1][j][1] + hypot(d, A[i] - B[j]))<br>
     dp[i][j][1] = Math.min(dp[i][j - 1][1] + B[j] - B[j - 1], dp[i][j - 1][0] + hypot(d, A[i] - B[j]))<br>

     初始:<br>
     dp[i][0][1] = inf;//右边没有走却到右边,不存在<br>
     dp[0][i][0] = inf;//左边没有走却到左边,不存在<br>
     dp[1][0][0] = Math.hypot(w / 2.0, A[1]);//从中央直接走到左边第一幅<br>
     dp[0][1][1] = Math.hypot(w / 2.0, B[1]);//从中央直接走到右边第一幅<br>
     dp[i][0][0] = dp[i - 1][0][0] + A[i] - A[i - 1];//只走左边<br>
     dp[0][i][1] = dp[0][i - 1][1] + B[i] - B[i - 1];//只走右边<br>

     答案:<br>
     dp[n][m]是走完全部画的最短距离<br>
     最终还要走到终点中央<br>
     min(dp[n][m][0] + hypot(w / 2.0, d - A[n]), dp[n][m][1] + hypot(w / 2.0, d - B[m]))<br>

     {@link H修路}
     */
    public static void main(String[] args) {
        int n = Int(), m = Int(), d = Int(), w = Int();
        int[] A = new int[n + 1], B = new int[m + 1];
        for (int i = 1; i <= n; i++) A[i] = Int();
        for (int i = 1; i <= m; i++) B[i] = Int();

        double[][][] dp = new double[n + 1][m + 1][2];
        for (int i = 1; i <= n; i++) {
            dp[i][0][1] = inf;//右边没有走却到右边,不存在
        }
        for (int i = 1; i <= m; i++) {
            dp[0][i][0] = inf;//左边没有走却到左边,不存在
        }
        dp[1][0][0] = Math.hypot(w / 2.0, A[1]);
        dp[0][1][1] = Math.hypot(w / 2.0, B[1]);
        for (int i = 2; i <= n; i++) {
            dp[i][0][0] = dp[i - 1][0][0] + A[i] - A[i - 1];
        }
        for (int i = 2; i <= m; i++) {
            dp[0][i][1] = dp[0][i - 1][1] + B[i] - B[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j][0] = Math.min(dp[i - 1][j][0] + A[i] - A[i - 1], dp[i - 1][j][1] + Math.hypot(w, A[i] - B[j]));
                dp[i][j][1] = Math.min(dp[i][j - 1][1] + B[j] - B[j - 1], dp[i][j - 1][0] + Math.hypot(w, A[i] - B[j]));
            }
        }
        double ans = Math.min(dp[n][m][0] + Math.hypot(w / 2.0, d - A[n]), dp[n][m][1] + Math.hypot(w / 2.0, d - B[m]));
        System.out.printf("%.2f", ans);
    }
}
