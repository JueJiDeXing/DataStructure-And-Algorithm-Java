package 数据结构实现.并查集.普通并查集;

import java.util.Arrays;

/**
 <h1>数组链</h1>
 */
public class DisjointSet {

    int[] fa;
    // 索引对应顶点
    // 元素是用来表示与之有关系的顶点
    /*
        索引  0  1  2  3  4  5  6
        元素 [0, 1, 2, 3, 4, 5, 6] 表示一开始顶点直接没有联系（只与自己有联系）
    */

    public DisjointSet(int size) {
        fa = new int[size];
        for (int i = 0; i < size; i++) {
            fa[i] = i;
        }
    }

    // find 是找到老大 - 优化1：
    /*
        find(2) 0
          find(1) 0
           find(0)
     */
    public int find(int x) {
        if (x < 0 || x >= fa.length) {
            throw new RuntimeException("索引越界");
        }
        if (x == fa[x]) {//索引对应自身的为老大
            return x;
        }
        return fa[x] = find(fa[x]); //路径压缩,在查找中将老大的值赋值过来,下次在该集合里查找时路径缩短
    }

    // union 是让两个集合“相交”，即选出新老大，x、y 是原老大索引
    /*例:
    0 3 相连 -> union(0,3) -> 3索引对应值改为0 (令较小值为老大)
    5 6 相连 -> union(5,6) -> 6索引对应值改为5
    0 3再与5 6 相连 -> union(0,5) -> 5索引对应值改为0
     */
    public void union(int x, int y) {
        int rx = find(x), ry = find(y);
        fa[ry] = rx;
    }


    public boolean isConnect(int x, int y) {
        return find(x) == find(y);
    }

    @Override
    public String toString() {
        return Arrays.toString(fa);
    }

    //最简写法
    static class UnionSet {
        int[] fa;

        UnionSet(int n) {
            fa = new int[n + 1];
            for (int i = 0; i <= n; i++) fa[i] = i;
        }

        int find(int x) {
            return x == fa[x] ? x : (fa[x] = find(fa[x]));
        }

        void union(int x, int y) {
            fa[find(x)] = find(y);
        }

        boolean isConnect(int x, int y) {
            return find(x) == find(y);
        }
    }
}


