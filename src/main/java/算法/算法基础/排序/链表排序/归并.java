package 算法.算法基础.排序.链表排序;


import 数据结构实现.链表.ListNode;

class 归并 {

    public static void main(String[] args) {
        归并 test = new 归并();
        ListNode head = new ListNode(3,
                new ListNode(5,
                        new ListNode(1,
                                new ListNode(4,
                                        new ListNode(2, null)))));
        head = test.sortList(head);
        System.out.println("结果:" + head);
    }

    /**

     * @param head 要排序的链表
     * @return 返回排序好的链表头节点(需要接收)
     */
    public ListNode sortList(ListNode head) {
        return doSort(head, null);
    }

    /**
     链表分区,排序[head,tail)
     返回排序好的[head,tail)的head节点
     */
    public ListNode doSort(ListNode head, ListNode tail) {
        // 2.治
        if (head == null) {
            return null;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        // 1.分--快慢指针找中间节点
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode L1 = doSort(head, slow), L2 = doSort(slow, tail);
        // 3.合--合并两个有序链表
        return merge(L1, L2);
    }

    public ListNode merge(ListNode L1, ListNode L2) {
        ListNode head = new ListNode();//哨兵头节点
        ListNode p1 = L1, p2 = L2, p3 = head;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {//compare -> val:int
                p3.next = p1;
                p1 = p1.next;
            } else {
                p3.next = p2;
                p2 = p2.next;
            }
            p3 = p3.next;
        }
        p3.next = p1 != null ? p1 : p2;
        System.out.println("merge:" + head.next);
        return head.next;
    }
}
