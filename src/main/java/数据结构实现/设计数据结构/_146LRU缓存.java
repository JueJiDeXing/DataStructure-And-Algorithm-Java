package 数据结构实现.设计数据结构;

import java.util.HashMap;
import java.util.Map;

public class _146LRU缓存 {
    /*
    请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。 --> 最久不使用元素淘汰机制

    实现 LRUCache 类：

    LRUCache(int capacity)
            以 正整数 作为容量 capacity 初始化 LRU 缓存
    int get(int key)
            如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
    void put(int key, int value)
            如果关键字 key 已经存在，则变更其数据值 value ；
            如果不存在，则向缓存中插入该组 key-value 。
            如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。

    函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
     */

}

class LRUCache {
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
            list.remove(node);
            list.addFirst(node);
        } else {//新增
            Node node = new Node(key, value);
            if (map.size() == capacity) {// 超容量了
                Node removed = list.removeLast();
                map.remove(removed.key);
            }
            list.addFirst(node);
            map.put(key, node);
        }
    }
}
