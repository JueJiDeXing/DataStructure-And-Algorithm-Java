package 算法OJ.蓝桥杯.题单.第15届第2期模拟;

import java.util.Scanner;
/**
 已AC
 */
public class I循环位移 {
    /*
    给出一个6位正整数x,将x循环左移一位输出
     */
    public static void main(String[] args) {
        int x = new Scanner(System.in).nextInt();
        int pre = x / 100000;
        int suf = x % 100000;
        System.out.println(suf * 10 + pre);
    }
}
