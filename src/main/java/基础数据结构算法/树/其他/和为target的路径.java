package 基础数据结构算法.树.其他;

import 数据结构实现.树.Node.TreeNode;

import java.util.LinkedList;

public class 和为target的路径 {

    void findPath(TreeNode root, int target) {
        LinkedList<TreeNode> stack = new LinkedList<>();//用栈存储路径
        stack.push(root);
        search(root, target - root.val, stack);//搜索左右,+'0'是因为存储的数据为字符类型,要转为数字
    }


    void search(TreeNode node, int target, LinkedList<TreeNode> stack) {
        //当node为叶子节点,且target为0时说明找到路径
        if (node.left == null && node.right == null && target == 0) {
            printStack(stack);
            return;
        }
        //如果node为叶子节点,而target不为0
        //或者target小于等于0,而node不是叶子节点
        //出栈回溯
        if (node.left == null && node.right == null || target <= 0) {
            return;
        }

        //搜索左边
        if (node.left != null) {
            stack.push(node.left);
            search(node.left, target - node.left.val, stack);
            stack.pop();
        }
        //搜索右边
        if (node.right != null) {
            stack.push(node.right);
            search(node.right, target - node.right.val, stack);
            stack.pop();
        }

    }

    void printStack(LinkedList<TreeNode> stack) {
        System.out.print("path: ");
        int len = stack.size();
        for (int i = 0; i < len; i++) {
            System.out.print(stack.get(i).val);
            if (i != len - 1) {
                System.out.print("->");
            }
        }
        System.out.println();

    }

}
