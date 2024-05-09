package 算法.数学.计算几何;

public class 三角形面积 {
    public static void main(String[] args) {
        三角形面积 test = new 三角形面积();
        System.out.println(test.calArea(new double[]{0, 3}, new double[]{0, 0}, new double[]{4, 0}));
    }

    /**
     <h1>根据三边长度计算三角形面积</h1>
     海伦公式:S=sqrt[ p(p-a)(p-b)(p-c) ]<br>
     其中p等于三角形周长的一半
     */
    public double calArea(double a, double b, double c) {
        double p = (a + b + c) / 2.0;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    /**
     <h1>根据三点坐标计算三角形面积</h1>
     构造向量:<br>
     AB = (x2 - x1, y2 - y1) ; AC = (x3 - x1, y3 - y1)<br>
     则S= |AB||AC|sinα /2 = AB×AC /2<br>
     其中 AB×AC= (x2 - x1)(y3 - y1) - (x3 - x1)(y2 - y1)<br><br>
     附: 向量积（矢积）与数量积（标积）的区别
     <table>
     <tr><th>名称</th><th>标积/内积/数量积/点积</th><th>矢积/外积/向量积/叉积</th></tr>
     <tr><th> 运算式</th><td>a·b=|a||b|·cosθ</td><td> a×b=c，其中|c|=|a||b|·sinθ，c的方向遵守右手定则</td></tr>
     <tr><th> 运算式</th><td>a·b=a1b1+a2b2+...</td><td> a×b=(a2b3-a3b2，a3b1-a1b3，a1b2-a2b1)</td></tr>
     <tr><th>几何意义</th><td>向量a在向量b方向上的投影与向量b的模的乘积</td><td>c是垂直a、b所在平面，且以|b|·sinθ为高、|a|为底的平行四边形的面积</td></tr>
     <tr><th>运算结果的区别</th><td>    标量（常用于物理）/数量（常用于数学）</td><td>矢量（常用于物理）/向量（常用于数学）</td></tr>
     </table>

     @param p1 坐标 p=[x,y]
     */
    public double calArea(double[] p1, double[] p2, double[] p3) {
        return Math.abs((p2[0] - p1[0]) * (p3[1] - p1[1]) - (p3[0] - p1[0]) * (p2[1] - p1[1])) / 2;
    }

    /**
     @param diffX1 p1.x - p.x
     @param diffY1 p1.y - p.y
     @param diffX2 p2.x - p.x
     @param diffY2 p2.y - p.y
     */
    public double calArea(double diffX1, double diffY1, double diffX2, double diffY2) {
        return Math.abs(diffX1 * diffY2 - diffX2 * diffY1) / 2;
    }

    /**
    <h1>根据周长与内切圆半径计算面积</h1>
    S=(a+b+c)r/2
    面积 = 三角形周长 与 内切圆半径 乘积 的一半。
     */
    public double calAreaByCircle(double a, double b, double c, double r) {
        return (a + b + c) * r / 2;
    }
}
