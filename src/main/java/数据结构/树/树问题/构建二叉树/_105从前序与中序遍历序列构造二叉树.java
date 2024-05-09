package 数据结构.树.树问题.构建二叉树;

import 数据结构.树.树实现.Node.TreeNode;

/**
 难度:中等
 */
public class _105从前序与中序遍历序列构造二叉树 {
    /*
    给定两个整数数组 preorder 和 inorder ，
    其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，
    请构造二叉树并返回其根节点
     */
    /*
    示例:
        preOrder={1,2,4,3,6,7} // 输入的节点值是不重复的
        inOrder={4,2,1,6,3,7}
    树:
             1
          2     3
        4      6  7
    思路:前序遍历第一个为根,根节点在中序遍历时,左边为左子树,右边为右子树,然后分解为2个子问题

     示例:
         输入:preOrder={1,2,4,3,6,7},inOrder={4,2,1,6,3,7}
         根为1
         pre            in
     左  2,4            4,2
     右  3,6,7          6,3,7

     左: preOrder={2,4},inOrder={4,2}   右:preOrder={3,6,7},inOrder={6,3,7}
        根为2,根据中序,4为2的左子树            根为3,根据中序,6为3的左子树,7为右子树
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    /**
     返回由前序遍历preorder和中序遍历inorder构建的二叉树子树
     preorder[pl,pr]  inorder[il,ir]为子树范围
     */
    TreeNode build(int[] preorder, int pl, int pr, int[] inorder, int il, int ir) {
        if (pl > pr || il > ir) return null;
        //前序遍历第一个为根节点
        int rootValue = preorder[pl];
        TreeNode root = new TreeNode(rootValue);
        int idx = il;
        while (inorder[idx] != rootValue) idx++;//寻找根节点在中序遍历的位置
        //中序遍历,根左边为左子树,右边为右子树,前序遍历子树长度与中序长度报持一致
        root.left = build(preorder, pl + 1, pl + idx - il, inorder, il, idx - 1);//
        root.right = build(preorder, pl + idx - il + 1, pr, inorder, idx + 1, ir);
        return root;
    }

    //优化----------
    /*
    pre root la...lm rb...rn
    in  lc...lm root rd...rn
    */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return build(preorder, inorder, 3001);
    }

    int pre = 0,in = 0;

    /**
     @param preorder 前序遍历
     @param inorder  中序遍历
     @param stop     当前寻找的根节点值,初始值为3001表示查找整个树
     @return 返回从索引in到中序遍历到stop停止的子树的根节点
     */
    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (pre == preorder.length) return null;//树查找完了
        if (inorder[in] == stop) {//3.找到最小子树根节点,停止
            in++;
            return null;
        }
        int root_val = preorder[pre];//1.取当前子树根节点
        TreeNode root = new TreeNode(root_val);
        pre++;
        //中序遍历的根节点左边为左子树,右边为右子树
        root.left = build(preorder, inorder, root_val);//2.在inorder寻找根节点(左)
        root.right = build(preorder, inorder, stop);
        return root;
    }
}
