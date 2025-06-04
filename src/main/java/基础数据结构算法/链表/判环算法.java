package 基础数据结构算法.链表;

import 数据结构实现.链表.ListNode;

/**
 判断链表是否有环
 */
public class 判环算法 {
    /**
     <div color=rgb(155,200,80)>
     <h1>龟兔赛跑法:判断链表是否有环</h1>
     龟一次走一步,兔一次走两步<br>
     如果兔追上龟,则有环,否则兔到达链表尾,无环<br>
     </div>
     */
    public boolean hasCircle1(ListNode head) {
        ListNode s = head;
        ListNode f = head;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
            if (s == f) return true;
        }
        return false;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>龟兔赛跑法:查找环的入口</h1>
     龟一次走一步,兔一次走两步<br>
     当龟兔相遇后,将龟放置在起点,然后龟兔都每次走一步,返回相遇时的节点<br>
     <br>
     数学证明:<br>
     设起点到环入口距离为a, 环的长度为b<br>
     相遇时: 兔走了 a+n1圈+k, 龟走了 a+n2圈+k, (k是距离环入口的距离)<br>
     又因为兔的速度是龟的两倍,所以 s龟 = s兔 - s龟 = n1 - n2 圈<br>
     a + n2圈 + k = n1 - n2 圈, 所以`a+k是圈的倍数`<br>
     所以: 从相遇点再走a步, 就回到了环的入口处<br>
     如果将龟放在起点, 龟再走a步也会到达环的入口<br>
     综上:相遇后,将龟放到起点,兔放到相遇点,再都一次走一步,第二次相遇时就是环的入口
     <pre>
     -----
     /       \ k
     ----------          |
     a      \       /
     -------
     </pre>
     </div>
     */
    public ListNode hasCircle2(ListNode head) {
        if (head == null || head.next == null) return null;// 无节点  |   1个节点但不连自己
        ListNode s = head;
        ListNode f = head;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
            if (s == f) break;// 相遇, 有环
        }
        if (s != f) return null;// 指针不相遇, 无环
        // 有环, 查找环入口
        s = head;// 把其中一个放到起点
        while (true) {
            if (s == f) return s;// 再次相遇时就是环的入口
            s = s.next;// 一次一步
            f = f.next;
        }
    }
}
