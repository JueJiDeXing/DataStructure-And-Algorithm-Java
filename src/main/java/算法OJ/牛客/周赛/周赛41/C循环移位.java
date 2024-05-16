package 算法OJ.牛客.周赛.周赛41;

import java.util.Scanner;

/**
 已AC
 */
public class C循环移位 {
    /*
    任何一个整数,如果它的最低两位是4的倍数,则这个数是4的倍数
    s = s[0]s[1]...s[n-3]00 + s[n-2]s[n-1]
    前n-2个数一定能被4整除
     */
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = s.length();
        if (n == 1) {
            int c = s.charAt(0) - '0';
            System.out.println(c % 4 == 0 ? 0 : -1);
            return;
        }
        if (Integer.parseInt(s.substring(n - 2)) % 4 == 0) {
            System.out.println(0);
            return;
        }
        char pre = s.charAt(n - 1);
        for (int i = 0; i < n; i++) {
            char cur = s.charAt(i);
            if (Integer.parseInt("" + pre + cur) % 4 == 0) {
                System.out.println(i + 1);
                return;
            }
            pre = cur;
        }
        System.out.println(-1);
    }
}
