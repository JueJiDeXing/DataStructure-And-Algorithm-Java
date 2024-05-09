package 数据结构.树.树实现.Node;

public class BSTNode {
    public int key;//使用key比较大小
    public Object value;
    public BSTNode left, right;

    public BSTNode() {
    }

    public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;

    }

    public BSTNode(int key, Object value) {
        this.key = key;
        this.value = value;
    }

    public BSTNode(int key) {
        this.key = key;
    }
}
