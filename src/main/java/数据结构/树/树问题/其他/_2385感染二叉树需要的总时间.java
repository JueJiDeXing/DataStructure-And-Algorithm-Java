package 数据结构.树.树问题.其他;

import 数据结构.树.树实现.Node.TreeNode;

/**
 难度:中等
 */
public class _2385感染二叉树需要的总时间 {

    /*
    给你一棵二叉树的根节点 root ，二叉树中节点的值 互不相同 。另给你一个整数 start 。在第 0 分钟，感染 将会从值为 start 的节点开始爆发。

    每分钟，如果节点满足以下全部条件，就会被感染：

    节点此前还没有感染。
    节点与一个已感染节点相邻。
    返回感染整棵树需要的分钟数。
     */
    private int ans;

    public int amountOfTime(TreeNode root, int start) {
        dfs(root, start);
        return ans;
    }

    private int[] dfs(TreeNode node, int start) {
        if (node == null) return new int[]{0, 0};//[len, found]

        int[] leftRes = dfs(node.left, start);
        int[] rightRes = dfs(node.right, start);
        int lLen = leftRes[0], lFound = leftRes[1];
        int rLen = rightRes[0], rFound = rightRes[1];
        if (node.val == start) {
            ans = Math.max(lLen, rLen);
            return new int[]{1, 1}; // 找到了 start
        }
        if (lFound == 1 || rFound == 1) {
            ans = Math.max(ans, lLen + rLen);
            // 保证 start 是直径端点
            return new int[]{(lFound == 1 ? lLen : rLen) + 1, 1};
        }
        return new int[]{Math.max(lLen, rLen) + 1, 0};
    }
}
