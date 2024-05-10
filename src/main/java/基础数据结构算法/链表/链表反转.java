package 基础数据结构算法.链表;

import 数据结构实现.链表.ListNode;

/**
 反转链表的五种方法
 */
public class 链表反转 {

    /**
     <div color=rgb(155,200,80)>
     <h1> 方法一:头部添加(创建新节点)</h1>
     遍历链表,每次添加元素至新链表头部<br>
     </div>
     */
    public ListNode reverseList1(ListNode head) {
        ListNode n1 = null;//新链表
        ListNode p = head;
        while (p != null) {
            //插入到头部:即新节点指向原头节点
            n1 = new ListNode(p.val, n1);
            p = p.next;//移动指针
        }
        return n1;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1> 方法二:头部移除添加</h1>
     遍历链表,每次移除头部元素至新链表头部<br>
     </div>
     */
    public ListNode reverseList2(ListNode head) {
        List list1 = new List(head);
        List list2 = new List(null);
        while (true) {
            ListNode first = list1.removeFirst();
            if (first == null) {
                break;
            }
            list2.addFirst(first);
        }
        return list2.head;
    }

    static class List {
        ListNode head;

        public List(ListNode head) {
            this.head = head;
        }

        public void addFirst(ListNode first) {
            first.next = head;
            head = first;
        }

        public ListNode removeFirst() {
            ListNode first = head;
            if (first != null) {
                //将头节点设置为头节点的下一个节点
                head = first.next;
            }
            return first;
        }
    }

    /**
     <div color=rgb(155,200,80)>
     <h1> 方法三:递归</h1>
     查找链表最后一个节点<br>
     </div>
     */
    public ListNode reverseList3(ListNode p) {
        if (p == null || p.next == null) {
            return p;
        }
        ListNode last = reverseList3(p.next);
        //递归调用完依次出栈,相当于后序遍历(因为传入的是p.next,所以出栈时出的p是4)
        p.next.next = p;//使后一个节点指向前一个节点,并把前一个节点置空
        p.next = null;
        return last;
        //例:p为4 p.next为5
        //将5指向4(p.next.next=4),再将4的指向置空(p.next=null)
    }

    /**
     <div color=rgb(155,200,80)>
     <h1> 方法四:双指针断链插入</h1>
     遍历链表,将元素取出插入到链表头部<br>
     </div>
     */
    public ListNode reverseList4(ListNode o1) {
        if (o1 == null || o1.next == null) {
            return o1;
        }
        ListNode o2 = o1.next;//旧链表的第二个节点
        ListNode head = o1;//新链表的头节点
        while (o2 != null) {
            o1.next = o2.next;//断开o2,使o1指向旧链表的第三个节点
            o2.next = head;//o2插入到头部

            head = o2;//新链表的头节点
            o2 = o1.next;//旧链表的第二个节点
        }
        return head;
        /*例:
        1(h,o1)->2(o2)->3->4->5
        1(h,o1)->3->4->5    2(o2)->3
        2(o2)->1(h,o1)->3->4->5
        2(h)->1(o1)->3(o2)->4->5

        2(h)->1(o1)->4->5   3(o2)->4
        3(o2)->2(h)->1(o1)->4->5
        ...
         */
    }

    /**
     <div color=rgb(155,200,80)>
     <h1> 方法五:双指针搬移</h1>
     遍历链表,将元素搬移插入到新链表头部<br>
     </div>
     */
    public ListNode reverseList5(ListNode o1) {
        ListNode head = null;//新链表的头节点
        while (o1 != null) {
            ListNode o2 = o1.next;//o2为旧链表的次节点
            //搬移
            o1.next = head;
            //head,o1指针复位
            head = o1;
            o1 = o2;
        }
        return head;
    }
    /*例:
        1(o1)->2(o2)->3->4->5     null(h)
        2(o2)->3->4->5            1(o1)->null(h)
        2(o1,o2)->3->4->5         1(h)->null
        2(o1)->3(o2)->4->5        1(h)->null

        3(o2)->4->5               2(o1)->1(h)->null
        3(o1,o2)->4->5            2(h)->1->null
        3(o1)->4(o2)->5           2(h)->1->null
        ...
         */
}

