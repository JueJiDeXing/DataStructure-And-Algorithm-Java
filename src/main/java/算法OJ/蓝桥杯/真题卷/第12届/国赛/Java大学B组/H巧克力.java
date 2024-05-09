package 算法OJ.蓝桥杯.真题卷.第12届.国赛.Java大学B组;

import java.io.*;
import java.util.*;

public class H巧克力 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }


    /**
     巧克力:种类、价格、数量、剩余保质期<br>
     花最少的钱买到x块巧克力,品种不限<br>
     <p>
     优先考虑后面能不能买到(保质期),在能买到的基础上选择最便宜的<br>
     从最后一天开始买,每买一块,所需的保质期就减少一天,扩大了选择范围<br>
     */
    public static void main(String[] args) {
        int x = nextInt(), n = nextInt();
        int[][] arrs = new int[n][3];
        for (int i = 0; i < n; i++) {
            arrs[i][0] = nextInt();
            arrs[i][1] = nextInt();
            arrs[i][2] = nextInt();
        }
        Arrays.sort(arrs, 0, n, (a, b) -> b[1] - a[1]);//按保质期降序
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);//按单价排序

        long ans = 0;
        int j = 0;
        for (int day = x; day > 0; day--) {
            while (j < n && arrs[j][1] >= day) {//把保质期符合的加入队列
                q.add(arrs[j]);
                j++;
            }
            if (q.isEmpty()) {//这一天没有可以买的
                System.out.println(-1);
                return;
            }
            int[] peek = q.peek();//符合保质期要求的,最便宜的
            ans += peek[0];
            peek[2]--;//数量
            if (peek[2] == 0) q.poll();//买完了,出队
            // 不需要出队,因为是从最后一天开始买,轮到前面天买的时候,现在加入的部分一定更能满足保质期要求
        }
        System.out.println(ans);
    }
}
