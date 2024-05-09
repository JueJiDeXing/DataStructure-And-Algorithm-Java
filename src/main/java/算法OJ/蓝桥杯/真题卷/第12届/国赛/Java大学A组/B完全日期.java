package 算法OJ.蓝桥杯.真题卷.第12届.国赛.Java大学A组;

import java.time.LocalDate;

/**
 已AC
 */
public class B完全日期 {
    //日期各位之和为完全平方数
    public static void main(String[] args) {
        LocalDate s = LocalDate.of(2001, 1, 1);
        LocalDate e = LocalDate.of(2021, 12, 31);
        int count = 0;
        while (s.isBefore(e)) {
            int y = s.getYear(), m = s.getMonthValue(), d = s.getDayOfMonth();
            if (is(y, m, d)) count++;
            s = s.plusDays(1);
        }
        System.out.println(count);//977
    }

static boolean is(int y, int m, int d) {
    int sum = getDigitSum(y) + getDigitSum(m) + getDigitSum(d);
    int sqrt = (int) Math.sqrt(sum);
    return sqrt * sqrt == sum;
}

    private static int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

}
