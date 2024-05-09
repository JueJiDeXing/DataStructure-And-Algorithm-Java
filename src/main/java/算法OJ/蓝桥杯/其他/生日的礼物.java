package 算法OJ.蓝桥杯.其他;

import java.io.*;
import java.util.*;

/**
 ??
 */
public class 生日的礼物 {
    /*
    长度为n的数组A,选出不超过M个不重叠的子数组
    求最大和
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
        int n = Int(), m = Int();
        List<Integer> A = new ArrayList<>();
        int sum = 0;//正数之和
        for (int i = 0; i < n; i++) {
            int a = Int();
            if (a == 0) continue;//0没有意义
            if (a > 0) sum += a;
            if (A.isEmpty()) {
                if (a >= 0) A.add(a);//首尾的负数都没有意义
                continue;
            }
            int lastIdx = A.size() - 1;
            Integer val = A.get(lastIdx);
            if (val * a > 0) {//同正同负,合并
                A.set(lastIdx, val + a);
            } else {
                A.add(a);//不同符号,添加到末尾
            }
        }
        if (A.get(A.size() - 1) < 0) A.remove(A.size() - 1);
        n = A.size();
        int r = (n + 1) / 2;//正数个数
        if (r <= m) { //所有正数都能取
            System.out.println(sum);
            return;
        }
        //需要操作至多r-m次,每次选择一个负数融合左右的正数,融合之后如果变小,说明不是最优
        Queue<int[]> queue = new PriorityQueue<>(((o1, o2) -> o1[0] - o2[0]));
        for (int i = 1; i < n; i += 2) {
            queue.offer(new int[]{A.get(i), i});
        }
        for (int i = 0; i < n / 2; i++) {
            if (queue.isEmpty()) break;
            int[] d = queue.poll();
            Integer left = A.get(d[1] - 1), right = A.get(d[1] + 1);
            int s = left + d[0] + right;
            if (left > -d[0] && right > -d[0]) {
                sum += d[0];
                A.set(d[1] - 1, s);
                A.set(d[1] + 1, s);
            } else {
                i--;
            }
        }
        System.out.println(sum);
    }
}
