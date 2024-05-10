package 基础数据结构算法.树.遍历二叉树;

import 数据结构实现.树.Node.TreeNode;

import java.util.*;

/**
 融合前序中序和后序
 */
public class 前中后合一 {
    public static List<List<Integer>> allOrder(TreeNode root) {
        List<List<Integer>> list_all = new ArrayList<>();
        List<Integer> list_pre = new ArrayList<>(), list_in = new ArrayList<>(), list_next = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode pop = null;//最近一次的弹栈元素

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                list_pre.add(curr.val);
                colorPrint("前: " + curr.val, 34);//------前序遍历输出,在左子树处理前
                //待处理左子树
                curr = curr.left;
            } else {//左子树处理完毕
                TreeNode peek = stack.peek();
                //case1:没有右子树
                if (peek.right == null) {
                    list_in.add(peek.val);
                    colorPrint("中: " + peek.val, 36);//--中序遍历输出,在左子树处理之后,右子树处理之前
                    pop = stack.pop();
                    list_next.add(pop.val);
                    colorPrint("后: " + pop.val, 35);//--后序遍历输出,在右子树处理之后
                }
                //case2:右子树处理完了
                else if (peek.right == pop) {
                    pop = stack.pop();
                    list_next.add(pop.val);
                    colorPrint("后: " + pop.val, 35);//--后序遍历输出,在右子树处理之后
                }
                //case3:待处理右子树
                else {
                    list_in.add(peek.val);
                    colorPrint("中: " + peek.val, 36);//--中序遍历输出,在左子树处理之后,右子树处理之前
                    curr = peek.right;
                }
            }
        }
        list_all.add(list_pre);
        list_all.add(list_in);
        list_all.add(list_next);
        return list_all;
    }

    public static void colorPrint(String print, int color) {
        System.out.printf("\033[%dm%s\033[0m %n", color, print);
    }
}
