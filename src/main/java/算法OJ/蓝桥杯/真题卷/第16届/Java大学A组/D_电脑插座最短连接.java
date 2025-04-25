package 算法OJ.蓝桥杯.真题卷.第16届.Java大学A组;

import java.util.Arrays;
import java.util.Scanner;

/**
 AC
 10分
 */
public class D_电脑插座最短连接 {
    /*
    给定直线上的n个电脑和n个插座, 每个插座与电脑只能一对一连接, 求最短连接长度
     */
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] a = new long[n];
        long[] b = new long[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        for (int i = 0; i < n; i++) b[i] = sc.nextInt();
        Arrays.sort(a);
        Arrays.sort(b);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.abs(a[i] - b[i]);
        }
        System.out.println(ans);
    }

}
