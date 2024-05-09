package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学A组;

import java.util.*;

/**
 已AC
 */
public class D棋盘 {
    public static void main(String[] args) {
        main_enter2();
    }

    private static void main_enter1() {
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

    static int[][] mp;


    private static void main_enter2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        mp = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            int x1 = sc.nextInt() - 1, y1 = sc.nextInt() - 1, x2 = sc.nextInt() - 1, y2 = sc.nextInt() - 1;
            add(x1, y1, x2, y2);//对(x1,y1)至(x2,y2)的区域的数进行加1
        }
        for (int[] ints : mp) System.out.println(Arrays.toString(ints));
        pre_sum(n);//求前缀和还原差分数组
        //打印结果
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print((mp[i][j] & 1) == 0 ? 0 : 1);
            }
            System.out.println();
        }
    }

    private static void pre_sum(int n) {
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                mp[i][j] += mp[i][j - 1] + mp[i - 1][j] - mp[i - 1][j - 1];
            }
        }
    }


    private static void add(int x1, int y1, int x2, int y2) {
        //差分二维数组,对原二维数组一个矩形区域进行+1处理 可转为 对差分二维数组的四个角进行处理
        mp[x1][y1]++;
        mp[x1][y2 + 1]++;
        mp[x2 + 1][y1]++;
        mp[x2 + 1][y2 + 1]++;
    }
}
