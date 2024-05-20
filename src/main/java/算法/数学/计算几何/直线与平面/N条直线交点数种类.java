package 算法.数学.计算几何.直线与平面;

import 算法OJ.洛谷.普及up_提高.P2789直线交点数;

import java.util.*;

/**
{@link  P2789直线交点数}
 */
public class N条直线交点数种类 {
    /*
    n条直线,有k个交点(不存在三线共点),求k的可能值有多少个
     */
    /*
    将直线分为两部分, S:r条互相平行的直线; T: 剩余的n-r条直线(都不与S中的直线平行)
    则 交点数 = S内部交点数 + S与T交点数 + T内部交点数
    S内部交点数为0, 因为他们互相平行
    S与T交点数 = r*(n-r), T中的每一条直线都与S有r个交点
    T内部交点数, 这是一个大小为n-r的子问题
    对所有交点数去重,集合大小即为答案
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        dfs(n, 0);
        System.out.println(set.size());
    }

    static Set<Integer> set = new HashSet<>();

    /**
     还剩余n条直线, 当前交点数为k, 筛选出可行方案到f数组
     */
    static void dfs(int n, int k) {
        if (n == 0) {//没有直线了, k是可行值
            set.add(k);
            return;
        }
        for (int r = 1; r <= n; r++) {// r条平行线
            int st = r * (n - r);//S与T的交点数
            dfs(n - r, k + st);// dfs:T内部交点数
        }
    }
}
