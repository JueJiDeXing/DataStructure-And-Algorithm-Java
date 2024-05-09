package 算法OJ.Codeforces.构造题单;

import java.util.*;

/**
 已AC
 */
public class _9二分数组 {
    /*
    定义好数组:
        当且仅当数组不能选出一个和为数组和一半的子序列
        或者说数组不能被分为两个相等的部分(需要完全分配元素,两个部分的元素个数可为0)
    给定一个数组a,问最少删除多少个元素才能变为好数组,并输出删除的元素下标
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int min = 10000;
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            min = Math.min(min, a[i] & -a[i]);
        }
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            a[i] /= min;//对序列除2,不影响结果
            sum += a[i];
        }
        //System.out.println(Arrays.toString(a));
        if (sum % 2 == 1) {//元素和为奇,不可能分得出
            System.out.println(0);
            return;
        }
        // f[i]:能否选出和为i的子序列
        int k = sum / 2;
        boolean[] f = new boolean[k + 1];
        f[0] = true;//什么都不选
        for (int i = 1; i <= n; i++) {
            for (int j = k; j >= a[i]; j--) {
                f[j] |= f[j - a[i]];
            }
        }
        if (!f[k]) {//不能分,输出0
            System.out.println(0);
            return;
        }
        // 能分,找到一个奇数删除即可
        System.out.println(1);
        for (int i = 1; i <= n; i++) {
            if (a[i] % 2 == 1) {
                System.out.println(i);
                return;
            }
        }
    }

}
