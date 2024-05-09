package 数据结构.链表.链表问题;

import 数据结构.链表.链表实现.ListNode;

/**
 删除倒数第n个节点(不需要考虑非法情况)
 */
class 删除倒数节点 {
    /**
     <div color=rgb(155,200,80)>
     <h1> 方法一:递归</h1>
     递归链表,根据返回值判断是倒数第几个元素<br>
     </div>
     */
    public ListNode removeElements1(ListNode head, int n) {
        ListNode s = new ListNode(-1, head);
        recursion(s, n);
        return head;
    }

    public int recursion(ListNode p, int n) {
        if (p == null) {
            return 0;
        }
        int nth = recursion(p.next, n);//找下一个节点的倒数位置
        //每个节点处理它的下一个节点的返回值
        if (nth == n) {
            //要删除的节点的返回值为nth
            //例:p=3  p.next=4  要删除4,需要让3指向5
            p.next = p.next.next;
        }
        return nth + 1;//最后p.next为null时(倒数第一个节点)返回0,倒数第二个节点返回1,倒数第三个节点返回2...
    }

    /**
     <div color=rgb(155,200,80)>
     <h1> 方法二:距离差</h1>
     双指针距离为n,后指针查找最后一个节点,删除前指针指向的节点<br>
     </div>
     */
    public ListNode removeElements2(ListNode head, int n) {
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2 = s;
        for (int i = 0; i < n + 1; i++) {
            p2 = p2.next;//移动n+1次,p1与p2中间相隔n个数
        }
        while (p2 != null) {
            p1 = p1.next;//同时移动一位
            p2 = p2.next;
        }
        p1.next = p1.next.next;//此时p2为null,倒数第n个数为p1.next
        return s.next;
    }
}
