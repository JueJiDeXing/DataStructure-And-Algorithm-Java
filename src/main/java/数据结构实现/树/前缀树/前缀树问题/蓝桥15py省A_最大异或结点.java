package 数据结构实现.树.前缀树.前缀树问题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 蓝桥15py省A_最大异或结点 {
    /*
    给定n个节点的一个树, 每个节点有一个权值
    可以从其中选择两个不直接相连的节点, 计算权值异或
    求最大异或值
     */
    static class Node {
        int pass = 0;
        Node left, right;

    }

    static class Tire {
        Node root = new Node();

        int getBit(int x, int bit) {
            return ((1 << bit) & x) >> bit;
        }

        Node goLeft(Node p) {
            if (p.left == null) p.left = new Node();
            return p.left;
        }

        Node goRight(Node p) {
            if (p.right == null) p.right = new Node();
            return p.right;
        }

        boolean isHave(Node p) {
            return p != null && p.pass != 0;
        }

        void add(int x) {
            Node p = root;
            for (int bit = 30; bit >= 0; bit--) {
                int t = getBit(x, bit);
                if (t == 1) {
                    p = goRight(p);
                } else {
                    p = goLeft(p);
                }
                p.pass++;
            }
        }

        void remove(int x) {
            Node p = root;
            for (int bit = 30; bit >= 0; bit--) {
                int t = getBit(x, bit);
                if (t == 1) {
                    p = goRight(p);
                } else {
                    p = goLeft(p);
                }
                p.pass--;
            }
        }

        int xor(int x) {
            Node p = root;
            int ans = 0;
            for (int bit = 30; bit >= 0; bit--) {
                int t = getBit(x, bit);
                Node to = isHave(p.left) ? p.left : p.right;// 至少有一边可以走
                boolean have = false;
                if (t == 1 && isHave(p.left)) {
                    to = p.left;
                    have = true;
                } else if (t == 0 && isHave(p.right)) {
                    to = p.right;
                    have = true;
                }
                if (have) ans |= 1 << bit;
                p = to;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] X = new int[n];
        Tire tire = new Tire();
        for (int i = 0; i < n; i++) {
            X[i] = sc.nextInt();
            tire.add(X[i]);
        }
        List<Integer>[] map = new ArrayList[n];
        Arrays.setAll(map, k -> new ArrayList<>());
        for (int i = 0; i < n; i++) {
            int p = sc.nextInt();
            if (p != -1) {
                map[p].add(i);
                map[i].add(p);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int connect : map[i]) {
                tire.remove(X[connect]);
            }
            ans = Math.max(ans, tire.xor(X[i]));
            for (int connect : map[i]) {
                tire.add(X[connect]);
            }
        }
        System.out.println(ans);
    }
}
