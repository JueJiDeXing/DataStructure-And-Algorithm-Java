package 数据结构.设计数据结构;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class _1206跳表 {
    /*
    引:
    设计一个方法,随机返回1~max的数字
    从1开始,数字的几率逐级减半
    例如:max=4   P(1)=50%, P(2)=25%, P(3)=12.5%, P(4)=12.5%
     */
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int testNum = 1000000;
        for (int i = 0; i < testNum; i++) {
            int r = random(4);
            if (map.containsKey(r)) {
                map.put(r, map.get(r) + 1);
            } else {
                map.put(r, 1);
            }
        }
        System.out.print("{");
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.printf(" %d:%.1f%% ", entry.getKey(), (float) entry.getValue() / testNum * 100);
        }
        System.out.print("}");
    }

    public static int random(int max) {
        Random r = new Random();
        for (int i = 1; i <= max; i++) {
            if (r.nextBoolean()) {
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
        Node[] next = new Node[MAX_NEXT];


        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }
    }

    private static final int MAX_NEXT = 16;
    private final Node head = new Node();

    public Node[] find(int val) {
        Node[] path = new Node[MAX_NEXT];
        Node curr = head;
        for (int level = MAX_NEXT - 1; level >= 0; level--) {//向下
            while (curr.next[level] != null && curr.next[level].val < val) {
                curr = curr.next[level];//向右
            }
            path[level] = curr;//记录每层路径
        }
        //找到值为val的节点的前驱
        return path;
    }

    public Skiplist() {

    }

    public boolean search(int target) {
        Node[] path = find(target);
        Node node = path[0].next[0];
        return node != null && node.val == target;
    }

    public void add(int num) {
        Node[] path = find(num);//找插入位置
        int height = randomHeight(MAX_NEXT);//高度随机生成,从1开始几率逐级减半
        //添加
        Node added = new Node(num);
        for (int level = 0; level < height; level++) {
            Node prev = path[level];
            added.next[level] = prev.next[level];
            prev.next[level] = added;
        }
    }


    public int randomHeight(int max) {
        Random r = new Random();
        for (int i = 1; i <= max; i++) {
            if (r.nextBoolean()) {
                return i;
            }
        }
        return max;
    }

    public boolean erase(int num) {
        Node[] path = find(num);//找删除节点
        Node delete = path[0].next[0];
        if (delete == null || delete.val != num) {
            return false;
        }
        //删除
        for (int level = 0; level < MAX_NEXT; level++) {
            if (path[level].next[level] != delete) {
                break;
            }
            path[level].next[level] = delete.next[level];
        }
        return true;
    }
}
