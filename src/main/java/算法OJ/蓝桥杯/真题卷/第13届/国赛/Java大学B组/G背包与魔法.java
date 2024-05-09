package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学B组;

import java.util.Scanner;

/**
 已AC
 */
public class G背包与魔法 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();
        int[] w = new int[n + 1], v = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }
        /*
        dp[i][j]:装前i件物品,最大容量为j的最大总价值
        0:不使用魔法 <=> 前件使用魔法,该件不使用
        1:使用魔法 <=> 前件使用魔法,该件不使用 or 前件不使用魔法,该件使用(须达到容量条件)
        */
        int[][] dp = new int[m + 1][2];

        for (int i = 1; i <= n; i++) {
            int wi = w[i], vi = v[i];
            for (int j = m; j >= wi; j--) {//降维
                dp[j][0] = Math.max(dp[j][0], dp[j - wi][0] + vi);//前件不使用魔法,该件不使用
                dp[j][1] = Math.max(dp[j][1], dp[j - wi][1] + vi);//前件使用魔法,该件不使用
                if (j >= wi + k) {//前件不使用魔法,该件使用
                    dp[j][1] = Math.max(dp[j][1], dp[j - wi - k][0] + 2 * vi);
                }
            }
        }
        System.out.println(Math.max(dp[m][0], dp[m][1]));
    }
}
