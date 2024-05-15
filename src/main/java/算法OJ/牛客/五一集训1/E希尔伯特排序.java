package 算法OJ.牛客.五一集训1;

import java.util.Arrays;
import java.util.Scanner;
/**
 已AC
 */
public class E希尔伯特排序 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        long[][] A = new long[n][2];
        for (int i = 0; i < n; i++) {
            A[i][0] = sc.nextLong();
            A[i][1] = sc.nextLong();
        }
        Arrays.sort(A, ((o1, o2) -> compare(o1, o2, k)));
        for (long[] a : A) {
            System.out.println(a[0] + " " + a[1]);
        }
    }

    /**
     k阶情况下点a和点b的前后关系

     @param a,b 点坐标[x,y], x为行轴(竖向),y为列轴(横向)
     @param k   曲线阶数
     @return -1:a在b前; 1:a在b后; 0:a=b
     */
    static int compare(long[] a, long[] b, int k) {
        // ab相同
        if (a[0] == b[0] && a[1] == b[1]) return 0;
        // ab不同
        int id1 = get(a, k), id2 = get(b, k);// 将k阶曲线分成四个区块(左上1,左下2,右下3,右上4)
        // 区块不同, 比较区块前后关系即可
        if (id1 != id2) return id1 - id2;
        // 区块相同,递归比较
        if (id1 == 1) { // 第一区块
            long[] na = new long[]{a[1], a[0]};// 看示例图, 从第k-1阶到k阶第一区块有一个转置
            long[] nb = new long[]{b[1], b[0]};
            return compare(na, nb, k - 1);
        }
        long half = 1L << (k - 1);
        if (id1 == 2) {// 第二区块
            long[] na = new long[]{a[0] - half, a[1]}; // 无转置, 将行坐标移到第一区块上即可
            long[] nb = new long[]{b[0] - half, b[1]};
            return compare(na, nb, k - 1);
        }
        if (id1 == 3) {// 第三区块
            long[] na = new long[]{a[0] - half, a[1] - half};// 无转置, 行列均移到第一区块上即可
            long[] nb = new long[]{b[0] - half, b[1] - half};
            return compare(na, nb, k - 1);
        }
        // 第四区块
        // 比较特殊, 它和第一区块是镜像的结构, 这里我把k阶终点映射做k-1阶的起点
        // 已右上角为原点, 向左的方向代替原x轴, 那么原x坐标就变为了现在的y坐标
        // 向下的方向代替原y轴, 现在的x坐标 = 右上角到当前y坐标的距离 = 2 * half - y + 1
        long[] na = new long[]{2 * half - a[1] + 1, a[0]};
        long[] nb = new long[]{2 * half - b[1] + 1, b[0]};
        return -compare(na, nb, k - 1);
    }


    static int get(long[] a, int k) {
        long half = 1L << (k - 1);
        long x = a[0], y = a[1];
        if (x <= half) {
            if (y <= half) {
                return 1;// 左上
            } else {
                return 4;// 右上
            }
        } else {
            if (y <= half) {
                return 2;// 左下
            } else {
                return 3;// 右下
            }
        }
    }
}
