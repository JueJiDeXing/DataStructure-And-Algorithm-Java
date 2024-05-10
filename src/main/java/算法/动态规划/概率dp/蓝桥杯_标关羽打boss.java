package 算法.动态规划.概率dp;

import 算法OJ.蓝桥杯.题单.动态规划基础30题.标关羽打boss;

/**
 {@link 标关羽打boss}
 */
public class 蓝桥杯_标关羽打boss {
   /*
    有a张杀,每张可造成1点伤害
    boss有b点血,如果受到1点伤害则有50%的概率损失1张手牌
    求击杀boss的概率
     */
    /*
    (b,a)  --50%-->  (b-1,a-1)
     --50%--> (b-1,a-2)
     dp[i][j]:i点血,j张牌的击杀概率
     dp[i][j] = ( dp[i-1][j-1]  + dp[i-1][j-2] ) * inv2
     dp[j] = ( dp[j-1]  + dp[j-2] ) * inv2
     */
}
