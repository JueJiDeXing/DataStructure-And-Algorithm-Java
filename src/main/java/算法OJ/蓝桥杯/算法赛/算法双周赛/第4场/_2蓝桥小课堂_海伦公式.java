package 算法OJ.蓝桥杯.算法赛.算法双周赛.第4场;

import java.util.Scanner;
/**
 已AC
 */
public class _2蓝桥小课堂_海伦公式 {
    /*
    S = sqrt( p(p-a)(p-b)(p-c) )
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
        if (!check(a, b, c)) {
            System.out.println(-1);
            return;
        }
        int p = (a + b + c) / 2;
        System.out.println((long) p * (p - a) * (p - b) * (p - c));
    }

    static boolean check(int a, int b, int c) {
        return a + b > c && a + c > b && b + c > a;
    }
}
