package 数据结构实现.并查集.普通并查集;

import java.util.Arrays;

/**
 数组链,根据集合大小合并集合
 */
public class SetUnionBySize {

    int[] s;
    public int[] size;//维护集合大小,在union时将顶点个数少的集合连接到顶点个数多的集合

    public SetUnionBySize(int size) {
        s = new int[size];
        this.size = new int[size];
        for (int i = 0; i < size; i++) {
            s[i] = i;
            this.size[i] = 1;//初始为1
        }
    }

    // find 寻找集合的老大
    public int find(int x) {
        if (x == s[x]) {
            return x;
        }
        return s[x] = find(s[x]);
    }

    // union 是让两个集合“相交”，即选出新老大，x、y 是原老大索引
    /*
    if (size[x] < size[y]) {
               // y 老大  x 小弟
               s[x] = y;
               size[y] = size[x] + size[y]; // 更新老大元素个数
           } else {
               // x 新老大 y 新小弟
               s[y] = x;
               size[x] = size[x] + size[y]; // 更新老大元素个数
     }
    */
    public void union(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx == ry) return;
        if (size[rx] < size[ry]) {
            // y 老大  x 小弟
            s[rx] = ry;
            size[ry] = size[rx] + size[ry]; // 更新老大元素个数
        } else {
            // x 新老大 y 新小弟
            s[ry] = rx;
            size[rx] = size[rx] + size[ry]; // 更新老大元素个数
        }
    }

    @Override
    public String toString() {
        return "内容：" + Arrays.toString(s) + "\n大小：" + Arrays.toString(size);
    }

    public static void main(String[] args) {
        //不相交集合对的大小乘积
        int n = 11;
        int[][] edges = new int[][]{{5, 0}, {1, 0}, {10, 7}, {9, 8}, {7, 2}, {1, 3}, {0, 2}, {8, 5}, {4, 6}, {4, 2}};
        SetUnionBySize set = new SetUnionBySize(n);

        for (int[] edge : edges) {
            set.union(set.find(edge[0]), set.find(edge[1]));
        }

        System.out.println(set);
        int[] count = new int[n];
        for (int num : set.s) {
            count[set.find(num)]++;
        }
        System.out.println(set);
        System.out.println(Arrays.toString(count));
        long res = 0;
        for (int i = 0; i < n - 1; i++) {
            if (count[i] == 0) continue;
            for (int j = i + 1; j < n; j++) {
                if (count[j] == 0) continue;
                res += (long) count[i] * count[j];
            }
        }
        System.out.println(res);
    }
}
