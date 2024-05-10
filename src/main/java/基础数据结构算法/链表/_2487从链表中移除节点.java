package 基础数据结构算法.链表;

import 数据结构实现.链表.ListNode;

public class _2487从链表中移除节点 {
    /*
    给你一个链表的头节点 head 。
    移除每个右侧有一个更大数值的节点。
    返回修改后链表的头节点 head 。
     */

    /**
     传入一个链表,返回移除右侧有大值的节点后的头节点
     (移除后,链表一定为递减)
     */
    public ListNode removeNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        head.next = removeNodes(head.next);
        //case 1: 移除后,后面没有节点了,返回当前节点
        //case 2: 后面的节点的最大值为head.next.val,如果head与head.next满足递减关系,head可以保留,否则head需要被删除
        if (head.next == null || head.val >= head.next.val) {
            return head;
        }
        return head.next;
    }
}
