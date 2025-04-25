package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学A组;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

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
        long X = sc.nextInt(), Y = sc.nextInt();// 太阳位置, 将太阳放在原点处
        Node[] a = new Node[2 * n + 1];
        a[0] = new Node(0, 1, false, 0);// 放一个哨兵点
        for (int i = 1; i <= 2 * n; i += 2) {
            long x = sc.nextInt() - X, y = sc.nextInt() - Y, l = sc.nextInt();
            int lineId = i / 2;
            a[i] = new Node(x, y, true, lineId);//左端点
            a[i + 1] = new Node(x + l, y, false, lineId);//右端点
        }
        Arrays.sort(a, 1, 2 * n + 1, (p1, p2) -> {//按斜率排序,(从左到右)
            long l = p1.x * p2.y - p1.y * p2.x;
            if (l > 0) return -1;
            if (l == 0) return 0;
            return 1;
        });
        TreeSet<Pair> s = new TreeSet<>();//按y轴坐标排序
        boolean[] ans = new boolean[n + 1]; // ans[i]: 线段i能否被照射到
        long res = 0;// 能被照射到的线段数量, res = cnt{ ans[i] = true }
        for (int i = 1; i <= 2 * n; i++) {
            if (!s.isEmpty() && !a[i].equals(a[i - 1])) {// 如果两点斜率相同,s.first端点会被本线段遮住(蓝桥杯数据比较水,去掉这个判断也能过)
                int shine = s.first().id; //最高的线段能被照到
                if (!ans[shine]) {
                    ans[shine] = true;
                    res++;
                }
            }
            Pair line = new Pair(a[i].y, a[i].id);
            var __ = a[i].isLeft ? s.add(line) : s.remove(line);
        }
        System.out.print(res);
    }

    static class Node {
        long x, y;//线段端点的x,y坐标
        boolean isLeft;//是否为左端点
        int id;//线段编号

        public Node(long x, long y, boolean isLeft, int id) {
            this.x = x;
            this.y = y;
            this.isLeft = isLeft;
            this.id = id;
        }

        public boolean equals(Node o) {
            return this.x * o.y == this.y * o.x;
        }
    }

    static class Pair implements Comparable<Pair> {
        long y;//y轴
        int id;//线段编号

        public Pair(long y, int id) {
            this.y = y;
            this.id = id;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.y > o.y) return -1;
            else if (this.y == o.y) return 0;
            return 1;
        }
    }
}


