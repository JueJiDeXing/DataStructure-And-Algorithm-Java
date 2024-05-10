package 数据结构实现.队列.双端队列;

import java.util.Iterator;


/**
 基于双向环形链表实现
 */
class LinkedListDeque<E> implements Deque<E>, Iterable<E> {

    static class Node<E> {
        LinkedListDeque.Node<E> prev;
        E value;
        LinkedListDeque.Node<E> next;

        public Node(LinkedListDeque.Node<E> prev, E value, LinkedListDeque.Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    int capacity;
    int size;
    LinkedListDeque.Node<E> sentinel = new LinkedListDeque.Node<>(null, null, null);

    public LinkedListDeque(int capacity) {
        this.capacity = capacity;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>向队列头插入值</h1>

     @param value 待插入值
     @return 是否插入成功
     */
    @Override
    public boolean offerFirst(E value) {
        if (isFull()) {
            return false;
        }
        LinkedListDeque.Node<E> a = sentinel;
        LinkedListDeque.Node<E> b = sentinel.next;
        LinkedListDeque.Node<E> added = new LinkedListDeque.Node<>(a, value, b);
        a.next = added;
        b.prev = added;
        size++;
        return true;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>向队列尾插入值</h1>

     @param value 待插入值
     @return 是否插入成功
     */
    @Override
    public boolean offerLast(E value) {
        if (isFull()) {
            return false;
        }
        LinkedListDeque.Node<E> a = sentinel.prev;
        LinkedListDeque.Node<E> b = sentinel;
        LinkedListDeque.Node<E> added = new LinkedListDeque.Node<>(a, value, b);
        a.next = added;
        b.prev = added;
        size++;
        return true;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>抛出队列头</h1>

     @return 返回队列头部值, 如果为空返回null
     */
    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        LinkedListDeque.Node<E> a = sentinel;
        LinkedListDeque.Node<E> removed = sentinel.next;
        LinkedListDeque.Node<E> b = removed.next;
        a.next = b;
        b.prev = a;
        size--;
        return removed.value;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>抛出队列尾</h1>

     @return 返回队列头部值, 如果为空返回null
     */
    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        LinkedListDeque.Node<E> b = sentinel;
        LinkedListDeque.Node<E> removed = sentinel.prev;
        LinkedListDeque.Node<E> a = removed.prev;
        a.next = b;
        b.prev = a;
        size--;
        return removed.value;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>获取队列头</h1>

     @return 返回队列头部值, 如果为空返回null
     */
    @Override
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return sentinel.next.value;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>获取队列尾</h1>

     @return 返回队列尾部值, 如果为空返回null
     */
    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return sentinel.prev.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            LinkedListDeque.Node<E> p = sentinel.next;

            @Override
            public boolean hasNext() {
                return p != sentinel;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }


}
