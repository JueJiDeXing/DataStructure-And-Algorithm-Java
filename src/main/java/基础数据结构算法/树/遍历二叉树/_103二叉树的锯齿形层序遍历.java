package 基础数据结构算法.树.遍历二叉树;

import 数据结构实现.树.Node.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 难度:中等↓
 */
class _103二叉树的锯齿形层序遍历 {
    /*
    给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。
    （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();//存储层序遍历的结果
        if (root == null) return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean odd = true;
        while (!queue.isEmpty()) {
            int size=queue.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode n = queue.poll();
                boolean unused= odd? level.offerLast(n.val): level.offerFirst(n.val);
                if (n.left != null) queue.offer(n.left);
                if (n.right != null) queue.offer(n.right);
            }
            odd = !odd;//奇偶互换
            ans.add(level);
        }
        return ans;
    }
}
