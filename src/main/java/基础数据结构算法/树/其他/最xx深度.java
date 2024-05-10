package 基础数据结构算法.树.其他;

import 数据结构实现.树.Node.TreeNode;
import 数据结构实现.栈.MyLinkedListStackClass;

import java.util.LinkedList;
import java.util.Queue;

public class 最xx深度 {
    //最大深度----------------------------------------------------------------------

    // max(左子树深度,右子树深度)+1
    // 因为要得到左右子树的深度,显然是后序遍历的典型应用
    // 递归实现
    public int maxDepth(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;

        int d1 = maxDepth(node.left);
        int d2 = maxDepth(node.right);
        return Integer.max(d1, d2) + 1;
    }

    //非递归实现
    public int maxDepth_(TreeNode node) {
        TreeNode curr = node;
        TreeNode pop = null;
        int max = 0;//栈的最大高度
        MyLinkedListStackClass<TreeNode> stack = new MyLinkedListStackClass<>();
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                int size = stack.size;
                if (size > max) {
                    max = size;
                }
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {//右子树处理完
                    pop = stack.pop();
                } else {
                    curr = peek.right;
                }
            }
        }
        return max;
    }

    //层序遍历法
    public static int maxDepth2(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        //int c1 = 1;//当前层元素个数
        int max = 0;
        while (!queue.isEmpty()) {
            max++;
            //int c2 = 0;//下一层个数
            int size = queue.size();//用size代替c1
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                assert poll != null;
                if (poll.left != null) {
                    queue.offer(poll.left);
                    //c2++;
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                    //c2++;
                }
            }
            //c1 = c2;
            System.out.println();
        }
        return max;
    }

    //最小深度----------------------------------------------------------------------
    // min(左,右)+1
    public int minDepth(TreeNode node) {
        if (node == null) return 0;

        if (node.left == null && node.right == null) return 1;


        int d1 = minDepth(node.left);
        int d2 = minDepth(node.right);
        //深度为0不可计算
        if (d1 == 0) return d2 + 1;
        if (d2 == 0) return d1 + 1;

        return Integer.min(d1, d2) + 1;
    }

    //层序遍历,遇到的第一个叶子节点即为最小深度
    public static int minDepth2(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        //int c1 = 1;//当前层元素个数
        int min = 0;
        while (!queue.isEmpty()) {
            min++;
            //int c2 = 0;//下一层个数
            int size = queue.size();//用size代替c1
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                assert poll != null;
                if (poll.left == null && poll.right == null) {
                    return min;
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                    //c2++;
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                    //c2++;
                }
            }
            //c1 = c2;
            //System.out.println();
        }
        return min;
    }
}
