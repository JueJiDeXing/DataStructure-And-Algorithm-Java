package 算法.数学.数论.进制.进制转换;

import java.util.*;

public class 蓝桥杯_EXCEL地址转换 {
    /*
    A -> 1
    AA -> 27
    第k(从0开始)位 位权为26^k
    但是没有0
     */

    /**
     正常转26进制,但是在余数为0时需要特殊处理<br>
     余数为0时,说明是26,需要将余数置为26<br>
     并且余数置为26,相当向前一位借位,所以n需要减掉1-<br>
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int t = n % 26;
            if (t == 0) {
                n--;
                t = 26;
            }
            sb.append(toChar(t));
            n /= 26;
        }
        System.out.println(sb.reverse());
        scan.close();
    }

    static char toChar(int t) {//t:字典序,从1开始
        return (char) ((t - 1) + 'A');
    }
}
