package 算法.算法基础.二分.寻找峰值;

public class _1539第k个缺失的正整数 {
    /**
     给定数组arr,和正整数k
     arr严格递增的正整数序列
     <p>
     求第k个arr中缺失的正整数
     <p>
     例: arr=[2,3,4,7,11],k=5
     缺失: 1 5 7 8 9 10 ...
     第k个缺失的数为9

     T = O(log n)
     */

    /**
     arr中缺失的正整数个数 = arr[n-1] - n
     称为"空位数"
     <p>
     若 arr[L]前的空位数 < k, 且 arr[R]前的空位数 >= k
     说明所求的空位在arr[L]~arr[R]上
     若arr[L]前的空位数为t, 则ans = arr[L] + (k - t)
     */
    public int findKthPositive(int[] arr, int k) {
        int n = arr.length;
        this.arr = arr;

        if (getSpace(0) >= k) {
            return k;
        }
        if (getSpace(n - 1) < k) {
            return arr[n - 1] + (k - getSpace(n - 1));
        }

        int L = 0, R = n - 1; //  L:space < k      R: space >= k
        while (L + 1 != R) {
            int mid = (L + R) / 2;
            if (getSpace(mid) < k) {
                L = mid;
            } else {
                R = mid;
            }
        }
        return arr[L] + (k - getSpace(L));
    }

    int[] arr;

    int getSpace(int i) {
        return arr[i] - (i + 1);  // 子数组sub=arr[0,i], sub缺少的正整数个数 = max(sub)-len(sub)
    }

}
