package 数据结构.树.树问题.遍历二叉树;

import 数据结构.树.树实现.Node.TreeNode;

import java.util.*;

/**
 第 122 场周赛 Q4
 难度分: 1676
 */
public class _987二叉树的垂序遍历 {
    /*
    给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。

    对位于 (row, col) 的每个结点而言，
    其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。
    树的根结点位于 (0, 0) 。

    二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，
    按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。
    如果同行同列上有多个结点，则按结点的值从小到大进行排序。

    返回二叉树的 垂序遍历 序列。
     */


    /**
     <h1>dfs+哈希表+排序</h1>
     */
    public List<List<Integer>> verticalTraversal (TreeNode root) {
        map.put(root, new int[]{0, 0, root.val});
        dfs(root);//遍历树,记录每个节点的行列和val值
        List<int[]> list = new ArrayList<>(map.values());//转存到list中
        list.sort((a, b) -> {//按列优先,行其次,最后为val的顺序排序
            if (a[0] != b[0]) return a[0] - b[0];
            if (a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });
        //再转换到ans
        int n = list.size();
        List<List<Integer>> ans = new ArrayList<>();
        int i = 0;
        while (i < n) {
            int currCol = list.get(i)[0];
            int j = i;
            List<Integer> tmp = new ArrayList<>();
            while (j < n) {
                if (list.get(j)[0] != currCol) break;//不同列,退出,下一列
                tmp.add(list.get(j)[2]);
                j++;
            }
            ans.add(tmp);
            i = j;
        }
        return ans;
    }

    Map<TreeNode, int[]> map = new HashMap<>(); // col, row, val

    void dfs(TreeNode root) {
        if (root == null) return;
        int[] info = map.get(root);
        int col = info[0], row = info[1];
        if (root.left != null) {
            map.put(root.left, new int[]{col - 1, row + 1, root.left.val});
            dfs(root.left);
        }
        if (root.right != null) {
            map.put(root.right, new int[]{col + 1, row + 1, root.right.val});
            dfs(root.right);
        }
    }
}
