package 算法OJ.蓝桥杯.题单.第15届第2期模拟;

import java.util.Scanner;

/**
 已AC
 */
public class E转换次数 {
    /*
    一次转换:取这个数各位进行相乘(0除外)
    输入一个整数,求每个转换后的数,直到小于10
     */

    /**
     模拟
     */
    public static void main(String[] args) {
        long n = new Scanner(System.in).nextLong();
        if (n < 10) {
            System.out.println(n);
            return;
        }
        while (n >= 10) {
            long c = change(n);
            System.out.println(c);
            n = c;
        }
    }

    static long change(long n) {
        long mu = 1;
        while (n > 0) {
            int t = (int) (n % 10);
            if (t != 0) mu *= t;
            n /= 10;
        }
        return mu;
    }
}
