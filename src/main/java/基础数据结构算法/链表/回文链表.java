package 基础数据结构算法.链表;

import 数据结构实现.链表.ListNode;

public class 回文链表 {
    /**
     <div color=rgb(155,200,80)>
     <h1>判断回文链表</h1>
     找中间点,中间点后半个链表反转,反转的链表与原链表逐一比较<br>
     </div>
     */
    public boolean isPalindrome(ListNode head) {
        ListNode middle = 中间查找.middle(head);
        ListNode newHead = reverse(middle);
        while (newHead != null) {
            if (newHead.val != head.val) {
                //有一个不相同则返回false
                return false;
            }
            newHead = newHead.next;
            head = head.next;
        }
        return true;
    }

    public ListNode reverse(ListNode o1) {
        ListNode n1 = null;//新链表的头节点
        while (o1 != null) {
            //旧链表的次节点
            ListNode o2 = o1.next;
            //旧链表头偏移新链表头
            o1.next = n1;
            //节点复位
            n1 = o1;
            o1 = o2;
        }
        return n1;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>判断回文链表 优化</h1>
     解构,找到中间点的同时反转前半个链表,然后进行比较<br>
     </div>
     */
    public boolean isPalindrome2(ListNode head) {
        //找中间点并反转
        ListNode p1 = head;
        ListNode p2 = head;
        ListNode n1 = null;//新链表的头
        ListNode o1 = head;//旧链表的头
        while (p2 != null && p2.next != null) {
            p1 = p1.next;//快慢指针
            p2 = p1.next.next;
            //ListNode o2=o1.next;//旧链表的次节点,o2与p1其实是同一个节点,后面直接用p1
            o1.next = n1;//搬移到新链表
            n1 = o1;//指针复位
            o1 = p1;
        }
        //判断奇偶
        if (p2 != null) {
            //奇数个节点的情况下,p1多一个中间节点,需要从p1.next开始比较
            p1 = p1.next;
        }
        while (n1 != null) {
            if (n1.val != p1.val) {
                //有一个不相同则返回false
                return false;
            }
            n1 = n1.next;
            p1 = p1.next;
        }
        return true;
    }
}
