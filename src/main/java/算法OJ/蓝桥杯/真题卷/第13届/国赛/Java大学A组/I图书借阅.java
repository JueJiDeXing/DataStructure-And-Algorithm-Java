package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学A组;

import java.util.Scanner;

/**
 不会
 */
public class I图书借阅 {
    /*
    有n种书,第i种书有ai本
    有m条预约借书,在[li,ri]借bi
    问,如果额外添加不超过x本书,最多满足多少条预约

     输入:
     第一行: n:书的种类数; m:预约请求; x:最多添加的书数
     第二行: n个整数,ai:第i种书的数量
     第k行: 预约请求 b:借的书的种类; l,r:借书时间范围

     输出: 最多满足的预约请求数
     */
    public static void main(String[] args) {
        main_enter();
    }

    private static void main_enter() {
        //接收数据
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), x = sc.nextInt();
        sc.nextLine();
        int[] a = new int[n];
        String[] arr = sc.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(arr[i]);
        }
        int[][] r = new int[m][3];
        for (int i = 0; i < m; i++) {
            String[] split = sc.nextLine().split(" ");
            for (int j = 0; j < 3; j++) {
                r[i][j] = Integer.parseInt(split[j]);
            }
        }
        //主逻辑
        System.out.println(solution(n, m, x, a, r));
    }

    /**
     求最多满足的预约请求数

     @param n 书种类
     @param m 预约请求数
     @param x 最多额外添加书数
     @param a a[i]=k表示第i种书现有k本
     @param r r[i]=[b,l,r]表示借第b种书,借书时间范围是[l,r]
     @return 最多满足的预约请求数
     */
    private static int solution(int n, int m, int x, int[] a, int[][] r) {
        return 0;
    }
}
