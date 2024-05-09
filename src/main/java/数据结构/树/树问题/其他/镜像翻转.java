package 数据结构.树.树问题.其他;

import 数据结构.树.树实现.Node.TreeNode;

public class 镜像翻转 {
    //将二叉树镜像翻转

    //交换左右子树
    public TreeNode invertTree(TreeNode root) {
        fn(root);
        return root;
    }

    private static void fn(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        fn(node.left);
        fn(node.right);
    }
}
