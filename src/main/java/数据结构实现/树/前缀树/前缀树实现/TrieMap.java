package 数据结构实现.树.前缀树.前缀树实现;

import java.util.HashMap;

public class TrieMap {

    static class Node {
        int pass = 0;//以 前面的节点到当前节点为开头的单词数
        int end = 0;//以当前节点结尾的单词数
        HashMap<Character, Node> nexts = new HashMap<>();//下级节点
    }

    Node root = new Node();

    public TrieMap() {
    }

    /**
     向前缀树里插入一个单词
     */
    public void insert(String word) {
        if (word.isEmpty()) {
            return;
        }
        Node p = root;
        root.pass++;
        for (char ch : word.toCharArray()) {
            if (!p.nexts.containsKey(ch)) {//不存在则新建节点
                p.nexts.put(ch, new Node());
            }
            p = p.nexts.get(ch);//走向下级节点
            p.pass++;
        }
        p.end++;//结尾
    }

    /**
     查找单词出现的次数
     */
    public int count(String word) {
        Node p = root;
        for (char ch : word.toCharArray()) {
            if (!p.nexts.containsKey(ch)) {
                return 0;
            }
            p = p.nexts.get(ch);
        }
        return p.end;
    }

    /**
     查找前缀出现的次数
     */
    public int countPrefix(String prefix) {
        Node p = root;
        for (char ch : prefix.toCharArray()) {
            if (!p.nexts.containsKey(ch)) {
                return 0;
            }
            p = p.nexts.get(ch);
        }
        return p.pass;//前缀,pass
    }

    /**
     单词是否存在
     */
    public boolean isExist(String word) {
        Node p = root;
        for (char ch : word.toCharArray()) {
            if (!p.nexts.containsKey(ch)) {
                return false;
            }
            p = p.nexts.get(ch);
        }
        return p.end != 0;//结尾
    }

    /**
     前缀是否存在
     */
    public boolean startsWith(String prefix) {
        Node p = root;
        for (char ch : prefix.toCharArray()) {
            if (!p.nexts.containsKey(ch)) {
                return false;
            }
            p = p.nexts.get(ch);
        }
        return p.pass != 0;//前缀
    }

    /**
     擦除一次单词
     */
    public void delete(String word) {
        if (!isExist(word)) {
            return;
        }
        Node p = root;
        root.pass--;
        for (char ch : word.toCharArray()) {
            if (--p.nexts.get(ch).pass == 0) {//只有这一条路径,剪完了,下级路径都不用走了
                p.nexts.remove(ch);
                return;
            }
            p = p.nexts.get(ch);
        }
        p.end--;
    }
}

