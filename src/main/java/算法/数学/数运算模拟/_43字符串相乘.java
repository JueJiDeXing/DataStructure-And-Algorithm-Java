package 算法.数学.数运算模拟;

//已测试:src.java.数据结构与算法.算法.字符串.TestMultiplyString
public class _43字符串相乘 {

    /* 189*996 -> 6位
    字符索引      [ 0, 1 ,2 ]
    num1[i]与num2[j]相乘结果的低位对应res[i+j+1],高位对应res[i+j]
                  1  8  9    第一次外循环,取"9"(i=2)
                x 9  9  6    内循环遍历"9 9 6"(j:2->0)
            -----------------
                     5  4   ->
                  8  1      -> 对于计算出的81,将其与上次的高位也一并相加后取余,如果高位溢出十位则暂存不进位,下次循环时再进位
            -----------------
                  8  6  4   -> 81+5=86 86%10=6 0+86/10=8
               8  1         -> 81+8=89 89%10=9 0+89/10=8
            -----------------
               8  9  6  4   第二次外循环,取"8"(i=1)
                  4  8      ->48+6=54 54%10=4 9+54/10=14
            -----------------
               8  14 4  4
               4   4        ->44+14=58 58%10=8 8+58/10=13
            -----------------
              13  8  4  4
               .....
    res[ 0, 1, 2, 3, 4, 5 ]
    */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";//有0返回0
        int len1 = num1.length(), len2 = num2.length();
        int[] res = new int[len1 + len2];//两数相乘结果的最大位数为len1+len2
        for (int i = len1 - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = len2 - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int n = n1 * n2 + res[i + j + 1];//res[i+j+1]为这次的低位,上次的高位,加上次的高位
                res[i + j + 1] = n % 10;//低位为结果余10
                res[i + j] += n / 10;//高位为结果除以10再加上这次的低位,如果此时溢出先暂存,下轮循环处理
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) continue;//去除首位的0
            sb.append(res[i]);
        }
        return sb.toString();
    }

}
