package 算法.字符串.其他;

/**
 第 341 场周赛 Q3
 难度分:1478
 */
public class _2645构造有效字符串的最少插入数 {
    /*
    给你一个字符串 word ，你可以向其中任何位置插入 "a"、"b" 或 "c" 任意次，
    返回使 word 有效 需要插入的最少字母数。

    如果字符串可以由 "abc" 串联多次得到，则认为该字符串 有效 。
     */

    /**
     <h1>解1</h1>
     假设[0,i-1]已经保持顺序,对于字符s[i-1]和s[i]
     要使[0,i]有效,需要插入s[i]-s[i-1]-1个字母(可能为负数,所以需要模3)
     a a -> abca 2
     a b -> 顺序,不需要插入
     a c -> abc  1
     b a -> bca  1
     b b -> bcab  2
     b c -> 顺序,不需要插入
     c a -> 顺序不需要插入
     c b -> cab  1
     c c -> cabc 2
     s[i-1]+1==s[i]不需要插入,s[i-1]==s[i]时插入2,其余时候插入1
     最后考虑开头和末尾,开头要添s[0]-a,末尾要添c-s[n-1],总共是s[0]+2-s[n-1]
     */
    public int addMinimum(String word) {
        char[] s = word.toCharArray();
        int ans = s[0] + 2 - s[s.length - 1];
        for (int i = 1; i < s.length; i++) {
            ans += (s[i] + 2 - s[i - 1]) % 3;
        }
        return ans;
    }

    /**
     <h1>解2</h1>
     如果最终有t组abc,那么插入字母数为3t-n
     对于s[i]和s[i-1],如果s[i]<=s[i-1],那么他们是两组的
     如果s[i]>s[i-1],那么s[i]可以被分在s[i-1]的同一组
     */
    public int addMinimum2(String word) {
        char[] s = word.toCharArray();
        int t = 1;
        for (int i = 1; i < s.length; i++) {
            if (s[i] <= s[i - 1]) t++;
        }
        return t * 3 - s.length;
    }
}
