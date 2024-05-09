package 算法OJ.洛谷.普及down;

import java.util.Scanner;

/**
 已AC
 */
public class P1017_NOIP2000_提高组_进制转换 {
    static String map = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /*
    输入十进制数n(可能为负),将其转换为一个负进制m(一定为负)
     */

    /**
     n = k * m + r
     如果r<0:
     n = (k+1) * m + (r-m)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int tempN = n;
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
        System.out.println(tempN + "=" + sb.reverse() + "(base" + m + ")");
    }

}
