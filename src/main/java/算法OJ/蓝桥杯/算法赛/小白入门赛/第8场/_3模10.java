package 算法OJ.蓝桥杯.算法赛.小白入门赛.第8场;

import java.math.BigInteger;
import java.util.Scanner;

/**
 已AC
 */
public class _3模10 {
    /*
    求x^p % 10
     */

    static BigInteger m4 = new BigInteger("4");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int x = sc.nextInt() % 10;//只需要看最后一位
            int m = new BigInteger(sc.next()).mod(m4).intValue();//最后一位去乘每乘4个1循环
            if (m == 0) m = 4;
            System.out.println(pow(x, m) % 10);
        }
    }

    static int pow(int x, int p) {
        int t = x;
        for (int i = 1; i < p; i++) {
            x = x * t;
        }
        return x;
    }
}
