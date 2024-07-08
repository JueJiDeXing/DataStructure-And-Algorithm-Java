package 算法.数学.计算几何.公式;

public class 鞋带定理_求任意多边形面积 {
    public static void main(String[] args) throws Exception {
        int[][] points = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};
        System.out.println(getArea(points));
    }
    /**
     S = abs{ sum{x[i]*y[i+1]} - sum{y[i]*x[i+1]} } / 2
     */
    public static double getArea(int[][] points) {
        double sum1 = 0, sum2 = 0;
        for (int i = 0; i < points.length; i++) {
            sum1 += points[i][0] * points[(i + 1) % points.length][1];
            sum2 += points[i][1] * points[(i + 1) % points.length][0];
        }
        return Math.abs(sum1 - sum2) / 2;
    }
}
