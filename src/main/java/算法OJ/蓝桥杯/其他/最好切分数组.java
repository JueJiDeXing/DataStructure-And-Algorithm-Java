package 算法OJ.蓝桥杯.其他;

import java.util.*;
/**
 已AC
 */
public class 最好切分数组 {
    /*
    数组分为两份,两子数组和的差的绝对值最小
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] preFix = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            preFix[i] = preFix[i - 1] + sc.nextInt();
        }
        int minDiff =  preFix[n];
        for (int i = 2; i < n  ; i++) {
            minDiff = Math.min(minDiff, Math.abs(preFix[n] - 2 * preFix[i]));
        }
        System.out.println(minDiff);
    }

}
