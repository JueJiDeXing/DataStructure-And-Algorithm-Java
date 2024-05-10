package 数据结构实现.队列.阻塞队列;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//双锁----------------------------------------------------------------------------------------------------------------------
class BlockingQueue2<E> implements BlockingQueue<E> {
    private final E[] array;
    private int head;
    private int tail;
    private final AtomicInteger size = new AtomicInteger(0);//原子整数类

    private final ReentrantLock tailLock = new ReentrantLock();
    private final ReentrantLock headLock = new ReentrantLock();
    private final Condition headWaits = headLock.newCondition();
    private final Condition tailWaits = tailLock.newCondition();

    @SuppressWarnings("all")
    public BlockingQueue2(int capacity) {
        array = (E[]) new Object[capacity];
    }

    @Override
    public void offer(E e) throws InterruptedException {
        tailLock.lockInterruptibly();//使用了两把锁,在offer的同时也可以poll,提高效率
        int c;//新增元素前的元素个数
        try {
            while (isFull()) {
                tailWaits.await();
            }
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            //size++;// <!-- 1.读取 2.自增 3.写回 -->
            c = size.getAndIncrement();//自增
            if (c + 1 < array.length) {//队列仍然非满,则唤醒下一个offer线程
                tailWaits.signal();
            }
//            headLock.lock();// <!-- 死锁问题 -->
//            try {
//                headWaits.signal();//唤醒前先加锁,配对使用
//            } finally {
//                headLock.unlock();
//            }
        } finally {
            tailLock.unlock();
        }
        // 队列元素个数0->1时加锁
        if (c == 0) {//级联思想减少加锁次数,offer方只有一个线程加poll的锁,poll方一个线程解锁另一个线程(级联)
            headLock.lock();//把加锁与解锁写在与另一个锁平级的地方,避免死锁
            try {
                headWaits.signal();
            } finally {
                headLock.unlock();

            }
        }
    }

    @Override
    public E poll() throws InterruptedException {
        headLock.lockInterruptibly();
        E e;
        int c;//取走前的元素个数
        try {
            while (isEmpty()) {
                headWaits.await();
            }
            e = array[head];
            array[head] = null;
            if (++head == array.length) {
                head = 0;
            }
            //size--;
            c = size.getAndDecrement();

            if (c > 1) {//减少前大于1,说明有富余的,可以唤醒其他一个poll线程
                headWaits.signal();
            }
//            tailLock.lock();
//            try {
//                tailWaits.signal();
//            } finally {
//                tailLock.unlock();
//            }
        } finally {
            headLock.unlock();
        }
        if (c == array.length) {//满->不满则加锁唤醒
            tailLock.lock();
            try {
                tailWaits.signal();
            } finally {
                tailLock.unlock();
            }
        }

        return e;
    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        tailLock.lockInterruptibly();
        int c;
        try {
            long t = TimeUnit.MILLISECONDS.toNanos(timeout);//毫秒转换为ns单位
            while (isFull()) {
                if (t < 0) {
                    return false;
                }
                t = tailWaits.awaitNanos(t);//单位:ns,返回值:还剩余的等待时间
            }
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            //size++;
            c = size.getAndIncrement();
//            headLock.lock();
//            try {
//                headWaits.signal();
//            } finally {
//                headLock.unlock();
//            }
        } finally {
            tailLock.unlock();
        }
        if (c == 0) {
            headLock.lock();
            try {
                headWaits.signal();
            } finally {
                headLock.unlock();
            }
        }
        return true;
    }


    private boolean isEmpty() {
        return size.get() == 0;
    }

    private boolean isFull() {
        return size.get() == array.length;
    }
}
