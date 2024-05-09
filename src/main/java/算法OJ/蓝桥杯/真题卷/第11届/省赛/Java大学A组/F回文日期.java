package 算法OJ.蓝桥杯.真题卷.第11届.省赛.Java大学A组;

import java.time.LocalDate;
import java.util.*;
/**
 已AC
 */
public class F回文日期 {
    /*
    从起始日期到终止日期有多少个回文日期
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int d1 = sc.nextInt(), d2 = sc.nextInt();
        LocalDate start = LocalDate.of(d1 / 10000, d1 % 10000 / 100, d1 % 100);
        LocalDate end = LocalDate.of(d2 / 10000, d2 % 10000 / 100, d2 % 100);
        int ans = 0;
        while (!start.isAfter(end)) {
            int num = start.getYear() * 10000 + start.getMonthValue() * 100 + start.getDayOfMonth();
            if (isP(num)) ans++;
            start = start.plusDays(1);
        }

        System.out.println(ans);
    }

    static boolean isP(int num) {
        String n = "" + num;
        for (int i = 0; i < 4; i++) {
            if (n.charAt(i) != n.charAt(7 - i)) return false;
        }
        return true;
    }
}
