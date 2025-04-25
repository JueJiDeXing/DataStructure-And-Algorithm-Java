package 算法.数学.数论.模.例题;

import java.util.Scanner;

public class 蓝桥_代码知音 {
    /*
    T<1e5次询问
    每次给定L,R
    问,L<=a<b<=R的数对(a,b),满足a^b = b^a (mod 8) 有多少个
     */
    /*
    1. a和b奇偶性相同

    若 a为奇数, b为偶数, a^b为奇数,b^a为偶数, 模8不同余
    反之同理

    2. 若a为奇数, 则 a = b (mod 8)

    1^1 = 1^3 = 1^5 = ... = 1 (mod 8)
    3^1 = 3^3 = 3^5 = ... = 3 (mod 8)
    5^1 = 5^3 = 5^5 = ... = 5 (mod 8)
    ...
    (2k+1)^(2t+1) = (2k+1) (mod 8)

    3. 若a为偶数

    令 a=2k1, b=2k2

    a^b = (2k1)^(2k2) = (4k1^2)^k2

    若 k1>=2, 因为k2>k1, 则 a^b = b^a = 0 (mod 8), b可以是任意大于a的偶数
    若 k1==1: a=2,
       2^(2k2) = (2k2)^2
       4^k2 = 4k2^2
       则 k2^2 是4的倍数, k2是2的倍数
       所以b是4的倍数


    总结:
    a是奇数: b是大于a的、与a模8同余的奇数
    a==2: b是4的倍数
    a是大于2的偶数: b是大于a的偶数
    */

    static long mod = (long) 1e9 + 7;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        long L, R, l, r, d, ans;
        for (int i = 0; i < T; i++) {
            L = scan.nextLong();
            R = scan.nextLong();
            ans = 0;
            // a奇
            for (int t = 1; t <= 7; t += 2) {
                // 1 9 17 25 ...
                l = (L - t + 7) / 8;
                r = (R - t) / 8;
                d = (r - l + 1) % mod;
                ans = (ans + d * (d - 1) / 2 % mod) % mod;
            }
            // a=2
            if (L <= 2) {
                l = (L + 3) / 4;
                r = R / 4;
                d = (r - l + 1) % mod;
                ans = (ans + d) % mod;
            }
            // a>2
            L = Math.max(4, L);
            l = (L + 1) / 2;
            r = R / 2;
            d = (r - l + 1) % mod;
            ans = (ans + d * (d - 1) / 2 % mod) % mod;

            System.out.println(ans);
        }
        scan.close();
    }


}
