package 算法.数学.计算几何;

import java.util.*;

public class 直线切分平面 {
    /*
    n条直线,第i条直线为y=A[i]x+B[i],求将平面被分成多少个区域
     */
    static class Line {
        int A, B;

        Line(int A, int B) {
            this.A = A;
            this.B = B;
        }

        @Override
        public boolean equals(Object o) {
            Line L = (Line) o;
            return A == L.A && B == L.B;
        }

        @Override
        public int hashCode() {
            return Objects.hash(A, B);
        }
    }

    static class Point {
        long xu, xd, yu, yd;

        public Point(int a, int b, int c, int d) {//y=ax+b与y=cx+d的交点
            //x=(b-d)/(c-a)
            //y=(bc+ad)/(c-a)
            xu = (b - d);
            xd = (c - a);
            yu = ((long) b * c - (long) a * d);
            yd = (c - a);
            if (c == a) {
                yu = 0;
            } else {
                long g = gcd(xu, xd);
                xu /= g;
                xd /= g;
                g = gcd(yu, yd);
                yu /= g;
                yd /= g;
            }

        }

        static long gcd(long a, long b) {
            if (b == 0) return a;
            return gcd(b, a % b);
        }

        @Override
        public boolean equals(Object o) {
            Point p = (Point) o;
            return xu == p.xu && xd == p.xd && yu == p.yu && yd == p.yd;
        }

        @Override
        public int hashCode() {
            return Objects.hash(xu, xd, yu, yd);
        }
    }

    /**
     添加直线到平面时,计算新增平面数
     新增平面数 = 交点数 + 1
     交点相当于将直线分段
     而直线的每一段都会经过一个平面,将平面分为两个部分,其贡献就是交点数+1
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Set<Line> lines = new HashSet<>();
        long ans = 1;//初始一个平面
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt(), b = sc.nextInt();
            Line curLine = new Line(a, b);
            if (lines.contains(curLine)) continue;//直线重叠
            //检查第i条直线与之前的直线一共有多少个交点(重叠只计算一次
            HashSet<Point> set = new HashSet<>();
            for (Line line : lines) {
                if (curLine.A == line.A) continue;//没有交点
                set.add(new Point(curLine.A, curLine.B, line.A, line.B));
            }
            ans += 1 + set.size();//有多少个交点,就新增多少个平面+1
            lines.add(curLine);
        }
        System.out.println(ans);
    }

}
