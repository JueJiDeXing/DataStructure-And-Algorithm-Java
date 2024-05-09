package 算法OJ.蓝桥杯.算法赛.小白入门赛.第5场;

import java.util.*;
/**
 已AC
 */
public class _5下棋的贝贝 {
    /*
    无限大的平面直角坐标系,放置n个棋子
    求邻居的最大总和
     */

    /**
     贪心: 为正方形的时候贡献最大
     先求出最大正方形的贡献，再慢慢把其他的补上去
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long n = scan.nextLong();
        long len = (long) Math.sqrt(n);
        long ans = 4 * 2;//四个角,每个角2个邻居
        ans += (len - 2) * 4 * 3;//四个边,每边len-2个点,每个点3个邻居
        ans += (len - 2) * (len - 2) * 4;//中心(len-2)^2个点,每个点4个邻居
        long diff = n - len * len;//剩余的点
        if (diff == 0) {
            System.out.println(ans);
            return;
        }
        ans += 4 * diff;//每个点有3个邻居,正方形边上的点邻居+1,就是4个邻居
        ans -= 2;//边上的两个点多算了一个邻居
        if (diff > len) {
            //能围2条边
            ans -= 2;//拐角多算了两个邻居
        }
        System.out.println(ans);
    }
}
