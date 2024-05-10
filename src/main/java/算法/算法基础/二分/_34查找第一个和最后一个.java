package 算法.算法基础.二分;

/**
 难度:中等
 */
public class _34查找第一个和最后一个 {
    /*
    给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
    如果数组中不存在目标值 target，返回 [-1, -1]。
    你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     */

    /**
     解法1: 先二分查找leftMost然后在left基础上二分查找rightMost
     O(log(n))
     */
    public int[] searchRange(int[] nums, int target) {
        int first = -1, last = -1;
        //查找第一个
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] < target) {//在右边
                left = mid + 1;
            } else if (nums[mid] == target) {//找到了,向左找更靠前的
                first = mid;
                right = mid - 1;
            } else {//在左边
                right = mid - 1;
            }
        }
        //查找最后一个
        right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] < target) {//在右边
                left = mid + 1;
            } else if (nums[mid] == target) {//找到了,向右找更靠后的
                left = mid + 1;
                last = mid;
            } else {//在左边
                right = mid - 1;
            }
        }

        return new int[]{first, last};
    }

    /**
     解法2,从两端for循环遍历找第一个相等的
     O(n)
     */
    public int[] searchRange2(int[] nums, int target) {
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                ans[0] = i;
                break;
            }
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == target) {
                ans[1] = i;
                break;
            }
        }
        return ans;
    }

    /**
     解法3: 查找target的leftMost,再查找target+1的leftMost-1
     */
    public int[] searchRange3(int[] nums, int target) {
        int l = search(nums, target), r = search(nums, target + 1);
        if (l == nums.length || nums[l] != target) return new int[]{-1, -1};
        return new int[]{l, r - 1};
    }

    //找>=target的第一个
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (r + l) >> 1;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
