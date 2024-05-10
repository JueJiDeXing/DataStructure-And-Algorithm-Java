package 算法.动态规划.其他;

public class 旅行商问题 {
    /*
    无向有权完全图,从指定起点开始,经过所有的点(只经过一次)回到起点,求走的最短距离
     */
    public static void main(String[] args) {
        //邻接矩阵
        //graph[i][j]表示从i到j的距离
        int[][] graph = new int[][]{
                {0, 1, 2, 3},
                {1, 0, 6, 4},
                {2, 6, 0, 5},
                {3, 4, 5, 0}
        };
        System.out.println(tsp(graph));
    }

    /**
     <h1>动态规划</h1>
     dp(出发城市,剩余城市集合) 表示 从出发城市开始, 走完剩余城市的最小距离 <br>
     设i为当前起点, j为剩余城市集合, k为j中第k个城市 <br>
     dp(i,j) = MIN( g[i][k] + dp(k, j-k) ) <br>
     当j为空时, dp(i,j) = g[last_k][0] <br>
     j的表示使用二进制位存储
     */
    public static int tsp(int[][] graph) {
        int m = graph.length;//点的个数
        int n = 1 << m;//点集的所有子集个数
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = graph[i][0];//j为空集时,距离为g[last_k][0]=g[i][0]
        }

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < m; i++) {
                dp[i][j] = Integer.MAX_VALUE / 2;//初始化为一个较大值
                if (contains(j, i)) {//剩余城市不能包含出发城市
                    continue;
                }
                for (int k = 0; k < m; k++) {
                    if (contains(j, k)) {//在包含当前城市的集合里搜索
                        dp[i][j] = Math.min(dp[i][j], graph[i][k] + dp[k][exclude(j, k)]);
                    }
                }
            }
        }

        return dp[0][n - 1];//(出发城市=0,剩余城市=n-1)
    }

    private static int exclude(int set, int k) {
        return set ^ (1 << (k - 1));//将set的第k位置为0
    }

    private static boolean contains(int set, int n) {
        return (set >> (n - 1) & 1) == 1;//右移n-1位看末位(第n位)是否为1,1表示存在
    }
    /*
    示例
     dp(0,1|2|3) --> g[0][1] + dp(1,2|3)
                                    g[1][2] + dp(2,3)
                                                g[2][3] + d(3,null)
                                                            g[3][0]
                                    g[1][3] + dp(3,2)
                     g[0][2] + dp(2,1|3)
                     g[0][3] + dp(3,1|2)
     */
}
