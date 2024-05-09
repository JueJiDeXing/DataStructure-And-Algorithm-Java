package 数据结构.树.树实现.前缀树.前缀树实现;

import java.util.Arrays;

public class TrieArray {
    static int capacity = 40000;//最大容量
    static int kinds = 26;//字符种数
    static int[][] tree = new int[capacity][kinds];
    static int[] pass = new int[capacity];
    static int[] end = new int[capacity];
    static int count = 0;

    public TrieArray() {
        for (int i = 0; i <= count; i++) {//清除脏数据
            Arrays.fill(tree[i], 0);
            pass[i] = 0;
            end[i] = 0;
        }
        count = 0;
    }


    /**
     向前缀树里插入一个单词
     */
    public void insert(String word) {
        if (word.isEmpty()) {
            return;
        }
        int curr = 0;
        pass[0]++;
        for (char ch : word.toCharArray()) {
            int path = ch - 'a';
            if (tree[curr][path] == 0) {
                tree[curr][path] = ++count;
            }
            curr = tree[curr][path];
            pass[curr]++;
        }
        end[curr]++;
    }


    /**
     单词是否存在
     */
    public boolean isExist(String word) {
        int curr = 0;
        for (char ch : word.toCharArray()) {
            int path = ch - 'a';
            if (tree[curr][path] == 0) {
                return false;
            }
            curr = tree[curr][path];
        }
        return end[curr] != 0;
    }

    public boolean startsWith(String prefix) {
        int curr = 0;
        for (char ch : prefix.toCharArray()) {
            int path = ch - 'a';
            if (tree[curr][path] == 0) {
                return false;
            }
            curr = tree[curr][path];
        }
        return pass[curr] != 0;//前缀
    }

    /**
     擦除一次单词
     */
    public void delete(String word) {
        if (!isExist(word)) {
            return;
        }
        int curr = 0;
        pass[curr]--;
        for (char ch : word.toCharArray()) {
            int path = ch - 'a';
            if (--pass[tree[curr][path]] == 0) {//只有这一条路径,剪完了,下级路径都不用走了
                tree[curr][path] = 0;
                return;
            }
            curr = tree[curr][path];
        }
        end[curr]--;
    }

    /**
     查找单词出现的次数
     */
    public int count(String word) {
        int curr = 0;
        for (char ch : word.toCharArray()) {
            int path = ch - 'a';
            if (tree[curr][path] == 0) {
                return 0;
            }
            curr = tree[curr][path];
        }
        return end[curr];
    }

    /**
     查找前缀出现的次数
     */
    public int countPrefix(String prefix) {
        int curr = 0;
        for (char ch : prefix.toCharArray()) {
            int path = ch - 'a';
            if (tree[curr][path] == 0) {
                return 0;
            }
            curr = tree[curr][path];
        }
        return pass[curr];//前缀,pass
    }


}


