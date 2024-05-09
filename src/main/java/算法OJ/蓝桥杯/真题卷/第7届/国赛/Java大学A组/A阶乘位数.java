package 算法OJ.蓝桥杯.真题卷.第7届.国赛.Java大学A组;

import java.math.BigInteger;
/**
 已AC
 */
public class A阶乘位数 {
    /*
    9999! 二进制有多少位
     */
    public static void main(String[] args) {
        BigInteger ans=BigInteger.ONE;
        for (int i=2;i<10000;i++){
            ans=ans.multiply(BigInteger.valueOf(i));
        }
        System.out.println(ans.bitLength());//118445
    }
}
