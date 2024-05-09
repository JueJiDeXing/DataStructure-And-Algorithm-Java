package 算法OJ.蓝桥杯.真题卷.第9届.国赛.Java大学A组;

import java.io.*;
import java.util.*;
import java.util.List;

/**
 已AC
 */
public class D版本分支 {
    /*
    N:版本总数 Q:查询总数
    每个节点都有一个父节点,1号节点除外,它是根节点
    给出N-1行数据,每行两个整数u,v,表示u是v的直接父节点
    每个询问,需要判断x是否为y的祖先
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static List<Integer>[] son;
    static int[][] fa;
    static int[] depth;
    static int n;


    public static void main(String[] args) {
        n = Int();
        int Q = Int();
        getLog(n);
        //最近公共祖先,只要x和y的最先公共祖先为x,则为YES,否则为NO
        son = new List[n + 1];
        Arrays.setAll(son, k -> new ArrayList<>());
        for (int i = 1; i <= n - 1; i++) {
            int u = Int(), v = Int();
            son[u].add(v);
        }
        fa = new int[n + 1][20];// log(2,1e5) = 5 * log(2,10) ~ 15
        depth = new int[n + 1];
        dfs(1, 0);
        for (int i = 0; i < Q; i++) {
            int x = Int(), y = Int();
            if (lca(x, y) == x) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    /**
     令fa[x][i]表示up(x,2^i)
     那么可以使用一次dfs求出fa[x][i]的值
     */
    static void dfs(int x, int parent) {
        depth[x] = depth[parent] + 1;//顺带算一下节点深度
        fa[x][0] = parent;//x向上走2^0步到达parent
        for (int i = 1; (1 << i) <= n; i++) {
            fa[x][i] = fa[fa[x][i - 1]][i - 1];//x向上走2^i步,等价于走两个2^(i-1)步
        }
        for (int i = 0; i < son[x].size(); i++) {
            dfs(son[x].get(i), x);//递归处理x的子节点
        }
    }


    static int lca(int x, int y) {
        if (depth[x] > depth[y]) {
            int t = x;
            x = y;
            y = t;
        }
        while (depth[x] != depth[y]) y = fa[y][lg[depth[y] - depth[x]]];
        if (x == y) return x;
        for (int k = lg[depth[x]]; k >= 0; k--) {
            if (fa[x][k] != fa[y][k]) {
                x = fa[x][k];
                y = fa[y][k];
            }
        }
        return fa[x][0];
    }

    static int[] lg;// 预处理log[i]

    static void getLog(int n) {
        lg = new int[n + 1];
        for (int i = 2; i <= n; i++) lg[i] = lg[i / 2] + 1;// 预处理log[i]
    }

    private static void solve1(int n, int Q) {
        int[] fa = new int[n + 1];
        for (int i = 0; i < n - 1; i++) {
            int u = Int(), v = Int();
            if (v == 1) continue;
            fa[v] = u;
        }
        out:
        for (int i = 0; i < Q; i++) {
            int x = Int(), y = Int();
            while (y != 0) {//从y往上跳,一直到根
                if (y == x) {
                    System.out.println("YES");
                    continue out;
                }
                y = fa[y];
            }
            System.out.println("NO");
        }
    }
}
