package 算法.数学.数论.进制.只出现一次的数字;

public class _136只出现一次的数字 {
    //1个元素只出现1次,其余的元素都出现2次

    /**
     <h1>异或</h1>
     一个数异或自身为0
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}
