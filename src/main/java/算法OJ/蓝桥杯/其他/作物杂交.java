package 算法OJ.蓝桥杯.其他;

import java.io.*;
import java.util.*;
/**
 已AC
 */
public class 作物杂交 {
    /*
    n个种子,每个种子成熟需要T[i]天
    A和B的杂交时间为二者的成熟的较长时间
    同一时段可以进行多个杂交 (必须拥有种子才能开始杂交,不能提前种植)

    初始有m种数量无限的种子
    求目标种子需要最短多少时间能杂交出来
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static class Node {
        boolean isHave = false;//是否拥有种子
        int time = 0;//种植花费时间
        List<int[]> ways = new ArrayList<>();//[f1,f2]杂交方案对应的父母
        int getTime = 0x3f3f3f3f;//得到该种子的最小时间
    }

    static Node[] node;

    static int calTime(int t) {//计算获得目标种子需要的时间
        Node tNode = node[t];
        if (tNode.isHave) return tNode.getTime;
        for (int[] parent : tNode.ways) {//枚举所有父路径
            int f1 = parent[0], f2 = parent[1];//f1 × f2 -> tNode
            node[f1].getTime = calTime(f1);//f1的获得时间
            node[f2].getTime = calTime(f2);//f2的获得时间
            //  tNode 的获得时间 = 父的获得时间 + 杂交花费时间
            // 由题目样例:必须拥有种子才能开始杂交,不能提前种植
            int time = Math.max(node[f1].time, node[f2].time)
                    + Math.max(node[f1].getTime, node[f2].getTime);//都要取最大值
            if (time < tNode.getTime) tNode.getTime = time;//取最小值
            tNode.isHave = true;//获得该种子
        }
        return tNode.getTime;
    }

    public static void main(String[] args) {
        int n = nextInt(), m = nextInt(), k = nextInt(), t = nextInt();
        node = new Node[n + 1];
        Arrays.setAll(node, i -> new Node());
        //种植时间
        for (int i = 1; i <= n; i++) node[i].time = nextInt();
        //拥有种子
        for (int i = 1; i <= m; i++) {
            int have = nextInt();
            node[have].isHave = true;
            node[have].getTime = 0;
        }
        //种子的杂交方案
        for (int i = 1; i <= k; i++) {
            int f1 = nextInt(), f2 = nextInt(), son = nextInt();
            node[son].ways.add(new int[]{f1, f2});
        }
        //计算获得目标种子t的最短时间
        System.out.println(calTime(t));
    }

}
