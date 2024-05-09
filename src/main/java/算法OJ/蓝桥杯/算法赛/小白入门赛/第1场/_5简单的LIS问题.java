package 算法OJ.蓝桥杯.算法赛.小白入门赛.第1场;

import java.util.*;
/**
 已AC
 */
public class _5简单的LIS问题 {
    /*
    求非负整数数组中的最长递增子序列长度
    但可以将某个位置的值改成任意非负整数
     */

    static int n;
    static int[] A;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        A = new int[n];
        for (int i = 0; i < n; i++) A[i] = sc.nextInt();
        //求最长递增子序列
        result = new ArrayList<>();
        result.add(new ArrayList<>(List.of(A[0])));
        for (int i = 1; i < n; i++) {
            update(A[i]);
        }
        List<Integer> res = result.get(result.size() - 1);
        int ans = res.size();
        //检查中间是否能进行一次操作,使长度加1
        if (ans != n && check(res)) ans++;
        System.out.println(ans);
    }
    static List<List<Integer>> result;

    static void update(int x) {
        //二分查找第一个x能拼接的序列
        int left = -1, right = result.size();
        while (left + 1 != right) {
            int mid = (left + right) >>> 1;
            List<Integer> list = result.get(mid);
            int last = list.get(list.size() - 1);
            if (last < x) {
                left = mid;//可以拼接
            } else {
                right = mid;//不能拼接
            }
        }
        if (left == -1) {
            //都不能拼接
            result.get(0).set(0, x);
        } else {
            List<Integer> cover = new ArrayList<>(result.get(left));
            cover.add(x);
            if (left == result.size() - 1) {//是最后一个序列,那么新增
                result.add(cover);
            } else {//中间的序列,拼接后覆盖下一列
                result.set(left + 1, cover);
            }
        }
    }
    static boolean check(List<Integer> a) {
        // A: ... a1 ... a2 ... a3 ... an ...
        //            x  i
        // (1) (特判)如果a1存在两个,且a1不为0,可以修改一个为0
        // (2) 0<a1,a1前面如果有数可以修改为0
        // (3) a1<a1+1<a2, 中间有值可以操作x到a1+1
        // (4) a1<x<x+1<a3, a1拼接x,操作a2,a2向上提升到x+1, 然后拼接a3
        // (5) an后面有数,将其置为大数拼接
        if (A[0] == a.get(0) && (n > 1 && A[0] == A[1] && A[0] != 0)) return true;//case1
        a.add(0, -1);//前面和后面放标志位,便于操作
        a.add(Integer.MAX_VALUE);
        int size = a.size();
        int i = 0, j = 1;
        for (; i < n && j < size; i++) {
            if (A[i] == a.get(j)) {
                j++;
                continue;
            }
            if (a.get(j - 1) + 1 < a.get(j)) return true;//case1&3
            if (j + 1 < size && a.get(j - 1) < A[i] && A[i] + 1 < a.get(j + 1)) return true; //case4
        }
        return i < n;//case5
    }


}
