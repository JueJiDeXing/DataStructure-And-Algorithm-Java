package 算法.字符串;

import java.util.*;

/**
 难度:困难
 */
public class _68文本左右对齐 {
    /*
    给定一个单词数组 words 和一个长度 maxWidth ，
    重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。

    你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。
    必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。

    要求尽可能均匀分配单词间的空格数量。
    如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。

    文本的最后一行应为左对齐，且单词之间不插入额外的空格。

    注意:

    单词是指由非空格字符组成的字符序列。
    每个单词的长度大于 0，小于等于 maxWidth。
    输入单词数组 words 至少包含一个单词。
     */


    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        List<String> line = new ArrayList<>();//当前行
        int lineRemain = maxWidth;//当前行的剩余空间

        for (String word : words) {//测试将word放到当前行
            if (word.length() > lineRemain) {//word无法放到当前行
                //将当前行平均排列后放到ans中
                int wordsLenCount = maxWidth - lineRemain - line.size();//当前行的字母数量
                ans.add(changeWordsToLine(line, maxWidth, wordsLenCount));
                //下一行
                line.clear();
                lineRemain = maxWidth;
            }
            //放到当前行
            line.add(word);
            lineRemain -= word.length() + 1;//单词间至少需要一个空格
        }
        //最后一行
        ans.add(leftJustify(line, maxWidth));
        return ans;
    }

    /**
     传入一行单词words,将其在一行内均匀排列后返回
     */
    String changeWordsToLine(List<String> words, int maxWidth, int lenCount) {
        if (words.size() == 1) return leftJustify(words, maxWidth);//只有一个单词
        //计算每个间隔需要放置的空格数
        int spaceLen = maxWidth - lenCount, space = words.size() - 1;//空格总长度和间隔数
        int len = spaceLen / space, count = spaceLen % space;//每个间隔的平均空格数,前count个间隔需要多放一个空格
        String spaceStr = repeat(" ", len);
        //排列
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < space; i++) {
            res.append(words.get(i)).append(spaceStr);
            if (i < count) res.append(" ");
        }
        res.append(words.get(words.size() - 1));
        return res.toString();
    }

    static String repeat(String s, int n) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; ++i) ans.append(s);
        return ans.toString();
    }

    /**
     左对齐一行,单词间由单个空格分隔,行末由空格填充至maxWidth长度
     */
    String leftJustify(List<String> words, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            sb.append(words.get(i));
            if (i != words.size() - 1) sb.append(" ");
        }
        //后面的部分用空格填充
        sb.append(repeat(" ",maxWidth - sb.length()));
        return sb.toString();
    }
}
