package 数据结构实现.树.前缀树.前缀树实现;

/**
 <h1>前缀树/字典树</h1>
 用于记录单词信息
 */
public class Trie {

    static class Node {
        int pass = 0;//以 前面的节点到当前节点为开头的单词数(前缀个数)
        int end = 0;//以当前节点结尾的单词数
        Node[] nexts = new Node[26];//下级节点
    }

    Node root = new Node();

    /**
     向前缀树里插入一个单词
     */
    public void insert(String word) {
        if (word.isEmpty()) return;
        Node p = root;
        root.pass++;
        for (char ch : word.toCharArray()) {
            int path = ch - 'a';
            if (p.nexts[path] == null) {//不存在则新建节点
                p.nexts[path] = new Node();
            }
            p = p.nexts[path];//走向下级节点
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
            int path = ch - 'a';
            if (p.nexts[path] == null) {
                return 0;//不存在这个单词
            }
            p = p.nexts[path];
        }
        return p.end;
    }

    /**
     查找前缀出现的次数
     */
    public int countPrefix(String prefix) {
        Node p = root;
        for (char ch : prefix.toCharArray()) {
            int path = ch - 'a';
            if (p.nexts[path] == null) {
                return 0;//不存在这个前缀
            }
            p = p.nexts[path];
        }
        return p.pass;
    }

    /**
     判断单词是否存在
     */
    public boolean isExist(String word) {
        Node p = root;
        for (char ch : word.toCharArray()) {
            int path = ch - 'a';
            if (p.nexts[path] == null) {
                return false;
            }
            p = p.nexts[path];
        }
        return p.end != 0;//结尾
    }

    /**
     判断前缀是否存在
     */
    public boolean startsWith(String prefix) {
        Node p = root;
        for (char ch : prefix.toCharArray()) {
            int path = ch - 'a';
            if (p.nexts[path] == null) {
                return false;
            }
            p = p.nexts[path];
        }
        return p.pass != 0;//前缀
    }

    /**
     擦除一次单词(擦除一次路径上的pass和结尾的一个end)
     */
    public void delete(String word) {
        if (!isExist(word)) return;
        Node p = root;
        root.pass--;
        for (char ch : word.toCharArray()) {
            int path = ch - 'a';
            if (--p.nexts[path].pass == 0) {//只有这一条路径,剪完了,下级路径都不用走了
                p.nexts[path] = null;
                return;
            }
            p = p.nexts[path];
        }
        p.end--;
    }
}
