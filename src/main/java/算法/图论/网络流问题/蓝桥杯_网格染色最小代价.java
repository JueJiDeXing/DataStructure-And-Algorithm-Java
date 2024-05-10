package 算法.图论.网络流问题;

import java.util.Scanner;

public class 蓝桥杯_网格染色最小代价 {
    /*
    n*n的网格,有一些已经被染成了黑色
    现在需要将所有格子染成黑色
    每次染色可以染一行或一列
    第i行的染色代价为a[i]
    第i列的染色代价为b[i]
    求最小代价
     */
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] a = new int[n], b = new int[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        for (int i = 0; i < n; i++) b[i] = sc.nextInt();
        char[][] map = new char[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = sc.next().toCharArray();
        }

    }


}
