package 算法.动态规划.其他;

public class _100210最大节点价值之和 {
    /*
    n个节点无向树,节点编号0~n-1
    给出nums数组,nums[i]表示节点i的价值

    给定一个正整数k,可以执行任意次操作:
    选择节点u和v连接的边,将u的值和v的值都异或上k

    求最大节点价值之和
     */

    /**
     如果对一条路径上的所有边进行操作<br>
     由于中间的节点都异或了两次,值不变,所以只有起点和终点异或上了k<br>
     这表示,操作可以发生在任意两个节点之间<br>
     <p>
     执行异或操作:<br>
     1. 两个数都异或了k -> 异或k的数减2<br>
     2. 两个数都没异或k -> 异或k的数加2<br>
     3. 一个异或了k,一个没有 -> 数量不变<br>
     所以执行操作后,异或了k的数一定是偶数<br>
     <p>
     问题转变为:在nums中选择偶数个数,将他们异或上k,求nums的最大和<br>
     <p>
     状态机:
     0:当前有偶数个数异或了k; 1:当前有奇数个数异或了k<br>
     对每个数字x独立考虑:<br>
     不操作: 0->0 或 1->1  sum + x<br>
     操作: 0->1 或 1->0    sum+ x^k<br>
     <p>
     定义f[i][0]前i个数选择了偶数个数异或k的最大和,f[i][1]为选择了奇数个数的最大和<br>
     由状态机得:<br>
     f[i+1][0] = max( f[i][0]+x, f[i][1]+x^k )<br>
     f[i+1][1] = max( f[i][1]+x, f[i][0]+x^k )<br>
     ans=f[n][0]<br>
     */
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        long f0 = 0, f1 = Long.MIN_VALUE;
        for (int x : nums) {
            long temp0 = f0, temp1 = f1;
            f0 = Math.max(temp0 + x, temp1 + (x ^ k));
            f1 = Math.max(temp1 + x, temp0 + (x ^ k));
        }
        return f0;
    }
}
