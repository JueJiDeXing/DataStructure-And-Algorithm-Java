package 数据结构.图.拓扑排序;

import java.util.LinkedList;
import java.util.Queue;

public class _牛的排名 {
    public static void main(String[] args) {
        _牛的排名 test = new _牛的排名();
        int[][] relation = {{8, 2}, {8, 6}, {8, 7}, {2, 1}, {2, 5}, {5, 3}, {1, 4}, {4, 3}, {3, 6}, {3, 7}};
        int sort = test.sort(8, relation);
        System.out.println(sort);
    }

    /**
     根据关系表,求可确定最终排名的牛的数量

     @param n        牛的数量
     @param relation [a,b]表示a能打败b
     @return 可确定最终排名的牛的数量
     */
    public int sort(int n, int[][] relation) {
        //建图的三种方法
        int[][] graph = new int[n + 1][n + 1];
        int[] indegree = new int[n + 1];
        int[] outdegree = new int[n + 1];
        for (int[] r : relation) {
            graph[r[0]][r[1]] = 1;
            outdegree[r[0]]++;
            indegree[r[1]]++;
        }
        //寻找初始入度和出度为0的节点
        Queue<Integer> inQueue = new LinkedList<>();
        Queue<Integer> outQueue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) inQueue.add(i);
            if (outdegree[i] == 0) outQueue.add(i);
        }
        //拓扑排序
        int ans = 0;
        while (!inQueue.isEmpty() || !outQueue.isEmpty()) {
            int size1 = inQueue.size();
            int size2 = outQueue.size();
            if (size1 == 1) {//当仅有一个节点是入度为0,可以确定它的排名
                ans++;
            }
            if (size2 == 1) {
                ans++;
            }
            //把现有的全部抛出,加入下一批
            for (int i = 0; i < size1; i++) {
                int c = inQueue.poll();
                //消除影响
                for (int j = 1; j <= n; j++) {
                    if (graph[c][j] == 1) {
                        graph[c][j] = 0;
                        outdegree[j]--;
                        if (outdegree[j] == 0) outQueue.add(i);
                    }
                }
            }
            for (int i = 0; i < size2; i++) {
                int c = outQueue.poll();
                for (int j = 1; j <= n; j++) {
                    if (graph[j][c] == 1) {
                        graph[j][c] = 0;
                        indegree[j]--;
                        if (indegree[j] == 0) outQueue.add(i);
                    }
                }
            }
        }
        return ans;
    }
}
