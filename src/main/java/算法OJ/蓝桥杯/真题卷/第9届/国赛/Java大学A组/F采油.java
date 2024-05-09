package 算法OJ.蓝桥杯.真题卷.第9届.国赛.Java大学A组;

import java.io.*;

/**
 题库都找不到这题,题解也没有
 */
public class F采油 {
    /*
     n个油井,有n-1条路径
     现在需要建立油井,建立需要大型设备和人力
     求 大型设备运输的最短路径长度 和 在路径长度最短的情况下,至少需要多少人

     对于人力:
     第i个油井需要花费B[i]个人建立,建立后需要S[i]个人维护油井
     如果一个人参与了油井建立,他可以选择参与下一油井建立,也可以选择一直维护当前油井
     油井建立是有序的, 人只能跟着设备走

     对于大型设备:
     现在准备在其中一个油井建立一个空运站,先将设备空运到空运站,再通过油井的道路运输
     油井建立后再从空运站运走, 要求大型设备在道路上运输的路径最短 (哈密顿回路)
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
