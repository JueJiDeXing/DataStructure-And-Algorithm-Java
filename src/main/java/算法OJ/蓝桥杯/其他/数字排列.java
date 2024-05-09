package 算法OJ.蓝桥杯.其他;

import java.util.*;
/**
 已AC
 */
public class 数字排列 {
    /*
    20,19,20,18,20,19,20,17,20,19,20,18,20,19,20,16...
    规则:
    最大为20,最小为1,前面的数尽可能大
    两个相同数字中间一定要有比他们小的数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        dp = new int[21];//dp[i]:i上次出现的位置
        Arrays.fill(dp, -1);
        out:
        for (int i = 0; i < n; i++) {
            for (int k = 20; k >= 1; k--) {
                if (check(k)) {
                    System.out.print(k+" ");
                    dp[k] = i;
                    continue out;
                }
            }
        }

    }

    static int[] dp = new int[21];//dp[i]:i上次出现的位置

    static boolean check(int k) {
        int last = dp[k];
        if (last == -1) return true;//这个数字没出现过,能放
        for (int i = 1; i < k; i++) {
            if (dp[i] > last) return true;// 有比k小的数字i,出现在last后面
        }
        return false;
    }
}
