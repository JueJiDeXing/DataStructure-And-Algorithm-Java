package 基础数据结构算法.栈.表达式;

import java.util.Arrays;
import java.util.LinkedList;

public class 中缀转前缀 {
    public static void main(String[] args) {
        String[] token = new String[]{"1", "+", "2", "*", "3"};
        中缀转前缀 test = new 中缀转前缀();
        System.out.println(Arrays.toString(test.infixToPrev(token)));// + * 3 2 1
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>中缀转前缀</h1>
     遇到操作符则入栈, 如果遇到更低级(或平级)的操作符 则弹出栈内的高级、平级运算符<br>
     a+b*c-d -> a 栈[+] -> ab 栈[+,*] -> abc*+ 栈[-] -> abc*+d-<br>
     括号也当做运算符, 左括号直接入栈, 右括号出栈到左括号<br>
     (a+b*c-d)*e -> 栈[(] -> ab 栈[(,+] -> abc 栈[(,+,*] -> abc*+d 栈[(,-]
     -> abc*+d- 栈[] -> abc*+d-e 栈[*] -> abc*+d-e*<br>
     优先级: 左括号 < 加减 < 乘除
     </div>
     */
    public String[] infixToPrev(String[] tokens) {
        LinkedList<String> operatorStack = new LinkedList<>();
        LinkedList<String> res = new LinkedList<>();
        for (int i = tokens.length - 1; i >= 0; i--) {//倒序即可,数字添加到res尾部
            String c = tokens[i];
            switch (c) {
                case "*":
                case "+":
                case "-":
                case "/": {
                    //输入操作符优先级低(或平级)时,栈内优先级高的和平级的都出栈,再入栈操作符
                    while (!operatorStack.isEmpty() && priority(operatorStack.peek()) >= priority(c)) {
                        res.push(operatorStack.pop());
                    }
                    //空栈直接推入操作符 、 优先级高的运算符直接入栈
                    operatorStack.push(c);
                    break;
                }
                case "(": {
                    operatorStack.push(c);//左括号直接入栈
                    break;
                }
                case ")": {
                    //右括号则出栈到左括号
                    while (!operatorStack.isEmpty()) {
                        String pop = operatorStack.pop();
                        if (pop.equals("(")) break;
                        res.push(pop);
                    }
                    break;
                }
                default:
                    res.addLast(c);
            }
        }
        //处理剩余栈内操作符
        while (!operatorStack.isEmpty()) {
            res.push(operatorStack.pop());
        }
        return res.toArray(new String[0]);
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
