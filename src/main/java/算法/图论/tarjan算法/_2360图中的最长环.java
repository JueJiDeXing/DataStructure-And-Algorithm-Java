package 算法.图论.tarjan算法;

/**
 难度:困难
 */
public class _2360图中的最长环 {

    /*
    给定n个节点, 每个节点最多一条出边

    求图中的最长环的长度

    输入 edges数组, len(edges)=n, edges[u]=v表示u的出边节点为v
     */

    /**
     n个节点 n-1条边的树无环, 再加入一条边, 最多有1个环
     本题 n个节点,每个节点最多一条出边, 所以每个连通块最多有1个环
     <p>
     从节点x出发, 如果x的路径上有环, 那么一定会访问到某个节点2次
     记录 从节点x出发的开始时间startTime[x] 、第一次访问的时间戳visTime[]
     当 visTime[i] < startTime[x] 时, 说明之前已访问到了节点i, curTime-visTime[i]就是环的长度
     <p>
     再从节点y出发, 如果 visTime[i] < startTime[y], 说明i上的环已被求过了
     */
    public int longestCycle(int[] edges) {
        int n = edges.length;
        int ans = -1;
        int curTime = 1; // 当前时间
        int[] visTime = new int[n];
        for (int i = 0; i < n; i++) {
            int x = i;
            int startTime = curTime; // 本轮循环的开始时间
            while (x != -1 && visTime[x] == 0) {// x未被访问过
                visTime[x] = curTime;
                curTime++;
                x = edges[x];
            }
            if (x != -1 && visTime[x] >= startTime) {// 找到了一个未被访问的环
                ans = Math.max(ans, curTime - visTime[x]);
            }
        }
        return ans;

    }
}
