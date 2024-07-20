package 算法.数学.数学基础.其他;

import java.util.ArrayList;

/**
 求给定数组的逆序对个数
 */
public class 逆序对 {


    public static long cal(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        long ans = 0;
        list.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            int a = arr[i];
            int idx = search(list, a);
            ans += i - idx;// 第i个数左侧大于他的有i-idx个
            list.add(idx, a);
        }
        return ans;
    }

    /**
     二分查找最小的大于target的索引
     */
    private static int search(ArrayList<Integer> list, long target) {
        int l = 0, r = list.size() - 1;// list[l] <= target < list[r]
        if (list.get(l) > target) return 0;
        if (list.get(r) <= target) return list.size();
        while (l + 1 != r) {
            int mid = (l + r) >>> 1;
            if (list.get(mid) > target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }
}
