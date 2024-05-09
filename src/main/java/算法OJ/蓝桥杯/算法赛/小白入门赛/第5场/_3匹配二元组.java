package 算法OJ.蓝桥杯.算法赛.小白入门赛.第5场;

import java.io.*;
import java.util.*;
/**
 已AC
 */
public class _3匹配二元组 {
    /*
    如果ai/j = aj/i (1<=i<j<=N)则(i,j)是匹配二元组
    求数组A中匹配二元组的数量
     */

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     A[i]=a[i]*i
     遍历存入map,找相同的
     */
    public static void main(String[] args) {
        int n = Int();
        long ans = 0;
        Map<Long, Integer> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            long a = (long) i * Int();
            int count = map.getOrDefault(a, 0);
            ans += count;
            map.put(a, count + 1);
        }
        System.out.println(ans);
    }
}
