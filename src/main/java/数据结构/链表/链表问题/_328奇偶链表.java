package 数据结构.链表.链表问题;

import 数据结构.链表.链表实现.ListNode;

public class _328奇偶链表 {
    /*
    给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
    第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
    请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
    你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
    例如:
    (带下划线的数为奇索引)
    1_  2  3_  4  5_  ->  1_  3_  5_  2  4
    2_  1  3_  5  6_  4  7_  ->  2_  3_  6_  7_  1  5  4
     */
    public 数据结构.链表.链表实现.ListNode oddEvenList(数据结构.链表.链表实现.ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        数据结构.链表.链表实现.ListNode p = head;
        数据结构.链表.链表实现.ListNode prev;
        ListNode odd = head;
        while (true) {
            odd = odd.next;
            if (odd == null) break;
            prev = odd;
            odd = odd.next;
            if (odd == null) break;
            prev.next = odd.next;
            odd.next = p.next;
            p.next = odd;
            p = odd;
            odd = prev;
        }
        return head;
    }
}
