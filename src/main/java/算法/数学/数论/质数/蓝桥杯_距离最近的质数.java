package 算法.数学.数论.质数;

import 算法OJ.蓝桥杯.算法赛.小白入门赛.第2场._3质数王国;

public class 蓝桥杯_距离最近的质数 {
    //素数筛+二分

    //素数筛
    static int N = 100010;
    static int[] prime = new int[N];
    static int k = 0;

    static {
        boolean[] isCom = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            if (!isCom[i]) {
                //i是质数
                prime[k++] = i;
            }
            for (int j = 0; j < k; j++) {
                int t = i * prime[j];
                if (t > N) break;
                isCom[t] = true;
                if (i % prime[j] == 0) break;
            }
        }

    }

    /**
     在prime数组中二分查找n最近的质数
     {@link  _3质数王国}
     */
    static int nearest(int n) {
        int left = 0, right = k;
        while (left + 1 != right) {
            int mid = (left + right) >>> 1;
            if (prime[mid] == n) return n;
            if (n < prime[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return prime[right] - n > n - prime[left] ? prime[left] : prime[right];
    }
}
