package 算法.算法基础.排序.链表排序;

import 数据结构实现.链表.ListNode;

public class 快速 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        quickSort(head, null);
        return head;
    }

    public void quickSort(ListNode head, ListNode tail) {//不包含tail
        if (head == tail || head.next == tail) return; //head->next == tail表示区间内只有一个节点
        ListNode q = partition(head, tail);
        quickSort(head, q);
        quickSort(q.next, tail);
    }


    public ListNode partition(ListNode head, ListNode tail) {
        int pivot = head.val;
        ListNode i = head;
        ListNode j = head.next;
        while (j != tail) {
            if (j.val < pivot) {//把小于基准点的放左边
                i = i.next;
                swap(i, j);
            }
            j = j.next;
        }
        swap(head, i);
        return i;
    }

    public void swap(ListNode node1, ListNode node2) {
        int t = node1.val;
        node1.val = node2.val;
        node2.val = t;
    }


}
