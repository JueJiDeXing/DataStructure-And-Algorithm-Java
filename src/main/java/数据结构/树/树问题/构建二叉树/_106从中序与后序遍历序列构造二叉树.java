package 数据结构.树.树问题.构建二叉树;

import 数据结构.树.树实现.Node.TreeNode;

import java.util.Arrays;

/**
 难度:中等
 */
public class _106从中序与后序遍历序列构造二叉树 {
    /*
    给定两个整数数组 inorder 和 postorder ，
    其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，
    请你构造并返回这颗 二叉树 。
     */
    /*
    示例:
        inOrder={4,2,1,6,3,7}
        postOrder={4,2,6,7,3,1}
    树:
             1
          2     3
        4      6  7
    思路:后序遍历最后一个为根,根节点在中序遍历时,左边为左子树,右边为右子树,然后分解为2个子问题

     示例:
         输入:inOrder={4,2,1,6,3,7},postOrder={4,2,6,7,3,1}
         根为1
          in         post
     左   4,2        4,2
     右   6,3,7      6,7,3

     左: inOrder={4,2},postOrder={4,2}    右:inOrder={6,3,7},postOrder={6,7,3}
        根为2,根据中序,4为2的左子树            根为3,根据中序,6为3的左子树,7为右子树
     */
    public TreeNode buildTree(int[] inOrder, int[] postOrder) {
        if (inOrder.length == 0) return null;
        //根节点
        int rootValue = postOrder[postOrder.length - 1];//后序遍历中,最后一个元素为根节点
        TreeNode root = new TreeNode(rootValue);
        //左右子树
        for (int i = 0; i < inOrder.length; i++) { //TODO 优化1:使用HashMap记录根节点,省去查找步骤
            if (inOrder[i] == rootValue) {
                int[] inLeft = Arrays.copyOfRange(inOrder, 0, i); //TODO 优化2:函数传参时直接传索引信息,不创建新数组
                int[] inRight = Arrays.copyOfRange(inOrder, i + 1, inLeft.length);
                int[] preLeft = Arrays.copyOfRange(postOrder, 0, i);
                int[] preRight = Arrays.copyOfRange(postOrder, i, postOrder.length - 1);
                root.left = buildTree(preLeft, inLeft);
                root.right = buildTree(preRight, inRight);
                break;
            }
        }
        return root;
    }

    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        int n = inorder.length;
        if (n == 0) return null;
        post = n - 1;//因为后序遍历最后一项是根节点,所以需要反向
        in = n - 1;//反向后,根据后序的左右根顺序,根的下一个是右子树,所以in也需要从右子树开始
        return build(inorder, postorder, 3001);
    }

    int in, post;

    TreeNode build(int[] inorder, int[] postorder, int bound) {
        if (in < 0 || post < 0) return null;
        if (inorder[in] == bound) {
            in--;
            return null;
        }
        int rootVal = postorder[post];
        TreeNode root = new TreeNode(rootVal);
        post--;
        root.right = build(inorder, postorder, rootVal);//先构建右子树,再构建左子树
        root.left = build(inorder, postorder, bound);
        return root;
    }
}
