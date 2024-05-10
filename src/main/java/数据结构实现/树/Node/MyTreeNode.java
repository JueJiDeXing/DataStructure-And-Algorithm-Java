package 数据结构实现.树.Node;


/**
 <div color=rgb(155,200,80)>
 <h1> 二叉树节点类</h1>
 </div>
 */
public class MyTreeNode<T> {
    public T value;
    public MyTreeNode<T> left;
    public MyTreeNode<T> right;

    public MyTreeNode(T value) {
        this.value = value;
    }

    public MyTreeNode() {
    }

    public MyTreeNode(MyTreeNode<T> left, T value, MyTreeNode<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
