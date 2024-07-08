package 数据结构实现.树.平衡搜索树合集;

import 数据结构实现.树.Node.RBNode;
import 数据结构实现.队列.链队列.LinkedListQueue;

import java.util.ArrayList;
import java.util.List;

import static 数据结构实现.树.Node.RBNode.Color.BLACK;
import static 数据结构实现.树.Node.RBNode.Color.RED;

//已测试:src.java.数据结构与算法.基础数据结构算法.树.平衡搜索树合集.TestRBTree

/**
 <h1>红黑树</h1>
 一种平衡搜索树,旋转次数少于AVL树
 */
public class RBTree {

    /*
    红黑规则:
    1. 每个节点都有两种颜色:红与黑
    2. 根节点是黑色的
    3. 所有叶子节点(null/nil)都被视为黑色
    4. 红色节点不能相邻
    5. 从根节点到任意一个叶子节点的最短路径中黑色节点数量相同
    (忽略null节点,黑色的叶子节点必须成对出现[有兄弟])
     */

    public RBNode root;

    public RBTree() {
    }

    public RBTree(RBNode root) {
        root.color = BLACK;
        this.root = root;
    }

    //判断节点颜色
    boolean isRed(RBNode node) {
        return node != null && node.color == RED;
    }

    boolean isBlack(RBNode node) {
        return node == null || node.color == BLACK;//叶子(空节点)默认为黑
    }

    public boolean contains(int key) {
        return find(key) != null;
    }

    //旋转
    // 不同点: 需要处理parent ,旋转后的父子关系需要在函数内完成

    /**
     左旋

     @param downNode 旋转下降节点
     */
    private void leftRotate(RBNode downNode) {
        RBNode upNode = downNode.right;//上位节点
        RBNode parentNode = downNode.parent;//更上层节点
        RBNode leftChild = upNode.left;//上位节点的左孩子
        //左孩子托孤
        downNode.right = leftChild;
        if (leftChild != null) {
            leftChild.parent = downNode;
        }
        //上位
        upNode.left = downNode;
        upNode.parent = parentNode;
        downNode.parent = upNode;

        if (parentNode == null) {//新根
            root = upNode;
        } else if (parentNode.left == downNode) {
            parentNode.left = upNode;
        } else {
            parentNode.right = upNode;
        }
    }

    /**
     右旋
     */
    private void rightRotate(RBNode downNode) {
        RBNode upNode = downNode.left; //需要上位的节点
        RBNode parentNode = downNode.parent;//下降节点的父节点
        RBNode rightChild = upNode.right;//上位节点的右孩子
        //右孩子托孤
        downNode.left = rightChild;
        if (rightChild != null) {
            rightChild.parent = downNode;
        }
        //上位
        upNode.right = downNode;
        upNode.parent = parentNode;
        downNode.parent = upNode;

        if (parentNode == null) {//新根
            root = upNode;
        } else if (parentNode.left == downNode) {
            parentNode.left = upNode;
        } else {
            parentNode.right = upNode;
        }
    }

    /**
     旋转
     */
    private void rotate(RBNode downNode, boolean isLeft) {
        RBNode upNode = downNode.right;//上位节点
        RBNode parentNode = downNode.parent;//更上层节点

        RBNode child = isLeft ? upNode.left : upNode.right;//上位节点左孩子
        //左孩子托孤
        if (isLeft) {
            downNode.right = child;
        } else {
            downNode.left = child;
        }
        if (child != null) {
            child.parent = downNode;
        }
        //上位
        if (isLeft) {
            upNode.left = downNode;
        } else {
            upNode.right = downNode;
        }
        upNode.parent = parentNode;
        downNode.parent = upNode;

        if (parentNode == null) {//新根
            root = upNode;
        } else if (parentNode.left == downNode) {
            parentNode.left = upNode;
        } else {
            parentNode.right = upNode;
        }
    }

