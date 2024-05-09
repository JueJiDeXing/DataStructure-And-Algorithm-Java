package 数据结构.队列.队列实现.链队列;import java.util.*;
import java.util.function.Consumer;

/**
 <div color=rgb(155,200,80)>
 <h1>环形数组队列优化2</h1>
 头尾指针不存储索引,而是直接增减<br>
 (int)Integer.toUnsignedLong(tail) % array.length 解决溢出问题<br>
 用X % 2^n = X & (2^n - 1)优化溢出解决方案</div>
 */
class ArrayQueue3<E> implements Iterable<E> {

    private E[] array;
    private int head = 0;
    private int tail = 0;

    @SuppressWarnings("all")
    public ArrayQueue3(int capacity) {//使容量为2^n次方
        //1.抛异常
//        if((capacity&capacity-1)!=0){
//            throw new RuntimeException("capacity必须是2的幂");
//        }

        //2.1改为最接近的2^n
//        capacity = ((int) (Math.log(capacity - 1) / Math.log(2)) + 1);
//        capacity = 1 << capacity;

        //2.2改为最接近的2^n
        capacity--;
        capacity = capacity | capacity >> 1;
        capacity = capacity | capacity >> 2;
        capacity = capacity | capacity >> 4;
        capacity = capacity | capacity >> 8;
        capacity = capacity | capacity >> 16;
        capacity++;

        array = (E[]) new Object[capacity];
    }

    public ArrayQueue3() {
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
//        array[(int)Integer.toUnsignedLong(tail) % array.length] = value;//新实现,tail本身不再为索引
        array[tail & array.length - 1] = value;//优化 X % 2^n = X & (2^n - 1)
        tail++;
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
//        E value = array[(int) Integer.toUnsignedLong(head) % array.length];
        E value = array[head & array.length - 1];
        head++;//新实现
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
        return tail - head == array.length;//无论tail有没有发生溢出,tail-head仍然是正确的
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
//                E value = array[(int) Integer.toUnsignedLong(p) % array.length];
                E value = array[p & array.length - 1];
                p++;
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
