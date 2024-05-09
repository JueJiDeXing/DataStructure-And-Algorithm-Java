package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学C组;

import java.util.Scanner;

public class I异或和之差 {
    /*
    长度为n的数组,选择两个不相交的子数组,求子数组的异或和,求差值的最大值
     */


    static class Trie {
        static class Node {
            Node l;
            Node r;
        }
        static Node root;

        public Trie() {
            root = new Node();
        }

        int getMax(int num) {
            if (root.l == null && root.r == null) return num;
            int max = 0;
            Node cur = root;
            for (int i = 21; i >= 0; i--) {
                int w = num >> i & 1;
                if (w == 0) {
                    if (cur.r != null) {
                        max += (1 << i);
                        cur = cur.r;
                    } else cur = cur.l;
                } else {
                    if (cur.l != null) {
                        max += (1 << i);
                        cur = cur.l;
                    } else cur = cur.r;
                }
            }
            return max;
        }

        int getMin(int num) {
            if (root.l == null && root.r == null) return num;
            int min = 0;
            Node cur = root;
            for (int i = 21; i >= 0; i--) {
                int w = num >> i & 1;
                if (w == 0) {
                    if (cur.l != null) {
                        cur = cur.l;
                    } else {
                        min += (1 << i);
                        cur = cur.r;
                    }
                } else {
                    if (cur.r != null) cur = cur.r;
                    else {
                        min += (1 << i);
                        cur = cur.l;
                    }
                }

            }
            return min;

        }

        void add(int num) {
            Node cur = root;
            for (int i = 21; i >= 0; i--) {
                int w = num >> i & 1;
                if (w == 0) {
                    if (cur.l == null) {
                        cur.l = new Node();
                    }
                    cur = cur.l;
                } else {
                    if (cur.r == null) {
                        cur.r = new Node();
                    }
                    cur = cur.r;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] num = new int[n];
        for (int i = 0; i < n; i++) num[i] = scan.nextInt();

        Trie trie = new Trie();
        int[] lmax = new int[n + 1], rmax = new int[n + 1];
        int[] lmin = new int[n + 1], rmin = new int[n + 1];
        lmin[0] = rmin[n] = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum ^= num[i - 1];
            lmax[i] = Math.max(lmax[i - 1], trie.getMax(sum));
            lmin[i] = Math.min(lmin[i - 1], trie.getMin(sum));
            trie.add(sum);
        }
        trie = new Trie();
        sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum ^= num[i];
            rmax[i] = Math.max(rmax[i + 1], trie.getMax(sum));
            rmin[i] = Math.min(rmin[i + 1], trie.getMin(sum));
            trie.add(sum);
        }
        int res = 0;
        for (int i = 1; i < n; i++) {
            res = Math.max(res, lmax[i] - rmin[i]);
            res = Math.max(res, rmax[n - i] - lmin[n - i]);
        }
        System.out.println(res);
        scan.close();
    }
}