    /**
     <h1>插入节点</h1>
     如果节点已存在则更新节点
     如果遇到红红不平衡则需要调整
     */
    public void put(int key, Object value) {
        RBNode p = root;
        RBNode parent = null;
        while (p != null) {//查找插入位置
            parent = p;
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                p.value = value;//有则直接更新值
                return;
            }
        }
        //插入
        RBNode inserted = new RBNode(key, value);
        if (parent == null) {
            root = inserted;
        } else if (key < parent.key) {
            parent.left = inserted;
        } else {
            parent.right = inserted;
        }
        inserted.parent = parent;
        fixRedRed(inserted);//检查红黑规则-红红不能相邻
    }

    /**
     <h1>检查插入节点,如果红红不平衡则修正</h1>

     <ul>
     <li> case 1 : 插入为根. <br>
     节点染黑</li>
     <li> case 2 : 父节点为黑.   <br>
     不需要改变 </li>
     <li> case 3 : 父节点为红.   <br>
     触发红红不平衡</li>
     <ul>
     <li> case 3.1 : 叔叔为红.  <br>
     将父和叔染黑, 祖父染红, 递归判断祖父</li>
     <li> case 3.2 : 叔叔为黑.  <br>
     需要旋转保持平衡</li>
     <ul>
     <li> case 3.2.1 : 父亲(红)为左孩子, 节点(红)为左孩子. (LL) <br>
     将父亲变黑, 祖父变红, 叔叔分支黑色减少, 右旋祖父, 父亲上位补黑 </li>
     <li> case 3.2.2 : 父亲为左孩子, 节点为右孩子. (LR) <br>
     左旋父节点, 回到3.2.1情况 (旋转后, 节点变为新的父节点) </li>
     <p>
     <li> case 3.2.3 : 父亲为右孩子,节点为右孩子. (RR) <br>
     将父亲变黑, 祖父变红, 叔叔分支黑色减少, 左旋祖父, 父亲上位补黑</li>
     <li> case 3.2.4 : 父亲为右孩子,节点为左孩子.  (RL) <br>
     右旋父节点, 回到 3.2.3 情况 (旋转后, 节点变为新的父节点) </li>
     </ul></li>
     </ul>
     </ul>

     @param node 插入的节点
     */
    void fixRedRed(RBNode node) {
        if (node == root) {// 1.根节点,将节点变黑
            node.color = BLACK;
            return;
        }
        if (isBlack(node.parent)) {// 2.插入节点的父节点为黑色,不需要调整
            return;
        }
        // 3.父亲为红,触发红红相邻,需要调整
        RBNode parent = node.parent;
        RBNode uncle = node.uncle();
        RBNode grandparent = parent.parent;
        // 3.1 叔叔为红
        if (isRed(uncle)) {
            // 将父亲变黑
            parent.color = BLACK;
            // 叔叔为红,为保持平衡,将叔叔也改为黑色
            uncle.color = BLACK;
            // 子树黑色过多,需要将祖父变为红色,此时可能会触发祖父与曾祖父的红红相邻
            grandparent.color = RED;
            fixRedRed(grandparent);//可以递归求解,一直到根节点变黑
            return;
        }
        // 3.2 叔叔为黑
        if (parent.isLeftChild()) {
            if (!node.isLeftChild()) { //LR
                // 对父亲左旋,从LR变为LL的状态
                leftRotate(parent);
                parent = node;//注意:节点上位后,该节点变为父节点
            }
            //父亲变黑,祖父变红
            parent.color = BLACK;
            grandparent.color = RED;
            // 此时由于祖父变红,叔叔分支黑色减少
            rightRotate(grandparent);// 需要右旋将祖父拉下来,父亲上位填补黑色
        } else {
            if (node.isLeftChild()) {//RL
                rightRotate(parent);
                parent = node;
            }
            //RR
            parent.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        }
    }

    /**
     <h1>删除节点</h1>
     如果遇到黑黑不平衡则需要调整
     */
    public void remove(int key) {
        RBNode deleted = find(key);
        if (deleted == null) {
            return;//节点不存在
        }
        doRemove(deleted);
    }

    /**
     <h1>查找节点</h1>
     */
    RBNode find(int key) {
        RBNode p = root;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    /**
     <h1>(删除时)查找替换节点</h1>
     没有孩子返回null,有一个孩子返回孩子节点,有两个孩子返回后继
     */
    RBNode findReplaced(RBNode deleted) {
        if (deleted.left == null && deleted.right == null) {//没有孩子返回null,
            return null;
        }
        if (deleted.left == null) {//一个孩子返回孩子节点
            return deleted.right;
        }
        if (deleted.right == null) {
            return deleted.left;
        }
        //有两个孩子,查找后继
        RBNode s = deleted.right;//右子树的最左侧节点
        while (s.left != null) {
            s = s.left;
        }
        return s;
    }

    /**
     <h1>删除节点</h1>
     主要思想: 李代桃僵, 与后继交换后删除本节点
     <ul>
     <li> case 1:要删除节点没有孩子<br>
     <ul>
     <li> case 1.1 : 删除根.<br>
     将root置空</li>
     <li> case 1.2 : 非根.<br>
     检查节点颜色,如果为黑则触发双黑(少一个黑),调整颜色<br>
     将父节点的对应孩子置空即可
     </li>
     </ul>
     <li> case 2 : 有一个孩子</li>
     <ul>
     <li> case 2.1 : 删除为根<br>
     根节点如果只有一个孩子,那么孩子一定为红色<br>
     将根节点与孩子节点进行键值交换,再删除孩子即可</li>
     <li> case 2.2 : 非根<br>
     父节点的对应孩子指针指向替换节点,替换节点父指针指向父节点,删除节点脱离树<br>
     判断删除节点和替换节点颜色<br>
     case 1: 如果都为黑, 则说明删除了一个黑, 触发双黑不平衡<br>
     case 2: 如果一红一黑, 删黑剩红,将替换节点(剩)置黑; 删红剩黑,平衡,不用改变<br>
     // 不可能两红,因为case2中删除节点只有一个孩子(替换节点),相邻不可能都为红,否则树是不平衡的
     </li>
     </ul>
     </li>
     <li> case 3 : 有两个孩子<br>
     将被删除节点与它的后继键值交换,然后递归删除后继节点即可
     </ul>
     */
    void doRemove(RBNode deleted) {
        RBNode replaced = findReplaced(deleted);//查找后继
        RBNode parent = deleted.parent;
        // 1.没有孩子
        if (replaced == null) {
            if (deleted == root) { //删除的为根节点
                root = null;
            } else {//非根节点(叶子)
                // 颜色检查
                if (isBlack(deleted)) {
                    //要删除的为黑色节点,触发双黑(少了一个黑色)
                    fixDoubleBlack(deleted);
                    //删除红色节点叶子不需要考虑平衡
                }

                if (deleted.isLeftChild()) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                deleted.parent = null;//垃圾回收
            }
            return;
        }
        // 2.有一个孩子
        if (deleted.left == null || deleted.right == null) {
            if (deleted == root) { //删除的为根节点
                //根节点如果只有一个孩子,那么孩子一定为红色
                // 李代桃僵:将根节点与孩子节点进行键值交换,再删除孩子
                root.key = replaced.key;
                root.value = replaced.value;
                root.left = root.right = null;
            } else {//非根节点
                if (deleted.isLeftChild()) {
                    parent.left = replaced;
                } else {
                    parent.right = replaced;
                }
                replaced.parent = parent;
                deleted.left = deleted.right = deleted.parent = null;//垃圾回收
                if (isBlack(deleted) && isBlack(replaced)) {
                    //删黑,剩黑,触发双黑(少了一个节点)
                    fixDoubleBlack(replaced);//因为先调整的位置,所以传入replaced
                } else {
                    // 若删黑剩红,将替换节点置黑; 若删红剩黑,则是平衡的,不用改变
                    replaced.color = BLACK;
                }
                // 不可能两红,因为删除节点只有一个孩子(替换节点),相邻不可能都为红,否则树是不平衡的
            }
            return;
        }
        // 3.有两个孩子 to case 1 or case 2
        // 李代桃僵:将待删除节点与它的后继进行键值交换,再删除后继(递归走上面的没有孩子和只有一个孩子的分支)
        int tempKey = deleted.key;
        Object tempValue = deleted.value;
        deleted.key = replaced.key;
        deleted.value = replaced.value;
        replaced.key = tempKey;
        replaced.value = tempValue;

        doRemove(replaced);
    }

    /**
     <h1>双黑不平衡(少一个黑)</h1>
     本节点为黑,但是子树仍少一个黑
     <ul>
     <li> case 1 : 被调整节点的兄弟为红<br>
     由于红红不相邻规则, 此时两个侄子定为黑<br>
     可对父节点通过一次旋转(向自己方向), 使自己获取一个侄子<br>
     即拥有一个黑色兄弟, 然后过渡到下面的两个情况
     </li>
     <li> case 2 : 兄弟为黑<br>
     <ul>
     <li> case 2.1 : 两个侄子都为黑<br>
     将兄弟变红, 使兄弟分支也缺一个黑<br>
     如果此时父节点为红, 那么父节点可以填补两个分支的黑<br>
     如果父节点为黑, 那么整个子树少一个黑, 父节点进入递归</li>
     <li> case 2.2 : 两个侄子不都为黑</li>
     <ul>
     <li> case 2.2.1 : LL, 兄弟为左孩子, 左侄子为红<br>
     对父节点右旋, 使兄弟上位, 兄弟取代父亲(颜色), 并将左侄子置黑<br>
     如果父亲为黑, 则兄弟上位后补足了自己的黑, 右侄子分支被托孤后黑数不变<br>
     如果父亲为红, 则兄弟上位后变红, 为了补足黑色, 需要将父亲置黑<br>
     (总的来说,父节点直接置黑)</li>
     <li> case 2.2.2 : LR, 兄弟为左孩子,右侄子为红<br>
     红色侄子取代父亲(颜色)<br>
     对兄弟左旋, 父亲右旋, 右侄子上位<br>
     如果父亲为黑, 则侄子变黑, 兄弟分支不变, 自己分支被侄子补齐<br>
     如果父亲为红, 则侄子变红, 兄弟分支不变, 自己分支仍缺一个黑, 将父节点置黑<br>
     (总的来说,父节点直接置黑)</li>
     <li> case 2.2.2 : RR, 兄弟为右孩子, 右侄子为红<br>
     对父节点左旋, 使兄弟上位, 兄弟取代父亲(颜色), 并将右侄子置黑<br>
     如果父亲为黑, 则兄弟上位后补足了自己的黑, 左侄子分支被托孤后黑数不变<br>
     如果父亲为红, 则兄弟上位后变红, 为了补足黑色, 需要将父亲置黑<br>
     (总的来说,父节点直接置黑)</li>
     <li> case 2.2.3 : RL , 兄弟为右孩子, 左侄子为红<br>
     红色侄子取代父亲(颜色)<br>
     对兄弟左旋, 父亲右旋, 左侄子上位<br>
     如果父亲为黑, 则侄子变黑, 兄弟分支不变, 自己分支被侄子补齐<br>
     如果父亲为红, 则侄子变红, 兄弟分支不变, 自己分支仍缺一个黑, 将父节点置黑<br>
     (总的来说,父节点直接置黑)</li>
     </ul>
     </li>
     </ul>
     </ul>
     */
    private void fixDoubleBlack(RBNode node) {
        if (node == root) {//递归到根节点不需要调整
            return;
        }
        RBNode parent = node.parent;
        RBNode brother = node.brother();
        //1.被调整节点的兄弟为红
        if (isRed(brother)) {
            //对父亲左旋or右旋,捕获一个黑色侄子成为自己的兄弟
            if (node.isLeftChild()) {
                leftRotate(parent);
            } else {
                rightRotate(parent);
            }
            //因为原兄弟为红,所以父亲一定为黑色
            parent.color = RED;//将父亲置空,兄弟置黑
            brother.color = BLACK;
            fixDoubleBlack(node);//此时兄弟分支保持平衡原样,自身分支保持原样缺少一个黑,进入递归
            return;
        }
        if (brother == null) {// 不可能发生的情况
            // 兄弟为null,此时有两种情况,父为黑和父为红(双黑函数,自身一定为黑),这两种情况都是不平衡的树
            fixDoubleBlack(parent);
            return;
        }
        //2.兄弟为黑,两个侄子都为黑
        if (isBlack(brother.left) && isBlack(brother.right)) {
            //将兄弟变红,使兄弟分支也缺一个黑
            brother.color = RED;
            if (isRed(parent)) {// 如果父亲为红,则直接用父亲填补即可
                parent.color = BLACK;
            } else {
                fixDoubleBlack(parent);// 如果父亲为黑,则该路径依然少一个黑色,父亲进入递归
            }
        }
        //3.兄弟为黑,两个侄子不都为黑
        else {
            if (brother.isLeftChild()) {
                //LL
                if (isRed(brother.left)) {
                    //右旋,兄弟上位
                    rightRotate(parent);
                    brother.left.color = BLACK;//侄子填补左边黑色(因为兄弟取代父亲,如果父亲为黑,则左边少一个黑,如果父亲为红,则兄弟变红,左边少一个黑)
                    brother.color = parent.color;//兄弟取代父亲
                }//LR
                else {
                    //兄弟为黑不用变颜色,旋转后兄侄关系改变,所以将变色放在旋转之前
                    brother.right.color = parent.color;//红色侄子取代父亲,填补右侧黑色
                    leftRotate(brother);
                    rightRotate(parent);
                }
            } else {
                //RL
                if (isRed(brother.left)) {
                    brother.left.color = parent.color;
                    rightRotate(brother);
                    leftRotate(parent);
                }
                //RR
                else {
                    leftRotate(parent);
                    brother.right.color = BLACK;
                    brother.color = parent.color;
                }
            }
            //提取出的公共部分
            parent.color = BLACK;//父亲填补黑色(兄弟替换父亲的颜色,那么右树因为要删除黑色节点所以会少一个黑色)
        }
    }

    /**
     层序遍历
     */
    public static List<List<Object>> levelOrder(RBNode root) {
        List<List<Object>> result = new ArrayList<>();//存储层序遍历的结果
        if (root == null) {
            return result;
        }
        //每次从队列中获取元素,并把左右子树添加进队列
        LinkedListQueue<RBNode> queue = new LinkedListQueue<>();
        queue.offer(root);
        int c1 = 1;//当前层数

        while (!queue.isEmpty()) {
            int c2 = 0;//下一层节点数
            List<Object> level = new ArrayList<>();
            for (int i = 0; i < c1; i++) {
                RBNode n = queue.poll();
                //System.out.print(n+" ");
                level.add(n.key + ":" + n.value + ":" + n.color);
                if (n.left != null) {
                    queue.offer(n.left);
                    c2++;//统计下一层节点数
                }
                if (n.right != null) {
                    queue.offer(n.right);
                    c2++;
                }
            }
            //System.out.println();//换行
            result.add(level);
            c1 = c2;//更新当前层的节点数
        }
        return result;
    }

    @Override
    public String toString() {
        List<List<Object>> lists = levelOrder(root);
        return lists.toString();
    }


}
