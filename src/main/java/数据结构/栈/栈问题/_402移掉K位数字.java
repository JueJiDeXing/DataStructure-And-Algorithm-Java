package 数据结构.栈.栈问题;

public class _402移掉K位数字 {
    /*
    给你一个以字符串表示的非负整数 num 和一个整数 k ，
    移除这个数中的 k 位数字，使得剩下的数字最小。
    请你以字符串形式返回这个最小的数字。
    示例 1 ：
        输入：num = "1432219", k = 3
        输出："1219"
        解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
    示例 2 ：
        输入：num = "10200", k = 1
        输出："200"
        解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
    示例 3 ：
        输入：num = "10", k = 2
        输出："0"
        解释：从原数字移除所有的数字，剩余为空就是 0 。
     */
    public String removeKdigits(String num, int k) {
        int remain = num.length() - k;//在len-k个数字里选择最小的序列
        char[] stack = new char[num.length()];
        int top = 0;

        for (char digit : num.toCharArray()) {
            while (k > 0 && top > 0 && stack[top - 1] > digit) {//单调栈(递增)
                top--; //遇到小数字,抛出栈顶的大数字
                k--;//可抛出次数-1
            }
            stack[top++] = digit; //数字入栈
        }
        //去除前导0
        int idx = 0;
        while (idx < remain && stack[idx] == '0') {
            idx++;
        }
        if (idx == remain) {//全是0
            return "0";
        }
        //返回后一段
        return new String(stack, idx, remain - idx);
    }
}
