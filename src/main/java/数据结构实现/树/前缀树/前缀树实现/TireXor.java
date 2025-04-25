package 数据结构实现.树.前缀树.前缀树实现;

import java.util.*;

/**
 异或树
 应用场景: 给定一组数, 从中选择两个, 求最大异或值
 */
public class TireXor {
    static class Node {
        int val;
        int pass = 0;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    static class Tire {
        Node root = new Node(0);

        void add(int x) {
            Node p = root;
            for (int bit = 30; bit >= 0; bit--) {
                int t = ((1 << bit) & x) >> bit;
                if (t == 1) {
                    if (p.right == null) p.right = new Node(1);
                    p = p.right;
                } else {
                    if (p.left == null) p.left = new Node(0);
                    p = p.left;
                }
                p.pass++;
            }
        }

        void remove(int x) {
            Node p = root;
            for (int bit = 30; bit >= 0; bit--) {
                int t = ((1 << bit) & x) >> bit;
                if (t == 1) {
                    if (p.right == null) p.right = new Node(1);
                    p = p.right;
                } else {
                    if (p.left == null) p.left = new Node(0);
                    p = p.left;
                }
                p.pass--;
            }
        }

        int xor(int x) {
            Node p = root;
            int ans = 0;
            for (int bit = 30; bit >= 0; bit--) {
                int t = ((1 << bit) & x) >> bit;
                if (t == 1) {
                    if (p.left == null || p.left.pass == 0) {
                        p = p.right;
                    } else {
                        p = p.left;
                        ans |= 1 << bit;
                    }
                } else {
                    if (p.right == null || p.right.pass == 0) {
                        p = p.left;
                    } else {
                        p = p.right;
                        ans |= 1 << bit;
                    }
                }
            }
            return ans;
        }
    }

}
