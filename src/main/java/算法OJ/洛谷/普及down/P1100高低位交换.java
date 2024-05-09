package 算法OJ.洛谷.普及down;

import java.util.Scanner;

/**
 已AC
 */
public class P1100高低位交换 {
    /*
    将给定数字的二进制前16位与后16位交换
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x = sc.nextInt();//数据虽然没爆int,但是由于符号位的原因,需要用long
        System.out.println(((x & 0xffff0000L) >> 16) | ((x & 0x0000ffff) << 16));
    }
}
