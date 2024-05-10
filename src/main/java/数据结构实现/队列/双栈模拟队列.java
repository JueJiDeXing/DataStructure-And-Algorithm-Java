package 数据结构实现.队列;

import java.util.*;

class 双栈模拟队列 {
    /*
       队列头            队列尾

       顶     底     底      顶
       s1                   s2

       入队列:调用s2.push()
       出队列:s1不为空则直接调用s1.pop(),否则先把s2移至s1,再调用s1.pop()
     */
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    public void push(int x) {
        s2.push(x);
    }

    public int pop() {
        if (s1.isEmpty()) {
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
        return s1.pop();
    }

    public int peek() {
        if (s1.isEmpty()) {
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
        return s1.peek();
    }

    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
