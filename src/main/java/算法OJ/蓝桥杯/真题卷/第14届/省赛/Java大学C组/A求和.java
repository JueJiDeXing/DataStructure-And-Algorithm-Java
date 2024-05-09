package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学C组;

import java.math.BigInteger;

/**
 已AC
 */
public class A求和 {
    public static void main(String[] args) {
        BigInteger a = new BigInteger("20230408");
        System.out.println(a.multiply(a.add(BigInteger.ONE)).divide(BigInteger.valueOf(2)));
    }
}
