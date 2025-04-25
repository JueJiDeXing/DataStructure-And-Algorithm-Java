package 算法.字符串.其他;

public class _395至少有K个重复字符的最长子串 {
    /*
    给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串，
    要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
    如果不存在这样的子字符串，则返回 0。
    输入：s = "ababbc", k = 2
    输出：5
    解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
     */

    /**
     <h1>分治思想</h1>
     思路:以少于K的字符分隔,子串递归分隔...<br>
     s="dddxaabaaabaacciiiiefbff", k=3<br>
     -> ddd aabaabaa  iiii fbff<br>
     -> 3 aa aa aa  4 f ff<br>
     ->4<br>
     */
    public static int longestSubstring(String s, int k) {
        if (s.length() < k) {//子串落选
            return 0;
        }
        //统计个数
        int[] counts = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            counts[c - 'a']++;
        }

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int count = counts[c - 'a'];
            if (count > 0 && count < k) {//找小于k的分隔
                int j = i + 1;//跳过连续的小于k的字符
                while (j < s.length() && counts[chars[j] - 'a'] < k) {
                    j++;
                }
                //分治
                return Math.max(longestSubstring(s.substring(0, i), k),
                        longestSubstring(s.substring(j), k));
            }
        }
        //子串入选(如果有小于k的的字符,会在for循环里return)
        return s.length();
    }
}
