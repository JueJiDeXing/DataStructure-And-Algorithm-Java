package 算法.算法基础.滑动窗口;

import java.util.*;

public class _3无重复字符的最长子串 {
    /*
    给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     */

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();//窗口内字符对应数量
        int left = 0, right = 0;//滑动窗口区域[left,right]
        int ans = 0;
        for (; right < s.length(); right++) {
            char ch = s.charAt(right);
            while (map.getOrDefault(ch, 0) >= 1) {//窗口内有字符重复,抛出到无重复为止
                char l = s.charAt(left++);
                map.put(l, map.get(l) - 1);
            }
            //更新最大长度
            ans = Math.max(ans, right - left + 1);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        return ans;
    }
}
