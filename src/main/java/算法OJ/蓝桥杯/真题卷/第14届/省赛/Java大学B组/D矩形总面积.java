package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学B组;

import java.util.*;

/**
 已AC
 */
public class D矩形总面积 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x1 = sc.nextInt(), y1 = sc.nextInt();
        int x2 = sc.nextInt(), y2 = sc.nextInt();
        int x3 = sc.nextInt(), y3 = sc.nextInt();
        int x4 = sc.nextInt(), y4 = sc.nextInt();
        long area = (long) (x2 - x1) * (y2 - y1) + (long) (x4 - x3) * (y4 - y3);
        int overlapWidth = Math.min(x2, x4) - Math.max(x1, x3); // 重叠部分宽度
        int overlapHeight = Math.min(y2, y4) - Math.max(y1, y3); // 重叠部分高度
        if (overlapWidth > 0 && overlapHeight > 0) { // 存在重叠部分
            area -= (long) overlapWidth * overlapHeight;
        }
        System.out.println(area);
    }


}
