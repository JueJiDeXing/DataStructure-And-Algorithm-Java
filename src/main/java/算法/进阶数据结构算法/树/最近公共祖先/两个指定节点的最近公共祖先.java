package 算法.进阶数据结构算法.树.最近公共祖先;

import 数据结构实现.树.Node.TreeNode;

public class 两个指定节点的最近公共祖先 {
    //给定一颗二叉树根节点root和内部节点p和q , 求节点p和q的公共祖先

    /**
     <h1>二叉搜索树的最近公共祖先</h1>
     待查找节点p,q在某一节点r的两侧,那么r就是p,q的最近公共祖先(p或q就是r的话也算)
     p<r<q
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode a = root;
        while ((p.val < a.val && q.val < a.val) || (p.val > a.val && q.val > a.val)) {
            //在同一侧则向下查找
            if (p.val < a.val) {
                a = a.left;
            } else {
                a = a.right;
            }
        }
        //退出循环 1.p与q在a的两侧 2.p或q与a相等
        return a;
    }

    /**
     <h1>普通二叉树的最近公共祖先</h1>
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor2(root.left, p, q);//查看左边是否有节点p或q
        if (left == null) {
            //左边没有,那么pq都在右边,搜索右子树即可
            return lowestCommonAncestor2(root.right, p, q);
        }
        //左边有p或q,查看右边是否有p或q
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (right == null) {
            //右边没有p或q,那么左边的结果就是答案
            return left;
        }
        //左右两边都有,p和q分散在root两侧,返回root
        return root;

    }
}
