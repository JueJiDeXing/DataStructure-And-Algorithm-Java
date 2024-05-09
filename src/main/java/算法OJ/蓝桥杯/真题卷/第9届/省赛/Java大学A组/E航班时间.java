package 算法OJ.蓝桥杯.真题卷.第9届.省赛.Java大学A组;

import java.util.*;

/**
 已AC
 */
public class E航班时间 {
    /*
    输入T组数据
    每组两行
    每行一个起飞时间,一个降落时间,如果降落时间是第二天了,则后面跟上一个(+1),如果是第三天了则(+2)
    第一行的是A去B的时间,第二行是B回A的时间
    A->B 和 B->A的飞行时间是相同的,但是有时差,导致时间不同
    上午10点从北京出发,中午12点就到达美国,但飞机实际飞行了14小时,因为有12小时的时差
    求飞机飞行一趟的时间
     */

    /**
     假设A到B的时差为t
     从A飞到B,时间会减少t
     从B飞到A,时间会增加t
     那么把两段的飞行时间加起来除以2就是答案了
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < t; i++) {
            String s = sc.nextLine();
            String e = sc.nextLine();
            System.out.println(cal(s, e));
        }
    }

    static String cal(String s, String e) {
        String[] s_sp = s.split(" ");
        String[] e_sp = e.split(" ");
        //求每个的总时间,单位为秒
        int s1 = change(s_sp[0], 0), s2 = change(s_sp[1], s_sp.length != 3 ? 0 : s_sp[2].charAt(2) - '0');
        int e1 = change(e_sp[0], 0), e2 = change(e_sp[1], e_sp.length != 3 ? 0 : e_sp[2].charAt(2) - '0');
        // 差值是行驶时间,因为是一个来回,所以除以2
        int ans = (s2 - s1 + e2 - e1) / 2;
        //转为格式时间
        int h = ans / 3600, m = (ans % 3600) / 60, sec = ans % 60;
        return String.format("%02d:%02d:%02d", h, m, sec);
    }

    static int change(String time, int add) {
        int[] s = Arrays.stream(time.split(":")).mapToInt(Integer::parseInt).toArray();
        s[0] += 24 * add;
        return s[0] * 3600 + s[1] * 60 + s[2];
    }

}
