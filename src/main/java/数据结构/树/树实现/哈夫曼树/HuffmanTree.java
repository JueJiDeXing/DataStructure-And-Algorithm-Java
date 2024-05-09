package 数据结构.树.树实现.哈夫曼树;

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
        n                                          n
       / \                                        / \
     (0) (1)                                    (0) (1)
     /     \        如果频次a<b<c -->             /     \
    a       n                                  n       c
           /  \                              /   \
         (0)  (1)                          (0)   (1)
         /      \                           /      \
        b        c                        a        b
    a:0                                 a:00
    b:10                                b:01
    c:11                                c:1
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
    String str;
    Map<Character, Node> map = new HashMap<>();

    public HuffmanTree(@NonNull String str) {
        if (str.isEmpty()) {
            throw new RuntimeException("str不能为空");
        }
        this.str = str;
        char[] chars = str.toCharArray();
        //统计频次
        for (char c : chars) {
            /*if (!map.containsKey(c)) {
                map.put(c, new Node(c));
            }
            Node node = map.get(c);
            node.freq++;*/
            Node node = map.computeIfAbsent(c, Node::new);
            node.freq++;
        }
        //构造
        PriorityQueue<Node> queue = new PriorityQueue<>(
                Comparator.comparingInt(Node::getFreq)
        );
        queue.addAll(map.values());
        while (queue.size() >= 2) {
            Node x = queue.poll();
            Node y = queue.poll();
            queue.offer(new Node(x.freq + y.freq, x, y));
        }
        this.root = queue.poll();
        //计算编码,深度优先
        StringBuilder stringBuilder = new StringBuilder();
        int sumBytes = dfs(root, stringBuilder);

    }

    private int dfs(Node node, StringBuilder code) {
        int sumBytes = 0;
        if (node.isLeaf()) {//找编码
            node.code = code.toString();
            sumBytes = node.freq * code.length();//一个叶子节点的占用
        } else {
            sumBytes += dfs(node.left, code.append(0));//向左
            sumBytes += dfs(node.right, code.append(1));
        }
        if (code.length()!=0) {
            code.deleteCharAt(code.length() - 1);
        }
        return sumBytes;
    }

    public String encode() {//编码为二进制
        char[] chars = str.toCharArray();
        StringBuilder code = new StringBuilder();
        for (char c : chars) {
            code.append(map.get(c).code);
        }
        return code.toString();
    }

    public String decode(String str) {//解码
        //遇到0向左走,遇到1向右走,如果走到叶子则重置到根节点
        char[] chars = str.toCharArray();
        int i = 0;
        StringBuilder code = new StringBuilder();
        Node node = root;
        while (i < chars.length) {
            if (!node.isLeaf()) {
                if (chars[i] == '0') {
                    node = node.left;
                } else if (chars[i] == '1') {
                    node = node.right;
                }
                i++;
            }
            if (node.isLeaf()) {//叶子,这里写两个if判断是避免最后i越界而还有数字待处理的情况
                code.append(node.code);
                node = root;
            }
        }

        return code.toString();
    }
}
