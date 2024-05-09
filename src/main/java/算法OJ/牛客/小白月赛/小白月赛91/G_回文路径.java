package 算法OJ.牛客.小白月赛.小白月赛91;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/**
 已AC
 */
public class G_回文路径 {
    /*
    给定1颗n个点的树,每个节点上有一个字符
    有q次询问,每次询问u->v的最短路径是否为回文字符串
     */

    /**
     <h1>字符串哈希+LCA</h1>
     令 hash[i][j]表示i->j的哈希值
     设u和v的最近公共祖先为lca
     那么u->v的路径可分为两段,u->lca->v
     判断路径u->v是否为回文字符串,只需要判断u->v的哈希值与u<-v的哈希值是否相同
     hash[u][v] = hash[u][lca] 组合 hash[lca][v]
     hash[u][lca] = hash[u][root] 分离 hash[lca][root]...
     u<-v同理

     总之就是维护根和节点路径的哈希值(自顶向下和自底向上)
     每次询问,先找到lca,根据这三个节点计算出u->v和u<-v的哈希值进行比较
     */
    public static void main(String[] args) {

        int n = sc.nextInt();
        str = ('\0' + sc.next()).toCharArray();//字符串首位添加0,平衡起始索引且防止对计算的影响
        g = new List[n + 1];
        Arrays.setAll(g, e -> new ArrayList<>());
        sc.nextInt();//根节点的父节点不要
        for (int i = 2; i <= n; i++) {//建树
            int fa = sc.nextInt();
            g[fa].add(i);
        }
        dfs(1, 0, str[0]);//预处理:节点深度depth、自顶向下节点哈希hs,自底向上节点哈希hash、up数组
        int q = sc.nextInt();
        while (q-- > 0) query();
    }

    static int MOD = 10_0000_0007;
    static Scanner sc = new Scanner(System.in);
    static List<Integer>[] g;
    static char[] str;
    static long base = 2333;//字符串哈希基值
    static int maxn = 200005;//n<2e5
    static int[] depth = new int[maxn >> 1];//深度
    static long[] hs = new long[maxn >> 1];//自根向下遍历的hash值
    static int[][] up = new int[maxn >> 1][20];//倍增
    static long[][] hash = new long[maxn >> 1][20];//自下向上的hash值
    static long[] fac = new long[1 << 20];

    static {
        for (int i = 0; i < 1 << 20; i++) {
            if (i == 0) fac[i] = 1;
            else fac[i] = fac[i - 1] * base % MOD;
        }
    }

    /**
     @param i   当前结点
     @param fa  父节点
     @param val hash前缀和
     */
    private static void dfs(int i, int fa, long val) {
        depth[i] = depth[fa] + 1;
        hs[i] = (val * base + str[i]) % MOD;
        up[i][0] = fa;
        hash[i][0] = str[i] * base + str[fa];
        for (int j = 1; j < 20; j++) {
            up[i][j] = up[up[i][j - 1]][j - 1];
            hash[i][j] = (hash[i][j - 1] * fac[(1 << j - 1)] % MOD + hash[up[i][j - 1]][j - 1] - str[up[i][j - 1]] * fac[(1 << j - 1)] % MOD + MOD) % MOD;
        }
        for (int x : g[i]) {
            if (depth[x] == 0) dfs(x, i, hs[i]);
        }
    }

    private static void query() {
        int u = sc.nextInt(), v = sc.nextInt();
        int lca = lca(u, v);//找到公共祖先

        int len = depth[u] + depth[v] - depth[lca] * 2 + 1;//u-v的链长
        long left = depth[u] - depth[lca] + 1;//u->lca的节点数
        int siz = (len + 1) / 2;//半数,向上取整
        //回文路径,比较正反链哈希
        long[] LR;
        if (left >= siz + 1) {//u->lca更长
            LR = deal(u, v, siz, len, lca, false);
        } else {//u->lca比v->lca短
            LR = deal(v, u, siz, len, lca, true);
        }
        System.out.println(LR[0] == LR[1] ? "YES" : "NO");

    }

    private static long[] deal(int u, int v, int siz, int len, int lca, boolean reverse) {
        int uu = get_fa(u, siz - 1);
        int vv = len % 2 == 1 ? uu : up[uu][0];
        long l = (hs[u] - hs[up[uu][0]] * fac[siz] % MOD + MOD) % MOD;
        int len2 = depth[vv] - depth[lca] + 1, len3 = siz - len2;
        long r = (get_fa_hash(vv, len2 - 1) * fac[len3] % MOD + hs[v] - hs[lca] * fac[len3] % MOD + MOD) % MOD;
        return reverse ? new long[]{r, l} : new long[]{l, r};
    }


    private static long get_fa_hash(int v, int t) {//v到v向上第t个点的hash
        long res = str[v];
        for (int j = 0; j < 20 && t > 0; j++, t >>= 1) {
            if (t % 2 == 1) {
                //pw.println("j:"+j);
                res = (res * fac[1 << j] % MOD + hash[v][j] - str[v] * fac[1 << j] % MOD + MOD) % MOD;
                v = up[v][j];
            }
        }
        return res;
    }

    private static int get_fa(int v, int t) {
        for (int j = 0; j < 20 && t > 0; j++, t >>= 1) {
            if (t % 2 == 1) v = up[v][j];
        }
        return v;
    }

    /**
     求u和v的lca
     */
    private static int lca(int u, int v) {
        if (depth[u] > depth[v]) {
            int t = u;
            u = v;
            v = t;
        }
        int t = depth[v] - depth[u];
        for (int j = 0; j < 20 && t > 0; j++, t >>= 1) {
            if (t % 2 == 1) v = up[v][j];
        }
        if (u == v) return u;
        for (int j = 19; j >= 0; j--) {
            if (up[u][j] != up[v][j]) {
                u = up[u][j];
                v = up[v][j];
            }
        }
        return up[u][0];
    }


}
