package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学B组;

import java.util.*;

/**
 测试通过: 8/10 超时2个
 */
public class G买二赠一 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Queue<Integer> queue = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        Queue<Integer> freeQueue = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        for (int i = 0; i < N; i++) {
            queue.offer(sc.nextInt());
        }
        long ans = 0;
        while (!queue.isEmpty()) {
            while (!queue.isEmpty() && !freeQueue.isEmpty() && queue.peek() <= freeQueue.peek()) {
                queue.poll();
                freeQueue.poll();
            }
            if (queue.isEmpty()) break;
            int p1 = queue.poll();
            ans += p1;
            while (!queue.isEmpty() && !freeQueue.isEmpty() && queue.peek() <= freeQueue.peek()) {
                queue.poll();
                freeQueue.poll();
            }
            if (queue.isEmpty()) break;
            int p2 = queue.poll();
            ans += p2;
            freeQueue.offer(Math.min(p1, p2) / 2);
        }
        System.out.println(ans);
    }

    public static void main2(String[] args) {//7/10 超时3个
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Integer> queue = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        for (int i = 0; i < N; i++) {
            int p = sc.nextInt();
            map.put(p, map.getOrDefault(p, 0) + 1);
            queue.offer(p);
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            int p1 = queue.poll();
            ans += p1;
            if (queue.isEmpty()) break;
            int p2 = queue.poll();
            ans += p2;
            if (map.get(p1) == 1) {
                map.remove(p1);
            } else {
                map.put(p1, map.get(p1) - 1);
            }
            if (map.get(p2) == 1) {
                map.remove(p2);
            } else {
                map.put(p2, map.get(p2) - 1);
            }
            int free = Math.min(p1, p2) / 2;
            Map.Entry<Integer, Integer> entry = map.floorEntry(free);
            if (entry != null) {
                queue.remove(entry.getKey());
                if (entry.getValue() == 1) {
                    map.remove(entry.getKey());
                } else {
                    map.put(entry.getKey(), entry.getValue() - 1);
                }
            }
        }
        System.out.println(ans);
    }
}
