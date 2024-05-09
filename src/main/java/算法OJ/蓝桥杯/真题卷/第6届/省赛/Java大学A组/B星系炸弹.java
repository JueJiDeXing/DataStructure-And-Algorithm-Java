package 算法OJ.蓝桥杯.真题卷.第6届.省赛.Java大学A组;

import java.time.LocalDate;
/**
 已AC
 */
public class B星系炸弹 {
    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2014, 11, 9);
        LocalDate date = start.plusDays(1000);
        System.out.println(date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth());//2017-8-5
    }
}
