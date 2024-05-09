package 算法OJ.蓝桥杯.真题卷.第13届.省赛.Java大学A组;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 已AC
 */
public class I最优清零方案 {
    /*
    输入正整数N和K, 和一个长度为N的数组A,A[i]非负
    现在需要将数组A的每个元素都变为0
    每次操作有两种选择:
    1. 选择一个正数-1
    2. 选择连续K个正数,都-1
    求最小操作次数
    1<= K,N <= 1e6 , 0<= A[i] <= 1e6
     */


    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     1. 区间操作一定优于单点操作 <br>
     2. 对于区间[l,r],假设最小值为min,则最多可以对[l,r]执行min次操作 <br>
     <p>
     假设[0,i]都已变为0,对于区间[i+1,N),选择[i+1,i+k]执行min次操作,假设min的位置是j <br>
     则[i+1,j-1]长度不足k,只能单点修改,剩余[j+1,N)的子问题 <br>
     <p>
     对于[0,k]这个区间,设[0,k-1]的最小值A[j]=min, 考虑对[0,k-1]操作和对[1,k]操作:
     case1: j!=0
     1. 因为有 A[j] < A[0], 所以[0,k-1]最多操作min次, 而[1,k]由于加入了A[k], A[k]可能比min更小,它的可操作次数就小于等于min次
     2. 如果选择操作[1,k],那么位置0不能操作只能进行单点修改
     综合这两点:[0,k-1]的操作优于[1,k]的操作
     case2: j==0
     设[0,k-1]的第二小值A[t]=sec
     1. 如果先对[0,k-1]操作,可操作min次, 再对[1,k]操作,可操作sec-min次, 总操作次数为sec
     2. 如果先对[1,k]操作,可操作sec次, 0位置不能再操作,单点修改A[0]次, 总操作次数为sec+A[0]
     所以对[0,k-1]操作优于对[1,k]操作
     <p>
     所以,贪心算法: 从前往后枚举区间,进行区间操作, 最大操作次数为min, min累加到ans中 <br>
     每次操作后当前区间不能再进行操作了, 因为当前区间有元素变为0, 此时需要跳转到下一个区间 <br>
     下一个区间为当前区间的最后一个0位置的下一位 <br>
     最后剩余的数字都是需要单点修改的,将其累加到ans中 <br>
     */
public static void main(String[] args) {
    int N = Int(), K = Int();
    int[] A = new int[N];
    for (int i = 0; i < N; i++) A[i] = Int();
    long ans = 0;
    int left = 0;
    while (left + K <= N) {
        //对[left,right]进行区间操作
        int right = left + K - 1;
        //求[left,right]的最小值
        int min = Integer.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            min = Math.min(min, A[i]);
        }
        //最多操作min次
        if (min != 0) {
            ans += min;
            for (int i = left; i <= right; i++) {
                A[i] -= min;//[left,right]区间都减去min TODO 线段树优化, N<1e6, O(n^2)咋能过的
            }
        }
        //[left,min]都无法再进行区间操作,left需要跳转到min+1位置进行区间操作
        //left跳转到idx[min]+1位置,如果有多个min,跳转到最后的一个
        for (int i = left; i <= right; i++) {
            if (A[i] == 0) left = i;
        }
        left++;
    }
    for (int i : A) ans += i;//剩下的单点修改
    System.out.println(ans);
}
}
