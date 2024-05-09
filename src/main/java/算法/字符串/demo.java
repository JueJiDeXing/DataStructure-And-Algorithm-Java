package 算法.字符串;

public class demo {
    public int kmp(String haystack, String needle) {
        char[] str_main = haystack.toCharArray(), str_sub = needle.toCharArray();
        int len1 = str_main.length, len2 = str_sub.length;
        int[] next = getNext(str_sub);

        int i = 0, j = 0;
        while (len1 - i < len2 - j) {
            if (str_main[i] == str_sub[j]) {//该位匹配,ij均后移
                i++;
                j++;
                if (j == len2) return i - j;//全部匹配
            } else if (j == 0) {//首位不匹配,i后移
                i++;
            } else {//非首位不匹配,j跳转
                j = next[j - 1];
            }
        }
        return -1;//没有该子串
    }

    private int[] getNext(char[] str) {
        int len = str.length;
        int[] next = new int[len];
        int i = 1, j = 0;//以i结尾,以j开头的前后缀字符串
        //假设j走到了k位置,则说明 0 ~ k 与 i-k ~ i是相同的,否则j会向前跳转
        while (i < len) {
            if (str[i] == str[j]) {
                next[i] = j + 1;
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = next[j - 1];
            }
        }

        return next;
    }
}
