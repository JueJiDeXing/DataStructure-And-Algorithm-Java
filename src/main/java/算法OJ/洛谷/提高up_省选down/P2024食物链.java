package 算法OJ.洛谷.提高up_省选down;

import java.io.*;

/**
 已AC
 */
public class P2024食物链 {
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
    - 当前的话中 X 或 Y 比 $N 大,就是假话
    - 当前的话表示 X 吃 X,就是假话

    你的任务是根据给定的 N 和 K 句话，输出假话的总数。
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     使用并查集维护x->y的连通性以及他们的关系
     如果xy连通,此时检查xy的关系与话相符
     如果xy不连通,此时需要将xy连通,建立x->y的关系
     */
    public static void main(String[] args) {
        int n = Int(), k = Int();
        Set set = new Set(n);
        int ans = 0;
        for (int i = 0; i < k; i++) {
            int type = Int(), x = Int(), y = Int();
            if (x > n || y > n || (type == 2 && x == y)) {//动物不能超过n,不能吃自己
                ans++;
                continue;
            }
            switch (type) {
                case 1: {//x与y是同类
                    if (!set.isConnect(x, y)) {//x与y不连通
                        set.union(x, y, 0);  //将x与y连通 ,x->y = 0表示xy为同类
                    } else if (set.getRelation(x, y) != 0) { // x与y连通,检查xy关系, 若不是同类关系,假话
                        ans++;
                    }
                    break;
                }
                case 2: {//x吃y
                    if (!set.isConnect(x, y)) {//x与y不连通
                        set.union(x, y, 1);  //将x与y连通 ,x->y = 1表示x吃y
                    } else if (set.getRelation(x, y) != 1) { // x与y连通,检查xy关系, x不吃y,假话
                        ans++;
                    }
                    break;
                }
                default: {
                    throw new RuntimeException("type error");
                }
            }
        }
        System.out.println(ans);
    }

    static class Set {
        int[] fa;//fa[i]=j,表示i与j连通,i的父节点为j,如果i==j,则i为连通分量的根
        int[] val;//fa[i]=j&&val[i]=t, 表示i->j的权值为t


        public Set(int n) {
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

}
