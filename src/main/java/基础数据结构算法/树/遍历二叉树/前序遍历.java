package 基础数据结构算法.树.遍历二叉树;

import 数据结构实现.树.Node.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 先根 再左 后右
 */
public class 前序遍历 {
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
        preOrder(root);
    }

    //递归版
    public static void preOrder(TreeNode node) {
        if (node == null) return;
        System.out.println(node.val + "\t");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     非递归实现<br>
     遍历左子树,依次入栈(包括该节点),如果左子树走到头则弹出该节点,并从该节点的右子树开始再次遍历
     */
    private static void preOrder_(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {//左子树未遍历完
                //System.out.println("入栈---" + curr.value);//------前序遍历输出
                stack.push(curr);
                curr = curr.left;
            } else {//左边走到头
                TreeNode pop = stack.pop();
                System.out.println("弹栈---" + pop.val);//------中序遍历输出
                curr = pop.right;//开始遍历上个节点的右子树
                /* 后序遍历情况
                 TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {  //右子树为空 或者 右子树已经处理完
                    //后序遍历,如果有右子树则不弹栈,等到右子树处理完再弹
                    //  pop为最后一次弹出的元素,且为右节点, peek.right == pop 表示为当前节点的右子树已经被抛出,即已处理过
                    pop = stack.pop();
                    System.out.println("弹栈---" + pop.value);//------后序遍历输出
                } else {
                    curr = peek.right;
                }
                 */
            }
        }
    }

    /**
     <h1>_144二叉树的前序遍历</h1>
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);//先根

            if (node.right != null) {
                stack.push(node.right);//记录右边
            }
            if (node.left != null) {
                stack.push(node.left);//优先处理左边
            }
        }
        return list;
    }

    /**
     <h1>_589N叉树的前序遍历</h1>
     难度:简单
     */
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        doOrder(ans, root);
        return ans;
    }

    void doOrder(List<Integer> ans, Node node) {
        if (node == null) return;
        ans.add(node.val);
        for (Node c : node.children) {
            doOrder(ans, c);
        }
    }

    static class Node {
        public int val;
        public List<Node> children;
    }

}
