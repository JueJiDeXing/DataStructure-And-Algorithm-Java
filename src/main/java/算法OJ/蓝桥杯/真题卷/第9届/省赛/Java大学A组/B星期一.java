package 算法OJ.蓝桥杯.真题卷.第9届.省赛.Java大学A组;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 已AC
 */
public class B星期一 {
    /*
    1901/1/1 到 2000/12/31 一共有多少个星期一
     */
    public static void main(String[] args) {
        LocalDate start = LocalDate.of(1901, 1, 1);
        LocalDate end = LocalDate.of(2000, 12, 31);
        int count = 0;
        while (!start.isAfter(end)) {
            if (start.getDayOfWeek() == DayOfWeek.MONDAY) count++;
            start = start.plusDays(1);
        }
        System.out.println(count);//5217
    }

    public static void solve() {
        // 统计2001/1/1到今天多少天
        // 假设今天是星期x, 统计后发现2001/1/1到今天除以7余x
        // 说明2001/1/1是星期一, 那么2000/12/31就是星期日
        // 所以只需要求1901/1/1~2000/12/31 有多少个满周即可,多出的天不会有星期一

        int s = 365 * 100;
        for (int i = 1901; i <= 2000; i++) {
            if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                s++;// 闰年加一天
            }
        }
        s =  s  / 7;//1901~2000有几个满周
        System.out.println(s);
    }
}
