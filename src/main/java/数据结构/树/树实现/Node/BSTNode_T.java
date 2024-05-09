package 数据结构.树.树实现.Node;

public class BSTNode_T<K extends Comparable<K>, V> {
    public K key;//使用key比较大小
    public V value;
    public BSTNode_T<K, V> left;
    public BSTNode_T<K, V> right;

    public BSTNode_T() {
    }

    public BSTNode_T(K key, V value, BSTNode_T<K, V> left, BSTNode_T<K, V> right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public BSTNode_T(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public BSTNode_T(K key) {
        this.key = key;
    }

}
