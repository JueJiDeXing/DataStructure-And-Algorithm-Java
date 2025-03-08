package 算法.动态规划.其他;

/**
 难度:简单
 */
public class _26删除有序数组中的重复项 {
    /**
     有序数组
     如果某元素超过2个,删除多余的(原地删除),返回剩余长度
     */

    public int removeDuplicates(int[] nums) {
        int retain = 1;//保留个数1个
        return process(nums, retain);
    }

    /**
     _80删除有序数组中的重复项II
     元素超过3个的,删除到仅剩两个

     @param nums   数组,非严格递增
     @param retain 需要保留的个数
     @return 剩余的元素个数
     */
    private static int process(int[] nums, int retain) {//通用性
        int k = 0;
        for (int n : nums) {
            if (k < retain || nums[k - retain] != n) {
                nums[k++] = n;
            }
        }
        return k;
    }
}
