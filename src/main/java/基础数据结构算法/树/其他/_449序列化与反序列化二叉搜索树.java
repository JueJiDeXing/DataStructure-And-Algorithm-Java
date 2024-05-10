package 基础数据结构算法.树.其他;

import 数据结构实现.树.Node.TreeNode;

import java.util.*;

public class _449序列化与反序列化二叉搜索树 {
    public static void main(String[] args) {
        _449序列化与反序列化二叉搜索树 test = new _449序列化与反序列化二叉搜索树();
        System.out.println(test.check(new TreeNode(2, new TreeNode(1), new TreeNode(3))));
    }


    TreeNode check(TreeNode root) {
        Codec ser = new Codec();
        Codec deser = new Codec();
        String tree = ser.serialize(root);
        return deser.deserialize(tree);
    }
}

/**
 <h1>后序遍历</h1>
 中序+前序 或 中序+后序 可以构建一颗二叉树<br>
 而对于二叉搜索树,只需要将前序或者后序进行重排即可得到中序遍历结果<br>
 使用后序遍历是因为栈的特性,顺序入栈后序遍历结果,栈顶即为根节点
 */
class Codec {
    public String serialize(TreeNode root) {
        //后序遍历
        List<Integer> list = new ArrayList<>();
        postOrder(root, list);
        //后序遍历编码为字符串
        String str = list.toString();//[1, 2, 3...]
        return str.substring(1, str.length() - 1);//1, 2, 3...
    }

    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        //获取后序遍历
        String[] arr = data.split(", ");
        Deque<Integer> stack = new ArrayDeque<>();
        for (String s : arr) {
            stack.push(Integer.parseInt(s));
        }
        //根据后序遍历构建二叉搜索树
        return construct(Integer.MIN_VALUE, Integer.MAX_VALUE, stack);
    }

    private void postOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postOrder(root.left, list);
        postOrder(root.right, list);
        list.add(root.val);
    }

    /**
     构建子树,子树所有节点的大小都在(lower,upper)

     @param lower 最小值(初始为MIN)
     @param upper 最大值(初始为MAX)
     @param stack 后序遍历结果
     @return 返回构建后的子树根
     */
    private TreeNode construct(int lower, int upper, Deque<Integer> stack) {
        if (stack.isEmpty() || stack.peek() < lower || stack.peek() > upper) {
            // case1: stack.isEmpty() 全部构建完
            // case2: stack.peek() < lower || stack.peek() > upper 栈顶值越界 -> 栈顶越界,说明当前节点不在这颗子树上,这颗子树为null
            return null;
        }
        //根节点
        int val = stack.pop();
        TreeNode root = new TreeNode(val);
        //构建左右子树
        root.right = construct(val, upper, stack);//先右后左(取决于栈的抛出顺序)
        root.left = construct(lower, val, stack);
        return root;
    }
}

/**
 取巧做法
 */
class Codec2 {

    TreeNode node;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        node = root;
        return "";
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return node;
    }
}

/**
 取巧做法进阶
 */
class Codec3 {
    static Map<String, TreeNode> map1 = new HashMap<>();
    static Map<TreeNode, String> map2 = new HashMap<>();
    static int count = 0;//全局计数器

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (map2.containsKey(root)) {
            return map2.get(root);
        }
        String str = String.valueOf(count);
        map2.put(root, str);
        map1.put(str, root);
        count++;
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (map1.containsKey(data)) {
            return map1.get(data);
        }
        TreeNode root = new TreeNode();
        String str = String.valueOf(count);
        map2.put(root, str);
        map1.put(str, root);
        count++;
        return root;
    }
}
