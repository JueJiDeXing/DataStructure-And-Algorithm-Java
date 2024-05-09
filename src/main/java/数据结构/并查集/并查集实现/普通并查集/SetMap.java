package 数据结构.并查集.并查集实现.普通并查集;

import java.util.HashMap;
import java.util.Map;

/**
 哈希映射
 */
public class SetMap {
    Map<Integer, Integer> father;//使用Map映射

    public SetMap() {
        father = new HashMap<>();
    }

    public void add(int x) {
        if (!father.containsKey(x)) {
            father.put(x, null);
        }

    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            father.put(rootX, rootY);
        }
    }


    public int find(int x) {
        int root = x;
        while (father.get(root) != null) {//查找root
            root = father.get(root);
        }
        while (x != root) {//将路径上的点归并到root集合
            int curFather = father.get(x);
            father.put(x, root);
            x = curFather;
        }
        return root;
    }

    public int find2(int x) {
        if (father.get(x) == null) return x;
        int f = find2(father.get(x));
        father.put(x, f);
        return f;
    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
}
