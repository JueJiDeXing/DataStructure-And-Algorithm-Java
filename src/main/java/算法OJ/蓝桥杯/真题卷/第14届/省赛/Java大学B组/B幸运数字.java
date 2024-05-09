package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学B组;

/**
 已AC
 */
public class B幸运数字 {
    /*
    k进制哈沙徳数,其能被各位数之和整除
    幸运数字:同时满足2,8,10,16进制哈沙徳数
    求第2023个幸运数字
     */
    public static void main(String[] args) {
        int count = 0, curr = 1;
        while (count < 2023) {
            if (is(curr)) count++;
            curr++;
        }
        System.out.println(curr - 1);//215040
    }

    static boolean is(int num) {
        int sum = get(num, 10);
        if (num % sum != 0) return false;
        sum = get(num, 2);
        if (num % sum != 0) return false;
        sum = get(num, 8);
        if (num % sum != 0) return false;
        sum = get(num, 16);
        return num % sum == 0;
    }

    static int get(int num, int k) {
        int sum = 0;
        while (num > 0) {
            sum += num % k;
            num /= k;
        }
        return sum;
    }
}
