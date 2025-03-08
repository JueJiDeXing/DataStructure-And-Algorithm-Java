package 数据结构实现.树.哈夫曼树;

import lombok.Getter;
import lombok.NonNull;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 <h1>哈夫曼树/带权路径长度最短树</h1>
 根据频次进行编码,实现最少字节传输
 */
public class HuffmanTree {

    /*示例:
        n                                              n
       / \                                            / \
     (0) (1)                                        (0) (1)
     /     \            如果频次a<b<c -->             /     \
    a       n                                      n       c
           /  \                                  /   \
         (0)  (1)                              (0)   (1)
         /      \                               /      \
        b        c                            a        b
    最终编码如下:
    a:0                                     a:00
    b:10                                    b:01
    c:11                                    c:1
     */
    /*构建过程
    1.统计字符频率,放入优先级队列
    2.循环,每次出队两个频次最低的元素,给他们找个爹
    3.将爹放入队列
    4.当队列只剩一个元素时,构建完成
     */
    static class Node {

        Character ch;//字符
        @Getter
        int freq = 0;//频次


        Node left;
        Node right;
        String code;//对应编码

        @Override
        public String toString() {
            return "Node{ch=" + ch + ", freq=" + freq + '\'' + '}';
        }

        public Node(int freq, Node left, Node right) {
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public Node(Character ch) {
            this.ch = ch;
        }

        boolean isLeaf() {
            return left == null;
        }

    }

    Node root;
    Map<Character, Node> map = new HashMap<>();// 字符 -> 字符节点

    /**
     @param cnt 频率统计表
     */
    public HuffmanTree(@NonNull HashMap<Character, Integer> cnt) {
        //统计频次
        if (cnt.isEmpty()) {
            throw new RuntimeException("频率表不能为空");
        }
        for (char c : cnt.keySet()) {
            Node node = map.computeIfAbsent(c, Node::new);
            node.freq = cnt.get(c);
        }
        //构造
        PriorityQueue<Node> queue = new PriorityQueue<>(
                Comparator.comparingInt(Node::getFreq)
        );// 按字符频率升序
        queue.addAll(map.values());// 节点入队
        while (queue.size() > 1) {// 每次取最低频率的节点
            Node x = queue.poll();
            Node y = queue.poll();
            queue.offer(new Node(x.freq + y.freq, x, y));
        }
        this.root = queue.poll();
        //给每个节点计算对应编码, 存储在code字段中
        dfs(root, new StringBuilder());
        for (char c : map.keySet()) {
            Node node = map.get(c);
            System.out.println("字符" + node.ch + "的编码为" + node.code);
        }

    }

    private int dfs(Node node, StringBuilder code) {
        int sumBytes = 0;
        if (node.isLeaf()) {//找编码
            node.code = code.toString();

            sumBytes = node.freq * code.length();//一个叶子节点的占用
        } else {
            sumBytes += dfs(node.left, code.append(0));//向左
            sumBytes += dfs(node.right, code.append(1));//向右
        }
        if (code.length() != 0) {
            code.deleteCharAt(code.length() - 1);// 出栈
        }
        return sumBytes;
    }

    /**
     字符串编码

     @param str 需要编码的字符串
     @return 编码后的01字符串
     */
    public String encode(String str) {
        char[] chars = str.toCharArray();
        StringBuilder code = new StringBuilder();
        for (char c : chars) {
            if (!map.containsKey(c)) {
                throw new RuntimeException("未知字符: " + c);
            }
            code.append(map.get(c).code);
        }
        return code.toString();
    }

    /**
     字符串解码

     @param str 用本哈夫曼树编码的01字符串
     @return 解码字符串
     */
    public String decode(String str) {
        // 字符存储在叶子节点
        // 0向左走,1向右走
        // 如果走到叶子则找到字符节点,加入字符后指针重置到根节点
        char[] chars = str.toCharArray();
        int i = 0;
        StringBuilder ans = new StringBuilder();
        Node node = root;
        while (i < chars.length) {
            if (!node.isLeaf()) {// 非叶子
                if (chars[i] == '0') {
                    node = node.left;
                } else if (chars[i] == '1') {
                    node = node.right;
                } else {
                    throw new RuntimeException("只能为0/1");
                }
                i++;
            }
            if (node.isLeaf()) {//叶子 (这里写两个if判断是避免最后i越界,而未走到的情况)
                ans.append(node.ch);
                node = root;
            }
        }

        return ans.toString();
    }
}
