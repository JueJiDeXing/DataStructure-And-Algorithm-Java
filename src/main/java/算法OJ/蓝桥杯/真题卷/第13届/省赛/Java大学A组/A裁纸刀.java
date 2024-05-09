package 算法OJ.蓝桥杯.真题卷.第13届.省赛.Java大学A组;

import java.util.Arrays;

/**
 已AC
 */
public class A裁纸刀 {
public static void main(String[] args) {
    for (int i = 0; i < 25; i++) {
        Arrays.fill(memo[i], -1);
    }
    System.out.println(4 + f(20, 22));// 443, 边缘需要额外的4刀
}

static int[][] memo = new int[25][25];//记忆化数组

/**
 x行y列需要裁多少刀
 */
static int f(int x, int y) {
    //一行一列,一个一个裁
    if (x == 1) return y - 1;
    if (y == 1) return x - 1;
    if (memo[x][y] != -1) return memo[x][y];
    //按行
    int min = Integer.MAX_VALUE;
    for (int i = 1; i < x; i++) {
        min = Math.min(min, 1 + f(i, y) + f(x - i, y));
    }
    //按列
    for (int i = 1; i < y; i++) {
        min = Math.min(min, 1 + f(x, i) + f(x, y - i));
    }
    return memo[x][y] = min;
}
}
