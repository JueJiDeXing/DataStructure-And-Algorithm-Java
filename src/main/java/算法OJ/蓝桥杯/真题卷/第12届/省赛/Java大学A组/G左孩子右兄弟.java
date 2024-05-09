package 算法OJ.蓝桥杯.真题卷.第12届.省赛.Java大学A组;

import java.util.*;

/**
 已AC
 */
public class G左孩子右兄弟 {
    /*
    多叉树可用左孩子右兄弟法转为二叉树
    节点的子节点是无序的,所以它的左孩子有多种选择
    求转换成的二叉树的 最大 高度

    输入:
    N:节点个数
    下面N-1行,第i行(i从2开始)一个整数,表示节点i的父节点编号
    节点1为根节点
     */


    public static int rest = 0;

    /**
     当前树根为root,子节点i的高度要最高,那么它需要去作为最后一个连接的右孩子
     那么i的高度就是root的子节点数,然后再去加上子树i的高度

     根节点下有2,3,4这三个节点
        1
     2  3  4
     ?  ?  ?

     ->    1
         x
       ?  x
        ?  y
         ?
     y位置2,3,4都可以坐,y位置一定比x位置要优,然后要让二叉树最高,那么就要枚举它们在y位置的情况进行搜索
     y位置的高度为根的子节点个数,再递归求y的子树转换二叉树的最大高度
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //邻接表建图(建树)
        ArrayList<Integer>[] tree = new ArrayList[n + 1];//tree[i]:节点i的子节点
        Arrays.setAll(tree, k -> new ArrayList<>());
        for (int i = 2; i < n + 1; i++) {//输入
            int w = sc.nextInt();
            tree[w].add(i);
        }

        dfs(1, tree, new int[n + 1]);
        System.out.println(rest);
    }

    public static void dfs(int parent, List<Integer>[] tree, int[] h) {
        List<Integer> children = tree[parent];
        int broCount = children.size();

        for (int child : children) {
            h[child] = h[parent] + broCount;//child作为最后一个连接的右孩子
            rest = Math.max(rest, h[child]);
            dfs(child, tree, h);
        }
    }

}
