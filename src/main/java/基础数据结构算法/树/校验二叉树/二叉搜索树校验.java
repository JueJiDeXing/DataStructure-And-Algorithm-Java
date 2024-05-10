package 基础数据结构算法.树.校验二叉树;

import 数据结构实现.树.Node.TreeNode;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

public class 二叉搜索树校验 {
    //判断一颗树是否为二叉搜索树
    // 中序遍历-非递归
    public static boolean isValidBST1(TreeNode node) {
        TreeNode p = node;
        LinkedList<TreeNode> stack = new LinkedList<>();
        long prev = Long.MIN_VALUE;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode pop = stack.pop();
                if (prev >= pop.val) {
                    return false;
                }
                prev = pop.val;
                p = pop.right;
            }
        }
        return true;
    }

    // 中序遍历-全局变量-递归实现
    static long prev = Long.MIN_VALUE;

    public static boolean isValidBST2(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (!isValidBST2(node.left)) {
            return false;
        }
        if (prev >= node.val) {
            //在多路递归里,如果把这里的prev作为参数传递,会造成变量不同步
            // 解决方案: 把prev设为全局变量 或 把prev设置为对象类型new AtomicLong(Long.MIN_VALUE)
            return false;
        }
        prev = node.val;
        return isValidBST2(node.right);
    }

    // 中序遍历-非全局变量-递归实现
    public static boolean isValidBST3(TreeNode node) {
        return doValid3(node, new AtomicLong(Long.MIN_VALUE));
    }

    private static boolean doValid3(TreeNode node, AtomicLong prev) {
        if (node == null) {
            return true;
        }
        boolean isValidLeft = doValid3(node.left, prev);
        if (!isValidLeft) {
            return false;
        }
        if (prev.get() >= node.val) {
            //在多路递归里,如果把这里的prev作为参数传递,会造成变量不同步
            // 解决方案: 把prev设为全局变量 或 把prev设置为对象类型new AtomicLong(Long.MIN_VALUE)
            return false;
        }
        prev.set(node.val);
        return doValid3(node.right, prev);
    }

    // 上下限递归
    public boolean isValidBST4(TreeNode node) {
        return doValid4(node, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean doValid4(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return doValid4(node.left, min, node.val) && doValid4(node.right, node.val, max);
    }
}
