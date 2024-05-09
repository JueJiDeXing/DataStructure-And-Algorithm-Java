package 算法.数学.数论;

import 算法OJ.蓝桥杯.真题卷.第11届.国赛.Java大学A组.B咫尺天涯;

public class 皮亚诺曲线 {
    /*
    1阶皮亚诺曲线,3*3
    ┌──┐  |
    │  │  │
    |  └──┘

  2阶皮亚诺曲线, 3^2 * 3^2
    ┌──┐  ┌──┐  ┌──┐  ┌──┐  |
    │  │  │  │  │  │  │  │  │
    │  └──┘  └──┘  │  │  └──┘
    │  ┌──┐  ┌──┐  │  │  ┌──┐
    │  │  │  │  │  │  │  │  │
    └──┘  │  │  └──┘  └──┘  │
    ┌──┐  │  │  ┌──┐  ┌──┐  │
    │  │  │  │  │  │  │  │  │
    |  └──┘  └──┘  └──┘  └──┘
    求k阶曲线中,左下角(0,0),到点(x,y)的距离
     */
    static long[] pow = new long[15];

    static {
        pow[1] = 1;
        for (int i = 2; i <= 13; i++) pow[i] = pow[i - 1] * 3;
    }

    /**
     {@link B咫尺天涯}<br>
     k+1阶曲线,从(0,0)走到(x,y)的距离<br>
     1. 走 count 个k阶,然后递归在下一个k阶函数中走到(nx,ny)<br>
     2. 走 count +1个k阶,然后往后走,减去递归在下一个k阶函数中走到(nx,ny)<br>

     @param k   曲线阶数
     @param x,y 目的地
     @return 沿曲线要走的步数
     */
    static long calc(int k, long x, long y) {
        if (k == 0) return 0;//0阶

        boolean flag = x / pow[k] == 1;// x是否在中间列,中间列是从上往下走的

        long count = x / pow[k] * 3;// 沿着曲线要走多少个k阶才能到达(x,y)所在的矩阵;
        count += flag ? (2 - y / pow[k]) : (y / pow[k]);

        if (count % 2 == 1) {// 走奇数个,因为编号偶数的矩阵是与初始矩阵对称的,而函数的默认起点在左下角,所以x坐标需要在矩阵内反向
            x = pow[k] - x % pow[k] - 1;
        }
        //递归下一阶
        long nx = x % pow[k], ny = y % pow[k];
        if (flag) {
            //在中间列的话先越过(x,y)所在的矩阵,然后往回走
            return (count + 1) * pow[k] * pow[k] - calc(k - 1, nx, ny) - 1;//第offset到第offset+1还多了一步,需要减掉
        }
        return count * pow[k] * pow[k] + calc(k - 1, nx, ny);
    }
}
