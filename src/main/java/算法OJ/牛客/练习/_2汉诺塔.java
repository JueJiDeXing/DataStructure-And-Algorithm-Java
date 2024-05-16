package 算法OJ.牛客.练习;

import java.util.Scanner;

/**
 没看懂题解
 */
public class _2汉诺塔 {
    /*
    汉诺塔有6种操作, 给定初始A柱上的盘个数n, 和操作权值排序, 按以下规则进行操作:
    1. 优先选择权值大的操作
    2. 不能连续操作同一个盘
    求操作次数
     */

    static long f(int x) {
        if (x == 1) return (long) ((long) 2 * Math.pow(3, n - 1) - 1);
        if (x != 0) return (long) Math.pow(2, n) - 1;
        return (long) Math.pow(3, n - 1);
    }

    static int n;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[] s = new int[10];
        for (int i = 6; i > 0; i--) {
            char[] a = sc.next().toCharArray();
            s[(a[0] - 'A') * 3 + (a[1] - 'A')] = i;
        }
        int p = 0;
        if (s[1] > s[2]) {
            if (s[5] < s[3]) {
                p = 1;
            } else if (s[6] > s[7]) {
                p = 2;
            }
        } else if (s[7] < s[6]) {
            p = 1;
        } else if (s[3] > s[5]) {
            p = 2;
        }
        System.out.println(f(p));
    }
}
