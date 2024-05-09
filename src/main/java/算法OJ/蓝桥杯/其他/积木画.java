package 算法OJ.蓝桥杯.其他;

import java.util.Scanner;
/**
 已AC
 */
public class 积木画 {
    /*
    一个长为N,宽为2的画布
    现在有两种积木
      ┰ ┌──┐      ┌──┐
      2 │  │      │  └──┐
      ┸ └──┘      └─────┘
        ┠ 1┨      ┠  2  ┨
     积木可旋转放置,且数量无限,问放满整个画布有多少种方案
     */

    /**
     状态表示: f[i][j]表示前i列积木中，第i列状态为j的情况数。<br>
     <p>
     四种情况:j=0表示当前列为空,1表示当前列上面有一个格子,2表示当前列下面有一个格子,3表示当前列已满。<br>
     令prev为f[i-1],curr为f[i]<br>
     <ul>
     <li>case1: 上一列放满,这列要放满需要竖放一个I型 <br>
     curr[0] = prev[3]  </li>
     <li>case2: 这列上面空一个格子,1.前列空,放一个L型;2.前列下面空,在那里横放一个I  <br>
     curr[1] = prev[0] + prev[2] </li>
     <li>case3: 与上面情况同理
     <br>curr[2] = prev[0] + prev[1] </li>
     <li>case4: 当前列放满,1.前列空,横放两个I(不能竖放两个I,因为前列空的前提就是没有竖放I);2.前列上面或下面空,放一个L;3.前列满,竖放I
     <br> curr[3] = prev[0] + prev[1] + prev[2] + prev[3] </li>
     </ul>
     */
    public static void main(String[] args) {
        int mod = 1000000007;
        int n = new Scanner(System.in).nextInt();
        long[] prev = new long[4], curr = new long[4];
        prev[3] = 1;//没有时为满
        for (int i = 0; i < n; i++) {
            curr[0] = prev[3] % mod;
            curr[1] = (prev[0] + prev[2]) % mod;
            curr[2] = (prev[0] + prev[1]) % mod;
            curr[3] = (prev[0] + prev[1] + prev[2] + prev[3]) % mod;
            prev = curr.clone();
        }
        System.out.println(curr[3]);
    }
}
