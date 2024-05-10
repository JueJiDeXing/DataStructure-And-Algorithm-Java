package 基础数据结构算法.树.其他;

import 数据结构实现.树.Node.TreeNode;

import java.util.LinkedList;

public class 二叉搜索树范围和 {
    //求二叉搜索树[low,high]的和
    //中序遍历
    public int rangeSumBST1(TreeNode node, int low, int high) {
        TreeNode p = node;
        LinkedList<TreeNode> stack = new LinkedList<>();
        int sum = 0;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode pop = stack.pop();
                int value = pop.val;
                if (value > high) {//剪枝
                    break;
                }
                if (low <= value) {
                    sum += value;
                }
                p = pop.right;
            }
        }
        return sum;
    }

    //上下限递归
    public int rangeSumBST2(TreeNode node, int low, int high) {
        if (node == null) {
            return 0;
        }
        int value = node.val;
        if (value < low) {
            return rangeSumBST2(node.right, low, high);
        }
        if (value > high) {
            return rangeSumBST2(node.left, low, high);
        }
        return value + rangeSumBST2(node.left, low, high) + rangeSumBST2(node.right, low, high);
    }
}
