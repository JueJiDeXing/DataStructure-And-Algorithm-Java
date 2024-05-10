package 基础数据结构算法.树.其他;


import 数据结构实现.树.Node.TreeNode;

public class 二叉树展开为链表 {
    //二叉搜索树,节点全部放右节点
    TreeNode next;

    public void flatten(TreeNode root) {
        if (root == null) return;
        //先右后左 的 后序遍历
        flatten(root.right);
        flatten(root.left);
        root.left = null;
        root.right = next;
        next = root;
    }
}
