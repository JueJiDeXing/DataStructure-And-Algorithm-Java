package 算法OJ.牛客.小白月赛.小白月赛94;

import java.io.*;
 /**
  已AC
  */
public class E_F_与背包 {
    /*
    n个物品,第i个物品价值v[i],重量w[i]
    背包容量k
    求最大价值

    另外: 总价值定义为价值的与, 总重量定义为重量的与
    (选的东西越多,价值和体积越小)
     */

    public static void main(String[] args) throws Exception {
        int n = I(), k = I();
        int[] v = new int[n], w = new int[n];//价值,重量
        for (int i = 0; i < n; i++) {
            w[i] = I();
            v[i] = I();
        }
        System.out.println(solve2(n, v, w, k));
    }

    /**
     n,k,w,v ∈ [0,2e3] 
     枚举答案, 将可选的价值都选上, 计算出重量, 如果满足容量条件, 则找到一个可行解

     @param v 价值
     @param w 重量|体积
     */
    private static int solve1(int n, int[] v, int[] w, int k) {
        for (int guess = 2000; guess > 0; guess--) {//枚举答案,总价值
            //计算重量是否超标
            int weight = (1 << 20) - 1;
            for (int i = 0; i < n; i++) {
                if ((v[i] & guess) == guess) {// &{v[i]} = guess  =>  (guess & v[i]) = guess
                    weight &= w[i];
                }
            }
            if (weight <= k) return guess;
        }
        return 0; //啥也选不了, 空包
    }

    /**
     n,k,w,v ∈ [0,1e9]
     从高到低, 试填法确定每一位
     */
    private static int solve2(int n, int[] v, int[] w, int k) {
        int ans = 0;
        for (int bit = 30; bit >= 0; bit--) {//从高位枚举
            int guess = ans | (1 << bit);//尝试在该位填1
            int weight = (1 << 30) - 1;// 1e9 < 2^30
            for (int i = 0; i < n; i++) {
                if ((guess & v[i]) == guess) {
                    weight &= w[i];
                }
            }
            if (weight <= k) ans = guess;//该位可以为1
        }
        return ans;
    }

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

}

