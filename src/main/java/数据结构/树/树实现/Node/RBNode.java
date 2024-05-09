package 数据结构.树.树实现.Node;


import static 数据结构.树.树实现.Node.RBNode.Color.RED;

/**
 红黑树节点
 */
public class RBNode {
    public enum Color {RED, BLACK}

    public int key;
    public Object value;
    public Color color = RED;
    public RBNode left, right, parent;

    public RBNode(int key, Color color, RBNode left, RBNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.color = color;
        if (left != null) {
            left.parent = this;
        }
        if (right != null) {
            right.parent = this;
        }
    }

    public RBNode() {
    }

    public RBNode(int key, Object value, RBNode left, RBNode right, RBNode parent) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
        if (left != null) {
            left.parent = this;
        }
        if (right != null) {
            right.parent = this;
        }
    }

    public RBNode(int key, Color color) {
        this.key = key;
        this.color = color;
    }

    public RBNode(int key, Object value) {
        this.key = key;
        this.value = value;
    }

    public RBNode(int key) {
        this.key = key;
    }

    //找叔叔
    public RBNode uncle() {
        if (parent == null || parent.parent == null) {
            return null;
        }
        if (parent.isLeftChild()) {
            return parent.parent.right;
        } else {
            return parent.parent.left;
        }
    }

    //找兄弟
    public RBNode brother() {
        if (parent == null) {
            return null;
        }
        if (this.isLeftChild()) {
            return parent.right;
        } else {
            return parent.left;
        }
    }

    //是否是左孩子
    public boolean isLeftChild() {
        return parent != null && parent.left == this;
    }
}
