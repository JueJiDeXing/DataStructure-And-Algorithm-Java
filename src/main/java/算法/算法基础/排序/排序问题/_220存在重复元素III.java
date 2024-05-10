package 算法.算法基础.排序.排序问题;

import java.util.HashMap;
import java.util.Map;

public class _220存在重复元素III {
    /*
    给你一个整数数组 nums 和两个整数 indexDiff 和 valueDiff 。
    找出满足下述条件的下标对 (i, j)：
    i != j,
    abs(i - j) <= indexDiff
    abs(nums[i] - nums[j]) <= valueDiff
    如果存在，返回 true ；否则，返回 false 。
     */

    /*
    一个数x的影响范围是[x,x+t],所以设置桶的大小为t+1
    如果两个数字被散列到了一个桶里,那么就是true
    如果两个数字被散列到了相邻桶里,那么检查这两个桶的距离是否小于t
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();// key:索引i号桶,value:桶的元素
        int size = t + 1;
        for (int i = 0; i < n; i++) {
            int id = getID(nums[i], size);
            if (map.containsKey(id)) {//检查当前桶
                return true;
            }
            //检查两个相邻桶
            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < size) {
                return true;
            }
            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < size) {
                return true;
            }
            map.put(id, nums[i]);
            if (i >= k) {//移除索引越界的桶
                map.remove(getID(nums[i - k], size));
            }
        }
        return false;
    }

    /**
     通过元素值和桶大小映射到索引i号桶
     例如: x=2 t=4 -> 映射到索引0号桶
     */
    public int getID(int x, int size) {
        if (x >= 0) {
            return x / size;
        }
        return (x + 1) / size - 1;
    }
}
