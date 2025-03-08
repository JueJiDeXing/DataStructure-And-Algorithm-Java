package 数据结构实现.队列.阻塞队列;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 测试--单锁实现
 使用 synchronized关键字 - 功能少
 使用 ReentrantLock可重入锁 - 功能多
 */
class TestThreadUnsafe {
    private final String[] array = new String[10];
    private int tail = 0;
    private int size = 0;
    ReentrantLock lock = new ReentrantLock();//锁
    Condition tailWaits = lock.newCondition();//条件变量对象 集合 存储多个线程

    public void offer(String e) throws InterruptedException {
        //lock.lock();//加锁,其他线程需要等待解锁(阻塞)
        lock.lockInterruptibly();//可打断加锁,阻塞时间过长可以打断并抛出异常
        try {
            while (isFull()) {//使用while,唤醒后重新检查条件,避免虚假唤醒(比如在非移除队列元素的方法中调用了唤醒方法,线程被唤醒但是条件仍然不满足)
                //队列满,则线程等待
                tailWaits.await();//将当前线程加入到tailWaits集合,并阻塞该线程   唤醒方法.signal()需要配合锁使用,没加锁则报错,解锁才唤醒
            }
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            size++;
        } finally {
            lock.unlock();//解锁
        }
    }

    private boolean isFull() {
        return size == array.length;
    }
}
