package 算法OJ.蓝桥杯.算法赛.小白入门赛.第1场;

import java.util.*;

/**
 已AC
 */
public class _2构造数字 {
    /*
    10进制N位正整数,数位和不超过M,求最大的数
     */
    //先给每位1个0,再从左到右放尽可能大的数
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();

        int k = m / 9;//可以构造k个9
        if (k >= n) {
            System.out.print("9".repeat(n));
            return;
        }
        System.out.print("9".repeat(k));
        int t = m % 9;
        int r = n - k;
        if (t != 0) {
            System.out.print(t);
            r--;
        }
        if (r > 0) System.out.print("0".repeat(r));
    }

}
