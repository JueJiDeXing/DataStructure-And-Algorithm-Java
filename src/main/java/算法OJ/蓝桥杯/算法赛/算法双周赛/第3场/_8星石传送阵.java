package 算法OJ.蓝桥杯.算法赛.算法双周赛.第3场;

import java.io.*;
import java.util.*;
/**
 测试通过: 8AC 2TLE
 */
public class _8星石传送阵 {
    /*

    每个星石都有能量值 , 多块星石的能量值为他们的乘积,消耗的价值为他们的和
    例如: 2,3,4 的能量值为2*3*=24, 价值之为2+3+4=9

     n个传送阵,每个传送阵有一个能量值x
     f(x) = sum % n + 1, sum是构成x的星石的价值之和
     例如:
     x=9,n=997, 3*3=9, 最小价值之和为3+3 则f(x) = (3+3) % 997 + 1 = 7

     (1) 如果两个传送阵的f值相等,则可以互相传送
     例如:
     当n>6时
     f(8) = (2+4)%n+1=7
     f(9) = (3+3)%n+1=7

     (2) 能量值为x的传送阵, 可以与 编号为f(x) 的传送阵互相传送
     例如:
     n>6时
     编号为k的传送阵x=9, f(9)=7, 则 k号传送阵 可以与 7号传送阵 互相传送

     求编号a到b的最少传送次数

     示例:
     输入: n=4 a=1 b=4
     1~4号传送阵x值: 9 8 7 6

     输出: 2

     (1) x=9, f(x)=3   ----   (2) x=8, f(x)=3
         |                 /         |
         |               /           |
         |             /             |
         |           /               |
     (3) x=7, f(x)=4   ----    (4) x=6, f(x)=2
     1与2为f(x)相同的互相连接, 其余为编号互相连接
     从1号节点到4号节点需要走至少2步
     */

    /**
     建图,求最短路
     建图:
     1. 根据x计算当前节点的f值可以与编号为f的互相连接,可以与f相同的互相连接,所以还需要哈希表存储f
     */

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static int n;
    static HashMap<Integer, Set<Integer>> map = new HashMap<>();
    static Set<Integer>[] graph;
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {
        n = nextInt();
        int start = nextInt() - 1, end = nextInt() - 1;
        if (start == end) {
            System.out.println(0);
            return;
        }
        map.clear(); //f->[id]
        graph = new HashSet[n];
        Arrays.setAll(graph, k -> new HashSet<>());
        for (int i = 0; i < n; i++) {
            int f = cal(nextInt()) % n;
            graph[i].add(f);
            graph[f].add(i);
            map.computeIfAbsent(f, k -> new HashSet<>()).add(i);
            for (int j : map.get(f)) {
                graph[i].add(j);
                graph[j].add(i);
            }
        }
        queue.clear();
        queue.offer(start);
        boolean[] isVisited = new boolean[n];
        isVisited[start] = true;
        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int next : graph[cur]) {
                    if (isVisited[next]) continue;
                    if (next == end) {
                        System.out.println(dist + 1);
                        return;
                    }
                    queue.offer(next);
                    isVisited[next] = true;
                }
            }
            dist++;
        }
        System.out.println(-1);
    }

    //static int X = 10000_0000;
    //static List<Integer> a;
    //
    //static {
    //    boolean[] isCom = new boolean[X + 1];
    //    a = new ArrayList<>();
    //    for (int i = 2; i <= X; i++) {
    //        if (!isCom[i]) a.add(i);
    //        for (int j = 0; j <= a.size(); j++) {//筛选素数，其实这种方法没以前那种素数法快，但是这里用来求一个数的质因子就比较好了
    //            int m = a.get(j) * i;
    //            if (m > X) break;
    //            isCom[m] = true;
    //            if (i % a.get(j) == 0) break;
    //        }
    //    }
    //}

    static int cal(int x) {
        //对x质因子分解求和
        int sum = 0;
        //for (int i : a) {
        //    if (i * i > x) break;
        //    int cnt = 0;
        //    while (x % i == 0) {
        //        cnt++;
        //        x /= i;
        //    }
        //    sum += cnt * i;
        //}
        for (int i = 2; i * i <= x; i++) {
            if (x % i != 0) continue;
            int cnt = 0;
            while (x % i == 0) {
                cnt++;
                x /= i;
            }
            sum += cnt * i;
        }
        if (x > 1) sum += x;
        return sum;
    }
}
