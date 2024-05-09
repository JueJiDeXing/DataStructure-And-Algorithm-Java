package 数据结构.哈希表.哈希表实现;

/**
 Entry节点类

 @属性 hash 哈希码
 @属性 key, value 存储的键值对
 @属性 next 下一个节点 */
public class MyEntry {
    int hash;//哈希码
    Object key;
    Object value;
    MyEntry next;

    public MyEntry(int hash, Object key, Object value) {
        this.hash = hash;
        this.key = key;
        this.value = value;
    }

    public MyEntry(int hash, Object key, Object value, MyEntry next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
