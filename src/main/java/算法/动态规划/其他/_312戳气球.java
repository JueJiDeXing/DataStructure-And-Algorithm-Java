package 算法.动态规划.其他;

public class _312戳气球 {//TODO
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = arr[n + 1] = 1;
        System.arraycopy(nums, 0, arr, 1, n);
        int[][] f = new int[n + 2][n + 2];
        for (int len = 3; len <= n + 2; len++) {
            for (int l = 0; l + len - 1 <= n + 1; l++) {
                int r = l + len - 1;
                //left~right
                for (int k = l + 1; k <= r - 1; k++) {
                    //枚举k, left~k + 中间乘积 + k~right
                    f[l][r] = Math.max(f[l][r], f[l][k] + arr[l] * arr[k] * arr[r] + f[k][r]);
                }
            }
        }
        return f[0][n + 1];
    }
}
