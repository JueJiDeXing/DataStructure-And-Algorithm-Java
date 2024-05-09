package 算法OJ.蓝桥杯.真题卷.第11届.国赛.Java大学A组;
/**
 已AC
 */
public class C玩具蛇 {
    /*
    编号 x 1~16 x 0~15 的玩具蛇块,放入4*4的盒子里,求不同的方案数
     */
    public static void main(String[] args) {
        int ans = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                boolean[][] vis = new boolean[4][4];
                vis[i][j] = true;
                ans += dfs(1, i, j, vis);//枚举0号蛇块位置
            }
        }
        System.out.println(ans);//552
    }

    static int[][] direction = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     @param k   已经放了多少块
     @param i,j 上一块放的位置
     @param vis 哪些位置已经放过了
     */
    private static int dfs(int k, int i, int j, boolean[][] vis) {
        if (k == 16) return 1;

        int ans = 0;
        for (int[] d : direction) {
            int ni = i + d[0], nj = j + d[1];
            if (!isValid(ni, nj) || vis[ni][nj]) continue;
            vis[ni][nj] = true;
            ans += dfs(k + 1, ni, nj, vis);
            vis[ni][nj] = false;
        }
        return ans;
    }

    static boolean isValid(int i, int j) {
        return 0 <= i && i < 4 && 0 <= j && j < 4;
    }
}
