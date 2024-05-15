package 算法OJ.牛客.周赛.周赛42;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 已AC
 */
public class D树上删边 {
    /*
    给定一颗树,问最多删除多少条边,使得每个连通块大小为偶数
     */
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }


    /**
     若 n为奇数,怎么删的不行,输出-1
     若n为偶数, 那么只要子树x的大小为偶数,则子树x的父边就是一定能删的
     所以,只需要统计子树大小为偶数的节点个数即可
     */
    public static void main(String[] args) throws IOException {
        int n = I();
        if ((n & 1) == 1) {
            System.out.println(-1);
            return;
        }
        g = new ArrayList[n + 1];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < n - 1; i++) {
            int u = I(), v = I();
            g[u].add(v);
            g[v].add(u);
        }
        size = new int[n + 1];
        Arrays.fill(size, 1);
        dfs(1, 0);
        System.out.println(cnt);
    }

    static List<Integer>[] g;
    static int[] size;
    static int cnt = 0;

    static void dfs(int u, int fa) {
        for (int v : g[u]) {
            if (v != fa) {
                dfs(v, u);
                size[u] += size[v];// 各子树大小
            }
        }
        if (fa != 0 && size[u] % 2 == 0) cnt++;// 统计偶数大小的子树个数
    }
}
