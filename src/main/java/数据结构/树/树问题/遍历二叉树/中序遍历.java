package 数据结构.树.树问题.遍历二叉树;

import 数据结构.树.树实现.Node.TreeNode;

import java.util.*;

/**
 先左 再根 后右
 */
public class 中序遍历 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                1,
                new TreeNode(
                        2,
                        new TreeNode(4),
                        new TreeNode(5)
                ),
                new TreeNode(
                        3,
                        new TreeNode(6),
                        new TreeNode(7)
                )
        );
        inOrder(root);
    }

    //先左 再中 后右
    // 将节点垂直映射到水平线上
    public static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.val + "\t");
        inOrder(node.right);
    }

    /**
     非递归实现<br>
     遍历左子树,依次入栈(包括该节点),如果左子树走到头则弹出该节点,并从该节点的右子树开始再次遍历
     */
    public static void inOrder2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {//左子树未遍历完
                stack.push(curr);
                curr = curr.left;
            } else {//左边走到头
                TreeNode pop = stack.pop();
                System.out.println("弹栈---" + pop.val);//------中序遍历输出
                curr = pop.right;//开始遍历上个节点的右子树
            }
        }
    }

    /**
     <h1>_94二叉树的中序遍历</h1>

     @param root
     @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
}
