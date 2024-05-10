package 算法.算法基础.二分;

/**
 难度:困难
 */
public class _410分割数组的最大值 {
    /*
    给定一个非负整数数组 nums 和一个整数 k ，
    你需要将这个数组分成 k 个非空的连续子数组。

    设计一个算法使得这 k 个子数组各自和的最大值最小。
    返回分隔后子数组各自和中的最大值
     */

    /**
     最小化最大值 和 最大化最小值 通常使用二分解决<br>
     做法:二分枚举上限m,检查在各分段的和都不超过m的情况下,能否分出k段,那么最小的m即为答案<br>
     二分界限:因为m表示分段和, 那么 二分上界right <= sum(nums), 下界left >= max(nums) 、 left >= avg(sum(nums)) (二者取大)
     */
    public int splitArray(int[] nums, int k) {
        int sum = 0, mx = 0;//求和,求最大值确定二分界限
        for (int x : nums) {
            sum += x;
            mx = Math.max(mx, x);
        }
        int left = Math.max(mx - 1, (sum - 1) / k), right = sum;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (check(nums, k, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    /**
     检查分段的最大和为mx的情况下,能否分出k段
     */
    private boolean check(int[] nums, int k, int mx) {
        int cnt = 1;
        int s = 0;
        for (int x : nums) {
            if (s + x <= mx) {//看这段还能不能加
                s += x;
            } else { // 新划分一段
                if (cnt == k) return false;//已经划了k段了
                cnt++;
                s = x;
            }
        }
        return true;
    }
}
