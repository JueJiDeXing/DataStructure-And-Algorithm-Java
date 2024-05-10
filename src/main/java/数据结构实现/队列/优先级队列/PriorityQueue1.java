package 数据结构实现.队列.优先级队列;

import 数据结构实现.队列.MyQueue;

/**
 基于无序数组实现
 */
class PriorityQueue1<E extends Priority> implements MyQueue<E> {
    /*
    入队:直接添加
    出队:选择排序找到优先级最高的元素出队
     */
    Priority[] array;
    int size;

    public PriorityQueue1(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[size++] = value;
        return true;
    }

    /**
     @return 返回优先级最高的索引值
     */
    private int selectMax() {
        int max = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].priority() > array[max].priority()) {
                max = i;
            }
        }
        return max;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        int max = selectMax();
        E value = (E) array[max];
        remove(max);
        return value;
    }

    private void remove(int index) {
        array[index] = null;//置空
        if (index < size - 1) {//不是最后一个元素
            //移动
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        size--;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        int index = selectMax();
        return (E) array[index];
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
