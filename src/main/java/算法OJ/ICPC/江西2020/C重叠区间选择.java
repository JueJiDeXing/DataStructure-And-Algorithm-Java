package 算法OJ.ICPC.江西2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 已AC(细节)
 */
public class C重叠区间选择 {
    /*
    m个区间, 最大化min{k,x}
    k为选择的区间个数, x为区间的重叠部分长度
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in), 65535));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws Exception {
        I();
        int m = I();
        int[][] list = new int[m][2];
        for (int i = 0; i < m; i++) list[i] = new int[]{I(), I()};
        Arrays.sort(list, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);//线段按照左端点优先从小到大排序,右端点从小到大排序

        Queue<Integer> queue = new PriorityQueue<>();//选择的线段, 按右端点从小到大排序
        int ans = 0;
        for (int i = 0; i < m; i++) {
            queue.offer(list[i][1]);//选择第i条线段
            while (!queue.isEmpty()) {
                int cnt = queue.peek() - list[i][0] + 1; // [L,R]: L为当前枚举的线段左端点, R为选择的线段的右端点最小值
                if (queue.size() <= cnt) break; // tol=q.size, 保证tol是小值
                // 如果tol>cnt, 说明选择了一些贡献很小的线段
                // L是固定/递增的, 选择的线段按R排序, R小的不再选择,应该抛出, 否则随着L递增,cnt会越来越小
                queue.poll();
            }
            ans = Math.max(ans, queue.size());
        }
        System.out.println(ans);
    }
}
