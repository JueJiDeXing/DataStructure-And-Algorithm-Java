package 算法OJ.蓝桥杯.真题卷.第7届.国赛.Java大学A组;

import java.util.*;
/**
 已AC
 */
public class D机器人塔 {
    /*
    A只能站在AA或BB上,B只能站在AB或BA上
    给定A和B的数量,求站的方案数
     */

    /**
     A = AA | BB , B = AB | BA
     将B编码为1 , A编码为0
     A: 0^0 = 0 , 1^1=0
     B: 0^1 = 1 , 1^0=1
     那么 上层的状态 可以由 下层的状态 通过 异或 运算得来
     <p>
     枚举最底层的01状态 ( 设最底层为第level层, 最上层为第一层)
     第k层第i个数 =  第k+1层第i个数 ^ 第k+1层第i+1个数
     第k层状态 = 去除最高位{第k+1层状态 ^ 第k+1层状态右移一位}
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();
        // sum = A + B
        // (1+k)*k/2 = sum -> k(k+1) = 2sum -> k = floor( sqrt(2sum) )
        int level = (int) Math.sqrt((A + B) * 2);
        int lastLevel = (1 << level);//最后一层的01状态数

        int ans = 0;
        for (int status = 0; status < lastLevel; status++) {//枚举最后一层的状态
            if (check(status, level)) {//检查最后一层状态是否满足要求
                ans++;
            }
        }
        System.out.println(ans);
    }

    static int A, B;

    static boolean check(int status, int level) {
        int num_0 = 0, num_1 = 0;//统计0和1的个数
        for (int i = level; i >= 1; i--) {
            num_1 += Integer.bitCount(status);
            num_0 += i - Integer.bitCount(status);

            status ^= status >> 1;//下面和右下的异或
            status &= (1 << (i - 1)) - 1;//去除最后一位, 第i层有i个数
        }
        return num_0 == A && num_1 == B;//A编码为0,B编码为1
    }
}
