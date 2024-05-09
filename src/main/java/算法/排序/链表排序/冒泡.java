package 算法.排序.链表排序;

import 数据结构.链表.链表实现.ListNode;

public class 冒泡 {
    public static void main(String[] args) {
        冒泡 test = new 冒泡();
        ListNode head = new ListNode(3,
                new ListNode(5,
                        new ListNode(1,
                                new ListNode(4,
                                        new ListNode(2, null)))));
        head = test.bubbleSort(head);
        System.out.println("结果:" + head);
    }

    public ListNode bubbleSort(ListNode head) {
        int len = getLength(head);
        ListNode s = new ListNode(0, head);
        for (int i = 0; i < len; i++) {
            ListNode prev = s, q = s.next;
            for (int j = 0; j < len - i - 1; j++) {
                if (q.next.val < q.val) {
                    ListNode temp = q.next;
                    q.next = temp.next;
                    temp.next = q;
                    prev.next = temp;
                } else {
                    q = q.next;
                }
                prev = prev.next;
            }
        }
        return s.next;
    }

    public int getLength(ListNode head) {
        ListNode p = head;
        int len = 0;
        while (p != null) {
            p = p.next;
            len++;
        }
        return len;
    }
}
