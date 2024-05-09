package 算法OJ.蓝桥杯.算法赛.算法双周赛.第4场;

import java.io.*;
import java.util.*;

/**
 已AC
 */
public class _5充能计划 {
    /*
    n个引擎排成一排,初始能量均为0
    宝石种类共有m种,第i种宝石有s[i]个能量,可以为长度为s[i]的子数组充能1点
    引擎对每一种宝石只接受1点能量,多余无效
    现在有q个宝石,第i个宝石种类为p[i],被放置在了第k个引擎上
    它为k和k后面的引擎充能(能量s[i],最多充到min{k+s[i]-1,n}

    求最后每个引擎的能量值
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     前缀和差分
     */
    public static void main(String[] args) {
        int n = nextInt(), m = nextInt(), q = nextInt();
        int[] s = new int[m + 1];
        for (int i = 1; i <= m; i++) s[i] = nextInt();
        PriorityQueue<int[]>[] diff = new PriorityQueue[m + 2];
        Arrays.setAll(diff, k -> new PriorityQueue<>((o1, o2) -> ((int[]) o1)[0] - ((int[]) o2)[0]));
        for (int i = 0; i < q; i++) {
            int p = nextInt(), k = nextInt();
            int end = Math.min(n, k + s[p] - 1);
            PriorityQueue<int[]> op = diff[p];
            //在差分数组op的[k,end]置1
            op.offer(new int[]{k, end + 1});
        }
        //前缀和融合
        int[] ansDiff = new int[n + 2];
        for (int type = 1; type <= m; type++) {
            PriorityQueue<int[]> d = diff[type];
            int left = -1, right = -1;
            while (!d.isEmpty()) {
                int[] op = d.poll();
                left = Math.max(op[0], right);
                right = op[1];
                //对ansDiff的[left,right)加1
                ansDiff[left]++;
                ansDiff[right]--;
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += ansDiff[i];
            System.out.print(ans + " ");
        }
    }
}
