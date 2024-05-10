package 数据结构实现.树.平衡搜索树合集;

import 数据结构实现.树.Node.BSTNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 Binary Search Tree 二叉搜索树
 */
public class BSTree {

    BSTNode root;//根节点

    public BSTree(BSTNode root) {
        this.root = root;
    }

    public BSTree() {
    }

    /**
     存储关键字对应的值

     @param key   关键字
     @param value 值
     */
    public void put(int key, Object value) {
        BSTNode node = root;
        BSTNode parent = null;//新增节点的父节点
        while (node != null) {
            parent = node;
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else {
                //有key则更新
                node.value = value;
                return;
            }
        }
        //没有key则新增节点
        if (parent == null) {
            root = new BSTNode(key, value);
            return;
        }
        if (key < parent.key) {
            parent.left = new BSTNode(key, value);
        } else {
            parent.right = new BSTNode(key, value);
        }


    }

    /**
     查找key对应的值
     递归实现

     @param key 关键字
     @return 关键字对应的值
     */
    public Object get2(int key) {
        return doGet(root, key);
    }

    /**
     递归查找
     */
    private Object doGet(BSTNode node, int key) {
        if (node == null) {
            return null;//没找到
        }
        if (key < node.key) {
            return doGet(node.left, key);//向左找,尾递归在java不支持自动优化,可以自己转写为非递归方法
        } else if (key > node.key) {
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
    public Object get(int key) {
        BSTNode node = root;
        while (node != null) {
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    /**
     查找最大关键字对应值

     @return 关键字对应的值
     */
    public Object max() {
        return max(root);
    }

    /**
     从某节点查找树最大关键字对应值

     @return 关键字对应的值
     */
    public Object max(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.right != null) {
            p = p.right;
        }
        return p.value;
    }

    /**
     查找最小关键字对应值

     @return 关键字对应的值
     */
    public Object min() {
        return min(root);
    }

    /**
     从某节点开始查找数最小关键字对应值

     @return 关键字对应的值
     */
    public Object min(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.left != null) {
            p = p.left;
        }
        return p.value;
    }

    /**
     查找前驱

     @param key 关键字
     @return 前驱值
     */
    public Object predecessor(int key) {
        BSTNode p = root;
        BSTNode ancestorFromLeft = null;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                ancestorFromLeft = p;//记录最近一次自左而来的祖先
                p = p.right;
            } else {
                break;
            }
        }//查找key对应节点
        if (p == null) {//没找到
            return null;
        }
        //找到节点

        if (p.left != null) {
            //如果节点有左子树,那么它的前驱为左子树里的最大值
            return max(p.left);
        }
        //如果没有左子树,那么它的前驱为离它最近的自左而来的祖先
        // (自左而来:节点处于某祖先节点的右子树,则将祖先称为自左而来)
        return ancestorFromLeft == null ? null : ancestorFromLeft.value;
    }

    /**
     查找后继

     @param key 关键字
     @return 后继值
     */
    public Object successor(int key) {
        BSTNode p = root;
        BSTNode ancestorFromRight = null;
        while (p != null) {
            if (key < p.key) {
                ancestorFromRight = p;//记录最近一次自右而来的祖先
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                break;
            }
        }//查找key对应节点
        if (p == null) {//没找到
            return null;
        }
        //找到节点
        if (p.right != null) {
            //如果节点有右子树,那么它的后继为右子树里的最小值
            return min(p.right);
        }
        //如果没有右子树,那么它的后继为离它最近的自右而来的祖先
        // (自右而来:节点处于某祖先节点的左子树,则将祖先称为自右而来)
        return ancestorFromRight == null ? null : ancestorFromRight.value;
    }


