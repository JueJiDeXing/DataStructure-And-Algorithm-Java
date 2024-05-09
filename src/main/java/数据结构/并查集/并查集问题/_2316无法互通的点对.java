package 数据结构.并查集.并查集问题;

import 数据结构.并查集.并查集实现.普通并查集.SetUnionBySize;

public class _2316无法互通的点对 {
    /*
    给你一个整数 n ，表示一张 无向图 中有 n 个节点，编号为 0 到 n - 1 。
    同时给你一个二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示节点 ai 和 bi 之间有一条 无向 边。
    请你返回 无法互相到达 的不同 点对数目 。
     */
    //不相交集合对的大小乘积
    public long countPairs(int n, int[][] edges) {
        SetUnionBySize set=new SetUnionBySize(n);
        for(int[] edge:edges){
            set.union(edge[0], edge[1]);
        }
        long res=0;
        for(int i=0;i<n;i++){
            res += n - set.size[set.find(i)];//n为点的个数,set.size[set.find(i)]为点i所在集合大小,相减表示不与点i相连的点数
        }
        return res/2;//重复计算除以2
    }


}
