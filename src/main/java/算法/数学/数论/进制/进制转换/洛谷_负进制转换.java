package 算法.数学.数论.进制.进制转换;

import 算法OJ.洛谷.普及down.P1017_NOIP2000_提高组_进制转换;

import java.util.Scanner;
/**
 {@link P1017_NOIP2000_提高组_进制转换}
 */
public class 洛谷_负进制转换 {
    /*
    [-16,-2]负进制转换
     */
    static String map = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();//m<0
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            int r = n % m;
            n /= m;
            if (r < 0) {
                r -= m;
                n++;
            }
            sb.append(map.charAt(r));
        }
        System.out.println(sb.reverse());
    }
}
