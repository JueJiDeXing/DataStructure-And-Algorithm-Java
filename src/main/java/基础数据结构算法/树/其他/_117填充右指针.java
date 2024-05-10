package 基础数据结构算法.树.其他;

public class _117填充右指针 {
    //给二叉树填充next指针
    // Definition for a Node.
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    /**
     满二叉树填充

     @param root
     @return
     */
    public Node connect1(Node root) {
        if (root == null) return null;
        Node L = root.left;
        Node R = root.right;
        if (L != null) {
            L.next = R;//连接当前子树的左右节点
            Node n = root.next;
            if (n != null && n.left != null) {//连接右边的左节点
                R.next = n.left;
            }
        }
        connect1(L);
        connect1(R);
        return root;
    }

    /**
     不完美二叉树的填充
     */
    public Node connect2(Node root) {
        Node prev = root;//上一层,每次起始为最左侧节点
        while (prev != null) {
            Node head = new Node();//当前层的哨兵头节点
            Node curr = head;//当前层
            while (prev != null) {//遍历上一层,,找左右非空子节点
                if (prev.left != null) {
                    curr.next = prev.left;//串起来
                    curr = curr.next;
                }
                if (prev.right != null) {
                    curr.next = prev.right;
                    curr = curr.next;
                }
                prev = prev.next;
            }
            prev = head.next;//置为最左侧
        }
        return root;
    }

}
