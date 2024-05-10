package 算法.算法基础.贪心;

import java.util.*;

/**
 第 344 场周赛 Q4
 难度分:1917
 */
public class _2673使二叉树所有路径值相等的最小代价 {
    /*
    给你一个整数 n 表示一棵 满二叉树 里面节点的数目，节点编号从 1 到 n 。根节点编号为 1 ，树中每个非叶子节点 i 都有两个孩子，分别是左孩子 2 * i 和右孩子 2 * i + 1 。

   树中每个节点都有一个值，用下标从 0 开始、长度为 n 的整数数组 cost 表示，其中 cost[i] 是第 i + 1 个节点的值。每次操作，你可以将树中 任意 节点的值 增加 1 。你可以执行操作 任意 次。

   你的目标是让根到每一个 叶子结点 的路径值相等。请你返回 最少 需要执行增加操作多少次。

   注意：

   满二叉树 指的是一棵树，它满足树中除了叶子节点外每个节点都恰好有 2 个子节点，且所有叶子节点距离根节点距离相同。
   路径值 指的是路径上所有节点的值之和
     */

    /**
     假设路径和为数组leafSum,其中最大和为max
     那么只需要将其余叶子节点都提升至max即可
     但是对于同一个子树的两个叶子节点,对它们的父节点+1,他们的路径和都会+1
     所以对于同一组的路径和,取其最大值m,对根节点+(max-m)
     然后二分该组进行递归处理
     */
    public int minIncrements1(int n, int[] cost) {
        //获取叶子上的路径和
        for (int i = 0; i < (n - 1) / 2; i++) {
            cost[2 * i + 1] += cost[i];
            cost[2 * i + 2] += cost[i];
        }
        int[] leafSum = Arrays.copyOfRange(cost, (n - 1) / 2, n);
        //取最大值
        int max = getMax(leafSum, 0, leafSum.length - 1);
        return minOperate(max, leafSum, 0, leafSum.length - 1);
    }

    //左右子树提升至max(对根节点加1会影响下面的所有叶子)
    private int minOperate(int max, int[] leafSum, int left, int right) {
        if (left == right) return max - leafSum[left];
        //取子树叶子最大值
        int m = getMax(leafSum, left, right);
        int count = max - m;//将该组提升至max
        for (int i = left; i <= right; i++) {
            leafSum[i] += count;
        }
        //二分提升左右子树
        int mid = (left + right) >>> 1;
        return count + minOperate(max, leafSum, left, mid) + minOperate(max, leafSum, mid + 1, right);
    }

    private static int getMax(int[] leafSum, int left, int right) {
        int m = 0;
        for (int i = left; i <= right; i++) {
            if (leafSum[i] > m) m = leafSum[i];
        }
        return m;
    }

    /**
     首先,对上层节点进行增加的影响更大,操作次数会更少
     但是同一颗子树两个相邻叶子节点,假设他们值为为x和y(x!=y)
     如果x和y不调整,因为他们的父级路径是相等的,所以他们的路径和会不等
     那么可以先把叶子节点用最少次数调整好(x,y提升至他们的较大值),再去考虑父级节点
     假设第k层下面所有层都已调整好了,对于同子树的路径,路径和是一样的(第k层开始)
     如果将路径和累加到第k层,那么第k层也相当于初始的叶子节点层,所以循环上面的操作进行处理即可
     */
    public int minIncrements2(int n, int[] cost) {
        int ans = 0;
        for (int i = n - 2; i >= 0; i -= 2) {//对相邻节点作差
            ans += Math.abs(cost[i] - cost[i + 1]); //相邻的节点i和i+1,提升至他们中的较大值
            cost[i / 2] += Math.max(cost[i], cost[i + 1]);//累加路径和到父节点
        }
        return ans;
    }
}
