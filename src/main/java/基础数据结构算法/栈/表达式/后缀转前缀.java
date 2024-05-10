package 基础数据结构算法.栈.表达式;

import java.util.Stack;

public class 后缀转前缀 {
    /**
     <div color=rgb(155,200,80)>
     <h1>后缀转前缀</h1>
     考虑错误输入,但输入必须合法<br>
     后缀表达式 ~~~~~~~~ 前缀表达式<br>
     3 4 + ~~~~~~~~~~~ + 3 4<br>
     5 2 * 8 7 - + ~~~~~~~ + * 5 2 - 8 7<br>
     9 2 7 * - 8 3 / + ~~~~~ + - 9 * 2 7 / 8 3<br>
     </div>
     */
    public String suffixToInfix(String[] tokens) {
        Stack<String> numStack = new Stack<>(), res = new Stack<>();//分别存数字和结果
        if (tokens.length == 0) {
            return "";
        }
        int curr = 0;//计数器,表示当前可参与运算的数字个数,用于判断表达式是否合法
        for (String token : tokens) {
            switch (token) {
                case "*":
                case "+":
                case "-":
                case "/": {
                    if (curr < 2) throw new RuntimeException("不是正确的表达式");//遇到双目运算符,算子需要大于等于2个
                    curr--;//计算后,两个数合为1个数,算子减1
                    if (numStack.isEmpty()) {//抛出两个数字,如果不够抛,从res里抛
                        String s1 = res.pop();
                        String s2 = res.pop();
                        res.push(token + s2 + s1);
                    } else {
                        String s2 = numStack.pop();
                        String s1;
                        if (numStack.isEmpty()) {
                            s1 = res.pop();//s1是从res栈抛,那么数字栈抛出的数字是在s1前面还没运算的数
                            res.push(token + s2 + s1);
                        } else {
                            s1 = numStack.pop();
                            res.push(token + s1 + s2);//两个都从数字栈抛,先抛的放后面
                        }
                    }break;
                }
                default : {
                    numStack.push(token);
                    curr++;//算子加1
                }
            }
        }
        if (!numStack.isEmpty()) {
            if (tokens.length == 1) {//单数字表达式
                return tokens[0];
            }
            throw new RuntimeException("不是正确的表达式");
        }
        return res.pop();
    }
}
