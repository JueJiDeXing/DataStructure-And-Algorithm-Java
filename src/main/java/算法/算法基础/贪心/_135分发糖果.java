package 算法.算法基础.贪心;

import java.util.*;

public class _135分发糖果 {
    /*
    n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。

    你需要按照以下要求，给这些孩子分发糖果：

    每个孩子至少分配到 1 个糖果。
    相邻两个孩子评分更高的孩子会获得更多的糖果。
    请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
     */

    /**
     左规则:当i的分数大于i-1的分数时,i需要多一颗
     右规则:当i的分数大于i+1的分数时,i需要多一颗
     先给每人一颗,然后分别按照左右规则给出糖果,然后在两种规则下对每个人取最大值即可
     <p></p>
     证明1:为什么取最大值不影响左右规则
     假设AB相邻(A左B右)
     如果A > B,那么按照左规则B≤A,而按照右规则,A=B+1,取最大值后,仍有B≤A
     如果A < B,那么按照左规则B=A+1,而按照右规则,A≤B,取最大值后,仍有A≤B
     如果A==B,他们不需要遵守规则
     综上:取最大值不会影响左右规则
     <p></p>
     证明2:为什么取最大值是最优解
     首先“评分高的学生必须获得更多的糖果 等价于 所有学生满足左规则且满足右规则”
     其次不论是左规则还是右规则都是以最小的单位1进行增加或者减少，均以最小操作作为原则，
     所以在每一个点上的取值不可能存在第三个最优解
     */
    public int candy(int[] ratings) { 
        int n = ratings.length;
        int[] left = new int[n], right = new int[n];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        int ans = left[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
            ans += Math.max(right[i], left[i]);
        }
        return ans;


    }
}
