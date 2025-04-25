package 算法.动态规划.数位dp;

import java.util.Scanner;

public class 数位dp模版 {

    /**
     <h1> 数位dp基础版</h1>
     解决[0,high]上的数位问题,如果区间是[low,high]则需要调用两次,dfs(high)-dfs(low-1)

     @param i          当前枚举的数位
     @param limit_high 前面填的数是否都触达上界
     @param high       枚举上界(字符串表示)
     @return [0, high]上的数位问题
     */
    static int dfs(int i, boolean limit_high, String high) {
        int n = high.length();
        if (i == n) return 1;
        int up = limit_high ? high.charAt(i) - '0' : 9;
        int ans = 0;
        for (int j = 0; j <= up; j++) {
            ans += dfs(i + 1, limit_high && j == up, high);
        }
        return ans;
    }

    /**
     <h1>数位dp改良版</h1>
     解决[low,high]上的数位问题

     @param i          当前枚举的数位
     @param limit_low  前面填的数是否都触达下界
     @param limit_high 前面填的数是否都触达上界
     @param low        枚举下界(字符串表示,需要补前导0与high对齐)
     @param high       枚举上界(字符串表示)
     @return [0, high]上的数位问题
     */
    static int dfs(int i, boolean limit_low, boolean limit_high, String low, String high) {
        int n = high.length();
        if (i == n) return 1;
        int down = limit_low ? low.charAt(i) - '0' : 0;
        int up = limit_high ? high.charAt(i) - '0' : 9;
        int ans = 0;
        for (int d = down; d <= up; d++) {
            ans += dfs(i + 1, limit_low && d == down, limit_high && d == up, low, high);
        }
        return ans;
    }

    /**
     <h1>数位dp拓展版</h1>
     支持前导0

     @param i          当前枚举的数位
     @param limit_low  前面填的数是否都触达下界
     @param limit_high 前面填的数是否都触达上界
     @param isNum      前面是否填了非0数字(看题目是否前导0对答案有影响)
     @param low        枚举下界(字符串表示,需要补前导0与high对齐)
     @param high       枚举上界(字符串表示)
     @return [0, high]上的数位问题
     */
    static int dfs(int i, boolean limit_low, boolean limit_high, boolean isNum, String low, String high) {
        int n = high.length();
        if (i == n) return 1;
        int ans = 0;
        if (!isNum && low.charAt(i) == '0') {
            //前面都是0,这一位也可以填0
            ans += dfs(i + 1, true, false, false, low, high);
        }
        int down = limit_low ? low.charAt(i) - '0' : 0;
        int up = limit_high ? high.charAt(i) - '0' : 9;
        int d0 = isNum ? 0 : 1;//前面填了数字,可以从0开始,否则必须从1开始
        for (int d = Math.max(d0, down); d <= up; d++) {
            ans += dfs(i + 1, limit_low && d == down, limit_high && d == up, false, low, high);
        }
        return ans;
    }
}
