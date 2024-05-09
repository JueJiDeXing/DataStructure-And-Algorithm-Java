package 算法OJ.蓝桥杯.真题卷.第6届.省赛.Java大学A组;

import java.util.*;
/**
 已AC
 */
public class D移动距离 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt(), m = sc.nextInt(), n = sc.nextInt();
        int[] posM = cal(w, m), posN = cal(w, n);
        System.out.println(Math.abs(posM[0] - posN[0]) + Math.abs(posM[1] - posN[1]));
    }

    static int[] cal(int w, int i) {
        int t = (i - 1) / w;//i在第t+1行(t从1开始)
        int k = i % w;//i对于第t行的最小值偏移为k
        if (k == 0) k = w;
        if (t % 2 == 0) {
            //左小
            return new int[]{t, k-1};
        } else {
            return new int[]{t, w - k};
        }


    }
}
