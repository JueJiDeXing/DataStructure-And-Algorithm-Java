package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学C组;

/**
 已AC
 */
public class A斐波那契与7 {
    //斐波那契第1到202202011200项,多少项个位是7
    public static void main(String[] args) {
        //每60项一循环,每循环8个7
        System.out.println(202202011200L / 60 * 8);//26960268160
        System.out.println(202202011200L % 60);//0
    }
}
