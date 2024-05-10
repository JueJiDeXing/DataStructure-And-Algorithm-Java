package 基础数据结构算法.链表;

/**
 难度:中等
 */
public class _287寻找重复数 {
    /*
    给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），
    可知至少存在一个重复的整数。

    假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。

    你设计的解决方案必须 不修改 动态数组 nums 且只用常量级 O(1) 的额外空间。
     */
    public int findDuplicate(int[] nums) {
        //快慢指针找环形链表入口
        int slow = nums[0], fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        int pre1 = 0, pre2 = slow;
        while (pre1 != pre2) {
            pre1 = nums[pre1];
            pre2 = nums[pre2];
        }
        return pre1;
    }
}
