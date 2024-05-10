package 基础数据结构算法.哈希表;

import java.util.*;

public class _2342数位和相等数对的最大和 {
    /**
     哈希维护数位和对应最大的数
     */
    public int maximumSum(int[] nums) {
        int max = -1;
        Map<Integer, Integer> map = new HashMap<>();//数位和->最大的数
        for (int num : nums) {
            int s = cal(num);
            if (!map.containsKey(s)) {
                map.put(s, num);
                continue;
            }
            int oldNum = map.get(s);
            max = Math.max(max, num + oldNum);
            if (num > oldNum) {
                map.put(s, num);
            }
        }
        return max;
    }

    /**
     使用数组代替哈希表
     */
    public int maximumSum2(int[] nums) {
        int ans = -1;
        int[] mx = new int[82]; // 数位和->最大的数 (至多 9 个 9 相加)
        for (int num : nums) {
            int s = cal(num);
            if (mx[s] > 0) {
                ans = Math.max(ans, mx[s] + num); // 更新答案的最大值
            }
            mx[s] = Math.max(mx[s], num); // 维护数位和等于 s 的最大元素
        }
        return ans;
    }


    public int cal(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
