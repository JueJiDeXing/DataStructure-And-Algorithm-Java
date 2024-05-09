package 算法.数学.数论.进制.只出现一次的数字;

public class _260只出现一次的数字 {
    //2个元素只出现1次,其余的元素都出现2次

    /**
     <h1>分组异或</h1>
     设两个数字为a和b<br>
     a^b = xor<br>
     <p>
     xor的二进制为   xxx10...0<br>
     对于xor中为1的位,假设其为第k位,可以说明a的第k位与b的第k位不同,一个为0,一个为1<br>
     以该位为标志,该位为1的在同一组异或,该位为0的在同一组异或<br>
     */
    public int[] singleNumber(int[] nums) {
        int[] res = new int[2];
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        int k = 0;
        while (((xor >> k) & 1) == 0) {//xor右移k位后最后一位为1(从后向前找1)
            k++;
        }
        for (int num : nums) {
            if (((num >> k) & 1) == 0) {//num在第k位为0的和为1的分组异或
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }
        return res;

    }
}
