package 算法.算法基础.排序.排序问题;

public class _1657确定两个字符串是否接近 {
    /*
    如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串 接近 ：

    操作 1：交换任意两个 现有 字符。
    例如，abcde -> aecdb
    操作 2：将一个 现有 字符的每次出现转换为另一个 现有 字符，并对另一个字符执行相同的操作。//两种字符数量可以不同,但必须都有
    例如，aacabb -> bbcbaa（所有 a 转化为 b ，而所有的 b 转换为 a ）
    你可以根据需要对任意一个字符串多次使用这两种操作。

    给你两个字符串，word1 和 word2 。如果 word1 和 word2 接近 ，就返回 true ；否则，返回 false 。
     */
    public boolean closeStrings(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        if (len1 != len2) return false;
        int[] count1 = new int[26], count2 = new int[26];
        for (char c : word1.toCharArray()) {
            count1[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            count2[c - 'a']++;
        }
        //按升序排序,但是跳过0的位置
        sort(count1);
        sort(count2);
        for (int i = 0; i < 26; i++) {
            if (count1[i] != count2[i]) return false;
        }
        return true;
    }

    /**
     冒泡排序,升序,如果有0则越过
     例如:[3,0,0,4,1,0,2] -> [1,0,0,2,3,0,4]
     */
    public void sort(int[] num) {
        outer:
        for (int i = 0; i < 25; i++) {
            int j = 1, prevj = 0;
            while (j < 26 - i) {
                while (j < 26 - i && num[j] == 0) {
                    j++;
                }
                if (j >= 26 - i) {
                    continue outer;
                }
                if (num[j] < num[prevj]) {
                    int t = num[j];
                    num[j] = num[prevj];
                    num[prevj] = t;
                }
                prevj = j;
                j++;
            }
        }
    }
}
