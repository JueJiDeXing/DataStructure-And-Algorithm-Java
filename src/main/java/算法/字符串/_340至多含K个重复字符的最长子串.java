package 算法.字符串;

import java.util.HashMap;
import java.util.Map;

public class _340至多含K个重复字符的最长子串 {
    /**
     最长不含重复元素子串
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, max_len = 0;
        int len = s.length();
        for (int right = 0; right < len; right++) {
            char ch = s.charAt(right);
            while (map.getOrDefault(ch, 0) >= 1) {
                char l = s.charAt(left);
                map.put(l, map.get(l) - 1);

                left++;
            }
            if (right - left + 1 > max_len) max_len = right - left + 1;
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        return max_len;
    }

    /**
     最多K个重复元素最长子串
     */
    public int lengthOfLongestSubstring(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, max_len = 0;
        int len = s.length();
        for (int right = 0; right < len; right++) {
            char ch = s.charAt(right);
            while (map.getOrDefault(ch, 0) >= k) {//1->k
                char l = s.charAt(left);
                map.put(l, map.get(l) - 1);

                left++;
            }
            if (right - left + 1 > max_len) max_len = right - left + 1;
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        return max_len;
    }
}
