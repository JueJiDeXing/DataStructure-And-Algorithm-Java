package 算法.算法基础.二分;

import 数据结构实现.树.Node.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 第 320 场周赛 Q2
 难度分:1597
 */
public class _2476 {
    /*
    给你一个 二叉搜索树 的根节点 root ，和一个由正整数组成、长度为 n 的数组 queries 。

    请你找出一个长度为 n 的 二维 答案数组 answer ，其中 answer[i] = [mini, maxi] ：

    mini 是树中小于等于 queries[i] 的 最大值 。如果不存在这样的值，则使用 -1 代替。
    maxi 是树中大于等于 queries[i] 的 最小值 。如果不存在这样的值，则使用 -1 代替。
    返回数组 answer 。
     */

    /**
     二叉搜索树的中序遍历是一个升序数组
     这样对于每个queries可以通过二分的方式查找答案
     */
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        dfs(root);//中序遍历树,存储到arr中
        List<List<Integer>> ans = new ArrayList<>();
        for (int q : queries) {
            //查找大于等于q的最小值索引
            int j = binarySearch(arr, q);
            int max, min;
            // j==n:不存在大于等于q的值
            max = j == n ? -1 : arr[j];
            // j==n:所有值都小于等于q,取最后一项arr[n-1];
            // q==arr[j]:min可以取arr[j],不需要取arr[j-1]
            if (j == n || q != arr[j]) j--;
            min = j < 0 ? -1 : arr[j];
            ans.add(Arrays.asList(min, max));
        }
        return ans;
    }

    int[] arr = new int[100001];
    int n = 0;

    void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        arr[n++] = node.val;
        dfs(node.right);
    }

    /**
     返回数组a中,大于等于target的最小值
     */
    int binarySearch(int[] a, int target) {
        int left = -1, right = n;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (a[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }
}
