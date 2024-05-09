package 算法OJ.蓝桥杯.算法赛.小白入门赛.第7场;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 已AC
 */
public class _4可结合的元素对 {
    /*
    如果lowbit(ai+aj)==ai+aj,(i<j)则ai与aj是可结合的
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     lowbit(ai+aj)==ai+aj => ai+aj=2^k
     */
    public static void main(String[] args) {
        int n = Int();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Int();
        }
        long res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < 32; j++) {
                // 计算当前数字 arr[i] 与 2 的 j 次幂之间的差值，并累加到结果res中
                res += map.getOrDefault((1 << j) - arr[i], 0);
            }
            // 更新HashMap中arr[i]对应的次数
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        System.out.print(res); // 输出最终结果
    }


    private static void main2() {
        int n = Int();
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            queue.offer(Int());
        }
        for (int i = 0; i < n; i++) {
            int a = queue.poll();
            int p = (Integer.highestOneBit(a) << 1) - a;
            if (map.containsKey(p)) {
                count += map.get(p);
            }
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        System.out.println(count);
    }

    private static void main1() {
        int n = Int();
        long count = 0;

        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = Int();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (lowbit(A[i] + A[j]) == A[i] + A[j]) count++;
            }
        }
        System.out.println(count);
    }

    static int lowbit(int x) {
        return x & (-x);
    }
}
