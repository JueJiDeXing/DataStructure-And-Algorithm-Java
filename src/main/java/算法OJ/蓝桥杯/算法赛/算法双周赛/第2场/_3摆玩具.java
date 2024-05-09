package 算法OJ.蓝桥杯.算法赛.算法双周赛.第2场;

import java.util.Arrays;
import java.util.Scanner;
/**
 已AC
 */
public class _3摆玩具 {

    public static void main(String[] aaaaa) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt();
        int pre = sc.nextInt();
        int[] d = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            int A = sc.nextInt();
            d[i] = A - pre;
            pre = A;
        }
        Arrays.sort(d);
        long sum = 0;
        for (int i = 0; i < n - k; i++) sum += d[i];
        System.out.println(sum);

    }
}
