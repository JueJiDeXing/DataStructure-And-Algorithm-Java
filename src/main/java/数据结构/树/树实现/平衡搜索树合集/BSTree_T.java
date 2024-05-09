package 数据结构.树.树实现.平衡搜索树合集;

import 数据结构.树.树实现.Node.BSTNode_T;

/**
 Binary Search Tree 二叉搜索树,泛型版本
 // TODO 未完成
 */
class BSTree_T<K extends Comparable<K>, V> {

    BSTNode_T<K, V> root;//根节点

    public BSTree_T(BSTNode_T<K, V> root) {
        this.root = root;
    }

    public BSTree_T() {
    }

    /**
     存储关键字对应的值

     @param key   关键字
     @param value 值
     */
    public void put(K key, V value) {

    }

    /**
     查找key对应的值
     递归实现

     @param key 关键字
     @return 关键字对应的值
     */
    public V get2(K key) {
        return doGet(root, key);
    }

    /**
     递归查找
     */
    private V doGet(BSTNode_T<K, V> node, K key) {
        if (node == null) {
            return null;//没找到
        }
        int result = key.compareTo(node.key);
        if (result < 0) {
            return doGet(node.left, key);//向左找,尾递归在java不支持自动优化,可以自己转写为非递归方法
        } else if (result > 0) {
            return doGet(node.right, key);//向右找
        } else {
            return node.value;//找到了
        }
    }

    /**
     查找key对应的值
     <b>非递归实现</b>

     @param key 关键字
     @return 关键字对应的值
     */
    public V get(K key) {
        BSTNode_T<K, V> p = root;
        while (p != null) {
            if (key == null) {
                throw new RuntimeException("key为null");
            }
            int result = key.compareTo(p.key);//key可能为null
            if (result < 0) {//compare:-1表示前小,0表示相等,1表示前一个大
                p = p.left;
            } else if (result > 0) {
                p = p.right;
            } else {
                return p.value;
            }
        }
        return null;
    }

    /**
     查找最大关键字对应值

     @return 关键字对应的值
     */
    public V max() {
        if (root == null) {
            return null;
        }
        BSTNode_T<K, V> p = root;
        while (p.right != null) {
            p = p.right;
        }
        return null;
    }

    /**
     查找最小关键字对应值

     @return 关键字对应的值
     */
    public V min() {
        if (root == null) {
            return null;
        }
        BSTNode_T<K, V> p = root;
        while (p.left != null) {
            p = p.left;
        }
        return null;
    }

    /**
     查找前驱

     @param key 关键字
     @return 前驱值
     */
    public V successor(K key) {
        return null;
    }

    /**
     查找后继

     @param key 关键字
     @return 后继值
     */
    public V predecessor(K key) {
        return null;
    }

    /**
     根据key删除

     @param key 关键字
     @return 被删除的值
     */
    public V delete(K key) {
        return null;
    }
}
