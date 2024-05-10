package 基础数据结构算法.链表;

import 数据结构实现.链表.ListNode;

/**
 有序链表,重复的节点只保留一个
 */
class 节点去重 {
    /**
     <div color=rgb(155,200,80)>
     <h1> 方法一:双指针</h1>
     双指针顺序遍历链表<br>
     </div>
     */
    public ListNode delete1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p1 = head;
        ListNode p2;

        while ((p2 = p1.next) != null) {
            if (p1.val == p2.val) {
                p1.next = p2.next;//删除p2
            } else {
                p1 = p1.next;//平移p1,p2(因为p2已经在while的判断条件平移了,所以这里只平移p1)
            }
        }
        return head;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1> 方法二:递归</h1>
     与节点删除方法二相似<br>
     </div>
     */
    public ListNode delete2(ListNode p) {
        if (p == null || p.next == null) {
            return p;
        }
        if (p.val == p.next.val) {
            //返回下一个节点开始的去重结果,去除自身
            return delete2(p.next);
            /* 去除所有重复值,一个不留:
            ListNode x=p.next.next;
            while(x!=null && x.val=p.val){
                x=x.next;
            }
            return delete2(x);
             */
        } else {
            //未重复,返回自身,并且自身的next指向从下一个节点开始的去重链表
            p.next = delete2(p.next);
            return p;
        }
    }

    /**
     <div color=rgb(155,200,80)>
     <h1> 方法三:三指针</h1>
     跳过所有重复的部分,使前节点指向下一个不重复的节点<br>
     </div>
     */
    public ListNode delete3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2, p3;
        // 移动p2,p3
        // 如果p3的值与p2相等,p3继续向后移动,如果不相等,p1指向p3
        while ((p2 = p1.next) != null && (p3 = p2.next) != null) {
            if (p2.val == p3.val) {
                while ((p3 = p3.next) != null && p3.val == p2.val) {
                    //已经写在判断里了,所以是空方法
                }
                //找到不重复的值,使p1指向p3这个不重复的项,即略过中间重复的节点
                p1.next = p3;
            } else {
                //p2与p3不相等,p1,p2,p3整体后移1位
                p1 = p1.next;
            }

        }
        return s.next;
    }
}
