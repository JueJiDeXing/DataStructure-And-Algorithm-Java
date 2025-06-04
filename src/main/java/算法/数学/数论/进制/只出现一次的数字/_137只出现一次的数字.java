package 算法.数学.数论.进制.只出现一次的数字;

public class _137只出现一次的数字 {
    // 每个数字出现3次,只有一个数字出现一次,找到那个数字

    /*
    将数字展开为二进制, 叠加每位1的个数, 出现三次的数叠加为3的倍数, 取余后只剩只出现一次的数

    统计二进制中每位1的个数, 将个数对3取余,即得出答案
    [3,3,3,5] -> 二进制1的个数  1 3 4 -> 对3求余 1 0 1 -> 5
     0 1 1
     0 1 1
     0 1 1
     1 0 1
    =1 3 4

    <优化>
    num = [n31, n30, ..., n0]
    highs = [high31, high30, ..., high0]
    lows = [low31, low30, ..., low0]
    使用两个二进制位(high,low)表示对3求余后该位的1的个数
    00表示求余后个数为0  01表示求余后个数为1  10表示求余后个数为2
    状态转换关系: 00+1=01  01+1=10  10+1=00  00+0=00 01+0=01 10+0=10
    列表: n   high   low  ->  high  low
          0    0      0        0     0
          0    0      1        0     1
          0    1      0        1     0

          1    0      0        0     1
          1    0      1        1     0
          1    1      0        0     0

    遍历列表后,得到一串high和low,因为每个数都出现三次,取余为1的位就是只出现一次的数字,不存在取余为2的位,所以取出低位串即可
    状态转移方程推导:
        先看low
    列表: n   high   low  ->   low
          0    0      0        0
          0    0      1        1
          0    1      0        0

          1    0      0        1
          1    0      1        0
          1    1      0        0

        low = ~n ~high low | n ~high ~low = ~high ( ~n low | n ~low) = low^n & ~high

        更新low后, 原列表变为
    列表: n   high   low  ->  high
          0    0     0        0
          0    0     1        0
          0    1     0        1

          1    0     1        0
          1    0     0        1
          1    1     0        0
         high = ~n high ~low | n ~high ~low = ~low ( ~n high | n ~high) = high^n & ~low
    所以总公式为:
    lows = lows ^ num & ~ highs;
    highs =  highs^ num & ~lows;
    */
    public int singleNumber(int[] nums) {
        int lows = 0, highs = 0;
        for (int num : nums) {
            lows = lows ^ num & ~highs;
            highs = highs ^ num & ~lows;
        }
        return lows;
    }
}

