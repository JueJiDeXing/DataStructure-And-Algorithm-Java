package 算法OJ.蓝桥杯.真题卷.第13届.省赛.Java大学A组;

import java.util.*;

/**
 已AC
 */
public class E蜂巢 {
    /*
    六边形,0:正西,1:西北,2:东北,3正东,4:东南,5:西南
        1 ╱╲ 2
       0 |  | 3
        5 ╲╱ 4
     一个点的坐标表示为(d,p,q): 从原点开始,先向d方向走p步,再向(d+2) mod 6 方向在走q步
     点的坐标表示方式不是唯一的
     现给出两点坐标,求他们的最短路径长度
     */
    static double[][] direction = new double[][]{
            {-1, 0}, {-0.5, 1}, {0.5, 1}, {1, 0}, {0.5, -1}, {-0.5, -1}
    };//横向距离与纵向距离,斜向移动会使横向距离改变0.5长度

/**
 数学坐标变换
 *斜向移动两步可以缩减一格的横向距离
 ans = 横向移动diffX-diffY/2 + 纵向移动diffY
 */
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int d1 = sc.nextInt(), p1 = sc.nextInt(), q1 = sc.nextInt();
    int d2 = sc.nextInt(), p2 = sc.nextInt(), q2 = sc.nextInt();
    double[] point1 = change(d1, p1, q1);//转换为(x,y)坐标
    double[] point2 = change(d2, p2, q2);
    double diffX = Math.abs(point1[0] - point2[0]);
    double diffY = Math.abs(point1[1] - point2[1]);
    System.out.println((int) (Math.max(0, diffX - diffY / 2) + diffY));
}

static double[] change(int d, int p, int q) {
    double x = 0, y = 0;
    double[] dir = direction[d];
    x += p * dir[0];
    y += p * dir[1];
    dir = direction[(d + 2) % 6];
    x += q * dir[0];
    y += q * dir[1];
    return new double[]{x, y};
}
}
