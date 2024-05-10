package 数据结构实现.树.Node;

public class MyTreeNodeInt {
    public int value;
    public MyTreeNodeInt left;
    public MyTreeNodeInt right;

    public MyTreeNodeInt(int value) {
        this.value = value;
    }

    public MyTreeNodeInt() {
    }

    public MyTreeNodeInt(MyTreeNodeInt left, int value, MyTreeNodeInt right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
