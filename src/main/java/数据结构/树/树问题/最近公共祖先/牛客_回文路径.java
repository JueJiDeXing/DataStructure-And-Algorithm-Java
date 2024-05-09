package 数据结构.树.树问题.最近公共祖先;

import 算法OJ.牛客.小白月赛.小白月赛91.G_回文路径;

/**
 {@link G_回文路径}
 */
public class 牛客_回文路径 {
 /*
    给定1颗n个点的树,每个节点上有一个字符
    有q次询问,每次询问u->v的最短路径是否为回文字符串
     */

    /*
     <h1>字符串哈希+LCA</h1>
     令 hash[i][j]表示i->j的哈希值
     设u和v的最近公共祖先为lca
     那么u->v的路径可分为两段, u->lca->v
     判断路径u->v是否为回文字符串,只需要判断u->v的哈希值与u<-v的哈希值是否相同
     hash[u][v] = hash[u][lca] 组合 hash[lca][v]
     hash[u][lca] = hash[u][root] 分离 hash[lca][root]...
     u<-v同理
     */

}
