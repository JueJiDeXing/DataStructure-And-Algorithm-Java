package 算法OJ.蓝桥杯.真题卷.第10届.省赛.Java大学A组;

import java.math.BigInteger;
/**
 已AC
 */
public class E_RSA解密 {
    /*
    RSA加密过程:
    1. 生成两个质数p,q, 令n=p*q
    2. 设d与(p-1)*(q-1)互质, 则可找到 e 使得 d*e mod  (p-1)*(q-1) = 1;
    3. nde组成私钥,nd组成公钥
    当用公钥加密X(X<n),加密的密文为C = X^d mod n
    密文C可用私钥解密, 解密的明文为X = C^e mod n

    现在n=1001733993063167141, d=212353, C=20190324
    求明文
     */

    /**
     (1)寻找pq
     首先根据 n=p*q, gcd(d, (p-1)*(q-1))=1 寻找质数p和q
     枚举质数p, 则 q = n/p,判定q是否为质数
     最后判定gcd(d, (p-1)*(q-1))是否为1

     (2)求e
     d*e % (p-1)(q-1)==1
     则 e = d 在 模 (p-1)(q-1) 的 乘法逆元

     (3)求X
     X = C^e mod n
     */
    public static void main(String[] args) {
        long n = 1001733993063167141L, d = 212353, C = 20190324;
        long[] pq = findPQ(n, d);//根据n和d求pq, 满足 n=p*q, gcd(d, (p-1)*(q-1))=1
        long p = pq[0], q = pq[1];
        System.out.println("p:"+p+" q:"+q);
        BigInteger res = solve(Big(n), Big(d), Big(C), Big(p), Big(q));
        System.out.println(res);//579706994112328949
    }

    private static BigInteger Big(long n) {
        return BigInteger.valueOf(n);
    }

    static BigInteger solve(BigInteger n, BigInteger d, BigInteger C, BigInteger p, BigInteger q) {
        //由于d*e % (p-1)(q-1)==1,利用逆元求解e的值
        BigInteger pq = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = d.modInverse(pq);
        System.out.println("e:"+e);
        return C.modPow(e, n);//X=C^e %n
    }


    public static long[] findPQ(long n, long d) {
        int sqrt = (int) Math.sqrt(n);
        for (long p = 2L; p <= sqrt; p++) {
            if (n % p != 0) continue;
            long q = n / p;
            if (!isPrime(p) || !isPrime(q)) continue;

            if (gcd(d, (q - 1) * (p - 1)) == 1) {
                return new long[]{p, q};
            }
        }
        return null;
    }

    static boolean isPrime(long n) {
        long sq = (long) Math.sqrt(n);
        for (long i = 2; i <= sq; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

}
