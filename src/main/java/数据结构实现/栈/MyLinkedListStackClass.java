package 数据结构实现.栈;

import 数据结构实现.队列.链队列.Node;

import java.util.Iterator;

/**
 <div color=rgb(155,200,80)>
 <h1>栈</h1>
 链表
 </div>
 */
public class MyLinkedListStackClass<E> implements MyStack<E>, Iterable<E> {
    public int capacity;
    public int size;
    public Node<E> head = new Node<>(null, null);
    public MyLinkedListStackClass() {
        this.capacity = Integer.MAX_VALUE;
    }
    public MyLinkedListStackClass(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;

            @Override
            public boolean hasNext() {
                return p != null;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>向栈顶压入元素</h1>

     @param value 待压入值
     @return 是否压入成功
     */
    public boolean push(E value) {
        if (isFull()) {
            return false;
        }
        head.next = new Node<>(value, head.next);//等价于向链表头插入元素
        size++;
        return true;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>从栈顶弹出元素</h1>

     @return 返回栈顶值, 如果为空返回null
     */
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        Node<E> first = head.next;
        E value = first.value;
        head.next = first.next;
        size--;
        return value;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>获取栈顶值</h1>

     @return 返回栈顶值, 如果为空返回null
     */
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return head.next.value;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>检查栈是否为空</h1>

     @return 空栈返回true, 否则返回false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>检查栈是否为满</h1>

     @return 满栈返回true, 否则返回false
     */
    public boolean isFull() {
        return size == capacity;
    }
}
//----------------------------------------------------------------------

