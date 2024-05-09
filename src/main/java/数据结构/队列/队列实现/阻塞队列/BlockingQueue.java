package 数据结构.队列.队列实现.阻塞队列;

//----------------------------------------------------------------------------------------------------------------------
interface BlockingQueue<E> {
    void offer(E e) throws InterruptedException;

    boolean offer(E e, long timeout) throws InterruptedException;

    E poll() throws InterruptedException;
}
