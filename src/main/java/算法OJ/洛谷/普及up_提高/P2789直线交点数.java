package 算法OJ.洛谷.普及up_提高;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/**
 已AC
 */
public class P2789直线交点数 {
    /*
     假设平面上有 N 条直线，且无三线共点，那么这些直线有多少种可能的交点数？
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
