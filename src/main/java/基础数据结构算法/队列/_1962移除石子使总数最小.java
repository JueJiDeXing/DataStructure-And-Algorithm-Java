package 基础数据结构算法.队列;

import java.util.*;

public class _1962移除石子使总数最小 {
    /*
    给你一个整数数组 piles ，动态数组 下标从 0 开始 ，其中 piles[i] 表示第 i 堆石子中的石子数量。另给你一个整数 k ，请你执行下述操作 恰好 k 次：

    选出任一石子堆 piles[i] ，并从中 移除 floor(piles[i] / 2) 颗石子。
    注意：你可以对 同一堆 石子多次执行此操作。

    返回执行 k 次操作后，剩下石子的 最小生成树 总数。

    floor(x) 为 小于 或 等于 x 的 最大 整数。（即，对 x 向下取整）。
     */
    public int minStoneSum1(int[] piles, int k) {
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int ans = 0;
        for (int i : piles) {
            queue.offer(i);
            ans += i;
        }
        for (int i = 0; i < k; i++) {
            int p = queue.poll();
            ans -= p / 2;
            queue.offer((p + 1) / 2);
        }
        return ans;
    }

    public int minStoneSum2(int[] piles, int k) {
        int[] queue = new int[10001];//使用数组代替优先级队列
        int ans = 0;
        for (int i : piles) {
            ans += i;
            queue[i]++;
        }
        int curr = 10000;//大数优先
        while (k > 0 && curr > 0) {
            while (k > 0 && queue[curr] > 0) {
                ans -= curr / 2;
                queue[curr - curr / 2]++;
                k--;
                queue[curr]--;
            }
            curr--;
        }
        return ans;
    }
}
