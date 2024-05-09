package 数据结构.并查集.并查集问题;

public class _765情侣牵手 {
    /*
    n 对情侣坐在连续排列的 2n 个座位上，想要牵到对方的手。

    人和座位由一个整数数组 row 表示，其中 row[i] 是坐在第 i 个座位上的人的 ID。
    情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2n-2, 2n-1)。

    返回 最少交换座位的次数，以便每对情侣可以并肩坐在一起。
     每次交换可选择任意两人，让他们站起来交换座位。
     */
    /*
    当有两对情侣相互坐错了位置，ta们两对之间形成了一个环。需要进行一次交换，使得每队情侣独立（相互牵手）
    如果三对情侣相互坐错了位置，ta们三对之间形成了一个环，需要进行两次交换，使得每队情侣独立（相互牵手）
    如果四对情侣相互坐错了位置，ta们四对之间形成了一个环，需要进行三次交换，使得每队情侣独立（相互牵手）
    也就是说，如果我们有 k 对情侣形成了错误环，需要交换 k - 1 次才能让情侣牵手。
     */
    public int minSwapsCouples(int[] row) {
        int len = row.length;
        UnionFind unionFind = new UnionFind(len / 2);
        for (int i = 0; i < len; i += 2) {
            unionFind.union(row[i] / 2, row[i + 1] / 2);//数环
        }
        return unionFind.count;
    }

    static class UnionFind {
        public int[] parent;
        public int count;

        public UnionFind(int n) {
            this.count = 0;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            parent[rootX] = rootY;
            count++;
        }
    }

    int[] p = new int[70];

    void union(int a, int b) {
        p[find(a)] = p[find(b)];
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    public int minSwapsCouples2(int[] row) {
        int n = row.length, m = n / 2;
        for (int i = 0; i < m; i++) p[i] = i;
        for (int i = 0; i < n; i += 2) union(row[i] / 2, row[i + 1] / 2);
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            if (i == find(i)) cnt++;
        }
        return m - cnt;
    }


}
