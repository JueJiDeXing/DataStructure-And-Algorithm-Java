package 算法.数学.计算几何;

public class 点的坐标变换 {
    static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        /**
         绕原点旋转angle度
         单位弧度
         方向逆时针,传入负数则为顺时针
         */
        public void rotate(double angle) {
            double tx = x, ty = y;
            x = tx * Math.cos(angle) - ty * Math.sin(angle);
            y = tx * Math.sin(angle) + ty * Math.cos(angle);
        }

        /**
         坐标平移
         */
        public void subtract(Point p) {
            x -= p.x;
            y -= p.y;
        }
    }
}
