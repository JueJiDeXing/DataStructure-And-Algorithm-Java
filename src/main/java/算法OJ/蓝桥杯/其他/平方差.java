package 算法OJ.蓝桥杯.其他;

import java.util.*;
/**
 已AC
 */
public class 平方差 {
    /*
    [L,R]上有多少个 x 可以表示为两个数的平方差, x = y^2 - z^2
     */

    /**
     n^2 % 4 的值只能为0或1
     所以 x = y^2 - z^2 , x % 4 的值可以取到0,1,3
     所以 x为4的倍数 或 x为奇数
     (1) x = 2k-1
     = [k+(k-1)][k-(k-1)]
     y=k,z=k-1即可
     所以所有奇数都可以
     (2) x = 4k
     待定系数
     y + z = 4k , y - z = 1  -->  不是整数,无解
     y + z = 2k , y - z = 2  -->  y=k+1,z=k-1
     所以所有4的倍数都可以
     <p>
     [1,N]上的x个数为 floor(n/4) + ceil(n/2)
     用前缀思想求[L,R]的x个数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long L = sc.nextLong(), R = sc.nextLong();
        System.out.println(f(R) - f(L - 1));
    }

    static long f(long n) {
        return n / 4 + (n + 1) / 2;
    }

}
