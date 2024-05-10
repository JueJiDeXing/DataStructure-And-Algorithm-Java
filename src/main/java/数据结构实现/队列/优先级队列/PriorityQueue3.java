package 数据结构实现.队列.优先级队列;

import 数据结构实现.队列.MyQueue;

/**
 <h1>基于<b>大顶堆</b>实现</h1>
 堆是一种基于树的结构,通常用完全二叉树实现,完全二叉树可以使用线性数组实现<br><br>
 如果从索引0开始存储节点:节点i的父节点为floor((i-1)/2)  [i>0] ,<br>
 左子节点为2i+1,右子节点为2i+2  [index < size]<br>
 如果从索引1开始存储节点:节点i的父节点为floor(i/2)  [i>0] ,<br>
 左子节点为2i,右子节点为2i+1 [index < size]
 */
class PriorityQueue3<E extends Priority> implements MyQueue<E> {
    /*
    入队:依次与其父节点比较,寻找插入点
    出队:将根与数组尾节点交换,再重新调整队列(把较大的子节点向上移,元素下潜)
     */
    Priority[] array;
    int size;

    public PriorityQueue3(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        int child = size++;//从最底层(小)向上(大)找插入位置
        int parent = (child - 1) / 2;
        while (child > 0 && value.priority() > array[parent].priority()) {
            array[child] = array[parent];//比父节点大,父节点优先级小,将父节点向下移
            child = parent;
            parent = (parent - 1) / 2;
        }
        array[child] = value;//插入
        return true;
    }


    @Override
    public E poll() {//抛出堆顶元素(优先级最高)
        if (isEmpty()) {
            return null;
        }
        swap(0, size - 1);//交换头元素与尾元素再将尾元素抛出
        size--;
        Priority value = array[size];
        array[size] = null;
        down(0);//下潜

        return (E) value;
    }

    private void down(int parent) {//优先级小的元素下潜
        int left = 2 * parent + 1;
        int right = left + 1;
        int max = parent;//大顶堆,寻找 父,左,右 三者较大的优先级
        if (left < size && array[left].priority() > array[max].priority()) {
            max = left;
        }
        if (right < size && array[right].priority() > array[max].priority()) {
            max = right;
        }
        if (max != parent) {//如果子节点比父节点优先级大
            swap(parent, max);// 交换
            down(max);//递归,直到max==parent,父节点大于左右子节点时停止
        }
    }

    private void swap(int i, int j) {//交换
        Priority t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[0];//返回堆顶元素
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
