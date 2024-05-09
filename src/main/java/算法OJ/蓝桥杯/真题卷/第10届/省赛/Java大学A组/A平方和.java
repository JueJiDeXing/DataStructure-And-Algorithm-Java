package 算法OJ.蓝桥杯.真题卷.第10届.省赛.Java大学A组;
/**
 已AC
 */
public class A平方和 {
    /*
    求[1,2019]中含有2,0,1,9的数的平方和(只有含有1个就算)
     */
    public static void main(String[] args) {
        long sum = 0;
        for (int i = 1; i <= 2019; i++) {
            if (contain(i)) {
                sum += i * i;
            }
        }
        System.out.println(sum);//2658417853
    }

    static boolean contain(int n) {
        while (n != 0) {
            int temp = n % 10;
            if (temp == 2 || temp == 0 || temp == 1 || temp == 9) return true;
            n /= 10;
        }
        return false;
    }
}
