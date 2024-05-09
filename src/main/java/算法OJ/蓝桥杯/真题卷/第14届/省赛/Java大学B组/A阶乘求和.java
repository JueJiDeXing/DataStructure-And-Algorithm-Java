package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学B组;

/**
 已AC
 */
public class A阶乘求和 {
    public static void main(String[] args) {
        long[] f = new long[50];
        f[1] = 1;
        for (int i = 2; i < f.length; i++) {
            f[i] = f[i - 1] * i % 10_0000_0000;
        }
        long sum = 0;
        for (int i = 1; i < f.length; i++) {
            sum = (sum + f[i]) % 10_0000_0000;
            System.out.println(sum);//420940313
        }
    }
}
