package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学B组;

import java.util.*;


/**
已AC
 */
public class B数数 {
    /*
    求区间[2333333,23333333]中含有12个素数因子的整数有多少个
     */
    public static void main(String[] args) {
        //素因子筛
        int count = 0;
        int L = 2333333, R = 23333333;
        int[] s = new int[R + 1];// s[i]表示i的素数因子个数(当遍历到i时,如果s[i]为0表示i是素数)
        List<Integer> prime = new ArrayList<>();
        for (int i = 2; i <= R; i++) {
            if (s[i] == 0) {// i是素数
                s[i] = 1;
                prime.add(i);// 加入素数表
            }
            if (i >= L && s[i] == 12) count++;
            for (int x : prime) {
                if (x * i > R) break;
                s[x * i] = s[i] + 1;// x是x*i的一个素数因子,i的素数因子有s[i]个
            }
        }
        System.out.println(count);
    }


}
