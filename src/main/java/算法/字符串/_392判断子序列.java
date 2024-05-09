package 算法.字符串;

import java.util.Arrays;

/**
 难度:简单
 附进阶
 */
public class _392判断子序列 {
    /*
    给定字符串 s 和 t ，判断 s 是否为 t 的子序列。

    字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
    （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

    进阶：
    如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，
    你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？

    致谢：

    特别感谢 @pbrother 添加此问题并且创建所有测试用例。
     */

    /**
     <h1>解法1:递归</h1>
     找到字符串s的第一个字符first在t中出现的位置i
     那么子问题就是 s除第一个字符外的子字符串 是否为 t在i之后的子字符串 的子序列
     如果first没有在t中出现,说明s不是t的子序列
     */
    public boolean isSubsequence1(String s, String t) {
        if (s.isEmpty()) return true;
        char first = s.charAt(0);
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == first) {
                return isSubsequence1(s.substring(1), t.substring(i + 1));
            }
        }
        return false;
    }

    /**
     <h1>双指针</h1>
     用指针sIdx指向s,然后遍历t中的字符,如果遇到相同的,sIdx向后移动一位,等待下一位相同字符出现
     如果sIdx指向了s的末尾,说明是t的子序列
     */
    public boolean isSubsequence2(String s, String t) {
        char[] sChars = s.toCharArray(), tChars = t.toCharArray();
        int sIdx = 0;
        for (int i = 0; i < t.length(); i++) {
            if (sChars[sIdx] == tChars[i]) {
                sIdx++;
                if (sIdx == s.length()) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    进阶：
    如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，
    你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？

    参考KMP算法,在t上生成伪链表,每个位置都指示下一个字符(26个)的位置,利用跳转的方式节约判断时间
    具体做法是:
    假设t长度为n,那么准备n*26的矩阵map,map[i][j]=k 表示从 索引i 之后的 字符j 在t中的最近位置
    例如t="bahbgdc" 那么map[0]['b']=0 map[0]['a']=2 map[1]['b']=3
     */
    int[][] map = null;

    /**
     判断字符串s是否为t的子序列
     */
    public boolean isSubsequence(String s, String t) {
        if (map == null) creatMap(t);
        if (s.isEmpty()) return true;
        if (s.length() > t.length()) return false;

        int sIdx = 0, curr_t = 0;
        while (sIdx < s.length()) {
            if (curr_t > t.length() || map[curr_t][s.charAt(sIdx) - 'a'] == -1) return false;
            curr_t = map[curr_t][s.charAt(sIdx) - 'a'];
            sIdx++;
        }
        return true;
    }

    /**
     在字符串t上构建map,map大小为n*26
     其中map[i][j]=k 表示从 索引i 之后的 字符j 在t中的最近位置
     */
    private void creatMap(String t) {
        int n = t.length();
        //初始化map
        map = new int[n + 1][26];//多加一行,指示第一个字符的位置
        //构建跳转表map
        int[] nextPos = new int[26];//nextPos[i]表示字符i在t中下一个出现的位置
        Arrays.fill(nextPos, -1);
        for (int i = n - 1; i >= 0; i--) {//倒序遍历
            map[i] = nextPos.clone();//拷给map
            nextPos[t.charAt(i) - 'a'] = i;//更新字符t[i]的最近出现位置
        }
        map[0] = nextPos.clone();
    }

    public static void main(String[] args) {
        _392判断子序列 test = new _392判断子序列();
        String t = "ahbgdc";
        test.creatMap(t);
        System.out.println(Arrays.deepToString(test.map));
        System.out.println(test.isSubsequence("abc", t));
    }
}
