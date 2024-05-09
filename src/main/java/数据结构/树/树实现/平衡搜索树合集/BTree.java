package 数据结构.树.树实现.平衡搜索树合集;

import 数据结构.树.树实现.Node.BNode;
//已测试:src.java.数据结构与算法.数据结构.树.平衡搜索树合集.TestBTree
/**
 <h1>B树(Balance) </h1>
 B树的高度低,磁盘IO次数少,适合磁盘存储<br>
 100万数据,使用avl树需要log(100万)≈20层,而使用B树(最小度数500时),树高约3层
 */
public class BTree {

    //度/阶:节点孩子数
    /*B树特性
    1.每个节点最多有m个孩子,m为阶
    2.除根与叶子,其他每个节点至少有ceil(m/2)个孩子
    3.所有叶子节点都在同一层
    4.每个非叶子节点由n个key和n+1个指针组成(有n+1个孩子)
    5.key为升序排列:节点内keys[i]<=keys[i+1] , p[i]指向key值位于keys[i]~keys[i+1]的子树
    孩子数: ceil(m/2) ~ m
    key数: ceil(m/2)-1 ~ m-1
     */

    public BNode root;
    int t;//最小度数
    final int MIN_keyNumber;//最小key数
    final int MAX_keyNumber;//最大key数

    public BTree() {
        this(2);
    }

    public BTree(int t) {
        this.t = t;
        root = new BNode(t);
        MAX_keyNumber = 2 * t - 1;
        MIN_keyNumber = t - 1;
    }

    public boolean contains(int key) {
        return root.get(key) != null;
    }

    public void put(int key, Object value) {
        doPut(root, key, value, null, 0);
    }

    /**
     @param node   待查找的节点
     @param key    插入位置
     @param value  插入值
     @param parent 父节点
     @param index  分裂时node节点的中间key插入到parent的位置
     */
    private void doPut(BNode node, int key, Object value, BNode parent, int index) {
        int i = 0;//寻找插入位置
        while (i < node.keyNumber) {
            if (node.keys[i] == key) {
                node.values[i] = value;//找到相同的key,更新
                return;
            }
            if (node.keys[i] > key) {//遇到更大的key,需要到下一层寻找
                break;
            }
            i++;
        }
        if (node.leaf) {//已经到叶子节点,直接插入
            node.insertValue(value, i);
            node.insertKey(key, i);
        } else {
            //非叶子情况,到下一层寻找孩子节点
            doPut(node.children[i], key, value, node, i);
        }
        //检查分裂
        if (node.keyNumber == MAX_keyNumber) {
            split(node, parent, index);
        }
    }

    /**
     分裂,找到中间的key进行三段拆分<br>
     左侧不动,中间节点移动到父节点的index处,右侧的作为新节点插入到index+1处<br>
     例:<br>
     [[2] | [1],[3,4,5]-->[[2,4] | [1],[3],[5]]<br>

     @param left   要分裂的节点
     @param parent 父亲节点
     @param index  要分裂的节点是children[index]
     */
    public void split(BNode left, BNode parent, int index) {
        if (left == root) {//分裂根节点,创建新根取代原根
            BNode newRoot = new BNode(t);
            newRoot.leaf = false;
            newRoot.insertChild(left, 0);
            root = newRoot;
            parent = newRoot;
        }
        BNode right = new BNode(t);
        right.leaf = left.leaf;//新节点要插入到parent的children中,所以是同一层的
        System.arraycopy(left.keys, t, right.keys, 0, t - 1);//t-1为中间索引
        System.arraycopy(left.values, t, right.values, 0, t - 1);
        if (!left.leaf) {
            //非叶子节点的分裂需要把t以后的孩子节点也拷贝过去
            System.arraycopy(left.children, t, right.children, 0, t);//t-1为中间索引
            for (int i = t; i <= left.keyNumber; i++) {
                left.children[i] = null;
            }
        }
        left.keyNumber = t - 1;
        right.keyNumber = t - 1;
        //插入中间节点
        int middleKey = left.keys[t - 1];
        Object middleValue = left.values[t - 1];
        parent.insertValue(middleValue, index);
        parent.insertKey(middleKey, index);
        parent.insertChild(right, index + 1);//右节点变孩子
    }


    public void remove(int key) {
        doRemove(null, root, 0, key);
    }

