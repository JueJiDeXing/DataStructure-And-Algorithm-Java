package 算法.进阶数据结构算法.树.最近公共祖先;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 倍增法 {
    /*
    令up(x,d)表示节点x向上d步的节点
    对于节点u,v,假设它们深度为d1的祖先节点为u`,v`
    它们的lca深度为d2
    那么对于d>=d1-d2, 都有 up(u`,d) = up(v`,d)
    d<d1-d2, 都有 up(u`,d) != up(v`,d)
    符合二段性,可以使用二分
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
        getLog(n);// 预处理log数组
        son = new ArrayList[n + 1];
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

    static int[] lg;

    static void getLog(int n) {
        lg = new int[n + 1];
        for (int i = 2; i <= n; i++) lg[i] = lg[i / 2] + 1;// 预处理log[i]
    }
}
