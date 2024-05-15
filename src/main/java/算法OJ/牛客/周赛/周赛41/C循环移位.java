package 算法OJ.牛客.周赛.周赛41;

import java.math.BigInteger;
import java.util.Scanner;

/**
 已AC
 */
public class C循环移位 {
    /**
     原数 s0,s1,...
     后数 s1,s2,...

     原数模 S
     后数模 (10 * ( S - s0*pow(10,n-1) ) + s0 ) %4
     = 10 * ( S - s0*pow(10,n-1) ) % 4 + s0%4
     = 2 * ( S%4 - s0*pow(10,n-1)%4) %4 + s0%4
     = (2*S + s0)%4
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
        if (n == 2) {
            if (Integer.parseInt(s) % 4 == 0) {
                System.out.println(0);
                return;
            }
            if (Integer.parseInt("" + s.charAt(1) + s.charAt(0)) % 4 == 0) {
                System.out.println(0);
                return;
            }
            System.out.println(-1);
            return;
        }
        int S = new BigInteger(s).mod(BigInteger.valueOf(4)).intValue();
        for (int i = 0; i < n; i++) {
            if (S == 0) {
                System.out.println(i);
                return;
            }
            S = (2 * S + (s.charAt(i) - '0')) % 4;
        }
        System.out.println(-1);
    }
}