    /**
     1.叶子,没找到    return<br>
     2.叶子,找到     直接删除<br>
     3.非叶子,没找到   到下一层找<br>
     4.非叶子,找到    李代桃僵删后继<br>
     5.删除后keyNumber<MIN,不平衡<br>
     6.根节点<br>

     @param parent 父节点
     @param node   正在查找的节点
     @param index  正在查找的节点是parent.children[index]
     @param key    查找删除节点的key
     */
    public void doRemove(BNode parent, BNode node, int index, int key) {
        int i = 0;
        while (i < node.keyNumber) {
            if (node.keys[i] >= key) {
                break;
            }
            i++;
        }
        //
        if (node.leaf) {
            if (!found(node, key, i)) {//1.叶子,没找到,直接返回
                return;
            } else {//2.叶子,找到,删除
                node.removeKey(i);
            }
        } else {
            if (!found(node, key, i)) {//3.非叶子,没找到,到下层寻找
                doRemove(node, node.children[i], i, key);
            } else {//4.非叶子,找到
                //找后继
                BNode s = node.children[i + 1];
                while (!s.leaf) {
                    s = s.children[0];
                }
                //李代桃僵
                int sKey = s.keys[0];
                node.keys[i] = sKey;
                doRemove(node, node.children[i + 1], i + 1, sKey);
            }
        }
        //依次出栈,检查平衡
        if (node.keyNumber < MIN_keyNumber) {
            balance(parent, node, index);
        }
    }

    /**
     旋转:被调整节点借父亲的key,父亲的key从左兄弟或右兄弟借key,如果兄弟有孩子,则将孩子过继给被调整节点<br>
     合并:左右兄弟不够借,进行节点合并,将父节点的对应key转移到左兄弟或者自身,然后与左兄弟或右兄弟进行合并

     @param parent 被调整节点的父节点
     @param node   被调整节点
     @param index  被调整节点是parent.children[index]
     */
    public void balance(BNode parent, BNode node, int index) {
        if (node == root) {
            //合并节点会使父节点key少一个
            //根节点key==0时需要使孩子成为根,没有孩子则不变
            if (root.keyNumber == 0 && root.children[0] != null) {
                root = root.children[0];
            }
            return;
        }
        //旋转
        BNode left = parent.childLeftBrother(index);
        BNode right = parent.childRightBrother(index);
        if (left != null && left.keyNumber > MIN_keyNumber) {//左边兄弟富裕,右旋借key
            //兄弟的一个key上去,父亲的一个key下来补足自身
            node.insertValue(parent.values[index - 1], 0);
            node.insertKey(parent.keys[index - 1], 0);//index-1为node的前驱key(左)
            if (!left.leaf) {//左边兄弟如果有孩子,需要将对应key的孩子也过继
                node.insertChild(left.removeRightmostChild(), 0);//left最右边的孩子过继到node的最左边
            }
            parent.values[index - 1] = left.removeRightmostValue();
            parent.keys[index - 1] = left.removeRightmostKey();//父亲的key补齐
            return;
        }
        if (right != null && right.keyNumber > MIN_keyNumber) { //右边兄弟富裕,左旋借key
            //兄弟的一个key上去,父亲的一个key下来补足自身
            node.insertValue(parent.values[index], node.keyNumber);
            node.insertKey(parent.keys[index], node.keyNumber);//index指向为node的key(右)
            if (!right.leaf) {//右边兄弟如果有孩子,需要将对应key的孩子也过继
                node.insertChild(right.removeLeftmostChild(), node.keyNumber);//right最左边的孩子过继到node的最右边
            }
            parent.values[index] = right.removeLeftmostValue();
            parent.keys[index] = right.removeLeftmostKey();
            return;
        }
        //合并
        if (left != null) {//自己向左兄弟合并
            // 拿一个父节点的key
            parent.removeChild(index);
            left.insertValue(parent.removeValue(index - 1), left.keyNumber);
            left.insertKey(parent.removeKey(index - 1), left.keyNumber);
            //复制key-value-children到left上
            node.moveToTarget(left);
        } else {//右兄弟向自己合并
            parent.removeChild(index + 1);
            node.insertValue(parent.removeValue(index), node.keyNumber);
            node.insertKey(parent.removeKey(index), node.keyNumber);
            assert right != null;//如果没有左右孩子,那么只能是根节点
            right.moveToTarget(node);
        }
        //递归在doRemove方法里做了,这里不需要递归检查
    }

    public static boolean found(BNode node, int key, int i) {
        return i < node.keyNumber && node.keys[i] == key;
    }

    public void travel() {
        doTravel(root);
    }

    public void doTravel(BNode node) {
        if (node == null) {
            return;
        }
        int i = 0;
        for (; i < node.keyNumber; i++) {
            doTravel(node.children[i]);
            System.out.println(node.keys[i]);
        }
        doTravel(node.children[i]);
    }
}
