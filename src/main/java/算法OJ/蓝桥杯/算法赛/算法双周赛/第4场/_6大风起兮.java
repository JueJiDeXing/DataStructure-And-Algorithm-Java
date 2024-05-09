package 算法OJ.蓝桥杯.算法赛.算法双周赛.第4场;

import java.io.*;
import java.util.*;
/**
 已AC(真是垃圾评测机制,双堆维护中位数超时,List暴力删除能过)
 */
public class _6大风起兮 {
    /*
    n个气球,每个气球有一个值
    会飞走q个气球,每次飞走一个气球后需要输出剩余的气球的中位数
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static PriorityQueue<Integer> left, right;
    static HashMap<Integer, Integer> map;//权值->计数

    public static void main(String[] args) {
        int n = nextInt();
        int[] vals = new int[n];
        List<Integer> sortVals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vals[i] = nextInt();
            sortVals.add(vals[i]);
        }
        sortVals.sort(((o1, o2) -> o1 - o2));
        int q = nextInt();
        for (int i = 0; i < q; i++) {
            int idx = nextInt() - 1;
            int val = vals[idx];
            idx = Collections.binarySearch(sortVals, val);
            sortVals.remove(idx);
            double mid;
            if (sortVals.size() % 2 == 1) {
                mid = sortVals.get(sortVals.size() / 2);
            } else {
                mid = (sortVals.get(sortVals.size() / 2 - 1) + sortVals.get(sortVals.size() / 2)) / 2.0;
            }
            System.out.printf("%.1f ", mid);
        }
    }


    private static void slove2(int n) {// 13AC 7TLE
        int[] vals = new int[n];
        left = new PriorityQueue<>(((o1, o2) -> o2 - o1));//左半区,大顶堆
        right = new PriorityQueue<>(((o1, o2) -> o1 - o2));//右半区,小顶堆
        for (int i = 0; i < n; i++) {
            vals[i] = nextInt();
            add(vals[i]);
        }
        int q = nextInt();
        double currMidVal = left.size() > right.size() ? left.peek() : ((double) left.peek() + right.peek()) / 2.0;
        for (int i = 0; i < q; i++) {
            int idx = nextInt() - 1;
            int val = vals[idx];
            if (val > currMidVal) {
                right.remove(val);
            } else {
                left.remove(val);
            }
            while (left.size() > right.size() + 1) {
                right.offer(left.poll());
            }
            while (left.size() < right.size()) {
                left.offer(right.poll());
            }
            currMidVal = left.size() > right.size() ? left.peek() : ((double) left.peek() + right.peek()) / 2.0;
            System.out.printf("%.1f ", currMidVal);
        }
    }

    private static void solve1(int n) {// 12AC 8WA
        int[] vals = new int[n + 1];
        map = new HashMap<>();//权值->计数
        left = new PriorityQueue<>(((o1, o2) -> o2 - o1));//左半区,大顶堆
        right = new PriorityQueue<>(((o1, o2) -> o1 - o2));//右半区,小顶堆
        for (int i = 0; i < n; i++) {
            vals[i] = nextInt();
            map.put(vals[i], map.getOrDefault(vals[i], 0) + 1);
            add(vals[i]);
        }
        int q = nextInt();
        int leftCnt = left.size(), rightCnt = right.size();
        double currMidVal = leftCnt > rightCnt ? left.peek() : ((double) left.peek() + right.peek()) / 2.0;
        for (int i = 0; i < q; i++) {
            int idx = nextInt() - 1;
            int val = vals[idx];
            int cnt = map.get(val);
            if (cnt == 1) {
                map.remove(val);
            } else {
                map.put(val, cnt - 1);
            }
            if (val > currMidVal) {
                rightCnt--;
            } else {
                leftCnt--;
            }
            while (leftCnt > rightCnt + 1 || leftCnt < rightCnt || !topContain(left) || !topContain(right)) {
                while (leftCnt > rightCnt + 1) {
                    int poll = left.poll();
                    if (!map.containsKey(poll)) continue;
                    right.offer(poll);
                    leftCnt--;
                    rightCnt++;
                }
                while (leftCnt < rightCnt) {
                    int poll = right.poll();
                    if (!map.containsKey(poll)) continue;
                    left.offer(poll);
                    leftCnt++;
                    rightCnt--;
                }
                top(left);
                top(right);
            }
            if (leftCnt > rightCnt) {
                currMidVal = left.peek();
            } else {
                currMidVal = ((double) left.peek() + right.peek()) / 2.0;
            }
            System.out.printf("%.1f ", currMidVal);
        }
    }

    static boolean topContain(PriorityQueue<Integer> queue) {
        return map.containsKey(queue.peek());
    }

    private static void top(PriorityQueue<Integer> queue) {
        while (!queue.isEmpty()) {
            int peek = queue.peek();
            if (map.containsKey(peek)) break;
            queue.poll();
        }
    }

    static void add(int v) {
        if (left.size() == right.size()) {
            right.offer(v);
            left.offer(right.poll());
        } else {
            left.offer(v);
            right.offer(left.poll());
        }
    }

}
