package 数据结构.图;

import 算法OJ.蓝桥杯.真题卷.第10届.国赛.Java大学A组.C估计人数;

public class 路径覆盖 {
    /*
    N*M的方格,1表示有人走过,0表示没有人走过
    一个人可以从任意方格开始,每次只能向右或向下,结束方格位置也是任意的
    求至少有多少人在矩阵上走过
     */

    /**
     {@link C估计人数}
     匹配法:
     为每个点x寻找一个匹配点i,i需要在x的可达路径上,且i是最近的点
     如果找到i,让i指向x,并计数1(计数是匹配的点数)
     如果i已有指向j(是另一个人走的),则递归寻找j的匹配点,如果找到j的匹配点k,则x->i->j->k构成路径
     最后剩余的点:
     1.路径终点,他们没有点去匹配
     2.路径的交叉处,因为一个人走过后,另一个人再走时会递归寻找交叉点的匹配,且该匹配未计数,所以再轮到交叉点时,它会少一个匹配
     (如果交叉点只有一条路径,那么交叉点无法匹配两次,计数为2(路径终点1+未匹配的交叉点1);如果交叉点有两条路径,那么交叉点可以匹配两次,计数为2(路径终点2))
     */
    public int minPath(int[][] martix) {
        int n = martix.length, m = martix[0].length;
        //邻接矩阵建图,给节点标号(从1开始)
        int sum = 1;
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (martix[i][j] == 1) map[i][j] = sum++;
            }
        }
        //构建连通网
        isConnect = new boolean[sum][sum];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0 && i + 1 < n && map[i + 1][j] > 0)
                    isConnect[map[i][j]][map[i + 1][j]] = true;
                if (map[i][j] > 0 && j + 1 < m && map[i][j + 1] > 0)
                    isConnect[map[i][j]][map[i][j + 1]] = true;
            }
        }
        for (int i = 1; i < sum; i++) {
            for (int j = 1; j < sum; j++) {
                for (int k = 1; k < sum; k++) {
                    if (isConnect[j][i] && isConnect[i][k] && j != k) isConnect[j][k] = true;
                }
            }
        }

        match = new int[sum];//match[i]=j表示i的匹配点为j, i->j
        int num = 0;
        for (int i = 1; i < sum; i++) {
            if (dfs(i, sum, new boolean[sum]))
                num++;
        }
        return (sum - 1) - num;//点数 - 匹配数 = 未匹配数 = 最少路径数
    }

    static boolean[][] isConnect;
    static int[] match;//匹配点

    /**
     给x寻找一个匹配点i,匹配点需要与x连通(在x的右下方,且x到i有路径)
     如果点i未与其他点匹配,则将i与x匹配,并返回true
     如果点i已与其他点匹配,则递归给它的匹配点j=boy[i]寻找匹配点(x->i->j构成同一个人的路径)
     如果j找不到匹配点了,则为x寻找下一个i,如果遍历完所有的点,返回false
     */
    boolean dfs(int x, int sum, boolean[] vis) {
        for (int i = 1; i < sum; i++) {
            if (!isConnect[x][i] || vis[i]) continue;
            vis[i] = true;
            if (match[i] == 0 || dfs(match[i], sum, vis)) {
                match[i] = x;
                return true;
            }
        }
        return false;
    }
}
