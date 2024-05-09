package 算法.字符串;

public class _14最长公共前缀 {
    /*
    编写一个函数来查找字符串数组中的最长公共前缀。
    如果不存在公共前缀，返回空字符串 ""。
     */
    public String longestCommonPrefix(String[] strs) {
        /*
        case 1:比较某一列时,有不同的字符,则返回该列之前的子串
        case 2:比较某一列时,字符串长度不够,则返回该列之前的子串
        case 3:循环正常结束,第一个字符串就是解
         */
        char[] first = strs[0].toCharArray();
        for (int i = 0; i < first.length; i++) {
            char ch = first[i];
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || ch != strs[j].charAt(i)) {
                    return new String(first, 0, i);
                }
            }
        }
        return strs[0];
    }
}
