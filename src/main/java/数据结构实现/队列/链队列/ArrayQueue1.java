package 数据结构实现.队列.链队列;

import java.util.*;
import java.util.function.Consumer;

/**
 <div color=rgb(155,200,80)>
 <h1>队列2:环形数组队列</h1>
 手写队列(环形数组实现)</div>
 */
class ArrayQueue1<E> implements Iterable<E> {

    private E[] array;
    private int head = 0;
    private int tail = 0;

    @SuppressWarnings("all")
    public ArrayQueue1(int capacity) {
        array = (E[]) new Object[capacity + 1];//留一个空位给tail,方便判断空与满的情况
    }

    public ArrayQueue1() {
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>向队列尾插入值</h1>
     </div>

     @param value 待插入值
     @return 是否插入成功
     */
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail] = value;//插入值
        tail = (tail + 1) % array.length;
        return true;
    }


    /**
     <div color=rgb(155,200,80)>
     <h1>抛出队列头</h1>
     </div>

     @return 返回队列头部值, 如果为空返回null
     */
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E value = array[head];
        head = (head + 1) % array.length;//head指针右移(如果到数组尾则更新到数组头)
        return value;
    }


    /**
     <div color=rgb(155,200,80)>
     <h1>获取队列头</h1>
     </div>

     @return 返回队列头部值, 如果为空返回null
     */
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[head];
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
        return (tail + 1) % 5 == head;//tail前进一步等于head为满(因为最后一位是不存数据的)
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E value = array[p];
                p = (p + 1) % array.length;
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
