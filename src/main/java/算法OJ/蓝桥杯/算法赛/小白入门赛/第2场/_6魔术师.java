package 算法OJ.蓝桥杯.算法赛.小白入门赛.第2场;

import java.io.*;

public class _6魔术师 {
    /*
    n个盒子(编号1~n),初始每个盒子有1个球(颜色有红黄蓝三种,记为1,2,3)
    有三种操作:
    (1) 颜色互换:
     选择两种颜色a和b,对区间[l,r]的球,如果是a或b,则颜色互换
    (2) 染色:
     选择两种颜色a和b, 对区间[l,r]的球,如果是a,则变为b
    (3) 分裂:
     选择一种颜色a, 对区间[l,r]的球, 如果是a,则变为2倍的球

    求最后颜色为1,2,3的各自数量

    输入:
    第一行 n:盒子数; m:操作次数
    第二行 n个整数,表示盒子内球的初始颜色
    下面m行, 每行先输入区间l,r和操作种类op, 如果op=1或2,则输入a,b,如果op=3,则输入a
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {

    }
}
