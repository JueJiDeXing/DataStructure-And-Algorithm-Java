package 算法OJ.蓝桥杯.真题卷.第6届.国赛;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class E铺瓷砖 {

    static PrintWriter out = new PrintWriter(System.out);
    static BigInteger mod = BigInteger.valueOf(65521);
    static BigInteger[] Big = new BigInteger[10];

    static {
        for (int i = 0; i < 10; i++) {
            Big[i] = BigInteger.valueOf(i);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong(), m = sc.nextLong();
        if (n == 1 || m == 1) {
            out.println(0);
        } else {
            BigInteger sum = solve(n, m);
            out.println(sum);
        }
        out.flush();
        out.close();
    }

    private static BigInteger solve(long n, long m) {
        BigInteger sum = BigInteger.ZERO;
        BigInteger BigN = BigInteger.valueOf(n);
        if (m == 2) {
            if (n == 2 || n == 4) return sum;
            if (n % 3 == 0) sum = Big[2].modPow(BigN.divide(Big[3]), mod);
            if (n % 5 == 0) sum = sum.add(Big[2].modPow(BigN.divide(Big[5]), mod));
            if (n % 8 == 0 && n % 3 != 0 && n % 5 != 0) {
                sum = Big[8].modPow(BigN.divide(Big[8]), mod);
            }
            return sum;
        }
        if (m == 3) {
            if (n % 2 == 0) sum = Big[2].modPow(BigN.divide(Big[2]), mod);
            return sum;
        }
        if (m == 4) {
            if (n % 3 == 0) sum = sum.add(Big[4].modPow(BigN.divide(Big[3]), mod));
            else if (n % 4 == 0) sum = sum.add(Big[2].modPow(BigN.divide(Big[4]), mod));
            else if (n % 5 == 0) sum = sum.add(Big[4].modPow(BigN.divide(Big[5]), mod));
            return sum;
        }
        if (m == 5) {
            if (n % 2 == 0) sum = sum.add(Big[2].modPow(BigN.divide(Big[2]), mod));
            if (n % 5 == 0) sum = sum.add(BigInteger.valueOf(32).modPow(BigN.divide(Big[5]), mod));
            return sum;
        }
        //m == 6
        if (n % 2 == 0) sum = sum.add(Big[4].modPow(BigN.divide(Big[2]), mod));
        if (n % 5 == 0) sum = sum.add(Big[2].modPow(BigN.divide(Big[5]), mod));
        return sum;
    }


}
