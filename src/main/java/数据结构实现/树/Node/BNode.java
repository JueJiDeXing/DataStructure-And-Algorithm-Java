package 数据结构实现.树.Node;

import java.util.Arrays;

public class BNode {

    public int[] keys;
    public BNode[] children;
    public Object[] values;
    public int keyNumber;//有效关键字数
    public boolean leaf = true;//是否是叶子节点
    int t;//最小度数

    public BNode(int t) { //t>=2
        this.t = t;
        this.keys = new int[2 * t - 1];
        this.children = new BNode[2 * t];
        this.values = new Object[2 * t - 1];
    }

    public BNode(int[] keys) { //t>=2
        this.t = 2;
        this.keys = keys;
        this.values = new Object[keys.length];
        this.children = new BNode[keys.length + 1];
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(keys, 0, keyNumber));
    }

    public BNode get(int key) {
        int i = 0;
        while (i < keyNumber) {
            if (keys[i] == key) {//找到
                return this;
            }
            if (keys[i] > key) {//遇到更大的key,需要到下一层寻找
                break;
            }
            i++;
        }
        if (leaf) {//已经到叶子节点
            return null;
        }
        //非叶子情况,到下一层寻找
        // 1.keys[i]>key 2.i==keyNumber
        return children[i].get(key);
    }

    public void insertKey(int key, int index) {
        System.arraycopy(keys, index, keys, index + 1, keyNumber - index);//搬移元素
        keys[index] = key;
        keyNumber++;
    }

    public void insertChild(BNode child, int index) {
        System.arraycopy(children, index, children, index + 1, keyNumber - index);//搬移元素
        children[index] = child;
    }

    public void insertValue(Object value, int index) {
        System.arraycopy(values, index, values, index + 1, keyNumber - index);//搬移元素
        values[index] = value;
    }

    //删除key
    public int removeKey(int index) {
        int temp = keys[index];
        System.arraycopy(keys, index + 1, keys, index, keyNumber - index);
        keyNumber--;
        return temp;
    }

    public int removeLeftmostKey() {
        return removeKey(0);
    }

    public int removeRightmostKey() {
        return removeKey(keyNumber - 1);
    }

    //删除孩子
    public BNode removeChild(int index) {
        BNode temp = children[index];
        System.arraycopy(children, index + 1, children, index, keyNumber - index);//搬移
        children[keyNumber] = null;
        return temp;
    }

    public BNode removeLeftmostChild() {
        return removeChild(0);
    }

    public BNode removeRightmostChild() {
        return removeChild(keyNumber);
    }

    //删除值
    public Object removeValue(int index) {
        Object temp = values[index];
        System.arraycopy(values, index + 1, values, index, keyNumber - index);
        values[keyNumber] = null;
        return temp;
    }

    public Object removeLeftmostValue() {
        return removeValue(0);
    }

    public Object removeRightmostValue() {
        return removeValue(keyNumber - 1);
    }

    //找索引的左孩子
    public BNode childLeftBrother(int index) {
        return index > 0 ? children[index - 1] : null;
    }

    public BNode childRightBrother(int index) {
        return index == keyNumber ? null : children[index + 1];
    }


    //把当前节点的key-value-child数据复制到target节点后
    public void moveToTarget(BNode target) {
        int start = target.keyNumber;
        if (!leaf) {
            System.arraycopy(children, 0, target.children, start, keyNumber + 1);
            Arrays.fill(children, null);
        }
        System.arraycopy(values, 0, target.values, start, keyNumber);
        System.arraycopy(keys, 0, target.keys, start, keyNumber);
        target.keyNumber += keyNumber;
    }

}
