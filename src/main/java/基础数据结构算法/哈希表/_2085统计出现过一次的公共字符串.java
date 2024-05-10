package 基础数据结构算法.哈希表;

import java.util.*;

public class _2085统计出现过一次的公共字符串 {
    /*
    给你两个字符串数组 words1 和 words2 ，
    请你返回在两个字符串数组中 都恰好出现一次 的字符串的数目。
     */

    public int countWords(String[] words1, String[] words2) {
        //解一:使用两个哈希表存储字符串出现次数,然后进行查找筛选
        Map<String, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
        for (String s : words1) {
            int c = map1.getOrDefault(s, 0);
            map1.put(s, c + 1);
        }
        for (String s : words2) {
            int c = map2.getOrDefault(s, 0);
            map2.put(s, c + 1);
        }
        int ans = 0;
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            if (entry.getValue() == 1) {
                String s = entry.getKey();
                if (map2.containsKey(s) && map2.get(s) == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public int countWords2(String[] words1, String[] words2) {
        //优化
        //1. 获取只在words1出现一次的字符串集合one
        //2. 获取在one中,且只在words2出现一次的字符串集合more
        Set<String> one = new HashSet<>(), more = new HashSet<>();

        for (String s : words1) if (!one.add(s)) more.add(s);//one存储全部的,more存储重复的
        one.removeAll(more);//one与more相减得只出现一次的

        more.clear();
        for (String s : words2) {
            //case 1 : s不在words1中
            //case 2 : s在words1中但重复出现在words2中
            if (!one.contains(s)) continue;

            if (more.contains(s)) {//s重复出现在words2中,s不是要查找的字符串,将s移除
                one.remove(s);//one中也移除,下次遍历到s时,直接跳过
                more.remove(s);
            } else {//s在words1中且第一次出现在words2中
                more.add(s);
            }
        }
        return more.size();//返回最终数量
    }
}
