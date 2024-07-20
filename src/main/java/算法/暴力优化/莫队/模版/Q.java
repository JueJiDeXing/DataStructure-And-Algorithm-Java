package 算法.暴力优化.莫队.模版;

public class Q {
    public int l, r;// 询问区间
    public int id;// 第几个询问
    public Object object;// 其他信息

    public Q(int l, int r, int id) {
        this.l = l;
        this.r = r;
        this.id = id;
    }

    public Q(int l, int r, int id, Object object) {
        this.l = l;
        this.r = r;
        this.id = id;
        this.object = object;
    }
}
