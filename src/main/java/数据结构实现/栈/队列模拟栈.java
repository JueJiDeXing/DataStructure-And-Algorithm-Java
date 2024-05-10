package 数据结构实现.栈;import java.util.*;

class 队列模拟栈{
    /*
       栈顶                栈底

       队列头              队列尾


       入栈:栈为空则调用queue.offer(),不为空则添加后再把前面的元素从队列头移除加在队列尾
       出栈:调用queue.pop()
     */
    int size=0;
    Queue<Integer> queue=new LinkedList<>();
    public void push(int x){
        queue.offer(x);
        for (int i = 0; i < size; i++) {
            queue.offer(queue.poll());
        }
        size++;
    }
    public int pop(){
        if (queue.isEmpty()){
            throw new RuntimeException("空队列");
        }
        size--;
        return queue.poll();
    }
    public int top(){
        if (queue.isEmpty()){
            throw new RuntimeException("空队列");
        }
        return queue.peek();
    }
    public boolean isEmpty(){
        return queue.isEmpty();
    }

}
