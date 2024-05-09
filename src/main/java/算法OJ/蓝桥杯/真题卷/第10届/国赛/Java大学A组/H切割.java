package 算法OJ.蓝桥杯.真题卷.第10届.国赛.Java大学A组;

import java.util.*;
/*
枚举直线,求直线穿过的格子,哈希表去重
枚举直线:标准方程ax+by+c=0,枚举abc
直线是否穿过某个格子:
        将四个矩形的四个点代入直线方程
        case1:四个点都在直线下或下方,没有穿过
        case2:有一个点恰好落在直线上,其余点同case1,没有穿过
        case3:有两个点(一条边)恰好落在直线上,其余点同case1,没有穿过
        case4:一部分在上方,一部分在下方,down和up均不为0,穿过
        -> 对直线上方和下方进行计数,落在直线上的不管,只要上下都有即为穿过
 */

/**
 已AC
 */
public class H切割 {
    /*
    4*4的方格矩阵,画一条直线, 直线穿过的方格组成集合,集合有多少种可能
    直线穿过方格:直线将方格分割为两个面积不为0的部分
     */
    final static int range = 50;//range大于等于27时答案都正确,200运行就已经比较慢了

    public static void main(String[] args) {
        Set<Integer> s = new HashSet<>();
        // 枚举直线标准方程 ax + by + c = 0的三个参数
        for (int a = -range; a <= range; a++) {
            for (int b = -range; b <= range; b++) {
                for (int c = -range; c <= range; c++) {
                    s.add(getStatus(a, b, c));// 记录当前点集
                }
            }
        }
        System.out.println(s.size());//267
    }

    private static int getStatus(int a, int b, int c) {
        int status = 0;//状态压缩:  status[i]=1: 格子i被穿过;  status[i]=0: 格子i没有被穿过;
        // 遍历16个格子
        for (int x = 1; x <= 4; x++) {
            for (int y = 1; y <= 4; y++) {
                status <<= 1; // 将上色的点集压缩成二进制的整数
                if (isThrough(a, b, c, x, y)) status |= 1;
            }
        }
        return status;
    }

    private static boolean isThrough(int a, int b, int c, int x, int y) {
        /*
        将四个矩形的四个点代入直线方程,判断点是在直线下面还是上面(刚好落在直线上不计数)
        case1:四个点都在直线下或下方,没有穿过
        case2:有一个点恰好落在直线上,其余点同case1,没有穿过
        case3:有两个点(一条边)恰好落在直线上,其余点同case1,没有穿过
        case4:一部分在上方,一部分在下方,down和up均不为0,穿过
        -> 对直线上方和下方进行计数,落在直线上的不管,只要上下都有即为穿过
        */
        int up = 0, down = 0;// 记录上下个数
        int pos;
        pos = a * x + b * y + c;
        if (pos > 0) up++;
        if (pos < 0) down++;

        pos = a * (x - 1) + b * (y - 1) + c;
        if (pos > 0) up++;
        if (pos < 0) down++;

        pos = a * x + b * (y - 1) + c;
        if (pos > 0) up++;
        if (pos < 0) down++;

        pos = a * (x - 1) + b * y + c;
        if (pos > 0) up++;
        if (pos < 0) down++;
        return up > 0 && down > 0;// 有正有负说明当前格子被直线穿过
    }
}

