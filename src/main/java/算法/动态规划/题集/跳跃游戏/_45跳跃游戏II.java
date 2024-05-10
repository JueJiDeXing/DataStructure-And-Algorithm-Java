package 算法.动态规划.题集.跳跃游戏;

public class _45跳跃游戏II {
    /*
    给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。

    每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。
    换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:

    0 <= j <= nums[i]
    i + j < n
    返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
     */

    /**
     <h1>贪心</h1>
     由于生成的测试用例一定可以到达 nums[n - 1]<br>
     假设当前位置为i,能跳到的最远位置为 j = i+nums[i]<br>
     那么 最优策略 是在 i~j 中选择一个 跳的最远的下标 进行跳跃
     */
    public int jump1(int[] nums) {
        int curr = 0, res = 0;
        while (curr < nums.length - 1) {
            int currMaxIndex = nums[curr] + curr;
            if (currMaxIndex >= nums.length - 1) {
                return res + 1;//跳最后一步
            }
            //找当前可到达的下标里 能跳的最远的一个
            int max = currMaxIndex, next = curr;
            for (int i = curr + 1; i <= currMaxIndex; i++) {
                if (i + nums[i] > max) {
                    max = i + nums[i];
                    next = i;
                }
            }
            curr = next;//跳跃
            res++;
        }
        return res;
    }

    /**
     <h1>动态规划+贪心</h1>
     第一次起跳: 从起点0开始, 那么最远可以跳到 nums[0] <br>
     第二次起跳: 那么从 0~nums[0] 都可以算作第二次起跳 ; 假设第二次起跳, 对于所有起跳点的最远距离为 d <br>
     第三次起跳: 那么从 nums[0]~d 都可以算作第三次起跳 <br>
     ....依次类推<br>
     <br>
     所以, 每次起跳都可以算作是在 上一次起跳可到达的最远处 起跳 <br>
     动态规划: 记录此刻的 最大跳跃距离 和 起跳边界 ,<br>
     当遇到 起跳边界 时, 此刻的最大距离作为下一次起跳的 起跳边界
     */
    public int jump2(int[] nums) {
        int border = 0;  // 记录当前能跳跃到的位置的边界下标
        int maxPosition = 0;// 记录在边界范围内，能跳跃的最远位置的下标
        int steps = 0;  // 记录所用步数
        for (int i = 0; i < nums.length - 1; i++) {
            // 继续往下遍历，统计边界范围内，哪一格能跳得更远，每走一步就更新一次能跳跃的最远位置下标
            // 其实就是在统计下一步的最优情况
            maxPosition = Math.max(maxPosition, nums[i] + i);
            // 如果到达了边界，那么一定要跳了，下一跳的边界下标就是之前统计的最优情况maxPosition，并且步数加1
            if (i == border) {
                border = maxPosition;
                steps++;
            }
        }
        return steps;
    }
}
