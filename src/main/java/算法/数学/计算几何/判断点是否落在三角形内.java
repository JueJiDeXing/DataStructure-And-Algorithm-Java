package 算法.数学.计算几何;

public class 判断点是否落在三角形内 {
    /*
    向量面积法
    如果点O落在三角形ABC内
    那么S(ABC) = S(OAB) + S(OAC) + S(OBC)
     */
    static class Vector {
        double xp, yp;

        public Vector(double x, double y) {
            xp = x;
            yp = y;
        }

        public double cP(Vector vector) {
            /*
               public double calArea(double x1, double y1, double x2, double y2) {
                return Math.abs(x1 * y2 - x2 * y1) / 2;
               }
             */
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
}
