package 算法OJ.蓝桥杯.真题卷.第16届.Java大学A组;

import java.io.PrintStream;
import java.util.Scanner;

/**
 AC
 10分
 */
public class C_乘以bitCount {
    static PrintStream out = System.out;

    /*
    给定数组a, 对每个元素做m次操作, a[i] *= bitCount(a[i])
     */
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner( System.in);
        int n = sc.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i] *= Long.bitCount(a[i]);
            }
        }
        for (int i = 0; i < n; i++) {
            out.print(a[i] + " ");
        }
    }
}
