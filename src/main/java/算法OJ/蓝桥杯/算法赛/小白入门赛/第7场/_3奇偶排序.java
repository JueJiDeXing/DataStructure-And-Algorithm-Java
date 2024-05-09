package 算法OJ.蓝桥杯.算法赛.小白入门赛.第7场;

import java.util.*;
import java.io.*;
/**
 已AC
 */
public class _3奇偶排序 {
    /*

    偶数大于奇数
    奇偶相同小数在前
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
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1 % 2 == o2 % 2) return o1 - o2;
            return o1 % 2 == 0 ? 1 : -1;
        });
        for (int i = 0; i < n; i++) {
            queue.offer(Int());
        }
        for (int i = 0; i < n; i++) {
            int t = queue.poll();
            System.out.print(t + " ");
        }
    }
}
