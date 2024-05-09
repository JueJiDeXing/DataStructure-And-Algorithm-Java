package 数据结构.链表.链表问题;


import 数据结构.链表.链表实现.ListNode;

/**
 根据值删除所有等于这个值的节点
 */
public class 节点删除 {

    /**
     <div color=rgb(155,200,80)>
     <h1> 方法一:双指针</h1>
     双指针遍历链表,不断移除元素<br>
     </div>
     */
    public ListNode removeElements1(ListNode head, int val) {
        ListNode s = new ListNode(-1, head);//哨兵节点,简化删除第一个节点的情况
        ListNode p1 = s;
        ListNode p2;
        while ((p2 = s.next) != null) {//赋值
            if (p2.val == val) {
                p1.next = p2.next;//移除节点
            } else {
                p1 = p2;//更新位置
            }
        }
        return s.next;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1> 方法二:递归</h1>
     递归链表,不断忽略元素<br>
     </div>
     */
    public ListNode removeElements2(ListNode p, int val) {
        if (p == null) {
            return null;
        }
        if (p.val == val) {
            //遇到需要删除的节点,直接返回下一个节点的返回结果,自身不要
            return removeElements2(p.next, val);
        } else {
            //返回自身,并且自身的next指向后续的返回值
            p.next = removeElements2(p.next, val);
            return p;
        }
    }
}

