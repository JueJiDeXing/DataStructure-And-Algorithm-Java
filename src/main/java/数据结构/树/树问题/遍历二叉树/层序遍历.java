package 数据结构.树.树问题.遍历二叉树;

import 数据结构.树.树实现.Node.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 层序遍历 {
    /**
     <div color=rgb(155,200,80)>
     <h1>层序遍历</h1>
     按从左到右一层一层地遍历<br>
     使用队列</div>
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();//存储层序遍历的结果
        if (root == null) {
            return result;
        }
        //每次从队列中获取元素,并把左右子树添加进队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = queue.poll();
                level.add(n.val);
                if (n.left != null) queue.offer(n.left);
                if (n.right != null) queue.offer(n.right);
            }
            result.add(level);
        }
        return result;
    }

    /**
     <h1>_429N叉树的层序遍历</h1>
     难度:中等↓
     */
    public List<List<Integer>> levelOrder_N(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                list.add(node.val);
                for (Node child : node.children) {
                    queue.offer(child);
                }
            }
            ans.add(list);
        }
        return ans;
    }

    static class Node {
        public int val;
        public List<Node> children;
    }
}


