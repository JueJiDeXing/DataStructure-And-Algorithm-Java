package 算法.算法基础.贪心;


/**
 难度: 中等
 */
public class _1717删除子字符串的最大得分 {
    /*
    给你一个字符串 s 和两个整数 x 和 y
    你可以执行下面两种操作任意次
        1. 删除子字符串 "ab" 并得到 x 分
        2. 删除子字符串 "ba" 并得到 y 分
        (删除后, 前后字符串拼接为新字符串, 后续操作都在新字符串上进行)
    请返回对 s 字符串执行上面操作若干次能得到的最大得分
     */
    public int maximumGain(String s, int x, int y) {
        return x > y ? getMax(s, 'a', 'b', x, y) : getMax(s, 'b', 'a', y, x);
    }

    int getMax(String s, char a, char b, int x, int y) {//x > y
        int cntA = 0, cntB = 0, ans = 0;
        s = s + "#";
        for (char c : s.toCharArray()) {
            // cntB个b    cntA个a   c
            if (c == a) {
                cntA++;
            } else if (c == b) {// 优先匹配ab,价值为x
                if (cntA == 0) {
                    cntB++;
                } else {
                    ans += x;
                    cntA--;
                }
            } else {// 遇到结束符, 匹配ba
                ans += (y * Math.min(cntA, cntB));
                cntA = 0;
                cntB = 0;
            }
        }
        return ans;
    }


}
