package 算法.算法基础.双指针;

/**
 难度:中等↓
 */
public class _167两数之和II_输入有序数组 {
    /*
    给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，
    请你从数组中找出满足相加之和等于目标数 target 的两个数。
    如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，
    则 1 <= index1 < index2 <= numbers.length 。

    以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
    你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
    你所设计的解决方案必须只使用常量级的额外空间。
     */

    /**
     假设现在在nums[left,right]中找和为target的下标对(i,j)<br>
     对于i=left和j=right,sum=nums[i]+nums[j]<br>
     如果 sum < target ,那么nums[i]与其他 j < right 下标位置元素之和一定更小,所以i一定不在答案里面,left++<br>
     同理 sum > target ,nums[j]与其他 i > left 下标位置之和一定更大,所以j一定不在答案里面,right-- <br>
     所以双指针,sum小了left右移,sum大了right左移<br>
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) return new int[]{left + 1, right + 1};//下标从1开始,答案需要+1
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{};
    }
}
