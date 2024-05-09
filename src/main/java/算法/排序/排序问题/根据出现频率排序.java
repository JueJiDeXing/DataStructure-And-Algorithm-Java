package 算法.排序.排序问题;

import java.util.Arrays;

public class 根据出现频率排序 {

    //出现最少的排在前面,频率相同则降序排序
    //元素:[-100,100]

    public static int[] frequencySort(int[] nums) {
        int[] count = new int[201];
        for (int i : nums) {
            count[i + 100]++;
        }
        //比较器
        return Arrays.stream(nums).boxed()
                .sorted((a, b) -> {
                    int af = count[a + 100];//nums的值加100为count的索引,count的值表示元素出现次数
                    int bf = count[b + 100];
                    if (af < bf) {//比较频率
                        return -1;
                    } else if (af > bf) {
                        return 1;
                    } else {
                        return b - a;//频率相等,降序
                    }
                })
                .mapToInt(Integer::intValue).toArray();
    }
}
