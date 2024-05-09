package 算法OJ.蓝桥杯.算法赛.算法季度赛.第1场;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 已AC
 */
public class _2蓝桥圣诞树 {
    /*
    二叉树,每个节点有红或绿色
    判断是否美观,如果有三个连续的节点是同一颜色,则为不美观的
     */


    public static void main(String[] args) {
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            solve();
        }
    }

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Integer>[] graph;
    static int n;
    static String s;

    public static void solve() {
        n = sc.nextInt();
        s = sc.next();
        graph = new ArrayList[n + 1];
        Arrays.setAll(graph, k -> new ArrayList<>());

        for (int i = 1; i <= n - 1; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }
        // 一个点有1~3条边
        // 如果是美观的,每个节点与它相连的三个点最多有两个与它相同
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) == '1') {
                if (count(graph[i], '1') >= 2) {
                    System.out.println("NO");
                    return;
                }
            } else {
                if (count(graph[i], '0') >= 2) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
    }

    static int count(ArrayList<Integer> V, char ch) {
        int count = 0;
        for (int v : V) {
            if (s.charAt(v - 1) == ch) count++;
        }
        return count;
    }
}
