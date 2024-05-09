package 数据结构.队列.队列实现.双端队列;

public interface Deque<E> {
    /**
     <div color=rgb(155,200,80)>
     <h1>向队列头插入值</h1>
     @param value 待插入值
     @return 是否插入成功
     */
    boolean offerFirst(E value);
    /**
     <div color=rgb(155,200,80)>
     <h1>向队列尾插入值</h1>
     @param value 待插入值
     @return 是否插入成功
     */
    boolean offerLast(E value);
    /**
     <div color=rgb(155,200,80)>
     <h1>抛出队列头</h1>
     @return 返回队列头部值,如果为空返回null
     */
    E pollFirst();
    /**
     <div color=rgb(155,200,80)>
     <h1>抛出队列尾</h1>
     @return 返回队列头部值,如果为空返回null
     */
    E pollLast();
    /**
     <div color=rgb(155,200,80)>
     <h1>获取队列头</h1>
     @return 返回队列头部值,如果为空返回null
     */
    E peekFirst();
    /**
     <div color=rgb(155,200,80)>
     <h1>获取队列尾</h1>
     @return 返回队列尾部值,如果为空返回null
     */
    E peekLast();
    /**
     <div color=rgb(155,200,80)>
     <h1>检查队列是否为空</h1>
     @return 空队列返回true,否则返回false
     */
    boolean isEmpty();
    /**
     <div color=rgb(155,200,80)>
     <h1>检查队列是否为满</h1>
     @return 满队列返回true,否则返回false
     */
    boolean isFull();
}
