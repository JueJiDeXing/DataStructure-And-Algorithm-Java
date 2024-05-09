package 算法OJ.蓝桥杯.真题卷.第9届.省赛.Java大学A组;

/**
 已AC
 */
public class A分数 {
    /*
    求 1/1 + 1/2 + 1/4+...
    20项
     */

    /**
     前1项和: 1/1
     前2项和: 3/2
     前3项和: 7/4
     分子*2+1 分母*2
     前n项和: (2^n - 1) / 2^(n-1)
     */
    public static void main(String[] args) {
        int n = 20;
        System.out.println(((1 << n) - 1) + "/" + (1 << (n - 1)));//1048575/524288

    }
}
