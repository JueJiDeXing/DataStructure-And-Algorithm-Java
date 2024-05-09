package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学A组;

import java.util.*;

/**
 已AC
 */
public class H太阳 {
    /*
    太阳坐标(X,Y)
    有n条平行于x轴的线段,靠近太阳的线段会把后面的线段遮住
    求能被照射到的线段个数,只要有一段能被照射到就算
     */

    /**
     极角排序
     端点离散化
     扫描
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long X = sc.nextInt(), Y = sc.nextInt();
        Node[] a = new Node[2 * n + 1];//0位置不存储数据
        for (int i = 1; i <= n; i++) {
            long x = sc.nextInt() - X, y = sc.nextInt() - Y, l = sc.nextInt();
            a[2 * i - 1] = new Node(x, y, 1, i);//左端点
            a[2 * i] = new Node(x + l, y, -1, i);//右端点
        }
        Arrays.sort(a, 1, 2 * n + 1, (aa, bb) -> {//按斜率排序,(从左到右)
            long l = aa.x * bb.y - aa.y * bb.x;
            if (l > 0) return -1;
            if (l == 0) return 0;
            return 1;
        });
        TreeSet<pair> s = new TreeSet<>(((o1, o2) -> Math.toIntExact(o1.y - o2.y)));//按y轴坐标排序
        boolean[] ans = new boolean[n + 1];//ans[i]表示编号为i的线段可以被照到
        a[0] = new Node(0, 1, 0, 0);
        for (int i = 1; i <= 2 * n; i++) {
            if (!a[i].equals(a[i - 1])) {//点的斜率不等
                if (!s.isEmpty()) ans[s.first().id] = true;//最高的线段能被照到
            }
            if (a[i].type == 1) {//线段起点
                s.add(new pair(a[i].y, a[i].id));
            } else {//线段终点
                s.remove(new pair(a[i].y, a[i].id));
            }
        }
        long res = 0;
        for (int i = 1; i <= n; i++) {
            if (ans[i]) res++;
        }
        System.out.print(res);
    }

    static class Node {
        long x, y;//线段端点的x,y坐标
        int type, id;//type==1则为左端点,-1则为右端点,id为线段编号

        public Node(long x, long y, int p, int id) {
            this.x = x;
            this.y = y;
            this.type = p;
            this.id = id;
        }

        public boolean equals(Node t) {
            return this.x * t.y == this.y * t.x;
        }
    }

    static class pair {
        long y;//y轴
        int id;//线段编号

        public pair(long x, int y) {
            this.y = x;
            this.id = y;
        }
    }
}


