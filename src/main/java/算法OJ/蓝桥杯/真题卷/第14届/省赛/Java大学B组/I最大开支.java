package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学B组;

import java.util.*;

/**
 已AC
 */
public class I最大开支 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), M = sc.nextInt();
        //存储每个项目的增加费用
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= M; i++) {
            int k = sc.nextInt(), b = sc.nextInt();
            for (int x = 1; x <= N; x++) {//对于每一个项目计算从1到N每添加一人所增加的费用
                int cost = 2 * k * x - k + b;//cost=(kx+b)x-[k(x-1)+b](x-1)=2kx-k+b
                if (cost > 0) list.add(cost);
                else break;
            }
        }
        long maxCost = 0;
        list.sort((o1, o2) -> o2 - o1);//从大到小排序
        int num = Math.min(N, list.size());//选择了项目的人数
        for (int i = 0; i < num; i++)
            maxCost += list.get(i);
        System.out.println(maxCost);
        sc.close();
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), M = sc.nextInt();
        Queue<Price> queue = new PriorityQueue<>(((o1, o2) -> Math.toIntExact(o2.add - o1.add)));//[K,B,X],按增量降序
        for (int i = 0; i < M; i++) {
            int K = sc.nextInt(), B = sc.nextInt();
            queue.offer(new Price(K, B));//当前已选择的人数
        }
        long ans = 0;
        for (int i = 0; i < N; i++) {//每个人依次选择
            Price poll = queue.poll();
            if (poll == null || poll.add <= 0) break;
            ans += poll.add;
            poll.addX();
            if (poll.add > 0) queue.offer(poll);
        }
        System.out.println(ans);
    }

    static class Price {
        int K, B, X = 0;
        long add;

        public Price(int k, int b) {
            K = k;
            B = b;
            cal();
        }

        public void addX() {
            X++;
            cal();
        }

        public void cal() {
            add = 2L * K * X + K + B;
        }

    }
}
