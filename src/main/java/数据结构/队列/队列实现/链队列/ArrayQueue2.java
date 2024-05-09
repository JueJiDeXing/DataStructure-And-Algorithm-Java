package 数据结构.队列.队列实现.链队列;

import java.util.*;
import java.util.function.Consumer;

/**
 <div color=rgb(155,200,80)>
 <h1>环形数组队列优化1</h1>
 不再留一个空位给尾指针</div>
 */
class ArrayQueue2<E> implements Iterable<E> {

    private E[] array;
    private int head = 0;
    private int tail = 0;
    int size;//元素个数

    @SuppressWarnings("all")
    public ArrayQueue2(int capacity) {
        array = (E[]) new Object[capacity];//不留一个空位给tail,功能由size代替
    }

    public ArrayQueue2() {
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
        size++;
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
        size--;
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
        return size == 0;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>检查队列是否已满</h1>
     </div>

     @return 满队列返回true, 否则返回false
     */
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;
            int count = 0;//遍历的个数

            @Override
            public boolean hasNext() {
                //return p != tail;
                //因为没有留空位给tail,所以遍历完的时候头尾重合
                return count < size;
            }

            @Override
            public E next() {
                E value = array[p];
                p = (p + 1) % array.length;
                count++;
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
