package 数据结构.树.树问题.构建二叉树;

import 数据结构.树.树实现.Node.TreeNode;

/**
 第 98 场周赛 Q3
 难度分:1732
 */
public class _889根据前序和后序遍历构造二叉树 {
    /*
    给定两个整数数组，preorder 和 postorder ，
    其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，postorder 是同一棵树的后序遍历，
    重构并返回二叉树。

    如果存在多个答案，您可以返回其中 任何 一个。
     */
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n=preorder.length;
        return build(preorder,0,n-1,postorder,0,n-1);
    }
    TreeNode build(int[]preorder,int pre_left,int pre_right,int[]postorder,int post_left,int post_right){
        if(pre_right<pre_left)return null;
        TreeNode root=new TreeNode(preorder[pre_left]);
        if(pre_left==pre_right)return root;
        //寻找左子树范围 长度len=i-post_left+1
        int i=post_left;
        while(preorder[pre_left+1]!=postorder[i]){
            i++;
        }
        int len=i-post_left+1;
        //pre:左子树[pre_left+1,pre_left+len],右子树[pre_left+lef+1,pre_right]
        //post:左子树[post_left,i],右子树[i+1,post_right-1]
        root.left=build(preorder,pre_left+1,pre_left+len,postorder,post_left,i);
        root.right=build(preorder,pre_left+len+1,pre_right,postorder,i+1,post_right-1);
        return root;
    }
}
