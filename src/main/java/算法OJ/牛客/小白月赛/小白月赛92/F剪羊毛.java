package 算法OJ.牛客.小白月赛.小白月赛92;

import java.util.*;
/**
 已AC
 */
public class F剪羊毛 {
    /*
    n块草皮,第i块为(w[i-1],w[i]],价值为v[i],有m只羊,羊在草皮i上则获得v[i]的价值
    现在可以水平移动,问可获得的价值之和有多少种
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        long[] w = new long[n + 1];
        long[] v = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            w[i] = w[i - 1] + sc.nextInt();
        }
        for (int i = 1; i <= n; i++) v[i] = sc.nextLong();
        long[] x = new long[m + 1];
        for (int i = 1; i <= m; i++) x[i] = sc.nextLong();
        HashMap<Long, Long> map = new HashMap<>();
        /*
        如果移动了d的距离,x[i]->x[i]+d
        则羊i在草j的条件为 w[j-1]-x[i]+1 <= d <= w[j]-x[i]
        那么羊i对d在 [ w[j-1]-x[i]+1, w[j]-x[i] ] 范围内有贡献v[i] (可差分操作)
        最后按序遍历d轴,前缀和为可行的取值,set一下即可
         */
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                long p1 = w[i - 1] - x[j] + 1;
                map.put(p1, map.getOrDefault(p1, 0L) + v[i]);
                long p2 = w[i] - x[j] + 1;
                map.put(p2, map.getOrDefault(p2, 0L) - v[i]);
            }
        }
        //按坐标排序
        Map.Entry<Long, Long>[] array = map.entrySet().toArray(new Map.Entry[0]);
        Arrays.sort(array, Map.Entry.comparingByKey());
        //统计取值个数
        HashSet<Long> set = new HashSet<>();
        long sum = 0;
        for (Map.Entry<Long, Long> e : array) {
            sum += e.getValue();
            set.add(sum);
        }
        System.out.println(set.size());
    }
}
