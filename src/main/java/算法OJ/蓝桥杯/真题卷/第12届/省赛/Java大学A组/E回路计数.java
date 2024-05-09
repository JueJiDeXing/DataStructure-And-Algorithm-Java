package 算法OJ.蓝桥杯.真题卷.第12届.省赛.Java大学A组;

import java.util.*;
/**
 已AC
 */
public class E回路计数 {
    /*
    [1,21]节点
    i与j之间有边当且仅当ij互质
    求从节点1出发,经过其他所有点恰好一次,回到节点1的方案数
     */
    public static void main(String[] args) {
        Arrays.setAll(graph, k -> new ArrayList<>());
        for (int i = 1; i <= 21; i++) {
            List<Integer> list = graph[i];
            for (int j = 2; j <= 21; j++) {
                if (i == j) continue;
                if (gcd(i, j) == 1) {
                    list.add(j);
                }
            }
        }
        System.out.println(Arrays.toString(graph));
        boolean[] isVisited = new boolean[n + 1];
        isVisited[1] = true;
        long ans = dfs(1, 1, isVisited);
        System.out.println(ans);//881012367360

    }

    static HashMap<String, Long> memo = new HashMap<>();//记忆化搜索

    /**
     当前在cur节点,已走sum个节点,isVisited为走过的标记
     */
    static long dfs(int cur, int sum, boolean[] isVisited) {
        if (sum == n) {
            return 1;
        }
        String k = cur + Arrays.toString(isVisited);
        if (memo.containsKey(k)) {
            return memo.get(k);
        }
        long count = 0;
        for (int next : graph[cur]) {
            if (isVisited[next]) continue;
            isVisited[next] = true;
            count += dfs(next, sum + 1, isVisited);
            isVisited[next] = false;
        }
        memo.put(k, count);
        return count;
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    static int n = 21;
    static List<Integer>[] graph = new List[n + 1];
}

