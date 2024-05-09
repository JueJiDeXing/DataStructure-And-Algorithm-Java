package 算法OJ.蓝桥杯.真题卷.第11届.省赛.Java大学A组;

import 算法.数学.计算几何.椭圆与三角形相交面积;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 已AC
 */
public class H荒岛探测 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static double D() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return st.nval;
    }

    /**
     {@link 椭圆与三角形相交面积}
     */
    public static void main(String[] args) {
        double xA = D(), yA = D(), xB = D(), yB = D(), L = D();
        double x1 = D(), y1 = D(), x2 = D(), y2 = D(), x3 = D(), y3 = D();
        //solve1(xA, yA, xB, yB, x1, y1, x2, y2, x3, y3, L);//精度不够
        Point A = new Point(xA, yA), B = new Point(xB, yB), t1 = new Point(x1, y1), t2 = new Point(x2, y2), t3 = new Point(x3, y3);
        //以椭圆中心为原点,长轴为x,短轴为y,重建直角坐标系
        double angle = Math.atan2(B.y - A.y, B.x - A.x);//tan(k) 直线AB与x轴的夹角
        A.rotate(-angle);//长轴旋转至平行于x轴
        B.rotate(-angle);
        t1.rotate(-angle);
        t2.rotate(-angle);
        t3.rotate(-angle);
        Point O = new Point((A.x + B.x) / 2, (A.y + B.y) / 2);
        A.subtract(O);//坐标偏移
        B.subtract(O);
        t1.subtract(O);
        t2.subtract(O);
        t3.subtract(O);
        if (t1.x > t2.x) swap(t1, t2);//保证t1,t2,t3的x轴顺序
        if (t1.x > t3.x) swap(t1, t3);
        if (t2.x > t3.x) swap(t2, t3);
        //计算面积
        double k1 = (t2.y - t1.y) / (t2.x - t1.x), b1 = t1.y - k1 * t1.x;  // line t1->t2
        double k2 = (t3.y - t1.y) / (t3.x - t1.x), b2 = t1.y - k2 * t1.x;  // line t1->t3
        double k3 = (t3.y - t2.y) / (t3.x - t2.x), b3 = t2.y - k3 * t2.x;  // line t2->t3
        double a = L / 2.0, c = B.x;//椭圆的a和c
        double a_2 = a * a, b_2 = a * a - c * c;
        double ans = 0.0, x;
        double xLeft = Math.max(-a, t1.x), xRight = Math.min(a, t3.x);
        double dx = 0.00001;//微分, 0.000001超时
        for (x = xLeft; x <= xRight; x += dx) {
            //统计x坐标下的y轴覆盖长度,x区间长度为d
            y1 = k1 * x + b1;//三角形的三条边在x坐标的y
            y2 = k2 * x + b2;
            y3 = k3 * x + b3;
            double rangeY = Math.sqrt(a_2 * b_2 - b_2 * x * x) / a;//椭圆在x的y坐标(x,rangeY),(x,-rangeY)
            if (x >= t2.x) y1 = y3;//t1~t2使用y1和y2, t2~t3使用y2和y3
            double up = Math.max(y1, y2), down = Math.min(y1, y2);
            if ((down <= -rangeY && up <= -rangeY) || (down >= rangeY && up >= rangeY)) {//没有覆盖到椭圆
                continue;
            }
            up = Math.min(up, rangeY);
            down = Math.max(down, -rangeY);
            ans += (up - down) * dx;
        }
        System.out.printf("%.2f", ans);
    }

    static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public void rotate(double angle) {
            double tx = x, ty = y;
            x = tx * Math.cos(angle) - ty * Math.sin(angle);
            y = tx * Math.sin(angle) + ty * Math.cos(angle);
        }

        public void subtract(Point p) {
            x -= p.x;
            y -= p.y;
        }
    }

    static void swap(Point x, Point y) {
        double tx = x.x, ty = x.y;
        x.x = y.x;
        x.y = y.y;
        y.x = tx;
        y.y = ty;
    }
}
