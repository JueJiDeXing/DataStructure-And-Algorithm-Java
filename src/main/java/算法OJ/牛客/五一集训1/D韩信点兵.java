package 算法OJ.牛客.五一集训1;

import java.math.BigInteger;
import java.util.Scanner;
/**
 已AC
 */
public class D韩信点兵 {
    static Scanner sc = new Scanner(System.in);

    static int I() {
        return sc.nextInt();
    }

    static long L() {
        return sc.nextLong();
    }

    public static void main(String[] args) {
        int n = I();
        BigInteger m = BigInteger.valueOf(L());
        //x[i]:满足前i个约束的最小x; lcm[i]:前i个约束的最小公倍数
        BigInteger x = BigInteger.ZERO, lcm = BigInteger.ONE;
        for (int i = 0; i < n; i++) {
            BigInteger a = BigInteger.valueOf(L()), b = BigInteger.valueOf(L());// 约束 ans % a = b
            // (x+t*lcm)%a=b => lcm * t = b-x (mod a)
            // 形如同余方程 ax = b ( mod p )
            // ax = b (mod p) 有解条件 gcd(a,p)|b
            // 即 (b-x) % gcd(lcm,a) = 0
            BigInteger gcd = lcm.gcd(a);
            if (!b.subtract(x).mod(gcd).equals(BigInteger.ZERO)) {// 无解
                System.out.println("he was definitely lying");
                return;
            }
            while (!x.mod(a).equals(b)) {
                x = x.add(lcm); // x[i] = x[i-1] + t*lcm => x[i] % i == map[i]
            }
            lcm = lcm.multiply(a).divide(gcd);
        }
        System.out.println(x.compareTo(m) > 0 ? "he was probably lying" : x);
    }
}
