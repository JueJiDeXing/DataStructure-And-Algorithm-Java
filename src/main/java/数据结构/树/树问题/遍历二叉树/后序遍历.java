package 数据结构.树.树问题.遍历二叉树;

import 数据结构.树.树实现.Node.TreeNode;

import java.util.Stack;
import java.util.*;

/**
 先左 再右 后根
 */
public class 后序遍历 {
    public static void main(String[] args) {
        /*
             1
          2    3
        4  5  6  7
        前序: 1 2 4 5 3 6 7
        中序: 4 2 5 1 6 3 7
        后序: 4 5 2 6 7 3 1
         */
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
        postOrder_(root);
    }

    //先左 后右 再中
    // 将节点垂直映射到水平线上
    public static void postOrder(TreeNode node) {
        if (node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.val + "\t");
    }

    /**
     非递归实现<br>
     */
    private static void postOrder_(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode pop = null;//最近一次的弹栈元素
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {//左子树未遍历完
                //System.out.println("入栈---" + curr.value);//------前序遍历输出
                stack.push(curr);
                curr = curr.left;
            } else {//左边走到头
                //后序遍历情况
                TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {  //右子树为空 或者 右子树已经处理完
                    //后序遍历,如果有右子树则不弹栈,等到右子树处理完再弹
                    //  pop为最后一次弹出的元素,且为右节点, peek.right == pop 表示为当前节点的右子树已经被抛出,即已处理过
                    pop = stack.pop();
                    System.out.println("弹栈---" + pop.val);//------后序遍历输出
                } else {
                    curr = peek.right;
                }
            }
        }
    }

    /**
     <h1>_145二叉树的后序遍历</h1>
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {//右子树遍历完了
                res.add(root.val);
                prev = root;
                root = null;
            } else {//右子树未访问
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }

    /**
     <h1>_590N叉树的后序遍历</h1>
     难度:简单
     */
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        doOrder(ans, root);
        return ans;
    }

    void doOrder(List<Integer> ans, Node node) {
        if (node == null) return;
        for (Node c : node.children) {
            doOrder(ans, c);
        }
        ans.add(node.val);
    }

    static class Node {
        public int val;
        public List<Node> children;
    }
}



