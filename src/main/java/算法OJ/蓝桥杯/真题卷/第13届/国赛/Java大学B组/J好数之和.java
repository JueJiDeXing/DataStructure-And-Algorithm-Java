package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学B组;

import java.util.*;

/**
 测试通过:8/10 2TL
 */
public class J好数之和 {
    /*
    好数:包含连续的2022四个数
    求L到R内的好数之和
     */

    public static void main(String[] args) {//2022*10^5
        Scanner sc = new Scanner(System.in);
        long L = sc.nextLong(), R = sc.nextLong();
        if (R < 2022) {
            System.out.println(0);
            return;
        }
        if (R == 2022) {
            System.out.println(2022);
            return;
        }
        int lenR = String.valueOf(R).length();
        long sum = 0;
        Set<Long> set = new HashSet<>();
        // qqqq2022hhhh
        for (int q = 0; q <= lenR - 4; q++) {//2022前面的位数
            for (int h = 0; h <= lenR - 4 - q; h++) {//2022后面的位数
                double hUp = Math.pow(10, h);
                for (int hn = 0; hn < hUp; hn++) {//枚举后面的数
                    double qUp = Math.pow(10, q);
                    for (int qn = 0; qn < qUp; qn++) {//枚举前面的数
                        //前+2022+后
                        long temp = (long) (qn * Math.pow(10, 4 + h) + 2022 * Math.pow(10, h) + hn);
                        //long temp = Long.parseLong("" + qn + 2022 + hn);
                        if (temp >= L && temp <= R && !set.contains(temp)) {//set防重复
                            sum += temp;
                            set.add(temp);
                        }
                    }
                }
            }
        }
        System.out.println(sum);
    }

    private static void main1() {//2/10 8TL
        Scanner sc = new Scanner(System.in);
        int L = sc.nextInt(), R = sc.nextInt();
        long ans = 0;
        for (int i = L; i <= R; i++) {
            if (String.valueOf(i).contains("2022")) {
                ans += i;
            }
        }
        System.out.println(ans);
    }
}
