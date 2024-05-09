package 算法.动态规划_贪心.贪心;

import java.util.*;

/**
 第 22 场双周赛 Q4
 难度分:2410
 */
public class _1388_3n块披萨 {
    /*
    给你一个披萨，它由 3n 块不同大小的部分组成，
    现在你和你的朋友们需要按照如下规则来分披萨：

    你挑选 任意 一块披萨。
    Alice 将会挑选你所选择的披萨逆时针方向的下一块披萨。
    Bob 将会挑选你所选择的披萨顺时针方向的下一块披萨。
    重复上述过程直到没有披萨剩下。
    每一块披萨的大小按顺时针方向由循环数组 slices 表示。

    请你返回你可以获得的披萨大小总和的最大值。
     */
    public int maxSizeSlices(int[] slices) {
        int len = slices.length;
        int n = len / 3;
        //构建环形双向链表
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> o2.value - o1.value);
        Node head = new Node(slices[0]);
        Node curr = head;
        queue.offer(head);
        for (int i = 1; i < len; i++) {
            curr = new Node(curr, slices[i]);
            queue.offer(curr);
        }
        curr.next = head;
        head.prev = curr;
        //在3n的数组中,选择n个数,求最大和
        int ans = 0;
        for (int i = 0; i < n; i++) {
            //贪心:选择最大的数
            while (queue.peek().deleted) queue.poll();
            curr = queue.poll();
            ans += curr.value;
            //选择curr之后,将curr的值更新为左右值-curr,如果curr再次被选取,那么相当于选择了左右,而非curr
            curr.value = curr.prev.value + curr.next.value - curr.value;
            queue.offer(curr);
            //选取不能相邻,将其删除
            deleteNode(curr.prev);
            deleteNode(curr.next);
        }
        return ans;
    }

    static class Node {
        int value;
        Node prev, next;
        boolean deleted;

        public Node(int value) {
            this.value = value;
        }

        public Node(Node prev, int value) {
            this.prev = prev;
            prev.next = this;
            this.value = value;
        }
    }

    void deleteNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.deleted = true;
    }
}
