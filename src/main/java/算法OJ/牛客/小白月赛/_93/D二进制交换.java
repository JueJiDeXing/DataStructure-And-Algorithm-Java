package 算法OJ.牛客.小白月赛._93;

import java.util.Scanner;

/**
 已AC
 */
public class D二进制交换 {
    /*
    有2^n个数字 0 1 2...2^n-1
    对[l,r]的操作如下:
    1. 将[l,r]编号为[0,1,...r-l]
    2. 将偶数编号放在区间左边,奇数编号放在区间右边
    3. 左右再递归操作,直到区间长为1
    现在对区间[0,2^n)操作一次, 求最后的数字(m个询问)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            long x = sc.nextLong();
            long ans = 0;
            for (int k = 0; k < n; k++) {
                long b = x % 2;
                x /= 2;
                ans += b * (1L << (n - k - 1));
            }
            System.out.println(ans);
        }
    }


}
