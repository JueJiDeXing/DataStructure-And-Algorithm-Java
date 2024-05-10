package 算法.进阶数据结构算法.并查集;

import java.util.HashMap;
import java.util.Map;

public class 最长连续子序列 {
    public int longestConsecutive(int[] nums) {
        DisjointSetMap setMap = new DisjointSetMap();
        for (int num : nums) {
            if (setMap.add(num)) {
                setMap.union(num, num - 1);
                setMap.union(num, num + 1);
            }

        }
        return setMap.maxSize;
    }

    static class Pair {
        Integer val;
        int size;

        public Pair(Integer val, int size) {
            this.val = val;
            this.size = size;
        }
    }

    static class DisjointSetMap {
        Map<Integer, Pair> father;//key:值  value: Pair(val:映射的老大,size:集合大小)
        int maxSize = 0;

        public DisjointSetMap() {
            father = new HashMap<>();
        }

        public boolean add(int x) {
            if (father.containsKey(x)) {
                return false;
            }
            father.put(x, new Pair(null, 1));
            if (maxSize == 0) maxSize = 1;
            return true;
        }

        public void union(int x, int y) {
            int rX = find(x);
            int rY = find(y);
            Pair rootX = father.get(rX);
            Pair rootY = father.get(rY);
            if (rootX == null || rootY == null) {
                return;
            }
            if (rootX.size >= rootY.size) {
                rootX.size += rootY.size;
                maxSize = Math.max(maxSize, rootX.size);
                rootY.val = rX;
            } else {
                rootY.size += rootX.size;
                maxSize = Math.max(maxSize, rootY.size);
                rootX.val = rY;
            }
        }


        public int find(int x) {
            int root = x;
            while (father.get(root) != null && father.get(root).val != null) {//查找root
                root = father.get(root).val;
            }
            while (x != root) {//将路径上的点归并到root集合
                Pair pair = father.get(x);
                x = pair.val;
                pair.val = root;
            }
            return root;
        }
    }
}
