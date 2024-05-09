package 算法OJ.蓝桥杯.真题卷.第9届.国赛.Java大学A组;
/**
 已AC
 */
public class A三角形面积 {
    public static void main(String[] args) {
        int x1 = 0, y1 = 8, x2 = 4, y2 = 0, x3 = 8, y3 = 6;
        System.out.println(cal(x1, y1, x2, y2, x3, y3));//28
    }

    static double cal(int x1, int y1, int x2, int y2, int x3, int y3) {
        /*
        S = AB × AC / 2
          = { (x2-x1,y2-y1) * (x3-x1,y3-y1) } / 2
          = { (x2-x1)*(y3-y1) - (x3-x1)*(y2-y1) } / 2
         */
        return ((x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1)) / 2.0;
    }
}
