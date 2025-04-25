package 算法.数学.数论.进制;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class 不使用比较计算最大值 {
    /*
    不使用任何比较运算,求a和b中的较大值
    a>b?a:b
     */

    /**
     <h1>abs</h1>
     若 a > b, 则 abs(a-b)=a-b
     若 a < b, 则 abs(a-b)=b-a
     可得: abs(a-b) + a + b = max(a,b) * 2
     */
    public int maximum1(int a, int b) {
        long aa = a, bb = b;
        return (int) ((aa + bb + absolute(aa - bb)) / 2);

    }

    /**
     重写绝对值函数, a>0?a:-a
     (1) 方法1
     将a的符号位映射到1和-1
     flag=0时表示正数,系数为1
     flag=1时表示正数,系数为-1
     则系数为1-2flag
     即abs(a) = a * (1 - 2flag)

     (2) 方法2
     若 a < 0, 则abs(a) = -a = ~a - 1 = a^1 - 1
     若 a > 0, a^0-0 = a不变
     即 abs(a) = (a^flag) - flag

     (3) 方法3
     abs(a) = sqrt(a*a), 用平方再开方去掉与0的比较运算
     */
    long absolute(long a) {
        int flag = (int) (a >> 63); // 正数flag = 0，负数flag = 1
        return (flag ^ a) - flag; // 任何数与0异或值不变，任何数与1异或等价于按位取反
    }

    /**
     <h1>符号位</h1>
     若 a > b, 则 a-b 符号位为 0
     若 a < b, 则 a-b 符号位为 1
     最大值可表示为 a * (flag^1) + b * flag

     a > b 时, a系数为1,b系数为0
     a < b 时, a系数为0,b系数为1
     */
    public int maximum2(int a, int b) {
        long aa = a, bb = b;
        long d = aa - bb;
        int flag = (int) (d >>> 63); // a>b时符号位为0,a<b时符号位为1
        return a * (flag ^ 1) + b * flag;
    }


}
