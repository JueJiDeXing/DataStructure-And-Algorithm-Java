package 算法OJ.南昌大学OJ.南昌大学第7届校赛;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 已AC
 */
public class B_智者 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(solve());
    }

    // 包含k个最大值的子数组个数
    private static long solve() throws IOException {
        int n = I(), k = I();
        if (k > n) return 0;
        if (k == n) return 1;
        int[] a = new int[n];
        Queue<Integer> queue = new PriorityQueue<>();//最小堆,容量为k
        for (int i = 0; i < n; i++) {
            a[i] = I();
            queue.offer(a[i]);
            if (i >= k) queue.poll();
        }
        HashSet<Integer> set = new HashSet<>(queue);//a[i] in set -> a[i]排前k
        //滑窗
        int left = 0; // 当前区间[left,right]
        int cnt = 0; // 当前区间最大值个数
        long ans = 0; // 子数组个数
        for (int right = 0; right < n; right++) {// right向右滑动
            if (set.contains(a[right])) cnt++;
            if (cnt < k) continue;// 不满足, 继续向右
            //满足, 计算方案数
            int next = left;  //right固定,找到下一个不满足的left点
            while (next <= right && cnt >= k) {
                if (set.contains(a[next])) cnt--;
                next++;
            }
            ans += (long) (next - left) * (n - right);//左边有next-left个选择(left之前已计算过),右边有n-right个选择
            left = next;
        }
        return ans;
    }


}
//50000000
/*
12 2
573 806 396 112 978 395 687 144 413 992 178 806
12 1
573 806 396 112 978 395 687 144 413 992 178 806
8 3
1 2 6 5 5 5 2 1
8 3
1 2 6 5 5 2 1 6
 */
