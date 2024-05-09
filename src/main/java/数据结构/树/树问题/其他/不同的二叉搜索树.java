package 数据结构.树.树问题.其他;

import 数据结构.树.树实现.Node.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class 不同的二叉搜索树 {
    /*
    给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
    返回满足题意的二叉搜索树的种数。
     */

    /**
     <h1>分治算法</h1>
     以i为根且节点数为n的种数为: 左种数 * 右种数
     */
    public int numTrees(int n) {
        if (n <= 2) {
            return n;
        }
        int[] counts = new int[n + 1];//记忆数组剪枝
        counts[0] = 1;//没有左(或右)子树,视为左(或右)子树种数为1
        counts[1] = 1;
        counts[2] = 2;
        return count(counts, n);
    }

    /**
     @param counts 记忆数组剪枝
     @param n      n个节点
     @return n个节点能组成的不同二叉搜索树种类
     */
    public int count(int[] counts, int n) {
        if (counts[n] != 0) {
            return counts[n];
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {//枚举以i为根
            res += count(counts, i - 1) * count(counts, n - i);
            //以i为根且节点数为n的种数为:左种数x右种数
            //树的种数与元素值无关,只与元素个数有关
        }
        counts[n] = res;
        return res;
    }

    /**
     <h1>卡特兰数</h1>
     C(n)= &sum {i=0 → n-1}  C(i)*C(n-1-i)<br>
     C(5) = C(0)*C(4) + C(1)*C(3) + C(2)*C(2) + C(4)*C(0) + C(3)*C(1)
     */
    public int numTrees2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            //求第C(i)的拆分
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }

        }
        return dp[n];
    }

    //------------------------------------------------------------------------------------------
    /*
    进阶:求所有排列的具体情况
     */


    public List<TreeNode> generateTrees(int n) {
        return dfs(1, n);
    }

    /*
    返回由start~end组成的不同的二叉搜索树的根节点集合
    */
    public List<TreeNode> dfs(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (start > end) {
            list.add(null);
            return list;
        }
        for (int i = start; i <= end; i++) {//枚举以i为根
            List<TreeNode> leftTrees = dfs(start, i - 1);
            List<TreeNode> rightTrees = dfs(i + 1, end);
            //组合左右
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    list.add(root);
                }
            }
        }
        return list;
    }

}
