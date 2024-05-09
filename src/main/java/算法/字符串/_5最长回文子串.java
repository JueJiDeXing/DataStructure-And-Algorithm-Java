package 算法.字符串;

public class _5最长回文子串 {
    /*
    给你一个字符串 s，找到 s 中最长的回文子串。
    如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
     */
    int left,right;

    public String longestPalindrome(String s) {
        left = 0;
        right = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            extend(chars, i, i);//一个字符作为中心点
            extend(chars, i, i + 1);//两个字符作为中心点
        }
        return s.substring(left, right+1);
    }

    public void extend(char[] chars, int i, int j) {
        //中心开花,选择一个中间点,向两边查找
        while (i >= 0 && j < chars.length && chars[i] == chars[j]) {
            i--;
            j++;
        }
        //直到不是回文,退出循环
        i++;
        j--;
        if (j - i > right - left) {//记录最长区间
            left = i;
            right = j;
        }
    }
}
