package 算法OJ.蓝桥杯.其他;

import java.util.*;

public class 小齐与朋友的卵石游戏 {
    /*
    三个位置,某个位置有一个球(未知)
    给出n次操作
    每次交换 a和b位置 然后猜测球在c位置(a,b,c∈[1,3])
    若猜中则得1分
    求可能的最大得分
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int pos1 = 1, pos2 = 2, pos3 = 3;
        int score1 = 0, score2 = 0, score3 = 0;
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
            pos1 = change(a, b, pos1);
            pos2 = change(a, b, pos2);
            pos3 = change(a, b, pos3);
            if (c == pos1) score1++;
            if (c == pos2) score2++;
            if (c == pos3) score3++;
        }
        System.out.println(Math.max(score1, Math.max(score2, score3)));
    }

    static int change(int a, int b, int pos) {
        if (a == pos) return b;//pos在a,交换到b
        if (b == pos) return a;//在b,交换到a
        return pos;//未交换
    }

}
