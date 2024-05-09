package 算法OJ.蓝桥杯.真题卷.第7届.国赛.Java大学A组;

import java.io.*;
import java.util.*;
/**
 已AC,但有部分代码不是很懂
 */
public class E广场舞 {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));


    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }
    static class Pair {
        public double y;
        int idx;

        public Pair(double y, int idx) {
            this.y = y;
            this.idx = idx;
        }
    }

    static class Line {
        int x1, y1, x2, y2;// 线段的两端点坐标
        long a, b, c;// 直线方程 y = (ax+b)/c
        boolean k;// 斜率是否大于0

        public Line(int x1, int y1, int x2, int y2) {
            if (x1 > x2) {//保证端点按x有序
                int tx = x1;
                x1 = x2;
                x2 = tx;
                int ty = y1;
                y1 = y2;
                y2 = ty;
            }
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            calLine();//计算直线方程 y=(ax+b)/c
        }

        /**
         计算直线方程, y = (ax+b)/c
         */
        void calLine() {
            long dy = this.y2 - this.y1, dx = this.x2 - this.x1;
            long c = gcd(dy, dx);
            dy /= c;
            dx /= c;
            if (dx < 0) {
                dx *= -1;
                dy *= -1;
            }
            // y = (ax+b)/c
            this.a = dy;// 斜率: a/c = dy/dx  -> a=dy, c=dx
            this.c = dx;
            this.b = dx * this.y1 - dy * this.x1;  // 截距: b/c = y1 - ax1/c -> b= dx*y1 - dy*x1
            this.k = this.y1 <= this.y2;
        }

        long gcd(long a, long b) {
            return b == 0 ? a : gcd(b, a % b);
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) {
        //输入
        int n = Int();
        int[] list_x = new int[n];//离散化x坐标
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {//点
            int x = Int(), y = Int();
            points[i] = new Point(x, y);
            list_x[i] = x;//存储x坐标
        }
        //构建线段
        Line[] lines = new Line[n];
        for (int i = 0; i < n; i++) {
            int x1 = points[i].x, y1 = points[i].y;
            int x2 = points[(i + 1) % n].x, y2 = points[(i + 1) % n].y;
            lines[i] = new Line(x1, y1, x2, y2);//通过两端点构建线段,Line存储端点坐标和直线方程
        }
        //离散化
        Arrays.sort(list_x);
        //统计
        System.out.println(countAll(n, list_x, lines));
    }

    private static long countAll(int n, int[] list_x, Line[] lines) {
        //枚举x区间, 求和 x区间 [xl,xr] 所截区域的完整格子数
        long res = 0;
        for (int i = 0; i + 1 < n; i++) {
            int xl = list_x[i], xr = list_x[i + 1];
            if (xl == xr) continue;
            // 将在[xl,xr]内的线段存储排序
            List<Pair> rank_y = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                Line line = lines[j];
                if (!(line.x1 <= xl && xr <= line.x2)) continue;//检查线段是否在该区域内(线段两端点一定包裹区间[xl,xr])
                //排序规则:按将x区域中点对应线段y轴坐标升序
                double centerY = (line.a * (double) (xl + xr) / 2.0 + line.b) / line.c;
                rank_y.add(new Pair(centerY, j));//{y坐标,线段编号}
            }
            rank_y.sort(Comparator.comparingDouble(o -> o.y));
            //每两个线段成一对,构成一个封闭图形,计算格子个数
            for (int j = 0; j < rank_y.size(); j += 2) {
                Line L1 = lines[rank_y.get(j + 1).idx], L2 = lines[rank_y.get(j).idx];
                res += doCount(L1, L2, xl, xr);//计算L1,L2在[xl,xr]上构成的封闭图形中的完整方块个数
            }
        }
        return res;
    }


    /**
     计算 sum{ ceil(y) | y = (a*x+b)/c,x∈[0,n) }
     */
    static long cal(long a, long b, long c, long n) {
        if (n == 0) return 0;
        if (a < 0) return cal(-a, a * (n - 1) + b, c, n);//保证a>=0

        long res = 0;
        if (a >= c) res += n * (n - 1) / 2 * (a / c);
        a %= c;
        if (Math.abs(b) >= c) res += n * (b / c);
        b %= c;
        if (a != 0) res += cal(c, (a * n + b) % c, a, (a * n + b) / c);
        return res;
    }


    static long floor(Line L, int xl, int xr) {
        long res = cal(L.a, L.b, L.c, xr + 1);
        res -= cal(L.a, L.b, L.c, xl);
        return res;
    }

    static long ceil(Line L, int xl, int xr) {
        long res = cal(L.a, L.b + L.c - 1, L.c, xr + 1);
        res -= cal(L.a, L.b + L.c - 1, L.c, xl);
        return res;
    }

    static boolean compare(Line L0, Line L1, int x1, int x2) {

        long v1 = L0.a * x1 + L0.b, v2 = L1.a * x2 + L1.b;
        long I1 = v1 / L0.c, I2 = v2 / L1.c;
        if (I1 <= I2) return true;
        if (I1 - I2 > 1) return false;

        long u = v1 % L0.c, v = v2 % L1.c;
        return u * L1.c < v * L0.c;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static int toInt(boolean b) {
        return b ? 1 : 0;
    }

    static long doCount(Line L0, Line L1, int xl, int xr) {
        //除去左边 floor<=ceil 的情况
        if (compare(L0, L1, xl + toInt(!L0.k), xl + toInt(L1.k))) {
            int ll = xl - 1, rr = xr + 1;
            while (ll + 1 != rr) {
                int mid = (ll + rr) >>> 1;
                if (compare(L0, L1, mid + toInt(!L0.k), mid + toInt(L1.k))) ll = mid;
                else rr = mid;
            }
            xl = rr;
            if (xl > xr) return 0;
        }
        //除去右边 floor<=ceil 的情况
        if (compare(L0, L1, xr - toInt(L0.k), xr - toInt(!L1.k))) {
            int ll = xl - 1, rr = xr + 1;
            while (ll + 1 != rr) {
                int mid = (ll + rr) >>> 1;
                if (compare(L0, L1, mid - toInt(L0.k), mid - toInt(!L1.k))) rr = mid;
                else ll = mid;
            }
            xr = ll;
            if (xl > xr) return 0;
        }

        long res = 0;
        //计算上边界下方格点数
        if (L0.k) {
            res += floor(L0, xl, xr - 1);  //斜率>0
        } else {
            res += floor(L0, xl + 1, xr);   //斜率<0
        }
        //计算下边界下方格点数
        if (L1.k) {
            res -= ceil(L1, xl + 1, xr);
        } else {
            res -= ceil(L1, xl, xr - 1);
        }
        return res;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
