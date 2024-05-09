package 数据结构.树.树问题.其他;

import 数据结构.树.树实现.Node.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class _2458移除子树后的二叉树高度 {
    /*
    给你一棵 树 的根节点 root ，树中有 n 个节点。每个节点都可以被分配一个从 1 到 n 且互不相同的值。
    另给你一个长度为 m 的数组 queries 。
    你必须在树上执行 m 个 独立 的查询，其中第 i 个查询你需要执行以下操作：

    从树中 移除 以 queries[i] 的值作为根节点的子树。题目所用测试用例保证 queries[i] 不 等于根节点的值。
    返回一个长度为 m 的数组 answer ，其中 answer[i] 是执行第 i 个查询后树的高度。

    注意：

    查询之间是独立的，所以在每个查询执行后，树会回到其 初始 状态。
    树的高度是从根到树中某个节点的 最长简单路径中的边数 。

     */

    Map<TreeNode, Integer> Height = new HashMap<>();
    int[] H;

    /**
     对于节点node,假设删除它的剩余高度为H(node),它的深度为D(node)<br>
     那么删除它的左子树剩余高度为 不含node的其他子树的高度 与 右子树高度 的较大值<br>
     即 H(left)=max( H(node), D(node)+Height(node.right) )   // right同理<br>
     <p>
     其中每个节点的高度 Height(node) 可以通过一次dfs求出<br>
     每个节点的 深度 D(node) 与 剩余高度 H(node) 可以在第二次dfs时递推<br>
     <p>
     然后根据查询到H数组里查找即可
     */
    public int[] treeQueries(TreeNode root, int[] queries) {
        getHeight(root);
        H = new int[Height.size()];
        dfs(root, -1, 0);
        for (int i = 0; i < queries.length; i++) {
            queries[i] = H[queries[i]];
        }
        return queries;
    }

    public int getHeight(TreeNode node) {
        if (node == null) return 0;
        int h = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        Height.put(node, h);
        return h;
    }

    public void dfs(TreeNode node, int depth, int h) {
        if (node == null) return;
        H[node.val] = h;
        depth++;
        dfs(node.left, depth, Math.max(h, depth + Height.getOrDefault(node.right, 0)));
        dfs(node.right, depth, Math.max(h, depth + Height.getOrDefault(node.left, 0)));
    }
}


class Solution {
    private Map<TreeNode, Integer> height = new HashMap<>(); // 每棵子树的高度
    private int[] res; // 每个节点的答案

    public int[] treeQueries(TreeNode root, int[] queries) {
        getHeight(root);
        height.put(null, 0); // 简化 dfs 的代码，这样不用写 getOrDefault
        res = new int[height.size()];
        dfs(root, -1, 0);
        for (var i = 0; i < queries.length; i++) {
            queries[i] = res[queries[i]];
        }
        return queries;
    }

    private int getHeight(TreeNode node) {
        if (node == null) return 0;
        var h = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        height.put(node, h);
        return h;
    }

    private void dfs(TreeNode node, int depth, int restH) {
        if (node == null) return;
        ++depth;
        res[node.val] = restH;
        dfs(node.left, depth, Math.max(restH, depth + height.get(node.right)));
        dfs(node.right, depth, Math.max(restH, depth + height.get(node.left)));
    }
}
