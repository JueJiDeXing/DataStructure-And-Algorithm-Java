package 算法.图论.拓扑排序;

import java.util.ArrayList;
import java.util.List;

public class _207课程表 {
    /*
    你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
    在选修某些课程之前需要一些先修课程。
    先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
    例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
    请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     */

    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;

    /**
     <h1>拓扑排序</h1>
     1.每次选取未搜索节点进行深度搜索<br>
     2.搜索时, 将搜索的节点标记为搜索中, 然后搜索它的所有相邻节点<br>
     3.如果搜索时遇到搜索中节点, 说明有循环依赖, 跳出搜索, return false<br>
     4.搜索后将节点标记为已搜索节点, 在搜索另一个节点时跳过该节点<br>
     5.如果没有循环依赖关系, 则return true
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //创建有向边
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        //选取未搜索节点开始搜索
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {//0为未搜索,2为已搜索
                dfs(i);
                if (!valid) {//搜索后发现有循环依赖,退出搜索,返回false
                    return false;
                }
            }
        }
        return true;
    }

    public void dfs(int u) {
        visited[u] = 1;//标记为搜索中节点
        for (int v : edges.get(u)) {//搜索该节点的所有相邻节点
            if (visited[v] == 0) {//节点未搜索
                dfs(v);//深搜_广搜
                if (!valid) {//搜索后发现有循环依赖,退出搜索,返回false
                    return;
                }
            } else if (visited[v] == 1) {//如果遇到搜索中节点,说明有循环依赖
                valid = false;
                return;
            }
            //已搜索,跳过
        }
        visited[u] = 2;//标记当前节点为已搜索
    }
}
