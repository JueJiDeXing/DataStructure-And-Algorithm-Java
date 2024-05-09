package 算法OJ.蓝桥杯.真题卷.第8届.国赛.Java大学A组;

import java.io.*;
import java.util.*;
/**
 已AC
 */
public class C发现环 {
    /*
    N台电脑,编号从1到N,有N-1条数据线,形成一个树
    现在误加了一条数据线,形成了环
    找到环上的电脑,从小到大输出编号
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        //建图和度表
        int n = Int();
        int[] degree = new int[n + 1];
        List<Integer>[] graph = new ArrayList[n + 1];
        Arrays.setAll(graph, k -> new ArrayList<Integer>());
        for (int i = 0; i < n; i++) {
            int a = Int(), b = Int();
            graph[a].add(b);
            graph[b].add(a);
            degree[a]++;
            degree[b]++;
        }
        //环中一定不存在度为1的节点
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
                degree[i] = 0;
            }
        }
        while (!queue.isEmpty()) {//逐步删去所有度为1的节点
            int i = queue.poll();
            degree[i] = 0;
            for (int j : graph[i]) {
                degree[j]--;
                if (degree[j] == 1) queue.offer(j);
            }
        }
        for (int i = 1; i <= n; i++) {//剩余的节点就在环上
            if (degree[i] > 0) {
                System.out.print(i + " ");
            }
        }
    }
}
