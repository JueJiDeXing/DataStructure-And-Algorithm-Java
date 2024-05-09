package 算法.字符串;

public class _28第一个匹配项 {
    /*
    求子串在字符串中的一个匹配项索引
     */
    //使用内置函数
    public int strStr1(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    //枚举
    public int strStr2(String haystack, String needle) {
        char[] origin = haystack.toCharArray();//原始
        char[] pattern = needle.toCharArray();//模式
        int i = 0, j;
        while (i <= origin.length - pattern.length) {
            for (j = 0; j < pattern.length; j++) {
                if (pattern[j] != origin[i + j]) break;
            }
            if (j == pattern.length) return i;//如果是正常退出for循环
            i++;
        }
        return -1;
    }
    //kmp算法(见KMP算法)
}
