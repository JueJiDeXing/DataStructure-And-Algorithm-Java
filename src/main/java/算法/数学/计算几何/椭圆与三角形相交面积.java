package 算法.数学.计算几何;

import 算法OJ.蓝桥杯.真题卷.第11届.省赛.Java大学A组.H荒岛探测;

import java.util.Random;

public class 椭圆与三角形相交面积 {
    /*
    给出椭圆两焦点坐标,以及L=2a, 再给出三点坐标表示一个三角形
    求椭圆与三角形相交部分的面积
     */
    /*
    首先变换坐标系,将椭圆中心放到原点处,方便计算
    方法1:
        概率统计,确定出一个矩形,在矩形中随机投点,计算落在相交区域的概率
    方法2:
        微分,从小到大枚举[x,x+d]区间,统计y的覆盖,计算小区间面积总和
     */

    /**
     微分
     {@link H荒岛探测}
     */
    public static double calArea(double xA, double yA, double xB, double yB, double L,
                                 double x1, double y1, double x2, double y2, double x3, double y3) {
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
        double dx = 0.00001;
        for (x = xLeft; x <= xRight; x += dx) {//微分
            //统计x坐标下的y轴覆盖长度,x区间长度为d
            double y_1 = k1 * x + b1;//三角形的三条边在x坐标的y
            double y_2 = k2 * x + b2;
            double y_3 = k3 * x + b3;
            double rangeY = Math.sqrt(a_2 * b_2 - b_2 * x * x) / a;//椭圆在x的y坐标(x,rangeY),(x,-rangeY)
            if (x >= t2.x) y_1 = y_3;//t1~t2使用y_1和y_2, t2~t3使用y_2和y_3
            double up = Math.max(y_1, y_2), down = Math.min(y_1, y_2);
            if ((down <= -rangeY && up <= -rangeY) || (down >= rangeY && up >= rangeY)) {//没有覆盖到椭圆
                continue;
            }
            up = Math.min(up, rangeY);
            down = Math.max(down, -rangeY);
            ans += (up - down) * dx;
        }
        return ans;
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
    /**
     概率统计
     */
    public static double calArea2(double xA, double yA, double xB, double yB, double L,
                                  double x1, double y1, double x2, double y2, double x3, double y3) {
        //椭圆方程为 (x-x0)^2 / a^2 + (y-y0)^2 / b^2 = 1;
        double x0 = (xA + xB) / 2.0, y0 = (yA + yB) / 2.0;
        double a = L / 2.0;//pA+pB<=2a,椭圆方程
        double c = Math.sqrt((xA - xB) * (xA - xB) + (yA - yB) * (yA - yB)) / 2;//AB=2c
        double b = Math.sqrt(a * a - c * c);//b^2=a^2-c^2
        // 将椭圆中心变换到原点,椭圆只需要使用a和c确定大小,不需要对AB坐标变换,现在只需要变换三角形坐标
        double nx1, nx2, nx3, ny1, ny2, ny3;
        if (xA == xB) {
            nx1 = y1;
            nx2 = y2;
            nx3 = y3;
            ny1 = x1;
            ny2 = x2;
            ny3 = x3;
        } else {
            /*
            ny1 = dy = 点(x1,y1) 到直线AB的距离, 设直线方程为 y=kx+b,kx-y+b=0
            根据点到直线距离公式 dy= | (kx1-y1+b) / sqrt(k^2+1) |
            因为A在直线上(使用点O误差较大),所以 yA=kxA+b, 代入得 dy= | (kx1-y1-yA+kxA) / sqrt(k^2+1) |

            设经过点O,与AB垂直的直线为l, 直线方程为y = -1/k x +b, x+ky+kb=0
            nx1 = dx = 点(x1,y1) 到直线l的距离, 代入得 dx = | (x1+ky1+kb) / sqrt(k^2+1) |
            因为点O在直线上, 所以 x0+ky0+kb=0, 代入得 dx = | (x1+ky1-(x0+ky0) / sqrt(k^2+1) |
             */
            double k = (yB - yA) / (xB - xA);
            double sqrt = Math.sqrt(k * k + 1);
            double b1 = yA - k * xA;
            ny1 = (k * x1 - y1 + b1) / sqrt;
            ny2 = (k * x2 - y2 + b1) / sqrt;
            ny3 = (k * x3 - y3 + b1) / sqrt;
            double b2 = x0 + k * y0;
            nx1 = (x1 + k * y1 - b2) / sqrt;
            nx2 = (x2 + k * y2 - b2) / sqrt;
            nx3 = (x3 + k * y3 - b2) / sqrt;
        }
        //计算椭圆与三角形(x1,y1),(x2,y2),(x3,y3)的相交面积
        //框定一个矩形,包含三角形和椭圆的全部
        double xmax = Math.max(a, Math.max(nx1, Math.max(nx2, nx3)));
        double xmin = Math.min(-a, Math.min(nx1, Math.min(nx2, nx3)));
        double ymax = Math.max(b, Math.max(ny1, Math.max(ny2, ny3)));
        double ymin = Math.min(-b, Math.min(ny1, Math.min(ny2, ny3)));
        int cnt = 0;

        int total = 20000000;//随机投点,用概率求面积
        for (int i = 0; i < total; i++) {
            double x = random.nextDouble() * (xmax - xmin) + xmin;
            double y = random.nextDouble() * (ymax - ymin) + ymin;
            if (isInTriangle(nx1, ny1, nx2, ny2, nx3, ny3, x, y) && isInElliptic(L, c, x, y)) {
                cnt++;
            }
        }
        return cnt * (xmax - xmin) * (ymax - ymin) / total;
    }

    static Random random = new Random();

    static class Vector {
        double xp, yp;

        public Vector(double x, double y) {
            xp = x;
            yp = y;
        }

        public double cP(Vector vector) {
            return Math.abs(xp * vector.yp - yp * vector.xp);
        }
    }

    static boolean isInTriangle(double x1, double y1, double x2, double y2, double x3, double y3, double x, double y) {
        //向量面积法判断点是否在三角形内部 S = S1 + S2 + S3
        Vector v1 = new Vector(x1 - x, y1 - y);
        Vector v2 = new Vector(x2 - x, y2 - y);
        Vector v3 = new Vector(x3 - x, y3 - y);

        Vector v4 = new Vector(x2 - x1, y2 - y1);
        Vector v5 = new Vector(x3 - x1, y3 - y1);

        double S = v4.cP(v5);
        double s = v1.cP(v2) + v2.cP(v3) + v3.cP(v1);
        return Math.abs(S - s) < 1e-6;
    }

    static boolean isInElliptic(double L, double c, double x, double y) {
        //点与两焦点距离之和小于等于2a
        double dis = Math.sqrt((x - c) * (x - c) + y * y) + Math.sqrt((x + c) * (x + c) + y * y);
        return dis <= L;
    }

}

