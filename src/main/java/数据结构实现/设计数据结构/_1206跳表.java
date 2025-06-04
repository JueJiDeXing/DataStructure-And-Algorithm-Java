package 数据结构实现.设计数据结构;

import java.util.Random;

public class _1206跳表 {
    /*
    引:
    设计一个random方法, 随机返回1~max的数字
    从1开始,数字的几率逐级减半
    例如:max=4   P(1)=50%, P(2)=25%, P(3)=12.5%, P(4)=12.5%
     */
    public static int random(int max) {
        Random r = new Random();
        for (int i = 1; i < max; i++) {
            if (r.nextBoolean()) {// 逐级减半
                return i;
            }
        }
        return max;
    }
    /*
    不使用任何库函数，设计一个 跳表 。
    跳表 是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。
    跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想与链表相似。
    例如，一个跳表包含 [30, 40, 50, 60, 70, 90] ，然后增加 80、45 到跳表中，以下图的方式操作：

    跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。
    跳表的每一个操作的平均时间复杂度是 O(log(n))，空间复杂度是 O(n)。

    在本题中，你的设计应该要包含这些函数：

    bool search(int target) : 返回target是否存在于跳表中。
    void add(int num): 插入一个元素到跳表。
    bool erase(int num): 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可。
    注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。
     */
}

class Skiplist {

    static class Node {
        int val;
        /**
         cur.next[level]: 在level层, cur节点的下一个节点
         <p>
         例: 4个Node对象
         level1  Head->3->   9->null
         level0  Head->3->5->9->null
         Head.next[1]=3    3.next[1]=9
         */
        Node[] next = new Node[MAX_LEVEL];

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node getNext(int level) {
            return next[level];
        }
    }

    private static final int MAX_LEVEL = 16;// 最大层高
    private final Node head = new Node();// 头节点

    public Node[] find(int val) {
        Node[] path = new Node[MAX_LEVEL];
        Node curr = head;
        for (int level = MAX_LEVEL - 1; level >= 0; level--) {//向下
            while (true) {
                Node nxt = curr.next[level];// 尝试向右
                if (nxt == null || nxt.val >= val) break;// 右值大于查找值
                curr = nxt;//向右
            }
            path[level] = curr;//记录每层路径
        }
        // 找到值为val的节点的前驱
        return path;
    }

    public Skiplist() {

    }

    /**
     是否含有target
     */
    public boolean search(int target) {
        Node[] path = find(target);
        Node node = path[0].next[0];
        return node != null && node.val == target;
    }

    /**
     插入一个数
     */
    public void add(int num) {
        Node[] path = find(num);//找插入位置
        int height = randomHeight(MAX_LEVEL);//高度随机生成,从1开始几率逐级减半
        //添加
        Node added = new Node(num);
        for (int level = 0; level < height; level++) {
            // 在path[level]后插入added
            Node prev = path[level];
            Node next = prev.next[level];
            added.next[level] = next;
            prev.next[level] = added;
        }
    }

    public int randomHeight(int max) {
        Random r = new Random();
        for (int i = 1; i <= max; i++) {
            if (r.nextBoolean()) return i;
        }
        return max;
    }

    /**
     删除元素
     */
    public boolean erase(int num) {
        Node[] path = find(num);// 找删除节点的前驱
        Node delete = path[0].next[0];
        if (delete == null || delete.val != num) return false;
        //删除
        for (int level = 0; level < MAX_LEVEL; level++) {//从最低层开始删
            if (path[level].next[level] != delete) {// 上层的右指针可能不是删除节点
                break;
            }

            path[level].next[level] = delete.next[level];
        }
        return true;
    }
}
