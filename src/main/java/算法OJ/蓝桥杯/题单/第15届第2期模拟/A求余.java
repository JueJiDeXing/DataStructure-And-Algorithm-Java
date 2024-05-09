package 算法OJ.蓝桥杯.题单.第15届第2期模拟;

import java.math.BigInteger;
/**
 已AC
 */
public class A求余 {
    /*
    2^2023 mod 1000
     */
    public static void main(String[] args) {
        System.out.println(BigInteger.valueOf(2).modPow(BigInteger.valueOf(2023), BigInteger.valueOf(1000)));
        System.out.println(pow(2, 2023, 1000));
    }

    static int pow(int base, int n, int mod) {
        int res = 1;
        while (n > 0) {
            if ((n & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            n >>= 1;
        }
        return res;
    }
}
