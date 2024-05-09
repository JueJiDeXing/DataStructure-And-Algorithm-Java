package 算法.排序.链表排序;

import 数据结构.链表.链表实现.ListNode;

public class 插入 {
    public static void main(String[] args) {
        插入 test = new 插入();
        ListNode head = new ListNode(3,
                new ListNode(5,
                        new ListNode(1,
                                new ListNode(4,
                                        new ListNode(2, null)))));
        head = test.insertSort(head);
        System.out.println("结果:" + head);
    }

    public ListNode insertSort(ListNode head) {
        ListNode s = new ListNode(0, head);
        ListNode prev = head, curr = head.next;
        while (curr != null) {
            if (prev.val <= curr.val) {//前面为升序,curr大于前面,不用插入
                prev = curr;
                curr = curr.next;
            } else {//需要插入
                //寻找插入位置p
                ListNode p = s;
                while (p.next.val < curr.val) {
                    p = p.next;
                }
                //curr插入到p后面
                prev.next = curr.next;
                curr.next = p.next;
                p.next = curr;
                curr = prev.next;
            }
        }
        return s.next;
    }


}
