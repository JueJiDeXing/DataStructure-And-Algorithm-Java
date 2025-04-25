package 算法.数学.数论.进制.只出现一次的数字;

public class _268丢失的数字 {
    /*
    nums为一个长度为n的数组, nums[i]∈[0,n], nums中的数各不相同
    求nums缺失的数字

    例: nums=[0,1,3], 缺失2
     */

    /**
     <h1>异或</h1>
     XOR{ [0,n] } ^ XOR{ nums[0,n-1] } = 缺失的数字
     */
    public int missingNumber1(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = ans ^ i ^ nums[i];
        }
        return ans ^ n;
    }

    /**
     <h1>求和</h1>
     sum{ [0,n] } - sum{ nums[0,n-1] } = 缺失的数字
     */
    public int missingNumber2(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;
        int s = 0;
        for (int num : nums) {
            s += num;
        }
        return sum - s;
    }

    /**
     <h1>哈希</h1>
     用hash[i]表示i是否在数组中出现了
     */
    public int missingNumber3(int[] nums) {
        int n = nums.length;
        boolean[] hash = new boolean[n + 1];
        for (int num : nums) hash[num] = true;

        for (int i = 0; i < n; i++) {
            if (!hash[i]) return i;
        }
        return n;
    }


}
