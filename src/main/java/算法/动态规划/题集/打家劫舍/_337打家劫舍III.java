package 算法.动态规划.题集.打家劫舍;

import 数据结构实现.树.Node.TreeNode;

public class _337打家劫舍III {
    /*
    小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。

    除了 root 之外，每栋房子有且只有一个“父“房子与之相连。
    一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
    如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。

    给定二叉树的 root 。
    返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
     */

    /**
     限制: 树形结构, 相邻不能偷, 最高金额<br>
     对于一个节点来说, 它有两种选择, 偷或不偷<br>
     如果 node 不偷 -> 则 left和right也是两种选择,偷或不偷, 问题变为2个子问题<br>
     如果 node 偷 -> 则 left和right都不能偷,那么决定偷不偷就从再下一层开始, 问题变为4个子问题<br>
     所以可以使用递归求解 <br>
     令 dfs(node) 返回 int[2] res , res[0] 和 res[1] 分别表示 偷node 和 不偷node 的最大值<br>
     res_left = dfs(left), res_right = dfs(right) <br>
     那么 res[0] = node + res_left[1] + res_right[1], res[1] = max(res_left) + max(res_right) <br>
     */
    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]); // 根节点选或不选的最大值
    }

    /**
     返回int[2],索引0为选择node节点的最佳结果,索引1为不选择node节点的最佳结果
     */
    private int[] dfs(TreeNode node) {
        if (node == null) return new int[]{0, 0}; // 没有节点了,怎么选都是 0
        int[] left = dfs(node.left);  // 递归左子树
        int[] right = dfs(node.right); // 递归右子树
        int rob = left[1] + right[1] + node.val; // 选node节点,left和right都不能选
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);  // 不选node节点,left和right都取最好的选择方式
        return new int[]{rob, notRob};
    }
}
