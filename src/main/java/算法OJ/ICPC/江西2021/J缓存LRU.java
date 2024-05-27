package 算法OJ.ICPC.江西2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 已AC(二分+实现LRU)
 */
public class J缓存LRU {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static int[] a;
    static int n, k;

    public static void main(String[] args) throws Exception {
        n = I();
        k = I();
        a = new int[n];
        HashSet<Integer> set = new HashSet<>();
        int t = 0;
        for (int i = 0; i < n; i++) {
            a[i] = I();
            if (!set.add(a[i])) t++;
        }
        if (t < k) {// 全部缓存也不够k
            System.out.println("cbddl");
            return;
        }
        int left = 0, right = n;
        while (left + 1 != right) {
            int mid = (left + right) >>> 1;
            if (check(mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        System.out.println(right);
    }

    static boolean check(int x) {
        LRUCache cache = new LRUCache(x);
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int t = cache.get(a[i]);
            if (t == -1) {
                cache.put(a[i], 1);
            } else {
                cnt++;
                if (cnt == k) return true;
            }
        }
        return false;
    }

    static class LRUCache {
        //思路:双向链表
        //在put和get时,把节点1插入到头部,淘汰时删除尾部节点
        static class Node {
            Node prev;
            Node next;
            int key;
            int value;

            public Node(Node prev, Node next, int key, int value) {
                this.prev = prev;
                this.next = next;
                this.key = key;
                this.value = value;
            }

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }

            public Node() {
            }
        }

        static class DoubleLinkedList {
            Node head;
            Node tail;

            public DoubleLinkedList() {
                head = new Node();
                tail = new Node();
                head.next = tail;
                tail.prev = head;
            }

            public void addFirst(Node node) {
                Node first = head.next;
                node.next = first;
                node.prev = head;
                first.prev = node;
                head.next = node;
            }

            public void remove(Node node) {
                Node p = node.prev;
                Node n = node.next;
                p.next = n;
                n.prev = p;
            }

            public Node removeLast() {
                Node delete = tail.prev;
                remove(delete);
                return delete;
            }
        }

        Map<Integer, Node> map = new HashMap<>();
        DoubleLinkedList list = new DoubleLinkedList();
        int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node node = map.get(key);
            list.remove(node);
            list.addFirst(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {//更新
                Node node = map.get(key);
                node.value = value;
            } else {//新增
                Node node = new Node(key, value);
                list.addFirst(node);
                map.put(key, node);
                if (map.size() > capacity) {
                    Node removed = list.removeLast();
                    map.remove(removed.key);
                }
            }
        }
    }
}


