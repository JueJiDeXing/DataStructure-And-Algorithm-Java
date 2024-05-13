package 算法OJ.牛客.小白月赛._93;

import java.util.Scanner;

/**
 已AC
 */
public class B交换数字 {
/*
a与b可以在同一位上交换数字,求min{a,b}
 */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[] a = sc.next().toCharArray();
        char[] b = sc.next().toCharArray();
        if (a[0] > b[0]) {
            char[] t = a;
            a = b;
            b = t;
        }
        long preA = a[0] - '0', preB = b[0] - '0';
        long ans = preA * preB;
        for (int i = 1; i < n; i++) {
            if (a[i] > b[i]) {
                char t = a[i];
                a[i] = b[i];
                b[i] = t;
            }
            int ta = a[i] - '0', tb = b[i] - '0';
            preA = (preA * 10 + ta) % MOD;
            preB = (preB * 10 + tb) % MOD;
            long l1 = ta * preB;
            long l2 = tb * preA;
            ans = (ans * 100 + l1 + l2 - ta * tb) % MOD;
        }
        System.out.println(ans);
    }

    static int MOD = 998244353;


}
