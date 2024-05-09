package 算法OJ.洛谷.ETOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;
/**
 无法提交答案
 */
public class P1074能不能走到捏 {
    /*
    给定一个n个点m条边的无向图,每条边一个权值
    问从1到n的路径上权值的最大值最小为多少
    如果1与n不连通,则输出-1
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     二分枚举最大值ans,看1能否不使用权值大于ans的边走到n
     */
    public static void main(String[] args) {
        int n = Int(), m = Int();
        List<int[]>[] graph = new List[n + 1];
        Arrays.setAll(graph, k -> new ArrayList<>());
        int left = 0, right = 0;
        for (int i = 0; i < m; i++) {
            int u = Int(), v = Int(), w = Int();
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
            right = Math.max(right, w);
        }
        while (left + 1 != right) {
            int mid = (left + right) >>> 1;
            if (check(graph, mid, n)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        System.out.println(right);

    }

    /**
     检查在不使用超过ans的边情况下,能否从节点1走到节点n

     @param graph 邻接表
     @param ans   最大使用边权值
     @param n     节点个数
     @return 节点n能否走到
     */
    static boolean check(List<int[]>[] graph, int ans, int n) {
        Stack<Integer> stk = new Stack<>();
        stk.push(1);
        boolean[] canVis = new boolean[n + 1];
        canVis[1] = true;
        while (!stk.isEmpty()) {
            int x = stk.pop();
            if (x == n) return true;
            for (int[] e : graph[x]) {
                if (canVis[e[0]] || e[1] > ans) continue;
                stk.push(e[0]);
                canVis[e[0]] = true;
            }
        }
        return canVis[n];
    }
}
