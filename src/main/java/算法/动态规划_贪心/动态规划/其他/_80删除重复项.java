package 算法.动态规划_贪心.动态规划.其他;

public class _80删除重复项 {
    //如果某元素超过2个,删除多余的(原地删除),返回剩余长度
    public int removeDuplicates(int[] nums) {
        int retain = 2;//保留个数2个
        return process(nums, retain);
    }

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
