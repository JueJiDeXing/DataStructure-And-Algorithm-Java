package 算法OJ.ICPC.江西2021;

/**
 已AC(这也打表吗?)
 */
public class H炉石游戏 {
    /*
    每一回合, p1抽牌 -> p1操作 -> p2抽牌 -> p2操作
    玩家初始有n点生命值,0点疲劳值, 当生命值<=0时立即输掉比赛
    玩家抽牌: 增加1点疲劳值,抽牌后 生命值-=疲劳值
    玩家操作: 给对手造成k点伤害 or 给自己回复k点血

    给定 n,k, 问谁能赢
     */
    /*
    t = int(input())
    for _ in range(t):
        n, k = map(int, input().split())
        if n == 1 or k + 1 < n:
            print("freesin")
        else:
            print("pllj")
     */

}
