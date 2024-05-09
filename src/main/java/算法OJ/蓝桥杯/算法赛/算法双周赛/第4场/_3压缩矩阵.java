package 算法OJ.蓝桥杯.算法赛.算法双周赛.第4场;

import java.util.Scanner;
/**
 已AC
 */
public class _3压缩矩阵 {
    /*
    n*n的带状矩阵
    仅有 与A[i][i]相邻(上下左右)的元素为正值,其余均为0
    现在将0抛弃,其余数转存为一维数组
    转存方式是先按行,行中按列扫描
    B[0]=A[0][0],B[1]=A[0][1],B[2]=A[1][0]

    有q次询问
    每次给出B的下标x,求B[x]对应A的下标
      */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextLong();
        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            long x = sc.nextLong();
            if (x == 0) {
                System.out.println("1 1");
                continue;
            }
            if (x == 1) {
                System.out.println("1 2");
                continue;
            }
            long line = 1 + (x - 2) / 3;//第几行
            long offset = (x - 2) % 3;//行内相对左位置的偏移
            long col = line - 1 + offset;
            System.out.println((line + 1) + " " + (col + 1));
        }
    }
}
