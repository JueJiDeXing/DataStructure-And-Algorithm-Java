package 数据结构实现.队列.链队列;

import 数据结构实现.队列.MyQueue;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 <div color=rgb(155,200,80)>
 <h1>队列1:链表队列</h1>
 手写队列(链表)</div>
 */
public class LinkedListQueue<E> implements MyQueue<E>, Iterable<E> {


    Node<E> head = new Node<>(null, null);//哨兵节点(无值)
    Node<E> tail = head;//尾节点(最后一个节点)
    private int size;//节点数
    private int capacity = Integer.MAX_VALUE;//容量

    {
        tail.next = head;
    }

    public LinkedListQueue() {
    }

    public LinkedListQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>向队列尾插入值</h1>
     </div>

     @param value 待插入值
     @return 是否插入成功
     */
    public boolean offer(E value) {
        if (isFull()) {//满队列
            return false;
        }
        Node<E> added = new Node<>(value, head);
        tail.next = added;
        tail = added;
        size++;
        return true;//考虑容量时可能返回flase
    }


    /**
     <div color=rgb(155,200,80)>
     <h1>抛出队列头节点</h1>
     获取头节点并从队列中移除这个节点</div>

     @return 返回队列头节点, 如果为空返回null
     */
    public E poll() {
        if (isEmpty()) {//空则返回null
            return null;
        }
        Node<E> first = head.next;
        head.next = first.next;//将头节点指向下一个节点
        if (first == tail) {//删除了仅有的一个节点
            tail = head;
        }
        size--;
        return  first.value;
    }


    /**
     <div color=rgb(155,200,80)>
     <h1>获取队列头节点值</h1>
     </div>

     @return 返回队列头部值, 如果为空返回null
     */
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return head.next.value;


    }


    /**
     <div color=rgb(155,200,80)>
     <h1>检查队列是否为空</h1>
     </div>

     @return 空队列返回true, 否则返回false
     */
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>检查队列是否已满</h1>
     </div>

     @return 满队列返回true, 否则返回false
     */
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;

            @Override
            public boolean hasNext() {
                return p != head;//走到head即为迭代结束
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return Iterable.super.spliterator();
    }
}


//--------------------------------------------------------------------------------------------


//--------------------------------------------------------------------------------------------


//--------------------------------------------------------------------------------------------


