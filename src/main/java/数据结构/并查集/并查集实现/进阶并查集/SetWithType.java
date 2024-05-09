package 数据结构.并查集.并查集实现.进阶并查集;

import 算法OJ.洛谷.提高up_省选down.P2024食物链;

/**
 种类并查集
 {@link P2024食物链}
 */
public class SetWithType {
    /*
  三类动物 A,B,C,这三类动物的食物链构成了环形: A 吃 B, B 吃 C, C 吃 A
  现有 N 个动物,编号1~N
  每个动物都是 A,B,C 中的一种, 但是我们并不知道它到底是哪一种
  有人用两种说法对这 N 个动物所构成的食物链关系进行描述：
  - 第一种说法是 1 X Y,表示 X 和 Y 是同类
  - 第二种说法是 2 X Y,表示 X 吃 Y

  此人对 N 个动物,用上述两种说法,一句接一句地说出 K 句话,这 K 句话有的是真的,有的是假的
  当一句话满足下列三条之一时,这句话就是假话,否则就是真话
  - 当前的话与前面的某些真的话冲突,就是假话
  - 当前的话中 X 或 Y 比 N 大,就是假话
  - 当前的话表示 X 吃 X,就是假话

  你的任务是根据给定的 N 和 K 句话，输出假话的总数。
   */

    int[] fa;// 父节点数组, fa[i]=j,表示i与j连通,i的父节点为j,如果i==j,则i为连通分量的根
    int[] val;// 边权数组, fa[i]=j&&val[i]=t, 表示i->j的权值为t


    public SetWithType(int n) {
        fa = new int[n + 1];
        val = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            fa[i] = i;
        }
    }

    /*
     val: 0:i与j为同类; 1:i吃j; 2:i被j吃
     a->b->c, 现在已知a->b和b->c,求a->c的权值
            a->b   b->c         a->c
     case1:  0      t    ==>     t     ab同类, 则ac的关系等于bc
     case2:  t      0    ==>     t     bc同类, 则ac的关系等于ab
     case3:  1      1    ==>     2     a吃b, b吃c, 则c吃a
     case4:  1      2    ==>     0     a吃b, c吃b, ac为同类
     case5:  2      1    ==>     0     b吃a, b吃c, ac为同类
     case6:  2      2    ==>     1     b吃a, c吃b, 则a吃c
     根据以上关系,可得等式a->c = (a->b + b->c) mod 3
      */
    public int find(int x) {
        if (x == fa[x]) return fa[x];
        int oldFa = fa[x];
        fa[x] = find(fa[x]);
        val[x] = (val[x] + val[oldFa]) % 3;// a->c = (a->b + b->c) mod 3
        return fa[x];
    }

    public boolean isConnect(int x, int y) {
        return find(x) == find(y);
    }

    /*
    x->fx
    y->fy
    现在需要连通xy集合,x->y为v,但是连接的指向是根节点fx->fy,x与y的指向不变,所以现在需要求fx->fy
    x到fy有两条路径:
    1. x->fx->fy
    2. x->y->fy
    其中x->fx,、x->y、y->fy已知
    那么根据等式 x->fx + fx->fy = x->y + y->fy // x到fy的关系值唯一
    得出fx->fy = x->y + y->fy -x->fx
    val[fx] = v + val[y] - val[x]
    然后对3取模即可
     */
    public void union(int x, int y, int v) {
        int rx = find(x), ry = find(y);
        if (rx == ry) return;
        fa[rx] = ry;
        val[rx] = (v + val[y] - val[x] + 3) % 3;
    }


    public int getRelation(int x, int y) {
        return (val[x] - val[y] + 3) % 3;
    }


}
