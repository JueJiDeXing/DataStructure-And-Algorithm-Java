package 算法OJ.蓝桥杯.算法赛.算法双周赛.第2场;

import java.io.*;
import java.util.*;

/**
 已AC
 */
public class _5串门 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {
        }
        return (int) st.nval;
    }

    static int n;
    static List<int[]>[] graph;

    public static void main(String[] aaaaa) {
        n = nextInt();
        graph = new ArrayList[n + 1];
        Arrays.setAll(graph, k -> new ArrayList<>());
        long total = 0;
        for (int i = 0; i < n - 1; i++) {
            int u = nextInt(), v = nextInt(), w = nextInt();
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
            total += w;
        }
        total *= 2;
        long len = getD();// 求直径长度
        System.out.println(total - len);
    }

    static long getD() {
        Node x = longest(1);// 从任意节点出发,找最远的节点x
        Node y = longest(x.id);// 从x出发找最远的节点y
        return y.w;//直径就是x与y的距离
    }

    static class Node {
        int id;//节点id
        long w;//从出发点到节点的距离

        public Node(int id, long w) {
            this.id = id;
            this.w = w;
        }
    }


    static Node longest(int start) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(start, 0));
        boolean[] isVisit = new boolean[n + 1];
        isVisit[start] = true;
        Node ans = new Node(0, 0);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (ans.w < node.w) ans = node;//寻找距离最长的节点
            for (int[] next : graph[node.id]) {
                int nid = next[0], nw = next[1];
                if (isVisit[nid]) continue;
                isVisit[nid] = true;
                queue.offer(new Node(nid, node.w + nw));
            }
        }
        return ans;

    }
}
