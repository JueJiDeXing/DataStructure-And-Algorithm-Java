package 算法OJ.洛谷.普及up_提高;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class P1016旅行家的预算 {
    /*
    从A到B距离为D,油箱容量C升,每升油行驶d0,出发时油箱为空,出发点每升油P元
    A到B中间有N个加油站,加油站i距离A为D[i],每升油价格为Pi(i∈[1,N])
    求到B的最小花费,如果不能到B,则输出No Solution
     */

    /**
     dp[i][j]:到达第i个加油站并剩余j升油的最小预算
     从加油站i-1到加油站i, 距离为d=D[i]-D[i-1], 需要油量need=d/d0
     假设加油站i-1剩余k升,加油站i剩余j升,在加油站i-1需要充t=max(0,need-k+j)
     如果t+k>C:无法到达加油站i
     如果t+k<=C:到达加油站i, 花费为P[i]*t
     dp[i][j] = min{ dp[i-1][k] + P[i]*max{0,d/d0-k+j} }
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));


    static double Double() {
        try {
            st.nextToken();
        } catch (Exception ignored) {
        }
        return st.nval;
    }

    static double[] D = new double[7];//D[i]:加油站i到A的距离
    static double[] P = new double[7];//P[i]:加油站i的油价

    public static void main(String[] args) {
        double all = Double(), C = Double(), d = Double(), p = Double();
        int N = (int) Double();
        double x = C * d;//满油量的最大行驶距离
        P[0] = p;
        for (int i = 1; i <= N; i++) {
            D[i] = Double();
            if (D[i] - D[i - 1] > x) {//某段路加满油都过不去
                System.out.println("No Solution");
                return;
            }
            P[i] = Double();
        }
        if (all - D[N] > x) {
            System.out.println("No Solution");
            return;
        }
        double ans = 0;//最小花费

        double curr = 0; //curr:当前已走距离
        double l = 0;//目前有的油量

        double minPrice = 1000;//当前加油站后面的最便宜的加油站价格
        int minIdx = 0;// 最便宜的加油站下标

        while (all >= curr) {
            //寻找后面的能走到的,最便宜的加油站
            for (int i = minIdx + 1; i <= N; i++) {
                if (D[i] - curr > x) break;//走不到这个加油站
                if (P[i] > minPrice) {//价格更便宜
                    minPrice = P[i];
                    minIdx = i;
                }
            }
            if (minPrice <= p) {//可以去后面能走到的,最便宜的加油站
                double add = (D[minIdx] - curr) / d - l;//还需要的油量
                ans += add * p;
                l = 0;//不剩油
            } else if (all - curr > x) {//能开到的加油站都比当前加油站贵
                //当前加油站是最便宜的,把油箱加满
                ans += (C - l) * p;
                l = C - (D[minIdx] - curr) / d;//开到最便宜的一个加油站(除去当前加油站)
            } else {//minPrice>p:中间没有比当前便宜的加油站;  all-curr<=x:能直接开到B;
                double add = (all - curr) / d - l;//加到足够到达B即可
                ans += add * p;
                break;
            }
            curr = D[minIdx];//到达minIdx加油站
            p = minPrice;
            minPrice = 1000;   //将minPrice重置为MAX_VALUE
        }
        System.out.printf("%.2f\n", ans);
    }


}
