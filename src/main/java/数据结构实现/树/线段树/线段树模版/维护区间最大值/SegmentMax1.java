package 数据结构实现.树.线段树.线段树模版.维护区间最大值;

/**
 纯数组+区间加值+区间最大值查询
 */
public class SegmentMax1 {  //线段树
    int M = 10000000;
    // M<<2开4*n个节点
    // Array[node.id]表示编号node.id节点的数据, node.left编号为2*node.id,node.right编号为2*node.id+1
    // node节点的数据区间是[l,r], node.left区间是[l,mid], node.right区间是[mid+1,r]
    // 原本根节点数据区间为[L,R]由构造时传入,现在直接在调用具体方法时传入

    int[] rest = new int[M << 2];// rest[node.id]表示到达站点[node.l,node.r]时的油量的最大值
    int[] lazy = new int[M << 2];// 懒更新

    //与传统线段树不同点在于:维护的数据rest需要求区间最大值
    //无build函数,因为rest数据初始全为0,到达一个站点后才会进行更新

    void pushUp(int node) {//用孩子节点更新父节点数据
        rest[node] = Math.max(rest[node << 1], rest[node << 1 | 1]);
    }

    void pushDown(int node) {//node节点懒任务下发
        if (lazy[node] == 0) return;//没有任务
        lazy[node << 1] += lazy[node];
        lazy[node << 1 | 1] += lazy[node];
        rest[node << 1] += lazy[node];
        rest[node << 1 | 1] += lazy[node];
        lazy[node] = 0;//下发完成
    }

    int query(int node, int l, int r, int left, int right) {//查询[left,right]区间内最大值
        if (left <= l && r <= right) return rest[node];
        pushDown(node);
        int res = 0;
        int mid = (l + r) / 2;
        if (mid >= left) res = Math.max(res, query(node << 1, l, mid, left, right));
        if (mid < right) res = Math.max(res, query(node << 1 | 1, mid + 1, r, left, right));
        pushUp(node);
        return res;
    }

    void add(int node, int l, int r, int left, int right, int v) {//将[left,right]区间内全部加上v
        if (left <= l && r <= right) {
            rest[node] += v;
            lazy[node] += v;
            return;
        }
        pushDown(node);
        int mid = (l + r) / 2;
        if (mid >= left) add(node << 1, l, mid, left, right, v);
        if (mid < right) add(node << 1 | 1, mid + 1, r, left, right, v);
        pushUp(node);
    }

}
