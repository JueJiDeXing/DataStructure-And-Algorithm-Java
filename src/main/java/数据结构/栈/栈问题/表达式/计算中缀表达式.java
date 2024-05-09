package 数据结构.栈.栈问题.表达式;

import java.util.*;

public class 计算中缀表达式 {
    public static void main(String[] args) {
        计算中缀表达式 test = new 计算中缀表达式();
        String[] token = {"2", "/", "2", "+", "(", "3", "-", "1", ")", "*", "3"};//7
        System.out.println(test.calIn(token));
    }

    public int calIn(String[] token) {
        Stack<Integer> nums = new Stack<>();
        Stack<String> operator = new Stack<>();
        for (String s : token) {
            switch (s) {
                case "+":
                case "-": {
                    while (!operator.isEmpty() && priority(s) <= priority(operator.peek())) {
                        popAndCal(operator, nums);
                    }
                    operator.push(s);
                    break;
                }
                case "*":
                case "/":
                case "(": {
                    operator.push(s);
                    break;
                }
                case ")": {
                    if (operator.isEmpty()) throw new RuntimeException("表达式错误,左括号未闭合");
                    String pop;
                    while (!Objects.equals(pop = operator.pop(), "(")) {
                        if (nums.size() < 2) throw new RuntimeException("表达式错误,算子不足");
                        if (operator.isEmpty()) throw new RuntimeException("表达式错误,左括号未闭合");
                        int a = nums.pop(), b = nums.pop();
                        nums.push(calFourOperations(b, pop, a));
                    }
                    break;
                }
                default: {
                    nums.push(Integer.parseInt(s));
                }
            }
        }
        while (!operator.isEmpty()) {
            popAndCal(operator, nums);
        }
        return nums.pop();
    }

    /**
     弹出一个操作符和两个算子进行四则运算

     @param operator 操作符栈
     @param nums     算子栈
     @throws RuntimeException 算子不足两个 、 错误的操作符
     */
    private void popAndCal(Stack<String> operator, Stack<Integer> nums) {
        String pop = operator.pop();
        if (nums.size() < 2) throw new RuntimeException("表达式错误,算子不足");
        int a = nums.pop(), b = nums.pop();
        nums.push(calFourOperations(b, pop, a));
    }

    /**
     四则运算 a ? b
     */
    public static int calFourOperations(int a, String s, int b) {
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

    /**
     运算符优先级

     @param c 运算符
     @return 优先级权重, int
     */
    static int priority(String c) {
        switch (c) {
            case "*":
            case "/": {
                return 2;
            }
            case "+":
            case "-": {
                return 1;
            }
            case "(": {
                return 0;
            }
            default: {
                throw new RuntimeException("运算符不合法");
            }
        }
    }
}
