package 算法OJ.蓝桥杯.真题卷.第13届.省赛.Java大学A组;

import java.io.*;

/**
 已AC
 */
public class G青蛙过河 {
    /*
    长度为n的河,河中有直线平均分布的的n-1个石子
    石子高度为H[1],H[2],...H[n-1],石子i与河岸A的距离为i
    青蛙从A出发,到B,再从B出发到A,往返x次
    从第i个石子起跳会使第i个石子的高度-1,高度下降到0后石子不能再作为起跳点
    求青蛙需要的最小跳跃能力 (跳跃能力为y表示从第i个石子起跳最远能跳到第i+y颗石子)
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     假如步长为y,则任意长度为y的区间和应当大于等于2x<br>
     如果长度为y的某个区间的和小于2x,那么这个区间无法支撑x次往返,步长y就不能作为答案<br>
     <p>
     如果步长y满足了上面的条件,那么一定能够往返x次<br>
     因为可以找到一个划分,使得每个区间的和都大于等于2x,这样每次的按照区间去跳即可<br>
     */
public static void main(String[] args) {
    int n = Int(), x = Int();
    //前缀和
    long[] preSum = new long[n];
    for (int i = 1; i < n; i++) {
        int h = Int();
        preSum[i] = h + preSum[i - 1];
    }

    //二分枚举步长
    int l = 0, r = n;
    while (l + 1 != r) {
        int mid = (l + r + 1) >> 1;
        if (check(preSum, mid, x)) {
            r = mid;
        } else {
            l = mid;
        }
    }
    System.out.println(r);
}

/**
 检查长度为y的h子数组和是否都大于等于2x

 @param preSum h数组的前缀和,preSum[i] = sum( h[0,i) )
 @param y      步长
 @param x      往返次数
 */
private static boolean check(long[] preSum, int y, int x) {
    int n = preSum.length;
    long _2x = 2L * x;
    for (int i = 0; i + y < n; i++) {// preSum[i + y] - preSum[i] = h[i,i+y)
        if (preSum[i + y] - preSum[i] < _2x) return false;
    }
    return true;
}
}
