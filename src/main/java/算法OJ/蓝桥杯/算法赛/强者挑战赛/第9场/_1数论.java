package 算法OJ.蓝桥杯.算法赛.强者挑战赛.第9场;

import java.util.Scanner;

/**
 已AC
 */
public class _1数论 {
    /*
    给定n和k
    有k条边的路径称为一条链
    如果任意两条长度为k的链均有交集,则树为好树
    求n个节点的有根树好树叶子节点的数量最小值
    叶子:无孩子节点的节点,如果根节点没有孩子节点也算叶子节点
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            long n = sc.nextLong(), k = sc.nextLong();
            if (n <= 2 * k + 1) {//一条链,1个叶子
                System.out.println(1);
                continue;
            }
            // 从根延伸k个节点,第k个节点作为中心点,剩余节点都以中心节点生成长度为k的链
            // 剩余节点数:n-(k+1)
            // 尽可能分配出长度等于k的链, 分配完长度为k的链后剩余节点形成一条链,叶子个数就是ceil{n-(k+1)/k}
            // = floor{(n-(k+1)+k-1)/k} = floor{(n-2)/k}
            System.out.println((n - 2) / k);
        }
    }

}
