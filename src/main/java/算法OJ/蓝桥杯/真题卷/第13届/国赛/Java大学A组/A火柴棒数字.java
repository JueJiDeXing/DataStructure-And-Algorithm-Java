package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学A组;

/**
 已AC
 */
public class A火柴棒数字 {
    /*
    用火柴拼0~9
    数字对应火柴消耗如下:
    数字 0 1 2 3 4 5 6 7 8 9
    消耗 6 2 5 5 4 5 6 3 7 6
    问最多使用300根火柴,且每个数字最多拼10个,能拼出的最大整数是多少
     */
    /*
    数字按消耗从低到高排序,同消耗的大数字优先,分别是
    数字 1 7 4 5 3 2 9 6 0 8
    消耗 2 3 4 5 5 5 6 6 6 7
    2+3+4+5+5+5+6=30
    所以正好1,7,4,5,3,2,9各拼10个,然后按从大到小排列就是
    9999999999777777777755555555554444444444333333333322222222221111111111
     */
}
