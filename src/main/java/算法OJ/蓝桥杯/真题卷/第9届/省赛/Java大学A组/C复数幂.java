package 算法OJ.蓝桥杯.真题卷.第9届.省赛.Java大学A组;

import java.math.BigInteger;
/**
 已AC
 */
public class C复数幂 {
    /*
    求 (2+3i)^123456

    (a+bi)*(c+di) = (ac-bd) + (bc+ad)i
    (2+3i)*(2+3i) = -5+12i

     */
    public static void main(String[] args) {
        BigInteger[] ans = pow(2, 3, 123456);
        String s = ans[0] + (ans[1].compareTo(BigInteger.ZERO) > 0 ? "+" : "") + ans[1] + "i";
        System.out.println(s);//太长了,不能粘答案提交,只能贴代码提交
    }

    static BigInteger[] pow(int s, int x, int n) {
        BigInteger[] res = {BigInteger.valueOf(s), BigInteger.valueOf(x)};
        n--;
        BigInteger[] m = {BigInteger.valueOf(s), BigInteger.valueOf(x)};
        while (n > 0) {
            if ((n & 1) == 1) {
                res = mul(res, m);
            }
            m = mul(m, m);
            n >>= 1;
        }
        return res;
    }

    /**
     (a+bi)*(c+di) = ac-bd + (bc+ad)i
     */
    static BigInteger[] mul(BigInteger[] s1, BigInteger[] s2) {
        BigInteger a = s1[0], b = s1[1];
        BigInteger c = s2[0], d = s2[1];

        BigInteger ans1 = a.multiply(c).subtract(b.multiply(d));
        BigInteger ans2 = b.multiply(c).add(a.multiply(d));
        return new BigInteger[]{ans1, ans2};
    }
}
