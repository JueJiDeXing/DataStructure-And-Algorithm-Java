package 算法.数学.数论.进制;

import java.util.Scanner;

public class 蓝桥15JavaA省_数字诗意 {
/*
若 x = a+a+1+..+a+k-1   (k>1)
则 x是诗意数字

给定n个数,求不是诗意数字的有多少个
 */
    /*
    若x是诗意数字
    x = a+a+1+..+a+k-1 = (2a+k-1)*k/2
    可以看到, k和2a+k-1奇偶性相反
    说明如果x是诗意数字,则x含有奇因子
    即: 2的幂一定不是诗意数字


    若x是非2的幂:

    若x是奇数:
        则 x = x/2 + (x/2+1),
        即: 奇数一定是诗意数字
    若x是偶数(含奇因子):
        令x = odd * even
        (1) 2a+k-1 = odd, k = even
            则a = (odd+1-even)/2, 当odd+1>even时一定能找到这个a
            odd+1>even 等价于 odd >= even
        (2) k = odd, 2a+k-1 = even
            则 a = (even+1-odd)/2 , 当even+1>odd时一定能找到这个a
            even+1>odd 等价于 even >= odd
        即x是含奇因子的偶数时,一定是诗意数字
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            long num = sc.nextLong();
            if ((num & (num - 1)) == 0) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}
