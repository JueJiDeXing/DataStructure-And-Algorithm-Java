package 数据结构实现.堆;

import java.util.Arrays;

public class MaxHeap {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        MaxHeap maxHeap = new MaxHeap(a);
        System.out.println(maxHeap);
    }

    /*
    入队:依次与其父节点比较,寻找插入点
    出队:将根与数组尾节点交换,再重新调整队列(把较大的子节点向上移,元素下潜)
     */
    int[] array;
    public int size;

    public MaxHeap(int capacity) {
        array = new int[capacity];
    }

    public MaxHeap(int[] array) {
        this.array = array;
        this.size = array.length;
        heapify();
    }

    /**
     <h1>弗洛伊德 建堆算法</h1>
     1. 找到最后一个非叶子节点<br>
     2. 从后往前依次找非叶子节点进行下潜<br>
     <h2>时间复杂度:</h2>
     总交换次数:最底层交换0次,倒数第二层交换1次,...,第i层交换i-1次<br>
     [i=1 ~ h] ∑ ( (2^h / 2^i) * (i - 1) ) = 2^h - h - 1  <br>
     由于2^h ≈ n, h≈ log_2_ n ,所以时间复杂度为O(n)
     */
    public void heapify() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(i);
        }
    }


    /**
     <div color=rgb(150,200,80)>
     <h1>索引非法异常</h1>
     </div>

     @param index 非法索引
     @return 返回报错信息
     */
    public static IllegalArgumentException IllegalIndex(int index) {
        return new IllegalArgumentException(
                String.format("index [%d] 不合法%n", index)
        );
    }

    /**
     添加元素
     */
    public boolean offer(int offered) {
        if (isFull()) {
            return false;
        }
        up(offered);
        size++;
        return true;
    }

    @Override
    public String toString() {
        return "MaxHeap{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    /**
     替换堆顶元素
     */
    public void replace(int replaced) {
        if (isEmpty()) {
            throw IllegalIndex(0);
        }
        array[0] = replaced;
        down(0);
    }

    /**
     删除堆顶元素

     @return 堆顶元素值
     */
    public int poll() {
        if (isEmpty()) {
            throw IllegalIndex(0);
        }
        swap(0, size - 1);//交换头元素与尾元素再将尾元素抛出
        size--;
        int value = array[size];
        down(0);//下潜

        return value;
    }

    //大元素上浮
    private void up(int offered) {
        int child = size;
        int parent = (child - 1) / 2;
        while (child > 0 && offered > array[parent]) {
            array[child] = array[parent];//offered比父节点大,将offered上浮
            child = parent;
            parent = (parent - 1) / 2;
        }
        array[child] = offered;//插入
    }

    //小的元素下潜
    public void down(int parent) {
        int left = 2 * parent + 1;
        int right = left + 1;
        int max = parent;//寻找 父,左,右 三者最大的元素
        if (left < size && array[left] > array[max]) {
            max = left;
        }
        if (right < size && array[right] > array[max]) {
            max = right;
        }
        if (max != parent) {//如果子节点比父节点优先级大
            swap(parent, max);// 交换
            down(max);//递归,直到max==parent(父节点大于左右子节点)时停止
        }
    }

    public void swap(int i, int j) {//交换
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public int peek() {
        if (isEmpty()) {
            throw IllegalIndex(0);
        }
        return array[0];//返回堆顶元素
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }
}


