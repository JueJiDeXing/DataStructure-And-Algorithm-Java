package 算法.算法基础.排序.洗牌算法;

import java.util.Random;

public class Shuffle {
    Random r = new Random();

    int nextInt(int low, int high) {
        return r.nextInt(high - low + 1) + low;
    }

    void shuffle(int[] arr, int[] res) {
        int k;
        for (int i = 0; i < arr.length - 1; ++i) {
            k = nextInt(i + 1, arr.length - 1);
            swap(arr, i, k);
        }
    }

    void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
