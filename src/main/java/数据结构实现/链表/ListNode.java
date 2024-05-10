package 数据结构实现.链表;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@NoArgsConstructor
@AllArgsConstructor
public class ListNode {
    public int val;
    public ListNode next;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        HashSet<ListNode> set = new HashSet<>();
        ListNode p = this;
        while (p != null) {
            if (set.contains(p)) break;
            set.add(p);
            sb.append(p.val).append("->");
            p = p.next;
        }
        sb.append("null");
        return sb.toString();
    }
}
