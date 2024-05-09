package 数据结构.栈.栈问题.表达式;

import java.util.Stack;

//已测试:src.java.数据结构与算法.数据结构.栈.TestEvalRPN
public class 计算后缀表达式 {
    /**
     <div color=rgb(155,200,80)>
     <h1>计算后缀表达式</h1>
     遇到数字则入栈,遇到操作符则弹出操作数,计算后再入栈<br>
     ["2","1","+","3","*"]-> 栈[2] -> 栈[2,1] -> 2+1 -> 栈[3] -> 栈[3,3] -> 3*3 -> 栈[9] ->9
     </div>
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                //操作符,弹出两个数字进行计算后将结果入栈
                case "*":
                case "+":
                case "-":
                case "/": { //弹出两个(注意顺序)
                    Integer b = stack.pop(), a = stack.pop();
                    stack.push(calFourOperations(a, token, b));
                    break;
                }
                //数字入栈
                default: {
                    stack.push(Integer.parseInt(token));
                }
            }
        }
        return stack.pop();
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

    /**
     <div color=rgb(155,200,80)>
     <h1>计算后缀表达式</h1>
     自底而上的递归分治法
     </div>
     */
    int index;

    public int evalRPN2(String[] tokens) {
        index = tokens.length - 1;//从底开始(后缀表达式
        return getNext(tokens);
    }

    private int getNext(String[] tokens) {
        int tmp;
        switch (tokens[index--]) {//+-*/和数字 index都减1
            case "+": {
                return getNext(tokens) + getNext(tokens);
            }
            case "-": {
                tmp = getNext(tokens);
                return getNext(tokens) - tmp;
            }
            case "*": {
                return getNext(tokens) * getNext(tokens);
            }
            case "/": {
                tmp = getNext(tokens);
                return getNext(tokens) / tmp;
            }
            default: {//是数字
                return Integer.parseInt(tokens[index + 1]);
            }
        }
    }
}
