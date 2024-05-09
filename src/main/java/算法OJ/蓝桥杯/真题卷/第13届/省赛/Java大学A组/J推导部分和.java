package 算法OJ.蓝桥杯.真题卷.第13届.省赛.Java大学A组;

import java.io.*;

/**
 已AC
 */
public class J推导部分和 {
    /*
    长度为N的数组A, 数组A未知, 已知信息为M个A数组的部分和
    部分和 l,r,S, 表示A[l,r]的元素之和为S

    有Q次询问
    每次询问给定l,r,求A[l,r]的元素之和,如果无法根据已知信息求出答案,输出UNKNOWN
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static long Long() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (long) st.nval;
    }

    /**
     若sum(A[x,y])已知,则x-1与y连通
     若a与b连通,b与c连通, 因为 sum(A[a+1,c]) = sum(A[b+1,c]) + sum(A[a+1,b]) ,可得a与c连通
     所以维护一个并查集,若i与j连通,则可推导[i,j]部分和,否则无法推导
     */
    public static void main(String[] args) {
        int n = Int(), m = Int(), q = Int();
        Set set = new Set(n);//并查集
        //m个部分和
        for (int i = 0; i < m; i++) {
            int l = Int(), r = Int();
            long s = Long();
            set.union(l - 1, r, s);// sum(a[l,r])=s
        }
        //q次询问
        for (int i = 0; i < q; i++) {
            int l = Int(), r = Int();
            if (!set.isConnect(l - 1, r)) System.out.println("UNKNOWN");
            else System.out.println(set.query(l, r));
        }
    }

    static class Set {
        int[] fa;//fa[i]=j,表示i与j为同一个集合,j比i高,如果i==j,说明i是集合里最高的(根)
        long[] val; // fa[i]=j && value[i]=x 表示i->j的权值为x

        public Set(int n) {
            fa = new int[n + 1];
            val = new long[n + 1];
            for (int i = 0; i <= n; i++) {
                fa[i] = i;
            }
        }

        public int find(int x) {
            if (x == fa[x]) return x;
            int oldFa = fa[x];
            int root = find(fa[x]);
            fa[x] = root;
            val[x] += val[oldFa];//回溯时oldFa累加了从oldFa到root的值,现在累加到x上
            return root;
        }

        public void union(int x, int y, long v) {
            int rx = find(x), ry = find(y);
            fa[rx] = ry;
            val[rx] = val[y] - val[x] + v;
        }

        public boolean isConnect(int x, int y) {
            return find(x) == find(y);
        }

        /**
         本题应用: val[i]表示 i->root 的和
         要求数组[left,right]的和, left->right 为 left->root - right->root
         */
        public long query(int left, int right) {
            return val[left - 1] - val[right];
        }
    }
}
