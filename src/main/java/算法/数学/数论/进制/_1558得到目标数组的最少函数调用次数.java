package 算法.数学.数论.进制;

public class _1558得到目标数组的最少函数调用次数 {
    /*
    操作1:选择1个数字加1
    操作2:整个数组都乘2
    求 从全0数组 变为 nums数组 的最小操作次数
     */

    /**
     <h1>贪心算法,反向思考</h1>
     从nums变为全0,要使操作次数尽可能小,除2的操作要尽可能多<br>
     要除以2,需要保证每个数字都为偶数<br>
     从二进制角度考虑:<br>
     如果数字k的二进制最后一位是1,那么需要减1,最后一位是0,等待所有的数都是0时整体右移1位<br>
     除2操作不会影响1的个数,所以1都是要减的,减的次数就是全部元素1的个数<br>
     而除2的次数取决于最大的元素是多少位的<br>
     */
    public int minOperations(int[] nums) {
        /*
            例:[1,2,10]->次数为7
            1        0     0       0      0      0       0
            10 ->    10 ->  1 ->    0 ->   0 ->   0  ->   0
            1010     1010   101     100     10      1       0
            1         2     3,4      5      6       7
            每个二进制的1都要减,当最后一位都为0(都是偶数)时可以除2(右移),右移次数取决于最高位1的位数
            1,2,10中1的个数为4,最大值10的位数3 -> 4+3=7
        */
        int countBit = 0;
        int max = 0;
        for (int num : nums) {
            countBit += Integer.bitCount(num);
            if (max < num) max = num;
        }
        return max == 0 ? 0 : countBit + Integer.bitCount(Integer.highestOneBit(max) - 1);
        // max=10  highestOneBit(1010)->1000 ->
        // 1000-1 -> 111 ->
        // bitCount(111) = 3
    }
}
