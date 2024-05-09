package 算法OJ.蓝桥杯.真题卷.第9届.国赛.Java大学A组;

import java.util.HashSet;
/**
 已AC
 */
public class B阅兵方阵 {
    /*
    队伍需要组成2个方阵
    130 = 9^2 + 7^2
        = 11^2 + 3^2
    130有两种方阵形式
    求至少要多少人才能有12种不同的方阵形式
     */
    public static void main(String[] args) {
        for (int x = 1000; ; x++) {
            if (check(x)) {
                System.out.println(x);//160225
                break;
            }
        }
    }

    /**
     检查x能否恰好凑出12种
     */
    static boolean check(int x) {
        int cnt = 0;
        int sqrt = (int) Math.sqrt(x);
        HashSet<String> set = new HashSet<>();
        for (int i = 1; i < sqrt; i++) {
            int e = x - i * i;
            if (set.contains(e + "," + i * i)) continue;
            if (isP(e)) {
                cnt++;
                set.add(i * i + "," + e);
            }

        }
        return cnt == 12;

    }

    static boolean isP(int x) {
        int sqrt = (int) Math.sqrt(x);
        return sqrt * sqrt == x;
    }
}
