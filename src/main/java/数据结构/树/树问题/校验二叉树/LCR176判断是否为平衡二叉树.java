package 数据结构.树.树问题.校验二叉树;

import 数据结构.树.树实现.Node.TreeNode;

public class LCR176判断是否为平衡二叉树 {
    /*
    输入一棵二叉树的根节点，判断该树是不是平衡二叉树。
    如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
     */
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode node) {
        if (node == null) return 0;
        int leftH = getHeight(node.left);
        if (leftH == -1) return -1; // 提前退出，不再递归
        int rightH = getHeight(node.right);
        if (rightH == -1 || Math.abs(leftH - rightH) > 1) return -1;
        return Math.max(leftH, rightH) + 1;
    }


    public boolean isBalanced_(TreeNode root) {
        if (root == null) {//空节点,平衡
            return true;
        }
        if (root.left == null && root.right == null) {//叶子节点平衡
            root.val = 1;//高度为1
            return true;
        }
        //搜索左右子树是否都平衡
        boolean ans = isBalanced_(root.left) && isBalanced_(root.right);
        if (!ans) {//左子树或右子树不平衡
            return false;
        }
        //回溯
        //判断本节点是否平衡
        int leftHeight = root.left == null ? 0 : root.left.val;
        int rightHeight = root.right == null ? 0 : root.right.val;
        if (Math.abs(leftHeight - rightHeight) > 1) {//如果左右高度差大于1,返回false
            return false;
        }
        //平衡则计算本节点高度
        root.val = Math.max(leftHeight, rightHeight) + 1;
        return true;
    }
}
