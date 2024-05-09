package 数据结构.树.树实现.平衡搜索树合集;

import 数据结构.树.树实现.Node.AVLNode;

/**
 在插入/删除节点时通过旋转保持平衡的自平衡二叉搜索树<br>
 AVL是实现之一
 */
class AVLTree {
    AVLNode root;

    //左右子树高度差大于1则旋转
    private int getHeight(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    private void updateHeight(AVLNode node) {
        int heightLeft = getHeight(node.left);
        int heightRight = getHeight(node.right);
        node.height = Integer.max(heightLeft, heightRight) + 1;
    }

    private int balanceFactor(AVLNode node) {
        // -1,0,1表示平衡
        // <-1 表示右重
        // >1 表示左重
        return getHeight(node.left) - getHeight(node.right);
    }

    /*不平衡情况
      LL:失衡节点左边高,左孩子左边高或等高   需要右旋
                 5                        3
             3        6     右旋       2       5
          2    4            -->     1       4    6
        1
      LR:失衡节点左边高,左孩子右边高     需要对左子树左旋,再右旋
                 6                         6                    4
            2         7    先左旋       4        7   再右旋    2       6
        1      4           -->      2     5         -->    1   3    5   7
             3   5                1   3
      RL:失衡节点右边高,左孩子左边高     需要对右子树左旋,再左旋
      RR:失衡节点右边高,左孩子右边高或等高      需要左旋
     */
    private AVLNode rightRotate(AVLNode downNode) {
        AVLNode upNode = downNode.left;
        downNode.left = upNode.right;//给上位节点的右孩子换爹
        upNode.right = downNode;//上位
        updateHeight(downNode);//旋转只会改变上位与下潜的两个节点的高度
        updateHeight(upNode);//必须先更新下面节点的高度,再更新上面节点的高度
        return upNode;
    }

    private AVLNode leftRotate(AVLNode downNode) {
        AVLNode upNode = downNode.right;
        downNode.right = upNode.left;
        upNode.left = downNode;
        updateHeight(downNode);
        updateHeight(upNode);
        return upNode;
    }

    private AVLNode leftRightRotate(AVLNode node) {
        leftRotate(node.left);
        return rightRotate(node);
    }

    private AVLNode rightLeftRotate(AVLNode node) {
        rightRotate(node.right);
        return leftRotate(node);
    }

    private AVLNode balance(AVLNode node) {
        if (node == null) {
            return null;
        }
        int bf = balanceFactor(node);
        if (bf > 1) {//左高
            int bfLeft = balanceFactor(node.left);
            if (bfLeft >= 0) {//LL
                return rightRotate(node);
            } else {//LR
                return leftRightRotate(node);
            }
        } else if (bf < -1) {//右高
            int bfRight = balanceFactor(node.right);
            if (bfRight <= 0) {//RR
                return leftRotate(node);
            } else {//RL
                return rightLeftRotate(node);
            }
        } else {
            return node;
        }
    }

    /**
     添加一个节点(键值对)到AVL树
     */
    public void put(int key, Object value) {
        root = doPut(root, key, value);
    }

    private AVLNode doPut(AVLNode node, int key, Object value) {
        //1.找到空位
        if (node == null) {
            return new AVLNode(key, value);
        }
        //2.已经存在key,更新节点值
        if (key == node.key) {
            node.value = value;
        }
        //3.还没找到,继续查找
        if (key < node.key) {
            node.left = doPut(node.left, key, value);//向左
        } else {
            node.right = doPut(node.right, key, value);//向右
        }
        //递归出栈
        updateHeight(node);//更新高度
        return balance(node);//检查失衡,返回子树根节点
    }

    /**
     从AVL树中删除一个节点(根据key)
     */
    public void remove(int key) {
        root = doRemove(root, key);
    }

    private AVLNode doRemove(AVLNode node, int key) {
        // 1. node==null
        if (node == null) {
            return null;
        }
        // 2. 没找到key,继续查找
        if (key < node.key) {
            node.left = doRemove(node.left, key);
        } else if (key > node.key) {
            node.right = doRemove(node.right, key);
        } else {
            // 3. 找到key
            if (node.left == null && node.right == null) { // (1)没有孩子,返回null
                return null;
            } else if (node.left == null) { // (2)只有一个孩子,返回该孩子
                node = node.right; //右子树需要检查平衡,所以不立刻返回,而是暂存到node里
            } else if (node.right == null) {
                node = node.left;//暂存
            } else { // (3)有两个孩子,后继上位,返回后继
                //找后继
                AVLNode s = node.right;
                while (s.left != null) {
                    s = s.left;
                }
                //后继上位
                s.right = doRemove(node.right, s.key);//处理后继的后任
                s.left = node.left;
                node = s;//暂存
            }
        }
        //递归出栈
        updateHeight(node);//更新高度
        return balance(node);//检查失衡,返回子树根节点
    }
}
