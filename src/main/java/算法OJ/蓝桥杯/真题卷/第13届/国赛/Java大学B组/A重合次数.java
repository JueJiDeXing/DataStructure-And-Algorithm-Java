package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学B组;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 已AC
 */
public class A重合次数 {
    /*
    6:13:22到14:36:20,分针与秒针的重合次数
     */
    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.of(2023, 1, 1, 6, 13, 22);
        LocalDateTime end = LocalDateTime.of(2023, 1, 1, 14, 36, 20);
        System.out.println(Duration.between(start, end).toSeconds() / 61);//每61秒重合一次

    }
}
