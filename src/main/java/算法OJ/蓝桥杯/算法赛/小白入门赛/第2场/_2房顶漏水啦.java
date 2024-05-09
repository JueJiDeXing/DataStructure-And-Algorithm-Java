package 算法OJ.蓝桥杯.算法赛.小白入门赛.第2场;

import java.io.*;
/**
 已AC
 */
public class _2房顶漏水啦 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static long Long() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (long) st.nval;
    }

    public static void main(String[] args) {
        long n = Long(), m = Long();
        long minX = n + 1, minY = n + 1;
        long maxX = 0, maxY = 0;
        for (int i = 0; i < m; i++) {
            long x = Long(), y = Long();
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
        }
        System.out.println(Math.max(maxX - minX, maxY - minY)+1);
    }
}
