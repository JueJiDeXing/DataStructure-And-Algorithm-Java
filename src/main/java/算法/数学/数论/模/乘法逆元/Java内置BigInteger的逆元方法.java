package 算法.数学.数论.模.乘法逆元;

import java.math.BigInteger;
import java.util.Random;

public class Java内置BigInteger的逆元方法 {
    public static void main(String[] args) {
        // 测试代码
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            int n = random.nextInt(1000) + 2, m = random.nextInt(10000) + 2;
            if (!isPrime(m)) continue;
            int res1 = inv(n, m);
            BigInteger res2 = inv(BigInteger.valueOf(n), BigInteger.valueOf(m));
            if (res1 != res2.intValue()) {
                System.out.println("Oops!");
                break;
            }
        }
    }

    static boolean isPrime(long n) {
        long sq = (long) Math.sqrt(n);
        for (long i = 2; i <= sq; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    /**
     <h1>自带的逆元方法</h1>
     */
    public static BigInteger inv(BigInteger n, BigInteger m) {
        return n.modInverse(m);
    }

    public static int inv(int a, int m) {
        return pow(a, m - 2, m);
    }

    public static int pow(int a, int n, int m) {
        int res = 1;
        while (n != 0) {
            if ((n & 1) == 1) res = (res * a) % m;
            a = (a * a) % m;
            n >>= 1;
        }
        return res;
    }
}
