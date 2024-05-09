package 算法OJ.蓝桥杯.真题卷.第13届.省赛.Java大学A组;

import java.util.Scanner;

/**
 已AC
 */
public class DGCD {
    /*
    给定正整数a和b,求gcd(a+k,b+k)最大时k的值,如果有多个k,输出最小的一个k
     */

    /**
     gcd(a+k,b+k)=gcd(a-b,b+k)
     */
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    long a = sc.nextLong(), b = sc.nextLong();
    if (b > a) {//交换ab,保证a>=b
        long t = a;
        a = b;
        b = t;
    }
    long diff = a - b; // 求 (b+k) % diff == 0 的最小k
    int t = (int) (b / diff + 1);// 求 diff * t > b 的最小t, 则 k=diff*t-b
    System.out.println(diff * t - b);
}
}
