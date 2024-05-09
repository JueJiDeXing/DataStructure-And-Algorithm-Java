package 算法OJ.蓝桥杯.题单.第15届第2期模拟;

import java.util.Scanner;

/**
 已AC
 */
public class H最多约数 {
    /*
    给出6*6个数,求约数最多的数是哪个,如果有多个,则输出最前面的一个
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int max = 0;
        int maxNum = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int num = sc.nextInt();
                int count = get(num);
                if (count > max) {
                    max = count;
                    maxNum = num;
                }
            }
        }
        System.out.println(maxNum);//901440
    }

    static int get(int num) {
        int s = (int) Math.sqrt(num);
        int count = 0;
        for (int i = 1; i < s; i++) {
            if (num % i == 0) count += 2;
        }
        if (s * s == num) count++;
        return count;
    }
}
