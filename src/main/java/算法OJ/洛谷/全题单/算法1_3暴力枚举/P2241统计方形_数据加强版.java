package 算法OJ.洛谷.全题单.算法1_3暴力枚举;

import java.util.*;

/**
 已AC
 */
public class P2241统计方形_数据加强版 {
    /*
    n*m的棋盘,求正方形和长方形(不含正方形)数量
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        long tm = (long) m * (m + 1) / 2;
        long all = (long) n * (n + 1) / 2 * tm;
        //long all = 0;//求全体长方形的个数,即以某个点为左上角,它的右下角有多少个选择,把每个点的选择加起来
        //for (int i = 0; i < n; i++) {
        //    all += (n - i) * tm;//第i行,下面有n-i行,第一个点有m个选择,第2个点有m-1个选择,...
        //}

        long cnt = 0;//求正方形个数
        for (int d = 0; d < n && d < m; d++) {
            cnt += (long) (n - d) * (m - d);//边长为(d+1)的正方形有(n-d)(m-d)个
        }
        System.out.println(cnt + " " + (all - cnt));
    }
}
