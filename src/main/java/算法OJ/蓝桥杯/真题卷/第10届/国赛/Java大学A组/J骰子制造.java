package 算法OJ.蓝桥杯.真题卷.第10届.国赛.Java大学A组;
/**
 已AC
 */
public class J骰子制造 {
    /*
    正六面体,1~6点,每点一个
    1,4,5旋转90*k度形状不变
    2,3,6旋转180*k度形状不变
    求能造出不同的骰子的种数
     */
    /**
     不考虑点数的旋转有30种骰子
     2,3,6,都可以旋转180变成"新的点数"
     2^3 * 30 =240
     */
    public static void main(String[] args) {
        System.out.println(240);
    }
}
