package 算法OJ.蓝桥杯.算法赛.小白入门赛.第6场;

import java.util.*;
/**
 已AC
 */
public class _4帕鲁 {
    /*
    给定一个非负整数x,
    如果x可以表达为两个连续正奇数的平方之差,输出Yes和这两个连续正奇数
    否则输出No
     */

    /**
     x = (2n+1)^2 - (2n-1)^2
     x = (4n^2 + 4n + 1) -  (4n^2 - 4n + 1)
     x = 8n
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            long x = sc.nextLong();
            if (x != 0 && x % 8 == 0) {
                System.out.println("Yes");
                long n = x / 8;
                System.out.println((2 * n - 1) + " " + (2 * n + 1));
            } else {
                System.out.println("No");
            }
        }
    }
}
