package 数据结构.树.树问题.其他;

import 数据结构.树.树实现.Node.TreeNode;

public class _147伪回文路径 {
    /*
    给你一棵二叉树，每个节点的值为 1 到 9 。
    我们称二叉树中的一条路径是 「伪回文」的，当它满足：路径经过的所有节点值的排列中，存在一个回文序列。

    请你返回从根到叶子节点的所有路径中 伪回文 路径的数目。
    例如
          2
        3    1
      3  1     1
      有[2,3,3]和[2,1,1]这两条伪回文路径
     */


    /**
     <h1>深度优先搜索</h1>
     由于数字只有1~9这些数字,所以使用9个比特位count进行记录<br>
     成对数字需要相消,使用异或运算<br>
     遇到叶子节点则判断count是否为0,或者只有一位为1(count&(count-1))==0
     */
    public int pseudoPalindromicPaths(TreeNode root) {
        dfs(root, 1 << root.val);
        return sum;
    }

    int sum = 0;

    public void dfs(TreeNode node, int curr) {
        if (node.left == null && node.right == null) {
            if (curr == 0 || (curr & (curr - 1)) == 0) {
                sum++;
            }
            return;
        }
        if (node.left != null) {
            dfs(node.left, curr ^ (1 << node.left.val));
        }
        if (node.right != null) {
            dfs(node.right, curr ^ (1 << node.right.val));
        }
    }
}
