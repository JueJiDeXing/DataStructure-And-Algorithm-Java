package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学A组;

import java.time.LocalDate;

/**
 已AC
 */
public class A特殊日期 {
    /**
     特殊日期: 年份各位数之和=月份各位数之和+日期各位数之和
     求1900.1.1至9999.12.31之间特殊日期个数
     */
    public static void main(String[] args) {
        LocalDate start = LocalDate.of(1900, 1, 1);
        LocalDate end = LocalDate.of(9999, 12, 31);
        int ans = 0;
        while (start.isBefore(end)) {
            int y = start.getYear(), m = start.getMonthValue(), d = start.getDayOfMonth();
            if (isEqual(y, m, d)) {
                ans++;
            }
            start = start.plusDays(1);
        }
        System.out.println(ans);//70910
    }

    private static boolean isEqual(int y, int m, int d) {
        int count = 0;
        while (y > 0) {
            count += y % 10;
            y /= 10;
        }
        while (m > 0) {
            count -= m % 10;
            m /= 10;
        }
        while (d > 0) {
            count -= d % 10;
            d /= 10;
        }
        return count == 0;
    }
}
