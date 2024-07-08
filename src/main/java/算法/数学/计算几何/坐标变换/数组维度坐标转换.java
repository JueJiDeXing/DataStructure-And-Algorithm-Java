package 算法.数学.计算几何.坐标变换;

public class 数组维度坐标转换 {
    /**
     一维坐标转二维
     * @param m 二维数组的列数
     */
    public static int[] convert_1_2(int idx, int m) {
        return new int[]{idx / m, idx % m};
    }

    /**
     二维坐标转一维
     @param m 二维数组的列数
     */
    public   static int convert_2_1(int x, int y, int m) {
        return x * m + y;
    }

    /**
     三维坐标转一维
     */
    public static int convert_3_1(int x, int y, int z) {
        int B = 100, C = 100;//A*B*C的三维数组
        return (x * B + y) * C + z;
    }
}
