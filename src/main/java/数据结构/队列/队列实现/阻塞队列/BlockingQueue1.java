package 数据结构.队列.队列实现.阻塞队列;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//单锁----------------------------------------------------------------------------------------------------------------------
class BlockingQueue1<E> implements BlockingQueue<E> {
    private E[] array;
    private int head;
    private int tail;
    private int size;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition headWaits = lock.newCondition();
    private final Condition tailWaits = lock.newCondition();


    public BlockingQueue1(int capacity) {
        array = (E[]) new Object[capacity];
    }

    @Override
    public void offer(E e) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (isFull()) {
                tailWaits.await();
            }
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            size++;
            headWaits.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E poll() throws InterruptedException {
        lock.lockInterruptibly();
        E e;
        try {
            while (isEmpty()) {
                headWaits.await();
            }
            e = array[head];
            array[head] = null;
            if (++head == array.length) {
                head = 0;
            }
            size--;
            tailWaits.signal();
        } finally {
            lock.unlock();
        }
        return e;
    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        lock.lockInterruptibly();
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
            size++;
            headWaits.signal();
        } finally {
            lock.unlock();
        }
        return true;
    }


    private boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == array.length;
    }
}
