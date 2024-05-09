package 数据结构.树.树问题.其他;

import 数据结构.树.树实现.Node.TreeNode;

public class _99恢复二叉搜索树 {
    //有两个节点的值被交换的二叉搜索树
    /*
    相当于一个升序数组被交换了两个位置的值
    使用4个指针,node为当前遍历元素,prev为前一个元素
    x记录第一个逆序的前一个,y记录第二个逆序的后一个,最后交换x,y即可
 */
    //用两个变量x，y来记录需要交换的节点
    private TreeNode x = null, y = null;
    private TreeNode pre = null;

    public void recoverTree(TreeNode root) {
        dfs(root);
        //xy有null值,说明没有两个节点被交换
        if (x == null || y == null) throw new RuntimeException();
        //如果x和y都不为空，说明二叉搜索树出现错误的节点，将其交换
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

    //中序遍历
    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        if (pre != null && pre.val > node.val) {//逆序
            y = node;
            if (x == null) x = pre;
        }
        pre = node;
        dfs(node.right);
    }
}
