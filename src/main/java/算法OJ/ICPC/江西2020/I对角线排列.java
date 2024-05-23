package 算法OJ.ICPC.江西2020;

import java.util.Scanner;
/**
 已AC(简单,算就行了)
 */
public class I对角线排列 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong(), x = sc.nextLong(), y = sc.nextLong();
        long line = x + y; // 在第几个对角线上
        if (line < n) { // 上半区
            long cnt = line * (line + 1) / 2; // 左上区的个数
            System.out.println(cnt + x); // x: 这条线上方的个数
        } else { // 下半区
            long cnt = (2 * n - line - 2) * (2 * n - line - 1) / 2; // 右下区的个数
            long last = n * n - 1;// 最后一个数
            System.out.println(last - cnt - (n - x - 1)); // n - x - 1: 这条线下方的个数
        }

    }


}
