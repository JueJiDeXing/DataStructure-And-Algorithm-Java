package 算法OJ.蓝桥杯.其他;

import java.io.*;
import java.util.Arrays;

/**
 ??
 */
public class 彩色二叉树 {
    /*
    n个节点的完全二叉树
    q次操作
    操作1: 将节点xi及其距离小于yi的节点都染为zi
    操作2: 查询节点xi的颜色

     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static int[] color;
    static boolean[] isVisited;

    public static void main(String[] args) {
        int n = Int(), q = Int();
        color = new int[n + 1];
        isVisited = new boolean[n + 1];
        for (int i = 0; i < q; i++) {
            int type = Int(), xi = Int();
            if (type == 1) {
                int yi = Int(), zi = Int();
                Arrays.fill(isVisited, false);
                update(xi, yi, zi);
            } else {
                System.out.println(color[xi]);
            }
        }
    }

    static void update(int x, int y, int z) {
        if (x >= color.length) return;
        if (isVisited[x]) return;
        isVisited[x] = true;
        color[x] = z;
        if (y > 0) {
            update(x << 1, y - 1, z);
            update((x << 1) | 1, y - 1, z);
            update((x >> 1), y - 1, z);
        }
    }
}
