package 算法OJ.蓝桥杯.真题卷.第7届.省赛.Java大学A组;

import java.util.*;
/**
 已AC
 */
public class C搭积木 {
    /*
    m块地基,排成一排,为第0层
    最多摆放至第n层
    每一层的积木必须连续放置且每一块都与下一层的对齐
    对于第i层,图纸上第i行画叉的地方不能放积木
    求放置方案数 (不放也是一种方案)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        Arrays.setAll(map, k -> new ArrayList<>());//map[i]: 第i层能放的所有区间
        for (int i = n - 1; i >= 0; i--) {
            char[] x = sc.next().toCharArray();
            int prev = 0;

            for (int j = 0; j < m; j++) {
                if (x[j] == '.') continue;
                if (prev <= j - 1) map[i].add(prev * MUL + j - 1);
                prev = j + 1;
            }
            if (prev <= m - 1) map[i].add(prev * MUL + m - 1);
        }
        System.out.println(dfs(0, m - 1, 0));
    }

    static List<Integer>[] map = new List[101];
    static int MOD = 10_0000_0007;
    static int n, m, MUL = 1000;
    //static HashMap<String, Long> memo = new HashMap<>();
    static long[][][] memo = new long[101][101][101];

    /**
     @param left,right 上一层有积木的位置
     @param level      当前层,-1是地基,0是第一层,最多到第n层,level=n-1
     */
    static long dfs(int left, int right, int level) {
        if (level == n) return 1;
        //String key = left + " " + right + " " + level;
        //if (memo.containsKey(key)) return memo.get(key);
        if (memo[left][right][level] != 0) return memo[left][right][level];
        long ans = 1;//不放,1种
        List<Integer> intervals = map[level];
        for (Integer interval : intervals) {
            int inter0 = interval / MUL, inter1 = interval % MUL;
            if (inter1 < left) continue;
            if (inter0 > right) break;
            int L = Math.max(inter0, left), R = Math.min(inter1, right);
            if (L > R) continue;
            //在区间[L,R]上放积木
            for (int l = L; l <= R; l++) {
                for (int r = l; r <= R; r++) {
                    ans += dfs(l, r, level + 1);
                }
            }
        }
        ans %= MOD;
        //memo.put(key, ans);
        return memo[left][right][level] = ans;
    }
}
