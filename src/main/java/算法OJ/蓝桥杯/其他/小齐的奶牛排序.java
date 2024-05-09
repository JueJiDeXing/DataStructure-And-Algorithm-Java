package 算法OJ.蓝桥杯.其他;

import java.io.*;
import java.util.*;
/**
 已AC
 */
public class 小齐的奶牛排序 {
    /*
    n只奶牛编号1~n,m组规则
    规则X: a1 a2 a3...  表示a1需要排在a2前面,a2需要排在a3前面...
    现在需要满足前面k条规则,输出k最大的情况下的奶牛排序,如果有多个,输出字典序最小的

    输入:
    输入两个整数n,m
    下面m行, 每行一个整数len表示该条规则的长度,后面len个数
    输出:排序后的结果
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {
        }
        return (int) st.nval;
    }

    static List<Integer>[] graph;

    /**
     <h1>图论</h1>
     定义有向边u->v表示u需要在v前面<br>
     那么对于一组规则X, 需要有X[i-1]->X[i]<br>
     <br>
     !什么情况下无法满足规则:<br>
       如果现在需要去满足u->v, 但是加入这条边后成环<br>
       或者说v的可达路径(从v出发可达的点)上有u,(这表明v需要在u前面)<br>
       则无法满足规则<br>
     <br>
     当无法满足某一条规则时,整组规则需要回退(改组已执行的),并忽略后面的规则(未执行的组)<br>
     <h2>拓扑序</h2>
     按照上述规则建好图后, 入度为0的点是排在最前面的<br>
     按照拓扑序进行输出,如果有多个,则输出编号最小的<br>
     */
    public static void main(String[] args) {
        int n = nextInt(), m = nextInt();
        graph = new ArrayList[n + 1];
        List<Integer>[] graphClone = new ArrayList[n + 1];
        Arrays.setAll(graph, k -> new ArrayList<>());
        Arrays.setAll(graphClone, k -> new ArrayList<>());
        int[] indegree = new int[n + 1];
        int[] indegreeClone = new int[n + 1];
        out:
        for (int i = 0; i < m; i++) {
            int k = nextInt();
            int[] X = new int[k];
            X[0] = nextInt();
            for (int j = 1; j < k; j++) {
                X[j] = nextInt();
                int pre = X[j - 1], cur = X[j];
                if (check(pre, cur)) break out;
                graph[X[j - 1]].add(cur);
                indegree[cur]++;
            }
            for (int j = 0; j < k - 1; j++) {
                graphClone[X[j]] = new ArrayList<>(graph[X[j]]);
            }
            indegreeClone = indegree.clone();
        }
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            if (indegreeClone[i] == 0) {
                queue.offer(i);
            }
        }
        StringBuilder ans = new StringBuilder();
        while (!queue.isEmpty()) {

            int p = queue.poll();
            ans.append(p).append(" ");
            for (int c : graphClone[p]) {
                indegreeClone[c]--;
                if (indegreeClone[c] == 0) queue.offer(c);
            }
        }
        System.out.println(ans);

    }

    /**
     现在pre需要指向cur,所以检查cur的可达路径中是否包含pre,如果包含则无法满足
     */
    static boolean check(int pre, int cur) {
        if (graph[cur].contains(pre)) return true;
        for (int v : graph[cur]) {
            if (check(pre, v)) return true;
        }
        return false;
    }

}
