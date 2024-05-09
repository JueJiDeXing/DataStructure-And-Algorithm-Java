package 算法.动态规划_贪心.动态规划.题集.子数组问题.最长递增序列;

import 算法OJ.蓝桥杯.真题卷.第11届.国赛.Java大学A组.D游园安排;

import java.util.*;

/**
 求真正序列,而非长度
 */
public class 最长递增子序列 {
    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(new 最长递增子序列().LIS(nums));//2 3 7 18
    }

    public List<Integer> LIS(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();
        List<List<Integer>> results = new ArrayList<>();
        results.add(Arrays.asList(nums[0]));
        for (int i = 1; i < nums.length; i++) {
            update(results, nums[i]);
        }
        return results.get(results.size() - 1);
    }

    private void update(List<List<Integer>> results, int num) {
        //寻找最长的可以在后面拼接num的子序列
        int left = -1, right = results.size();
        while (left + 1 != right) {
            int mid = (left + right) >>> 1;
            List<Integer> line = results.get(mid);
            int tail = line.get(line.size() - 1);//当前一行的最后一个数字(当前子序列的末位)
            if (tail < num) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (left == -1) {//比所有行的行末数字都小
            results.set(0, Arrays.asList(num));
            return;
        }
        //行拼接num后覆盖下一行
        List<Integer> cover = new ArrayList<>(results.get(left));
        cover.add(num);
        if (left == results.size() - 1) {
            results.add(cover);
        } else {
            results.set(left + 1, cover);//相同长度下的更优解
        }
    }

    /**
     性能优化<br>
     {@link D游园安排}
     */
    public static List<Integer> LIS2(int[] names) {
        int len = names.length;
        if (len == 0) return new ArrayList<>();

        int[] lastString = new int[len];//lastString[i]:长度为i的最长严格递增子序列的最后一个字符串
        int[] path = new int[len];  //path[i],以第i个名字结尾的最长递增子序列长度(从0开始)
        path[0] = 0;
        lastString[0] = names[0];
        int now = 0;//最长的序列长度

        for (int i = 1; i < len; i++) {
            int name = names[i];
            if (name > lastString[now]) {//比最后当前最长序列的最后一个大,可以拼接到最后一个序列
                now++;
                lastString[now] = name;
                path[i] = now;
            } else if (name < lastString[now]) {
                int l = 0, r = now + 1; //二分查找大于name的第一个位置l
                while (l <= r) {
                    int mid = (l + r) >>> 1;
                    if (lastString[mid] >= name) r = mid - 1;
                    else l = mid + 1;
                }
                lastString[l] = name;//用name覆盖长度为l的序列的最后一个
                path[i] = l;
            }
        }

        int MAX = Integer.MAX_VALUE;
        int[] ans = new int[now + 1];
        for (int i = len - 1; i >= 0 && now >= 0; i--) {//根据path找出最优解--长度为now的序列
            int name = names[i];
            int iLen = path[i];//以name结尾的最长递增子序列长度
            if (iLen == now && name <= MAX) {//name是要找的那个序列里的(长度匹配,且比后一项小)
                ans[now--] = i;
                MAX = name;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int an : ans) res.add(names[an]);
        return res;
    }
}
