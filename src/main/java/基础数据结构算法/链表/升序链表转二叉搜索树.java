package 基础数据结构算法.链表;


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class 升序链表转二叉搜索树 {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode slow = head;
        ListNode prev_slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            prev_slow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode root_L = slow;
        prev_slow.next = null;
        TreeNode root = new TreeNode(root_L.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(root_L.next);
        return root;
    }
}
