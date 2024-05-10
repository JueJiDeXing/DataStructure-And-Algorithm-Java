package 数据结构实现.设计数据结构;

import java.util.LinkedList;

public class _155最小栈 {
/*
设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
实现 MinStack 类:
MinStack() 初始化堆栈对象。
void push(int val) 将元素val推入堆栈。
void pop() 删除堆栈顶部的元素。
int top() 获取堆栈顶部的元素。
int getMin() 获取堆栈中的最小元素。
 */
}

class MinStack {
    //思路:使用另外一个栈记录每次的最小值
    LinkedList<Integer> stack = new LinkedList<>();
    LinkedList<Integer> min_stack = new LinkedList<>();

    public MinStack() {
        min_stack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.push(val);
        min_stack.push(Math.min(val, min_stack.peek()));
    }

    public void pop() {
        if (stack.isEmpty()) return;
        stack.pop();
        min_stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min_stack.peek();
    }
}

class MinStack2 {
    static class Data {
        int val;
        int min;

        public Data(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }


    //优化,只使用一个栈,将元素与最小值作为整体放入
    LinkedList<Data> stack = new LinkedList<>();

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(new Data(val, val));
        } else {
            stack.push(new Data(val, Math.min(stack.peek().min, val)));
        }

    }

    public void pop() {
        if (stack.isEmpty()) return;
        stack.pop();
    }

    public int top() {
        return stack.peek().val;
    }

    public int getMin() {
        return stack.peek().min;
    }

}
