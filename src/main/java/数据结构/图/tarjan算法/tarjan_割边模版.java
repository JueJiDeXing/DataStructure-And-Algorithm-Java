package 数据结构.图.tarjan算法;

import java.util.*;

public class tarjan_割边模版 {
    /*
    给出一个无向连通图,求割边
    割边:当去掉边e后,图被分为两部分,不再连通,则边e为割边
     */
    int SIZE = 100010;
    int[] head = new int[SIZE], vertex = new int[SIZE * 2], next = new int[SIZE * 2];//链式前向星建图

    boolean[] bridge = new boolean[SIZE * 2];
    int total;

    void add(int x, int y) {
        vertex[++total] = y;
        next[total] = head[x];
        head[x] = total;
    }

    /**
     输入无向图,返回割边集合

     @param n     顶点个数,顶点标号从1开始
     @param graph 无向图,graph[i]=[x,y]表示x和y之间有一条边
     */
    public List<int[]> cutEdge(int n, int[][] graph) {
        //链式前向星建图
        total = 1;//当前顶点标号
        for (int[] ints : graph) {
            add(ints[0], ints[1]);
            add(ints[1], ints[0]);
        }
        //计算桥(割边)
        for (int i = 1; i <= n; i++) {
            if (dfn[i] != 0) continue;
            tarjan(i, 0);
        }
        //存储到ans
        List<int[]> ans = new ArrayList<>();
        for (int i = 2; i < total; i += 2) {
            if (bridge[i]) ans.add(new int[]{vertex[i], vertex[i ^ 1]});
        }
        return ans;
    }

    /**
     dfn表示深搜的序号(时间戳)
     */
    int[] dfn = new int[SIZE];
    /**
     low表示追溯值
     low[x]为以下的最小值:
     1. subtree(x)中的节点 // subtree(x)表在搜索树中以x为根的子树(含x)
     2. 通过1条不在搜索树上的边,可到达subtree(x)的节点
     */
    int[] low = new int[SIZE];
    int num = 0;

    private void tarjan(int x, int edge) {
        dfn[x] = low[x] = ++num;
        for (int i = head[x]; i != 0; i = next[i]) {
            int y = vertex[i];
            if (dfn[y] == 0) {//节点y未访问
                tarjan(y, i);
                low[x] = Math.min(low[x], low[y]);
                if (low[y] > dfn[x]) {
                    bridge[i] = bridge[i ^ 1] = true;
                }
            } else if (i != (edge ^ 1)) {//节点y已访问,且节点y在搜索树外,且不是由当前节点指向它的父节点
                low[x] = Math.min(low[x], dfn[y]);
            }
        }
    }
}
