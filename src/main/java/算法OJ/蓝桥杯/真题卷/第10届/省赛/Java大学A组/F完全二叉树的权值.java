package 算法OJ.蓝桥杯.真题卷.第10届.省赛.Java大学A组;

import java.io.*;

/**
 已AC
 */
public class F完全二叉树的权值 {
    /*
    N个节点的完全二叉树数组A,A[i]表示i位置节点的权值
    求最大权值和的层的深度
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
        int n = Int();

        long maxSum = Long.MIN_VALUE;
        int maxLevel = 0;

        int curNum = 0, curSum = 0, curLevel = 1, nodeNum = 1;
        for (int i = 1; i <= n; i++) {
            int val = Int();
            curSum += val;
            curNum++;
            if (curNum == nodeNum) {
                if (curSum > maxSum) {
                    maxSum = curSum;
                    maxLevel = curLevel;
                }
                curSum = 0;
                curNum = 0;
                nodeNum *= 2;
            }
        }
        if (curSum > maxSum) {
            maxLevel = curLevel;
        }
        System.out.println(maxLevel);
    }
}
