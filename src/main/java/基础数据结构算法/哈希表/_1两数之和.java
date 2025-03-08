package 基础数据结构算法.哈希表;

import java.util.*;

/**
 难度:简单
 */
public class _1两数之和 {
    /*
    给定一个无序数组,在其中找到和为target的两个数
    返回下标[i,j],nums[i]+nums[j]=target
     */

    /**
     {@link 算法.算法基础.双指针._15三数之和}
     */
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            int y = target - x;
            if (map.containsKey(y)) {
                return new int[]{i, map.get(y)};
            } else {
                map.put(x, i);
            }
        }
        return null;

    }

}
