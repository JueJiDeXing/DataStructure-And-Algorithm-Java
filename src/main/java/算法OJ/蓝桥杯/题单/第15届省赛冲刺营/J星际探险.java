package 算法OJ.蓝桥杯.题单.第15届省赛冲刺营;

import java.util.*;
/**
 已AC
 */
public class J星际探险 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) A[i] = sc.nextInt();

        Set set = new Set(n);//并查集
        Queue<int[]> queue = new PriorityQueue<>(((o1, o2) -> o2[0] - o1[0]));//o[边权,点1,点2],按边权降序
        for (int i = 0; i < n; i++) {//把边都丢进去
            for (int j = i + 1; j < n; j++) {
                int weight = Math.max(pow(A[i], A[j]), pow(A[j], A[i]));//我不理解:为什么是按取模后的边权排序,取模不会影响到结果吗
                queue.offer(new int[]{weight, i, j});
            }
        }

        long ans = 0;
        for (int i = 0; i < n - 1; ) {
            int[] poll = queue.poll();
            int weight = poll[0], id1 = poll[1], id2 = poll[2];
            if (set.isConnected(id1, id2)) continue;//已连接,跳过该边
            set.union(id1, id2);//连接
            ans = (ans + weight) % MOD;
            i++;
        }
        System.out.println(ans);
    }

    static class Set {
        int[] fa;

        public Set(int n) {
            this.fa = new int[n];
            for (int i = 0; i < n; i++) fa[i] = i;
        }

        public int find(int x) {
            return fa[x] == x ? x : (fa[x] = find(fa[x]));
        }

        public void union(int a, int b) {
            fa[find(a)] = find(b);
        }

        public boolean isConnected(int a, int b) {
            return find(a) == find(b);
        }
    }

    static int MOD = 10_0000_0007;

    static int pow(long x, long n) {
        long ans = 1;
        while (n != 0) {
            if ((n & 1) == 1) ans = ans * x % MOD;
            x = x * x % MOD;
            n >>= 1;
        }
        return (int) ans;
    }
}
