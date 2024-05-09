package 算法OJ.蓝桥杯.算法赛.小白入门赛.第7场;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 已AC
 */
public class _5兽之泪 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static long Long() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (long) st.nval;
    }

    static class Node {
        long a, b;//a:第一次价格; b:第二次价格

        public Node(long a, long b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) {
        int k = Int(), n = Int();
        Node[] nodes = new Node[k];//怪兽价格数组
        long sum = 0;//打第一次的全部价格
        long min = Long.MAX_VALUE;//打第二次的最小值
        for (int i = 0; i < k; i++) {
            long first = Long(), second = Long();
            nodes[i] = new Node(first, second);//第一次和第二次
            min = Math.min(min, nodes[i].b);
            sum += nodes[i].a;
        }
        Arrays.sort(nodes, 0, k - 1, (o1, o2) -> (int) (o1.a - o2.a));//按照第一次的大小排序,最后一只不排
        long ans = 0;
        long t = Long.MAX_VALUE;//当前打过的里面 第二次最便宜的
        long x = n;//还需要打的个数
        for (int i = 0; i < k - 1 && x > 0; i++) {//不打第k只
            ans += nodes[i].a;
            x--;
            t = Math.min(t, nodes[i].b);
            if (t <= nodes[i + 1].a) {//某只打完后的价格比下一个最小的第一次打的价格便宜,后面都打这只
                ans += x * t;
                x = 0;
                break;
            }
        }
        // k-1只,每只都打完了,且 min(第二次打的价格)>max(第一次打的价格), 后面都打最便宜的一只
        ans += x * t;//x:打了k-1只还需要打的个数; t:前面k-1只,第二次最便宜的

        if (n >= k) {// 需要打的个数大于k只,考虑打第k只的情况
            long r = sum + (n - k) * min;
            ans = Math.min(ans, r);//ans:不打第k只; r:打第k只(每只都要打,打完一次后,第二次挑最便宜的打)
        }
        System.out.println(ans);
    }


    private static void main1() {//7/10
        int k = Int(), m = Int();
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] != o2[0]) return o1[0] > o2[0] ? 1 : -1;
            if (o1[1] == o2[1]) return 0;
            return o1[1] > o2[1] ? 1 : -1;
        });
        int inf = Integer.MAX_VALUE;
        for (int i = 0; i < k - 1; i++) {
            int x = Int(), y = Int();
            queue.offer(new int[]{x, y, i});
        }
        int lastX = Int(), lastY = Int();
        boolean[] isC = new boolean[k];
        int cCount = 1;
        BigInteger ans = BigInteger.ZERO;
        for (int i = 0; i < m; i++) {
            int[] d = queue.poll();
            if (isC[d[2]]) {
                ans = ans.add(BigInteger.valueOf(m - i).multiply(BigInteger.valueOf(d[0])));
                break;
            }
            ans = ans.add(BigInteger.valueOf(d[0]));
            isC[d[2]] = true;
            cCount++;
            if (cCount == k) queue.offer(new int[]{lastX, lastY, k - 1});
            queue.offer(new int[]{d[1], inf, d[2]});
        }
        System.out.println(ans);
    }
}
