package 基础数据结构算法.链表;

import 数据结构实现.链表.ListNode;

/**
 查找链表的中间节点
 */
public class 中间查找 {
    /**
     <div color=rgb(155,200,80)>
     <h1>快慢指针法</h1>
     两个指针,一个一次走一步,一个一次走两步<br>
     </div>
     */
    public static ListNode middle(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p1.next.next;
        }
        return p1;
    }
}
