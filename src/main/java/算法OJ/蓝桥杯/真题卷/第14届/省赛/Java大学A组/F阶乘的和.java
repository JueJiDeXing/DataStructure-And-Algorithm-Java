package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学A组;

import java.util.*;

/**
 已AC
 */
public class F阶乘的和 {
    //n个k!可以凑出n/(k+1)个(k+1)!,其中需要满足n%(k+1)==0
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //将数据存储到优先级队列中,按A的值进行排序
        HashMap<Integer, Integer> map = new HashMap<>();//A_Value->A_Count
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Queue<int[]> queue = new PriorityQueue<>((Comparator.comparingInt(o -> o[0])));//[A,A的个数]
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(new int[]{entry.getKey(), entry.getValue()});
        }
        //计算答案
        int ans = queue.peek()[0];//第一个最小值一定能整除
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            int A_Value = p[0], A_Count = p[1];//A_Value有A_Count个
            if (A_Count % (A_Value + 1) != 0) {//不能完全融合,最后剩余的最小的A就是当前A
                break;
            } //该项A可以完全融合为A+1
            int[] next = queue.peek();
            if (next == null || next[0] != A_Value + 1) {//不是连续的A项,需要新建这个A项
                next = new int[]{A_Value + 1, A_Count / (A_Value + 1)};
                queue.offer(next);
            } else {//连续A项,直接在它原数量上增加
                next[1] += A_Count / (A_Value + 1);
            }
            ans++;//融合是从A_Value->V_Value+1,所以ans的增加一定也是连续的
        }
        System.out.println(ans);
        sc.close();
    }
}
