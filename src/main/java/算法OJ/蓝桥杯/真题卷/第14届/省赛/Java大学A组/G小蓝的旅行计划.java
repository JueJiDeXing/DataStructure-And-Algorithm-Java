package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学A组;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.io.*;

/**
 已AC
 */
public class G小蓝的旅行计划 {
    public static void main(String[] args) {
        main_enter1();
    }

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    private static void main_enter1() {//9/10 1WA
        int n = Int(), m = Int();//n个站点,最大容量m
        int cost = 0;//当前最小消耗
        int curr = m;//当前油量
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));//[油价,剩余可加的油量]按油价排序
        for (int i = 0; i < n; i++) {
            int distance = Int();// 到下一个站点的消耗
            if (curr >= distance) {// 可以直接走到下一个站点
                curr -= distance;
            } else {// 走不到下一站
                int need = distance - curr;// 需要加need升油, 只加need升,不多加,下次出发时再加
                while (need > 0) {
                    if (queue.isEmpty()) { // 还需要加油,但是没油可加了
                        System.out.println(-1);
                        return;
                    }
                    int[] data = queue.poll();// 取已到达过的,还能加油的,最便宜的站点加油
                    if (data[1] > need) { // 有超出的油,加need升,剩余的放回队列
                        data[1] -= need;
                        cost += need * data[0];
                        need = 0;
                        queue.offer(data);
                    } else {// 全部加
                        cost += data[1] * data[0];
                        need -= data[1];
                    }
                }
                curr = 0;
            }
            // 到达下一个站点,加油机会存入队列
            int co = Int();// 油价
            int maxAdd = Int();// 最多加的油量
            queue.offer(new int[]{co, maxAdd});
        }
        System.out.println(cost);
    }

}

/**
 AC
 */
class AC {
    static int M = 200001;
    static int[] distance = new int[M];// 开往下一个站点的距离
    static int[] limit = new int[M];// 加油站的加油上限
    static int[] cost = new int[M];// 加油站的加油花费

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            distance[i] = sc.nextInt();
            cost[i] = sc.nextInt();
            limit[i] = sc.nextInt();
        }
        int ans = solve(n, m);
        System.out.println(ans);
    }

    static int solve(int n, int m) {
        int ans = 0;
        PriorityQueue<gas> queue = new PriorityQueue<>((Comparator.comparingInt(o -> o.cost)));
        SegmentTree tree = new SegmentTree();
        int curr = m;//当前油量
        for (int i = 1; i <= n; i++) {
            curr -= distance[i];
            while (curr < 0) {//走不到下一站,需要加-curr升油
                if (queue.isEmpty()) return -1;//没油可加了
                gas a = queue.poll();//找最便宜的站点
                int maxOil = tree.query(1, 1, n, a.id, i - 1);//从想加油的id站点到i-1站点的最大油量,能加油数取决于该值
                int cnt = Math.min(m - maxOil, limit[a.id]);
                if (cnt <= 0) continue;
                if (cnt <= -curr) {//不够,cnt升全部都加
                    ans += a.cost * cnt;
                    curr += cnt;
                    limit[a.id] = 0;
                    tree.add(1, 1, n, a.id, i - 1, cnt);//[a.id,i-1]内的站点到达时的油量都增大cnt
                } else {// cnt升加完还会有剩余
                    ans += a.cost * (-curr);
                    limit[a.id] = cnt + curr;//更新一下a.id站点的剩余可加油量
                    tree.add(1, 1, n, a.id, i - 1, -curr);
                    queue.add(new gas(a.id, a.cost));//重新加入队列
                    curr = 0;
                }
            }
            if (curr > 0) tree.add(1, 1, n, i, i, curr);
            limit[i] = Math.min(limit[i], m - curr);
            queue.add(new gas(i, cost[i]));
        }
        return ans;
    }

    static class gas {
        int cost;//单价
        int id;//加油站id

        public gas(int i, int a) {
            id = i;
            cost = a;
        }
    }

    //线段树
    static class SegmentTree {
        // M<<2开4*n个节点
        // Array[node.id]表示编号node.id节点的数据, node.left编号为2*node.id,node.right编号为2*node.id+1
        // node节点的数据区间是[l,r], node.left区间是[l,mid], node.right区间是[mid+1,r]
        // 原本根节点数据区间为[L,R]由构造时传入,现在直接在调用具体方法时传入

        int[] rest = new int[M << 2];// rest[node.id]表示到达站点[node.l,node.r]时的油量的最大值
        int[] lazy = new int[M << 2];// 懒更新

        //与传统线段树不同点在于:维护的数据rest需要求区间最大值
        //无build函数,因为rest数据初始全为0,到达一个站点后才会进行更新

        void pushUp(int node) {//用孩子节点更新父节点数据
            rest[node] = Math.max(rest[node << 1], rest[node << 1 | 1]);
        }

        void pushDown(int node) {//node节点懒任务下发
            if (lazy[node] == 0) return;//没有任务
            lazy[node << 1] += lazy[node];
            lazy[node << 1 | 1] += lazy[node];
            rest[node << 1] += lazy[node];
            rest[node << 1 | 1] += lazy[node];
            lazy[node] = 0;//下发完成
        }

        int query(int node, int l, int r, int left, int right) {//查询[left,right]区间内最大值
            if (left <= l && r <= right) return rest[node];
            pushDown(node);
            int res = 0;
            int mid = (l + r) / 2;
            if (mid >= left) res = Math.max(res, query(node << 1, l, mid, left, right));
            if (mid < right) res = Math.max(res, query(node << 1 | 1, mid + 1, r, left, right));
            pushUp(node);
            return res;
        }

        void add(int node, int l, int r, int left, int right, int v) {//将[left,right]区间内全部加上v
            if (left <= l && r <= right) {
                rest[node] += v;
                lazy[node] += v;
                return;
            }
            pushDown(node);
            int mid = (l + r) / 2;
            if (mid >= left) add(node << 1, l, mid, left, right, v);
            if (mid < right) add(node << 1 | 1, mid + 1, r, left, right, v);
            pushUp(node);
        }
    }

}

