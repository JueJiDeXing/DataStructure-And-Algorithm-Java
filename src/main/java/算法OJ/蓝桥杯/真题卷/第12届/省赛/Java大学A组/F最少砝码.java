package 算法OJ.蓝桥杯.真题卷.第12届.省赛.Java大学A组;

import java.util.Scanner;
/**
 已AC
 */
public class F最少砝码 {
    /*
    要称出[1,n]的全部整数
    需要使用的最小砝码数
     */

    /**
     N   个数k  实现
     1   1     1
     2   2     1,3
     3   2     1,3
     4   2     1,3
     5   3     1,3,9
     6   3     1,3,9
     7   3     1,3,9
     <p>
     <h1>贪心</h1>
     能称出f[N]的实现一定能称出f[N-1],所以如果两种实现的使用砝码数相同,小的N可以直接使用更优的实现
     对于同一实现,它会有一个上界x,它只能称[1,x]
     <p>
     对于当前实现, 假设它的上一个实现到达了上界x
     上一个实现的砝码结构不需要改变,它是局部的最优解
     当前实现的上一个实现的基础上加上一个砝码重t
     t可以调用的砝码可以认为只能使用1个,重量范围[1,x]
     那么t的最小称重为t-x,最大称重为t+x,中间的数都能是称出来的,也就是说称的区间长度固定(x固定,长度为2x)
     为了让方案最优, t称出的区间需要与[1,x]不重合
     即t-x = x+1 得 t=2x+1
     当前实现的上界即为 x+t=3x+1
     <p>
     k    x    实现
     1    1     1
     2    4     1,3
     3    13    1,3,9
     4    40    1,3,9,27
     <p>
     可得an = 3 an-1 + 1
     => ak = (3^k - 1) / 2
     ak >= n
     3^k >= 2n+1
     k >= ln(2n+1) / ln3
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        //直接算ln(2n+1)/ln(3)误差会比较大,所以用二分查找,二分上界可以用ln(2n+1)预估计
        long left = 0, right = (int) Math.log1p(2 * n) + 1;
        while (left + 1 != right) {
            long mid = (left + right) >> 1;
            if (Math.pow(3, mid) < 2 * n + 1) {
                left = mid;
            } else {
                right = mid;
            }
        }
        System.out.println(right);
    }
}
