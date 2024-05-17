package 算法OJ.牛客.五一集训1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

public class F射气球 {
    /*
    n个气球,第i个气球在(x[i],y[i])处
    给定r, 可以选择 x,x+r,x+2r 和 y,y+r,y+2r 六条线(水平和竖直分别选择三条距离为r的线)
    求线上的气球数最大值
    n,r,x,y<1e5
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static final int N = 600005;//坐标
    static List<Integer>[] xMapToY = new ArrayList[N];// 中间打在x=i,能射到的气球的y坐标
    static int[] cntY = new int[N];// 中间打在y=i，能射中气球的个数
    static HashMap<Integer, Integer> s = new HashMap<>();// cntY的计数表

    /**
     思路:
     (1) 枚举打x的中间线, 拿到 这三列的气球个数 和 全部y坐标
     (2) 将这三列在y上移除, 因为x的个数已经拿到了
     (3) 移除后想办法拿到y的最大个数即可
     (4) 求完这个x后需要把这三列再加回来(回溯)
     <p>
     如何进行维护:
     (1) 需要 这三列的气球个数 和 全部y坐标, 那么使用一个二维数组 arr[x] -> [listY] 即可
     (3) 需要 拿y的最大个数, 可以使用一个cnt数组, cnt[y] 存储 [y-r,y,y+r]三行的点的个数, 这样对cnt求max就是y的最大个数
     但不能直接求, 枚举x已经O(n), 所以需要维护cnt的最大值, 可以使用TreeMap有序集合(但是直接用HashMap也能过)
     (2/4) 需要 移除/添加y, 移除/添加 只需要在cnt数组上 减1/加1 即可
     */
    public static void main(String[] args) throws IOException {
        // 输入
        Arrays.setAll(xMapToY, k -> new ArrayList<>());
        int n = I(), r = I();
        int[] d = new int[]{-r, 0, r};
        for (int i = 0; i < n; i++) {
            int X = I() + 2 * r, Y = I() + 2 * r;//把图扩大，向上移2*r
            for (int tr : d) {
                xMapToY[X + tr].add(Y);//打在x, [x-r,x,x+r]计数加1
                cntY[Y + tr]++;// 打在y, [y-r,y,y+r]计数加1
            }
        }
        // 维护计数
        for (int i = r; i <= N - r; i++) {
            s.put(cntY[i], s.getOrDefault(cntY[i], 0) + 1);
        }
        // 枚举打x的中间线
        int ans = 0;
        for (int x = r; x <= N - r; x++) {
            // 求x -> 删点 -> 求y -> 回溯
            int xNum = xMapToY[x].size();//打在x, [x-r,x,x+r]三列的气球个数
            for (int y : xMapToY[x]) {// 将这三列的气球全部移除
                for (int tr : d) del(y + tr);
            }
            ans = Math.max(ans, xNum + max(s.keySet()));// s的key为cnt数组,对其求max即为打y的最大值
                for (int y : xMapToY[x]) {// 打完将这三列的气球加回来
                for (int tr : d) add(y + tr);
            }
        }
        System.out.println(ans);
    }

    /**
     删去y轴的一个点
     cntY[y]-1, 再同时维护cntY的计数表s
     */
    static void del(int y) {
        Integer k = s.get(cntY[y]);
        if (k > 1) {
            s.put(cntY[y], k - 1);
        } else {
            s.remove(cntY[y]);
        }
        cntY[y]--;
        s.put(cntY[y], s.getOrDefault(cntY[y], 0) + 1);

    }

    static void add(int y) {
        Integer k = s.get(cntY[y]);
        if (k > 1) {
            s.put(cntY[y], k - 1);
        } else {
            s.remove(cntY[y]);
        }
        cntY[y]++;
        s.put(cntY[y], s.getOrDefault(cntY[y], 0) + 1);
    }

    static int max(Set<Integer> cntY) {
        int maxY = 0;
        for (int cnt : cntY) maxY = Math.max(maxY, cnt);
        return maxY;
    }
}
