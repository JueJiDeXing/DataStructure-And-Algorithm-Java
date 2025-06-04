package 数据结构实现.堆;

import lombok.Data;

import java.util.Arrays;
import java.util.Comparator;

@Data
public class Heap {
    /*
    入队:依次与其父节点比较,寻找插入点
    出队:将根与数组尾节点交换,再重新调整队列(把较大的子节点向上移,元素下潜)
     */
    int[] array;
    int size;
    Comparator<Integer> cmp;

    public Heap(int capacity, Comparator<Integer> cmp) {
        array = new int[capacity];
        this.cmp = cmp;
    }

    public Heap(int[] array) {
        this.array = array;
        this.size = array.length;
        heapify();//建堆
    }

    /**
     <h1>弗洛伊德 建堆算法</h1>
     1. 找到最后一个非叶子节点<br>
     2. 从后往前依次找非叶子节点进行下潜<br>
     <h2>时间复杂度:</h2>
     总交换次数:第1层交换h-1次,第二层交换h-2次,...,第h层交换0次<br>
     由于2^h ≈ n, h ≈ log_2_ n <br>
     第i层有2^(i-1)个元素, 交换2^(i-1)*(h-i)次<br>
     S = 2^0 * (h-1) + 2^1 * (h-2) + ... + 2^(h-2) * 1 <br>
     2S =              2^1 * (h-1) + 2^2 * (h-2) + ... + 2^(h-1) * 1 <br>
     错位相减: S = -(h-1) + 2^1+2^2+...+2^(h-1) = 2^h - h - 1 = n - h - 1<br>
     所以时间复杂度为O(n)<br>
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
    static IllegalArgumentException IllegalIndex(int index) {
        return new IllegalArgumentException(
                String.format("index [%d] 不合法%n", index)
        );
    }

    /**
     添加元素
     */
    public void offer(int offered) {
        if (isFull()) grow();//扩容
        // 插入
        up(offered);
        size++;
    }

    public void grow() {
        int capacity = size + (size >> 1);
        int[] newArray = new int[capacity];
        System.arraycopy(array, 0,
                newArray, 0, size);
        array = newArray;
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
     弹出堆顶元素

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

    /**
     小元素上浮
     视为将offered添加到了array[size]处,但size还未更新
     */
    private void up(int offered) {
        int child = size;

        while (child > 0) {
            int parent = (child - 1) / 2;
            //boolean cmp = max ? offered > array[parent] : offered < array[parent];//根据大顶堆与小顶堆
            if (cmp.compare(offered, array[parent]) > 0) {
                array[child] = array[parent];//上浮
            } else {
                break;
            }
            child = parent;
        }
        array[child] = offered;//插入
    }

    /**大的元素下潜*/
    public void down(int parent) {
        int left = 2 * parent + 1;
        int right = left + 1;
        int search = parent;//寻找 父,左,右 三者较大的优先级
        if (left < size && cmp.compare(array[left], array[search]) > 0) {
            search = left;
        }
        if (right < size && cmp.compare(array[right], array[search]) > 0) {
            search = right;
        }
        if (search != parent) {//如果子节点比父节点优先级大
            swap(parent, search);// 交换
            down(search);//递归,直到max==parent(父节点大于左右子节点)时停止
        }
    }

    public void swap(int i, int j) {//交换
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public int peek() {
        if (isEmpty()) throw IllegalIndex(0);
        return array[0];//返回堆顶元素
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }
}




