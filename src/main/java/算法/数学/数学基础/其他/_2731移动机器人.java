package 算法.数学.数学基础.其他;

import java.util.Arrays;

public class _2731移动机器人 {
    public static void main(String[] args) {
        _2731移动机器人 test = new _2731移动机器人();
        System.out.println(test.sumDistance(new int[]{1, 0}, "RL", 2));
    }

    /*
    有一些机器人分布在一条无限长的数轴上，他们初始坐标用一个下标从 0 开始的整数数组 nums 表示。
    当你给机器人下达命令时，它们以每秒钟一单位的速度开始移动。

    给你一个字符串 s ，每个字符按顺序分别表示每个机器人移动的方向。
    'L' 表示机器人往左或者数轴的负方向移动，'R' 表示机器人往右或者数轴的正方向移动。

    当两个机器人相撞时，它们开始沿着原本相反的方向移动。

    请你返回指令重复执行 d 秒后，所有机器人之间两两距离之和。
    由于答案可能很大，请你将答案对 109 + 7 取余后返回。

    注意：

    对于坐标在 i 和 j 的两个机器人，(i,j) 和 (j,i) 视为相同的坐标对。
    也就是说，机器人视为无差别的。
    当机器人相撞时，它们 立即改变 它们的前进方向，这个过程不消耗任何时间。
    当两个机器人在同一时刻占据相同的位置时，就会相撞。

    例如，如果一个机器人位于位置 0 并往右移动，另一个机器人位于位置 2 并往左移动，
    下一秒，它们都将占据位置 1，并改变方向。
    再下一秒钟后，第一个机器人位于位置 0 并往左移动，而另一个机器人位于位置 2 并往右移动。

    例如，如果一个机器人位于位置 0 并往右移动，另一个机器人位于位置 1 并往左移动，下一秒，
    第一个机器人位于位置 0 并往左行驶，而另一个机器人位于位置 1 并往右移动。
     */


    public int sumDistance(int[] nums, String s, int d) {
        int MOD = 1000000007;
        int len = nums.length;
        //d秒后的位置
        long[] arr = new long[len];
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == 'R') {
                arr[i] = (long) nums[i] + d;//碰撞等价于穿过
            } else {
                arr[i] = (long) nums[i] - d;
            }
        }
        Arrays.sort(arr);//对位置排序
        //求两两距离之和
        /*
        从最外侧大线段开始,计算贡献,然后去除最外侧端点,计算 内线段贡献
        当前端点(i,len-i-1)的线段(长度为d)上 分布着中间的点有n=len - 2 * i - 1个 .
        对于线段内某点, 其到两端点的距离和为线段长d, 所以, 以当前端点距离总和为d*(n+1)
        然后去除这两个端点,计算下一条线段
        */
        long ans = 0;
        for (int i = 0; i < len / 2; i++) {
            long distance = (arr[len - i - 1] - arr[i]) * (len - 2 * i - 1);
            ans = (ans + distance) % MOD;
        }
        return (int) ans;
    }

    /**
    组合计数
     */
    public int sumDistance2(int[] nums, String s, int d) {
        long MOD = (long) 1e9 + 7;
        int len = nums.length;
        long[] arr = new long[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (long) nums[i] + (s.charAt(i) == 'R' ? d : -d);
        }
        Arrays.sort(arr);
        long ans = 0, sum = 0;
        /*
        a[i]与左侧的距离之和:
        (a[i]−a[0]) + (a[i]−a[1]) + ⋯ + (a[i]−a[i−1])
        = i⋅a[i]−(a[0]+a[1]+⋯+a[i−1])
        = i⋅a[i]− sum[0,i-1]
        ans = Sum(i*a[i]-sum[0,i-1])
         */
        for (int i = 0; i < len; i++) {
            ans = (ans + i * arr[i] - sum) % MOD;
            sum += arr[i];
        }
        return (int) ans;
    }
}
