package 数据结构.图.拓扑排序;

import java.util.*;

public class LCR114火星词典 {
    /*
    现有一种使用英语字母的外星文语言，这门语言的字母顺序与英语顺序不同。
    给定一个字符串列表 words ，作为这门语言的词典，
    words 中的字符串已经 按这门新语言的字母顺序进行了排序 。

    请你根据该词典还原出此语言中已知的字母顺序，并 按字母递增顺序 排列。
    若不存在合法字母顺序，返回 "" 。 若存在多种可能的合法字母顺序，返回其中 任意一种 顺序即可。

    字符串 s 字典顺序小于 字符串 t 有两种情况：
    在第一个不同字母处，如果 s 中的字母在这门外星语言的字母顺序中位于 t 中字母之前，那么 s 的字典顺序小于 t 。
    如果前面 min(s.length, t.length) 字母都相同，那么 s.length < t.length 时，s 的字典顺序也小于 t 。
     */
    public String alienOrder(String[] words) {
        //创建入度表
        int[] indegree = new int[26];
        Arrays.fill(indegree, -1);//初始为-1
        for (String w : words) {
            for (int i = 0; i < w.length(); i++) {
                indegree[w.charAt(i) - 'a'] = 0;//有该字符,设为0
            }
        }
        //邻接表建图
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }
        //连接边
        for (int i = 0; i < words.length - 1; i++) {
            String cur = words[i];
            String next = words[i + 1];//比较cur和next
            int j = 0;
            int len = Math.min(cur.length(), next.length());
            while (j < len) {
                char c = cur.charAt(j);
                char n = next.charAt(j);
                //跳过前缀相同部分
                if (c != n) {
                    graph.get(c - 'a').add(n - 'a');//建立邻居关系
                    indegree[n - 'a']++;//入度
                    break;
                }
                j++;
            }
            if (j < cur.length() && j == next.length()) {
                //cur长,但是遍历到next尾了 例:abc 应该排在 abcd 前
                return "";
            }
        }
        //拓扑排序
        int kinds = 0;//全部字符种数
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            int deg = indegree[i];
            if (deg != -1) kinds++;
            if (deg == 0) {//添加入度为0的字符
                queue.offer(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int cur = queue.poll();//出队
            sb.append((char) (cur + 'a'));
            for (int next : graph.get(cur)) {//查看cur的邻居的入度
                if (--indegree[next] == 0) {//入度为0,入队
                    queue.offer(next);
                }
            }
        }
        //数量不等 说明有循环依赖
        return sb.length() == kinds ? sb.toString() : "";
    }
}
