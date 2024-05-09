package 数据结构.树.树问题.其他;

import java.util.*;

/**
 第 81 场周赛 Q4
 难度分: 1900
 */
public class _823带因子二叉树 {
    /*
    给出一个含有不重复整数元素的数组 arr ，每个整数 arr[i] 均大于 1。

    用这些整数来构建二叉树，每个整数可以使用任意次数。
    其中：每个非叶结点的值应等于它的两个子结点的值的乘积。

    满足条件的二叉树一共有多少个？
    答案可能很大，返回 对 109 + 7 取余 的结果。
     */
    /**
     <h1>dfs</h1>
     dfs(x)表示以x为根节点的二叉树数量<br>
     假设 a 是 x 的因子 (a 在 arr 中) , 并且 b = x / a 也在 arr 中<br>
     那么以 x 为根节点的二叉树数量 = 不带孩子的方案 + 带孩子的方案 = 1 + sum( dfs(a) * dfs(b) )<br>
     <br>
     枚举根节点 x <br>
     统计以 x 为 根的二叉树数量取模 <br>
     */
    public int numFactoredBinaryTrees(int[] arr) {
        long MOD = (long) 1e9 + 7;
        int n = arr.length;
        Set<Integer> s = new HashSet<>(n);
        for (int x : arr) {
            s.add(x);
        }
        long ans = 0;
        for (int num : arr) {//枚举根节点
            ans = (ans + dfs(num, arr, s)) % MOD;
        }
        return (int) ans;
    }

    /**
     以n为根节点的二叉树个数
     */
    public long dfs(int n, int[] arr, Set<Integer> s) {
        long res = 1;//自身不带孩子为1种方案
        for (int num : arr) {
            //两个因数都要在arr中
            if (n % num == 0 && s.contains(n / num)) {
                res += dfs(num, arr, s) * dfs(n / num, arr, s);//求两个因子为根的数量
            }
        }
        return res;
    }

    /**
     <h1>记忆化</h1>
     */
    public int numFactoredBinaryTrees2(int[] arr) {
        final long MOD = (long) 1e9 + 7;
        Arrays.sort(arr);//排序
        int n = arr.length;
        Map<Integer, Integer> idx = new HashMap<>(n);//映射 值 -> 索引
        for (int i = 0; i < n; i++) {
            idx.put(arr[i], i);
        }

        long[] memo = new long[n];//记忆数组
        Arrays.fill(memo, -1); // -1 表示没有计算过
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += dfs(i, arr, memo, idx);
        }
        return (int) (ans % MOD);
    }

    /**
     @param i    根节点索引
     @param memo 记忆数组
     @param idx  哈希映射
     @return 以arr[i]为根的二叉树数量
     */
    private long dfs(int i, int[] arr, long[] memo, Map<Integer, Integer> idx) {
        if (memo[i] != -1) // 之前计算过
            return memo[i];
        int val = arr[i];//根节点值
        long res = 1;
        for (int j = 0; j < i; ++j) { // val 的因子一定比 val 小
            int x = arr[j];//因子x
            if (val % x == 0 && idx.containsKey(val / x)) { // 因子 x 和 val/x
                res += dfs(j, arr, memo, idx) * dfs(idx.get(val / x), arr, memo, idx);
            }
        }
        return memo[i] = res; // 记忆化
    }

    /**
     <h1>递推</h1>
     dp[i]表示以 x = arr[i] 为根节点的二叉树数
     若 a = arr[j] 为 x 的因子, 且 b = x / a = arr[k]
     则 dp[i] = 1 + sum( dp[j] * dp[k] )
     最后答案为 sum(dp)
     */
    public int numFactoredBinaryTrees3(int[] arr) {
        final long MOD = (long) 1e9 + 7;
        Arrays.sort(arr);
        int n = arr.length;
        Map<Integer, Integer> idx = new HashMap<>(n);//映射 值 -> 索引
        for (int i = 0; i < n; i++) {
            idx.put(arr[i], i);
        }
        long ans = 0;
        long[] dp = new long[n];
        for (int i = 0; i < n; i++) {
            int val = arr[i];
            dp[i] = 1;//初始化为1,不带孩子的方案
            for (int j = 0; j < i; ++j) { // val 的因子一定比 val 小
                int x = arr[j];
                if (val % x == 0 && idx.containsKey(val / x)) { // 另一个因子 val/x 必须在 arr 中
                    dp[i] += dp[j] * dp[idx.get(val / x)];
                }
            }
            ans += dp[i];
        }
        return (int) (ans % MOD);
    }

    /**
     <h1>优化</h1>
     因此在枚举 a = arr[j] 时，不需要枚举超过 x 的 a
     如果 x^2 > val ,退出循环。
     如果 x^2 = val ,把 f[j]^2 加入答案，退出循环。
     如果 x^2 < val ,把 2×f[j]×f[k] 加入答案。
     */
    public int numFactoredBinaryTrees4(int[] arr) {
        final long MOD = (long) 1e9 + 7;
        Arrays.sort(arr);
        int n = arr.length;
        Map<Integer, Integer> idx = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            idx.put(arr[i], i);
        }
        long ans = 0;
        long[] f = new long[n];
        for (int i = 0; i < n; i++) {
            int val = arr[i];
            f[i] = 1;
            for (int j = 0; j < i; ++j) {
                int x = arr[j];
                if ((long) x * x > val) { // 防止乘法溢出
                    break;
                }
                if (x * x == val) {
                    f[i] += f[j] * f[j];
                    break;
                }
                if (val % x == 0 && idx.containsKey(val / x)) {
                    f[i] += f[j] * f[idx.get(val / x)] * 2;
                }
            }
            ans += f[i];
        }
        return (int) (ans % MOD);
    }

}
