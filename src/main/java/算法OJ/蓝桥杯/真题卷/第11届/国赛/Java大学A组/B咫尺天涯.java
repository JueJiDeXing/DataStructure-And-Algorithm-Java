package 算法OJ.蓝桥杯.真题卷.第11届.国赛.Java大学A组;
/**
 已AC
 */
public class B咫尺天涯 {
    /*
    k阶曲线距离和=9个k-1阶曲线距离和+9个k-1阶曲线的相邻部分距离和
    f[k]=9*f[k-1]+g[k]
    k-1阶曲线的相邻部分可以分为2行和2列,k阶曲线中k-1阶曲线一行一列的相邻距离为I[k]
    则f[k]=9*f[k-1]+2I[k]
    对于I[k]的计算:枚举边界的相邻点,计算从(0,0)出发走到的步数差
     */
    public static void main(String[] args) {
        long ans = 0;

        for (int i = 1; i <= 12; i++) {
            //计算i阶相邻部分的和,i=1时计算出的答案就等于总体相邻距离
            long tmp = 0;
            for (int j = 0; j < pow[i + 1]; j++) {
                tmp += Math.abs(calc(i, j, pow[i]) - calc(i, j, pow[i] - 1));//一列的和, (0,0)->(j,pow[i]) - (0,0)->(j,pow[i]-1)
                tmp += Math.abs(calc(i, pow[i], j) - calc(i, pow[i] - 1, j));//一行的和, (0,0)->(pow[i],j) - (0,0)->(pow[i]-1,j)
            }
            ans = 9 * ans + 2 * tmp;//ans[i]=ans[i-1]+2I, I:i-1阶两行两列的距离
        }
        System.out.println(ans);//184731576397539360
    }

    static long[] pow = new long[15];

    static {
        pow[1] = 1;
        for (int i = 2; i <= 14; i++) pow[i] = pow[i - 1] * 3;
    }


    /**
     k+1阶曲线,从(0,0)走到(x,y)的距离

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
