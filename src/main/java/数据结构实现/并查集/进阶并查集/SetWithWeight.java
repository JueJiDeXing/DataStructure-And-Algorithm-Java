package 数据结构实现.并查集.进阶并查集;

import 算法OJ.蓝桥杯.真题卷.第13届.省赛.Java大学A组.J推导部分和;

/**
 数组链,带权并查集
 */
public class SetWithWeight {

    int[] fa;// fa[i]=j,表示i与j为同一个集合,j比i高,如果i==j,说明i是集合里最高的(根)
    long[] val;// fa[i]=j && value[i]=x 表示i->j的权值为x

    public SetWithWeight(int n) {
        fa = new int[n + 1];
        val = new long[n + 1];
        for (int i = 0; i <= n; i++) {
            fa[i] = i;
        }
    }

    public int find(int x) {
        if (x == fa[x]) return x;
        int oldFa = fa[x];
        fa[x] = find(fa[x]);
        val[x] += val[oldFa];// 回溯时oldFa累加了从oldFa到root的值,现在累加到x上
        return fa[x];
    }

    public void union(int x, int y, long v) {
        int rx = find(x), ry = find(y);
        fa[rx] = ry;
        val[rx] = val[y] - val[x] + v;
        // x->fx y->fy 现在合并xy, x->y为v,求fx->fy
        // x从fx到fy 为 val[x] + val[fx], 从y到fy为 v + val[y]
        // 可得 val[fx] = val[y] + v - val[x]
    }

    public boolean isConnect(int x, int y) {
        return find(x) == find(y);
    }

    public long getVal(int x) {
        return val[x];
    }

    /**
     例题:{@link J推导部分和}
     <pre>
     在 推导部分和 中,val[i]记录了i->root的值,它表示Sum(A[i+1,root]) = preSum(root)-preSum(i)
     // 调用union时传入l-1和r,记录val[l-1]=Sum(a[l,r])
     现在求Sum(A[left,right])
     原式 = preSum(right)-preSum(left-1)
     = { preSum(root)-preSum(left-1) } - { preSum(root)-preSum(right) }
     = val[left - 1] - val[right]
     </pre>
     */
    public long query(int left, int right) {
        return val[left - 1] - val[right];
    }
}