    /**
     根据key删除

     @param key 关键字
     @return 被删除的值
     */
    public Object delete(int key) {
        /*
        情况分析:
        1. 被删除的节点只有左孩子或右孩子,将左孩子或右孩子托孤给parent(如果没有孩子将null托孤)
        2. 被删除的节点有左右孩子,设它的后继节点为next,next的父亲为nextP (这里也可以去寻找前驱节点)
            2.1 如果nextP就是被删除的节点,则将next托孤给parent
            2.2 如果nextP不是被删除的节点,则将next的后任托孤给nextP,再将next托孤给parent
         */
        BSTNode deleted = root;//被删除的节点
        BSTNode parent = null;//记录父节点
        while (deleted != null) {
            if (key < deleted.key) {
                parent = deleted;
                deleted = deleted.left;
            } else if (key > deleted.key) {
                parent = deleted;
                deleted = deleted.right;
            } else {
                break;
            }
        }
        if (deleted == null) {
            return null;//没找到
        }
        //找到,执行删除操作
        if (deleted.left == null) {//孩子数<=1
            shift(parent, deleted, deleted.right);
        } else if (deleted.right == null) {
            shift(parent, deleted, deleted.left);
        } else {//有左右孩子
            //查找后继
            BSTNode next = deleted.right;
            BSTNode nextP = null;//后继节点的父节点
            while (next.left != null) {
                nextP = next;
                next = next.left;
            }
            //如果删除节点与后继节点不相邻,处理后继的后任
            if (deleted != nextP) {
                //这里不需要考虑左后任,因为后继是子树里的最小值,它不可能有左孩子,只可能有右孩子
                shift(nextP, next, next.right);
                next.right = deleted.right;
            }
            //后继取代被删除节点
            shift(parent, deleted, next);
            next.left = deleted.left;
        }

        return deleted.value;
    }

    /**
     托孤方法

     @param parent  父节点
     @param deleted 被删除的节点
     @param child   要托孤的孩子节点
     */
    private void shift(BSTNode parent, BSTNode deleted, BSTNode child) {
        if (parent == null) {//被删除的节点为根节点
            root = child;
        } else if (parent.left == deleted) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    /**
     根据key删除  递归实现

     @param key 关键字
     @return 被删除的值
     */
    public Object delete2(int key) {
        ArrayList<Object> result = new ArrayList<>();//保存值
        root = doDelete(root, key, result);
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     @param node   起点
     @param key    被删除的节点的key
     @param result 保存需要返回的value值
     @return 删除的节点的孩子
     */
    private BSTNode doDelete(BSTNode node, int key, ArrayList<Object> result) {
        if (node == null) {
            return null;
        }

        if (key < node.key) {//删除的节点在左子树
            node.left = doDelete(node.left, key, result);//在左子树里去删
            return node;
        }
        if (key > node.key) {
            node.right = doDelete(node.right, key, result);
            return node;
        }
        // 已经找到要删除的节点node

        result.add(node.value);
        // 1.孩子数<=1,返回删除节点的孩子
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }
        // 2.有左右孩子
        //查找后继
        BSTNode next = node.right;
        while (next.left != null) {
            next = next.left;
        }
        //调整后继的孩子关系,然后返回后继节点
        next.right = doDelete(node.right, next.key, new ArrayList<>());//处理后继的后任,相当于在右子树里删除后继,再将这个子树作为后继的右孩子
        next.left = node.left;//接替node的左孩子
        return next;
    }

    // 根据key的范围查找---核心思想:中序遍历

    /**
     查找小于key的节点值
     (-∞,key) -> list:values
     */
    public List<Object> less(int key) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {//向左子树走到头
                stack.push(p);
                p = p.left;
            } else {//走到头往回走处理右子树
                BSTNode pop = stack.pop();
                if (pop.key < key) {
                    result.add(pop.value);
                } else {//已经大于key了就不用再查找了(节点的右子树都是大于这个节点的)
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }

    /**
     查找大于key的节点的key
     (key,+∞) -> list:values
     */
    public List<Object> greater(int key) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.right;//倒序中序遍历,在查找大值时可以提前退出
            } else {
                BSTNode pop = stack.pop();
                if (pop.key > key) {
                    result.add(pop.value);
                } else {
                    break;
                }
                p = pop.left;
            }
        }
        return result;
    }

    /**
     查找所有处于key1与key2之间的节点的value<br>
     [key1,key2] -> list:values
     */
    public List<Object> between(int key1, int key2) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                if (key1 <= pop.key && pop.key <= key2) {
                    result.add(pop.value);
                } else if (pop.key > key2) {
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }
}


