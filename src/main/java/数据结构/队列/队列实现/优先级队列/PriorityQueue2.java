package 数据结构.队列.队列实现.优先级队列;

import 数据结构.队列.队列实现.MyQueue;

/**
 基于有序数组实现
 */
class PriorityQueue2<E extends Priority> implements MyQueue<E> {
    /*
    入队:入队时插入排序查找插入位置
    出队:数组尾元素出队
     */
    Priority[] array;
    int size;

    public PriorityQueue2(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        insert(value);
        size++;
        return true;
    }

    private void insert(E value) {
        int i = size - 1;
        while (i >= 0 && array[i].priority() > value.priority()) {
            array[i + 1] = array[i];//向后移
            i--;
        }
        array[i + 1] = value;//插入
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E value = (E) array[size - 1];
        array[--size] = null;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[size - 1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }
}
