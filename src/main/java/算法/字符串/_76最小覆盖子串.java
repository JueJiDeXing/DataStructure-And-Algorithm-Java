package 算法.字符串;

public class _76最小覆盖子串 {
    public static void main(String[] args) {
        _76最小覆盖子串 test = new _76最小覆盖子串();
        System.out.println(test.minWindow("ADOBECODEBANC", "ABC"));
    }
    /*
    给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
    如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
    注意：
    对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
    如果 s 中存在这样的子串，我们保证它是唯一的答案。

     */

    /**
     <h1>滑动窗口-双指针</h1>
     如果找到解,更新最小串,然后left++,直至不满足解
     如果不满足解,right++,寻找解,直到right到达末尾退出
     */
    public String minWindow(String s, String t) {
        //统计目标串各字符个数
        char[] target = t.toCharArray();
        int[] targetCount = new int[128];
        int passTotal = 0;//模式串的总条件字符种类
        int passed = 0;//目标串已通过条件数
        for (char c : target) {
            if (targetCount[c]++ == 0) {//数量加1
                passTotal++;//如果是第一次统计该字符,条件总数加1
            }
        }
        //寻找所有解
        char[] source = s.toCharArray();
        int[] sourceCount = new int[128];//记录left~right的各字符数
        int left = 0, right = 0;
        Result res = null;
        while (right < source.length) {
            char rightChar = source[right];
            sourceCount[rightChar]++;
            if (sourceCount[rightChar] == targetCount[rightChar]) {//扩大right时,使该字符数目相等
                passed++;//通过了一个条件
            }
            //条件全部通过,缩小left
            while (passTotal == passed && left <= right) {
                char leftChar = source[left];
                if (sourceCount[leftChar]-- == targetCount[leftChar]) {//缩小left时,使该字符数目相等
                    passed--;//已通过条件数减少1个
                    if (res == null || right - left < res.right - res.left) {//找到新的最短解,与上次比较,更新结果
                        res = new Result(left, right);
                    }
                }
                left++;
            }
            right++;
        }
        return res == null ? "" : s.substring(res.left, res.right + 1);
    }

    static class Result {
        int left;
        int right;

        public Result(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}
