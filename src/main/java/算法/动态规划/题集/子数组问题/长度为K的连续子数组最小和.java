package 算法.动态规划.题集.子数组问题;

public class 长度为K的连续子数组最小和 {
    public int minSum(int[] arr, int n) {
        int min = Integer.MAX_VALUE;
        int[] prev = new int[arr.length + 1];
        prev[0] = 0;
        for (int i = 1; i <= arr.length; i++) {
            prev[i] = arr[i - 1] + prev[i - 1];
        }
        for (int i = n; i <= arr.length; i++) {
            int sum = prev[i] - prev[i - n];
            if (sum < min) {
                min = sum;
            }
        }
        return min;
    }
}
