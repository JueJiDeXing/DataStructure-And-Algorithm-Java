package 算法OJ.蓝桥杯.真题卷.第10届.省赛.Java大学A组;
/**
 已AC
 */
public class B数列求值 {
    public static void main(String[] args) {
        int d1 = 1, d2 = 1, d3 = 1;
        for (int i = 4; i <= 20190324; i++) {
            int d4 = (d1 + d2 + d3) % 10000;
            d1 = d2;
            d2 = d3;
            d3 = d4;
        }
        System.out.print(d3);//4281

    }
}
