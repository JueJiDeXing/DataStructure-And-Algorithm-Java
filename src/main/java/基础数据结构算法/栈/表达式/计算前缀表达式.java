package 基础数据结构算法.栈.表达式;

import java.util.Stack;

public class 计算前缀表达式 {
    public static void main(String[] args) {
        计算前缀表达式 test = new 计算前缀表达式();
        String[] token = new String[]{"*", "+", "1", "2", "3"};//9
        System.out.println(test.calPrev(token));
    }

    public int calPrev(String[] token) {
        Stack<Integer> nums = new Stack<>();
        for (int i = token.length - 1; i >= 0; i--) {
            String s = token[i];
            switch (s) {
                case "*":
                case "+":
                case "-":
                case "/": {
                    if (nums.size() < 2) {
                        throw new RuntimeException("表达式错误,算子不足");
                    }
                    int a = nums.pop(), b = nums.pop();
                    nums.push(calFourOperations(b, s, a));break;
                }
                default :{
                    nums.push(Integer.parseInt(s));
                }
            }
        }
        return nums.pop();
    }

    /**
     四则运算 a ? b
     */
    public int calFourOperations(int a, String s, int b) {
        switch (s) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                throw new RuntimeException("错误的操作符");
        }
    }
}
