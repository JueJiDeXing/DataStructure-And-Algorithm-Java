package 数据结构实现.并查集.进阶并查集;

import java.util.Arrays;
import java.util.Stack;

/**
 可撤销并查集
 */
public class SetUndo {
    /*
    可撤销并查集适用于经过多次操作后,需要回退到某一版本,它的实现依靠栈,并不能像可持久化一样快速回到指定版本
    维护回退:合并时需要按秩合并,每次操作都记录在栈中,回退时从栈中不断弹出,进行反向操作即可
     */
    int[] fa;
    int[] size;
    Stack<int[]> stack = new Stack<>();//用栈记录操作

    public SetUndo(int n) {
        fa = new int[n];
        for (int i = 0; i < n; i++) fa[i] = i;
        size = new int[n];
        Arrays.fill(size, 1);
    }

    public int find(int x) {
        return x == fa[x] ? x : find(fa[x]);//因为是按秩合并,所以不要路径压缩,会破坏性质
    }

    public void union(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx == ry) return;
        if (size[rx] > size[ry]) {
            int t = rx;
            rx = ry;
            ry = t;
        }
        fa[rx] = ry;//小的指向大的
        size[ry] += size[rx];
        stack.push(new int[]{rx, ry});//记录合并操作
    }

    /**
     撤销上一次操作
     */
    public void undo() {
        if (stack.isEmpty()) return;
        int[] op = stack.pop();
        int x = op[0], y = op[1];
        fa[x] = x;   // 操作时为fa[x]=y, 回退fa[x]=x
        size[y] -= size[x];// 操作时为size[y] += size[x]; 回退size[y] -= size[x];
    }

    public boolean isConnect(int x, int y) {
        return find(x) == find(y);
    }


}
