package 算法.数学.计算几何;

public class 距离公式 {
    /**
     两点距离
     */
    public static double d1(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    /**
     点到直线
     */
    public static double d2(int A, int B, int C, int x, int y) {
        return Math.abs(A * x + B * y + C) / Math.sqrt(A * A + B * B);
    }

    /**
     平行线距离
     */
    public static double d3(int A, int B, int C1, int C2) {
        return Math.abs(C1 - C2) / Math.sqrt(A * A + B * B);
    }


}
