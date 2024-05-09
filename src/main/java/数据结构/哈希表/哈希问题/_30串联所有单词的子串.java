package 数据结构.哈希表.哈希问题;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 难度:困难
 */
public class _30串联所有单词的子串 {
    /*
    给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。

     s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。

    例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。 "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
    返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案。
     */

    public List<Integer> findSubstring(String s, String[] words) {
        int n = s.length(), m = words.length, w = words[0].length();
        List<Integer> ans = new ArrayList<>();
        if (m * w > n) return ans;
        char[] str = s.toCharArray();

        //将words存入哈希表
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) map.put(word, map.getOrDefault(word, 0) + 1);
        //枚举所有子串s[i,i+w),判断是否由words构成
        for (int i = 0; i + m * w <= n; i++) {
            if (isSub(str, i, m, w, map)) {
                ans.add(i);
            }
        }
        return ans;
    }

    /**
     判断字符串str[i,i+m*w)是否由words构成
     */
    boolean isSub(char[] str, int i, int m, int w, Map<String, Integer> map) {
        Map<String, Integer> cur = new HashMap<>();
        for (int j = i; j < i + m * w; j += w) {//每个单词长度为w
            String item = String.valueOf(str, j, w);
            if (!map.containsKey(item)) return false;//这个单词不出现在map中,false
            cur.put(item, cur.getOrDefault(item, 0) + 1);//在哈希表cur中记录
        }
        return cur.equals(map);//比较单词种类与数量
    }
}
