package 数据结构.队列.队列实现.链队列;

/**
 节点类

 @param <E>  */
public class Node<E> {

    public E value;
    public Node<E> next;

    public Node() {
    }

    public Node(E value, Node<E> next) {
        this.value = value;
        this.next = next;
    }

}
