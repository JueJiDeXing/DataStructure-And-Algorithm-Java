package 算法.动态规划_贪心.动态规划.其他;

public class _1079活字印刷 {
    /*
    输入:String tiles 一个大写英文字母组成的字符串,可能包含重复字母
    输出:int 由tiles中的字母可组成的序列种数
    示例:
        输入:"AAB"
        输出:8
        解释:可能的序列为"A","B","AA","AB","BA","AAB","ABA","BAA"
     */
    public int numTilePossibilities(String tiles) {
        int[][] c = getC();//TODO 待解析

        int[] count = new int[26];//统计字符对应个数
        int totalNum = tiles.length();//总个数
        int type = 0;//种类数
        for (int i = 0; i < totalNum; i++) {
            int ch = tiles.charAt(i) - 'A';
            if (count[ch] == 0) {
                type++;
            }
            count[ch]++;
        }
        /*动态规划_贪心
          dp[i][j]:使用i种类的字符,使用j个字符,可生成的序列的种数
         */
        int[][] dp = new int[type + 1][totalNum + 1];
        dp[0][0] = 1;

        int i = 1;
        for (int h = 0; h < 26; h++) {//变量h用于跳过空字符
            if (count[h] == 0) {
                continue;
            }

            for (int j = 0; j <= totalNum; j++) {
                for (int k = 0; k <= j && k <= count[h]; k++) {
                    dp[i][j] += dp[i - 1][j - k] * c[j][k];//dp[i-1][j-k]:已使用当前字符k个
                }
            }
            i++;//遇到非空字符i才自增,进入下一行dp点位
        }

        //统计最后一行即为结果(使用所有种类字符,使用字符数量1~n)
        int res = 0;
        for (int j = 1; j <= totalNum; j++) {
            res += dp[type][j];
        }
        return res;
    }

    private static int[][] getC() {
        int maxLen = 8;//题目输入限制tiles.length<=7
        int[][] c = new int[maxLen][maxLen];//???????

        for (int i = 0; i < maxLen; i++) {
            c[i][0] = c[i][i] = 1;
            for (int j = 1; j < i; j++) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            }
        }
        return c;
    }

}
