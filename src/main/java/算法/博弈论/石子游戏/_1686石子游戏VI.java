package 算法.博弈论.石子游戏;

import java.util.Arrays;

public class _1686石子游戏VI {
    /*
    Alice 和 Bob 轮流玩一个游戏，Alice 先手。

    一堆石子里总共有 n 个石子，轮到某个玩家时，他可以 移出 一个石子并得到这个石子的价值。
    Alice 和 Bob 对石子价值有 不一样的的评判标准 。双方都知道对方的评判标准。

    给你两个长度为 n 的整数数组 aliceValues 和 bobValues 。
    aliceValues[i] 和 bobValues[i] 分别表示 Alice 和 Bob 认为第 i 个石子的价值。

    所有石子都被取完后，得分较高的人为胜者。如果两个玩家得分相同，那么为平局。
    两位玩家都会采用 最优策略 进行游戏。

    请你推断游戏的结果，用如下的方式表示：

    如果 Alice 赢，返回 1 。
    如果 Bob 赢，返回 -1 。
    如果游戏平局，返回 0 。
     */

    /**
     <h1>贪心</h1>
     假设现在有两堆石子,A先手<br>
     石子价值是[va1(vb1),va2(vb2)],且va1+vb1>va2+vb2<br>
     如果A选择1,那么分差为va1-vb2 , 如果A选择2,那么分差为va2-vb1<br>
     因为 va1+vb1>va2+vb2 , 所以 va1-vb2>va2-vb1<br>
     A应该选择va1的一堆,所以从贪心考虑,应该选择双方价值之和最大的一堆<br>
     <p>
     假设石子全是B的,A从里面拿走一颗,那么A的分数加了a[i],B的分数减了b[i],分差的改变就是a[i]+b[i]<br>
     石子的价值=双方的价值之和<br>
     最优策略:选择下标为i的,其中a[i]+b[i]为当前最高的值<br>
     */
    public int stoneGameVI(int[] a, int[] b) {
        int n = a.length;
        //生成下标序列[0,n)
        Integer[] ids = new Integer[n];
        for (int i = 0; i < n; i++) ids[i] = i;
        //按照a[i]+b[i]对下标数组排序(从大到小)
        Arrays.sort(ids, (i, j) -> a[j] + b[j] - a[i] - b[i]);

        int diff = 0;
        for (int i = 0; i < n; i++) {
            diff += i % 2 == 0 ? a[ids[i]] : -b[ids[i]];
        }
        return Integer.compare(diff, 0);
    }
}
