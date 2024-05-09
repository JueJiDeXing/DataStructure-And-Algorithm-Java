package 算法OJ.蓝桥杯.真题卷.第8届.国赛.Java大学A组;

import java.util.*;

/**
 已AC
 */
public class B希尔伯特曲线 {
    /*
    没看题,直接把空猜出来了
    交完一看过了一个测试点,还有一个测试点没过
    然后把 m * 2 - x + 1 改成 y 就过了
     */
    public static long f(int n, int x, int y) {
        if (n == 0) return 1;
        int m = 1 << (n - 1);
        if (x <= m && y <= m) {
            return f(n - 1, y, x);
        }
        if (x > m && y <= m) {
            return 3L * m * m + f(n - 1, x - m, y); //填空
        }
        if (x <= m && y > m) {
            return 1L * m * m + f(n - 1, x, y - m);
        }
        if (x > m && y > m) {
            return 2L * m * m + f(n - 1, x - m, y - m);
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();

        System.out.println(f(n, x, y));
    }
}
