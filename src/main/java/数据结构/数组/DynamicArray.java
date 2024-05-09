package 数据结构.数组;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 <div color=rgb(150,200,80)>
 <h1>动态数组</h1>
 </div>
 */
public class DynamicArray implements Iterable<Integer> {
    //数组地址
    //每个int占4个字节,前一个元素+一个int字节=后一个元素的地址
    //对齐字节:所有的数据都是8个字节的整数倍,不足的用对齐字节补齐
    //数组=8字节对象头markword+4字节class指针+4字节数组长度+n*int字节元素大小+对齐字节
    //例如一个数组有5个元素,则其大小=8+4+4+5*4+4(alignment)=40

    //二维数组与缓存行
    //缓存的最小存储单元是缓存行,每次访问到一个元素,都会读取后续的64个字节填满一个缓存行(空间局部性)
    //当缓存里有所需的数据时CPU直接从缓存中读取(快),如果没有则读取内存加载一个缓存行(慢),缓存装满后覆盖前面的缓存行
    // 数组的元素地址是连续分布的,所以二维数组行遍历比列遍历快得多
    public int size = 0;//逻辑大小
    public int capacity = 8;//容量
    public int[] array = {};//懒惰初始化,当添加元素时创建

    /**
     <div color=rgb(150,200,80)>
     <h1>添加元素</h1>
     添加一个元素到数组尾部
     </div>

     @param element 要添加的元素
     */
    public void add(int element) {
        //array[size] = element
        //size++
        add(size, element);
    }

    /**
     <div color=rgb(150,200,80)>
     <h1 >添加元素</h1>
     插入一个元素到数组指定索引位置
     </div>

     @param index   要插入的位置
     @param element 要插入的元素
     */
    public void add(int index, int element) {
        checkAndGrow();//容量检查
        if (0 <= index && index < size) {
            System.arraycopy(array, index,
                    array, index + 1, size - index);//从index处的元素全部向后移一位
        }
        array[index] = element;//插入元素(如果index==size直接添加到末尾,不拷贝数组)
        size++;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1 >容量检查</h1>
     第一次添加元素时开辟数组空间<br>
     当数组装满元素时,扩容至1.5倍
     </div>
     */
    private void checkAndGrow() {
        if (size == 0) {
            array = new int[capacity];
        } else if (size == capacity) {
            capacity += capacity >> 1;//扩容1.5倍
            int[] newArray = new int[capacity];//创建新数组
            System.arraycopy(array, 0, newArray, 0, size);//拷贝元素
            array = newArray;
        }
    }

    /**
     <div color=rgb(150,200,80)>
     <h1 >删除元素</h1>
     删除指定索引处的元素
     </div>

     @param index 要删除的位置
     @return 返回该删除的元素
     */
    public int remove(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        int removed = array[index];
        if (index < size - 1) {
            System.arraycopy(array, index + 1,
                    array, index, size - index - 1);//从index+1处的元素全部向前移一位
        }
        array[size] = 0;
        size--;
        return removed;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>查询元素</h1>
     通过索引访问数组元素<br>
     支持负索引访问
     </div>

     @param index 要查询的索引
     @return 返回该索引对应元素
     */
    public int get(int index) {
        while (index < 0) {
            index += size;
        }
        while (index >= size) {
            index -= size;
        }
        return array[index];
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>遍历元素1</h1>
     for循环获取到每一个元素
     </div>

     @param consumer 接受一个函数
     */
    public void foreach(Consumer<Integer> consumer) {
        for (int i = 0; i < size; i++) {
            consumer.accept(array[i]);
        }
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>遍历元素2</h1>
     通过迭代器遍历
     </div>
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int i = 0;

            @Override
            public boolean hasNext() {//是否有下一个元素
                return i < size;
            }

            @Override
            public Integer next() {//返回当前元素,并让指针移向下一个元素
                return array[i++];
            }
        };
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>遍历元素3</h1>
     通过流遍历
     </div>
     */
    public IntStream stream() {//转换为流
        return IntStream.of(Arrays.copyOfRange(array, 0, size));
    }


    public int[] array() {
        return Arrays.copyOfRange(array, 0, size);
    }
}
