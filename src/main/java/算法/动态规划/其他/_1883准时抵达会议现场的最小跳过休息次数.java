package 算法.动态规划.其他;

import java.util.Arrays;

/**
 难度:困难
 */
public class _1883准时抵达会议现场的最小跳过休息次数 {

    /*
    给你一个整数 hoursBefore ，表示你要前往会议所剩下的可用小时数。要想成功抵达会议现场，你必须途经 n 条道路。
    道路的长度用一个长度为 n 的整数数组 dist 表示，其中 dist[i] 表示第 i 条道路的长度（单位：千米）。
    另给你一个整数 speed ，表示你在道路上前进的速度（单位：千米每小时）。

    当你通过第 i 条路之后，就必须休息并等待，直到 下一个整数小时 才能开始继续通过下一条道路。
    注意：你不需要在通过最后一条道路后休息，因为那时你已经抵达会议现场。

    例如，如果你通过一条道路用去 1.4 小时，那你必须停下来等待，到 2 小时才可以继续通过下一条道路。
    如果通过一条道路恰好用去 2 小时，就无需等待，可以直接继续。
    然而，为了能准时到达，你可以选择 跳过 一些路的休息时间，这意味着你不必等待下一个整数小时。
    注意，这意味着与不跳过任何休息时间相比，你可能在不同时刻到达接下来的道路。

    例如，假设通过第 1 条道路用去 1.4 小时，且通过第 2 条道路用去 0.6 小时。
    跳过第 1 条道路的休息时间意味着你将会在恰好 2 小时完成通过第 2 条道路，且你能够立即开始通过第 3 条道路。
    返回准时抵达会议现场所需要的 最小跳过次数 ，如果 无法准时参会 ，返回 -1 。
     */
}

/**
 记忆化搜索
 */
class Solution1 {
    public int minSkips(int[] dis, int sp, int hoursBefore) {
        int sumDist = 0;
        for (int d : dis) sumDist += d;
        long maxDistance = (long) sp * hoursBefore;
        if (sumDist > maxDistance) return -1;

        int n = dis.length;
        for (int[] row : memo) Arrays.fill(row, -1); // -1 表示没有计算过
        dist = dis;
        speed = sp;
        for (int i = 0; ; i++) {//枚举最少跳过次数i
            if (dfs(i, n - 2) + dis[n - 1] <= maxDistance) return i;
        }
    }

    int N = 1001;
    int[][] memo = new int[N][N];
    int[] dist;
    int speed;

    // 当前在j位置,可以跳过i次休息, 最长的行走距离
    private int dfs(int i, int j) {
        if (j < 0) return 0; // 递归边界
        if (memo[i][j] != -1) return memo[i][j]; // 之前计算过

        int res = (dfs(i, j - 1) + dist[j] + speed - 1) / speed * speed;//不跳过休息
        if (i > 0) {//跳过休息
            res = Math.min(res, dfs(i - 1, j - 1) + dist[j]);
        }
        return memo[i][j] = res; // 记忆化
    }
}

/**
 改递推+空间优化
 */
class Solution2 {
    public int minSkips(int[] dist, int speed, int hoursBefore) {
        int sumDist = 0;
        for (int d : dist)  sumDist += d;
        if (sumDist > (long) speed * hoursBefore) return -1;

        int n = dist.length;
        int[] f = new int[n];
        for (int i = 0; ; i++) {
            int pre = 0;
            for (int j = 0; j < n - 1; j++) {
                int tmp = f[j + 1];
                f[j + 1] = (f[j] + dist[j] + speed - 1) / speed * speed;
                if (i > 0) {
                    f[j + 1] = Math.min(f[j + 1], pre + dist[j]);
                }
                pre = tmp;
            }
            if (f[n - 1] + dist[n - 1] <= (long) speed * hoursBefore) return i;
        }
    }
}
