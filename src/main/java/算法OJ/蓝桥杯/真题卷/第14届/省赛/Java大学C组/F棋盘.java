package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学C组;

import java.util.Scanner;

/**
 已AC
 */
public class F棋盘 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            //对(x1,y1)至(x2,y2)的区域的数进行取反
            int x1 = sc.nextInt() - 1, y1 = sc.nextInt() - 1, x2 = sc.nextInt() - 1, y2 = sc.nextInt() - 1;
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    arr[x][y] ^= 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        sc.close();
    }
}
