package 数据结构实现.链表;

import java.util.*;
import java.util.function.Consumer;

/**
 <div color=rgb(150,200,80)>
 <h1>环链表</h1>
 手写环形链表源码(双向,带哨兵)
 </div>
 */
class CircleLinkedList implements Iterable<Integer> {
    private Node sentinel = new Node(null, 666, null);

    //头哨兵节点索引为-1

    public CircleLinkedList() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>索引非法异常</h1>
     </div>

     @param index 非法索引
     @return 返回报错信息
     */
    private static IllegalArgumentException IllegalIndex(int index) {
        return new IllegalArgumentException(
                String.format("index [%d] 不合法%n", index)
        );
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>添加节点到头部</h1>
     </div>

     @param value 添加的节点的值
     */
    public void addFirst(int value) {
        Node next = sentinel.next;
        Node node = new Node(sentinel, value, next);
        sentinel.next = node;
        next.prev = node;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>添加节点到尾部</h1>
     </div>

     @param value 添加的节点的值
     */
    public void addLast(int value) {
        Node prev = sentinel.prev;
        Node node = new Node(prev, value, sentinel);
        prev.next = node;
        sentinel.prev = node;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>删除第一个节点</h1>
     </div>

     @throws IllegalArgumentException 链表为空,抛出index非法异常
     */
    public void removeFirst() {
        remove(0);
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>删除指定节点</h1>
     </div>

     @param index 要删除的索引位置节点
     @throws IllegalArgumentException 抛出index非法异常
     */
    public void remove(int index) {
        Node prev = findByIndex(index - 1);
        if (prev == null) {//找不到上一个节点
            throw IllegalIndex(index);
        }
        Node removed = prev.next;
        if (removed == sentinel) {//不能删除尾哨兵
            throw IllegalIndex(index);
        }
        Node next = removed.next;
        prev.next = next;
        next.prev = prev;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>删除最后一个节点</h1>
     </div>

     @throws IllegalArgumentException 抛出index非法异常
     */
    public void removeLast() {
        Node removed = sentinel.prev;
        if (removed == sentinel) {
            throw IllegalIndex(0);
        }
        Node prev = removed.prev;
        prev.next = sentinel;
        sentinel.prev = prev;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>根据元素值删除节点</h1>
     </div>

     @param value 要删除的值
     @throws IllegalArgumentException 抛出index非法异常
     */
    public void removeByValue(int value) {
        Node removed = findByIndex(value);
        if (removed == null) {
            throw IllegalIndex(0);
        }
        Node prev = removed.prev;
        Node next = removed.next;
        prev.next = next;
        next.prev = prev;
    }


    /**
     <div color=rgb(150,200,80)>
     <h1>遍历链表1</h1>
     while循环
     </div>

     @param consumer 对遍历的每个元素执行的函数
     */
    public void foreach1(Consumer<Integer> consumer) {
        Node p = sentinel.next;//从哨兵节点的下一个节点开始遍历
        while (p != sentinel) {
            consumer.accept(p.value);//获取当前节点值
            p = p.next;//指向下一个节点
        }
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>遍历链表2</h1>
     for循环
     </div>

     @param consumer 对遍历的每个元素执行的函数
     */
    public void foreach2(Consumer<Integer> consumer) {
        for (Node p = sentinel.next; p != sentinel; p = p.next) {
            consumer.accept(p.value);
        }
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>遍历链表3</h1>
     迭代器(增强for)
     </div>
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = sentinel.next;//从哨兵节点的下一个节点开始遍历

            @Override
            public boolean hasNext() {//是否有下一个元素
                return p != sentinel;
            }

            @Override
            public Integer next() {//返回当前值,并把指针指向下一个节点
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>查找节点1</h1>
     通过索引访问节点
     </div>

     @param index 要查找的索引值
     @return 返回该索引的节点
     */
    private Node findByIndex(int index) {
        //TODO 这个方法未检查,可能有bug
        int i = 0;
        for (Node p = sentinel.next; p != sentinel; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;//没有找到
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>查找节点2</h1>
     通过元素值访问节点
     </div>

     @param value 要查找的元素值
     @return 返回该索引的节点
     */
    private Node findByValue(int value) {
        Node p = sentinel.next;
        while (p != sentinel) {
            if (p.value == value) {
                return p;
            }
            p = p.next;
        }
        return null;//没有找到
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>查找元素</h1>
     通过索引访问节点的值
     </div>

     @param index 要查找的索引值
     @return 返回该索引节点的值
     @throws IllegalArgumentException 找不到元素,抛出index非法异常
     */
    public int get(int index) {
        Node node = findByIndex(index);
        if (node == null) {
            throw IllegalIndex(index);
        }
        return node.value;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>插入元素</h1>
     通过索引插入节点的值
     </div>

     @param index 要查找的索引值
     @throws IllegalArgumentException 索引越界,抛出index非法异常
     */
    public void insert(int index, int value) {
        Node prev = findByIndex(index - 1);//上一个节点
        if (prev == null) {
            throw IllegalIndex(index);
        }
        Node next = prev.next;//下一个节点

        Node Inserted = new Node(prev, value, next);//新指针
        prev.next = Inserted;
        next.prev = Inserted;
    }

    /**
     <div color=rgb(150,200,80)>
     <h1>双向节点类</h1>
     </div>
     */
    private static class Node {
        Node prev;
        int value;//值
        Node next;//下一个节点

        /**
         <div color=rgb(150,200,80)>
         <h1>有参构造</h1>
         </div>

         @param prev  上一个节点
         @param value 值
         @param next  下一个节点
         */
        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}
