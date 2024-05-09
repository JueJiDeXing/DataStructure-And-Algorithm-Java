package 算法.字符串;

public class _1461检查一个字符串是否包含所有长度为K的二进制子串 {
    /*
    给你一个二进制字符串 s 和一个整数 k 。
    如果所有长度为 k 的二进制字符串都是 s 的子串，请返回 true ，否则请返回 false 。
     */

    /**
     <h1>滑动窗口</h1>
     长度为k的子串: [0...0 , 1...1] 都要在s中出现
     那么有 need = 1 << k 个要求
     创建一个长为k的滑动窗口,记录窗口内的子串
     如果是第一次记录,则满足一条要求,need-1
     如果need减为0则返回true

     @param s 由0和1组成的二进制字符串
     @param k 长度为k的二进制串
     @return 所有长度为 k 的二进制字符串都是 s 的子串
     */
    public boolean hasAllCodes(String s, int k) {
        char[] str = s.toCharArray();
        int need = 1 << k;
        int allOne = need - 1;//使用allOne进行按位与运算,去除高位溢出的1
        boolean[] added = new boolean[need];//使用位图记录是否有对应二进制字符串
        int hashVal = 0;//滑动窗口
        for (int i = 0; i < str.length; i++) {
            //去除高位,加入低位
            int curr = str[i] - '0';
            hashVal = (hashVal << 1) & allOne | curr;// allOne去除 hashVal << 1最前的溢出位,curr添加当前位
            //对得到的子串检验
            if (i >= k - 1 && !added[hashVal]) {
                //对应二进制字符串是第一次添加
                added[hashVal] = true;
                if (--need == 0) {//满足要求
                    return true;
                }
            }
        }
        return false;
    }
}
