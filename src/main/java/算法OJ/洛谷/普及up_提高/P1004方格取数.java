package 算法OJ.洛谷.普及up_提高;

import java.io.*;
import java.util.Arrays;

/**
 已AC
 */
public class P1004方格取数 {
    /*
    N*N的方格,其中某些格子放了数(>0),其余格子都是0
    问,从左上角A走到右下角B,(只能向右或向下),走两次,最多能拿多少数(第一次拿的,第二次就没有了)
     */

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static int N;
    static int[][] mat = new int[10][10];//N<=9

    /**
     在每个格子都有2种情况:向右或向下
     由于搜索2次,那么每个格子就是2*2=4种情况
     对四种情况进行搜索即可
     令dfs(x1,y1,x2,y2)表示第一次目前走到了(x1,y1),第二次目前走到了(x2,y2),最后能得到的最大数值
     搜索的四种情况是:
     dfs(x1+1,y1,x2+1,y2), dfs(x1+1,y1,x2,y2+1), dfs(x1,y1+1,x2+1,y2), dfs(x1,y1+1,x2,y2+1)
     由于一个格子的数只能取到一次,那么如果2次搜索的位置在一起了,需要减去一份值
     */
    public static void main(String[] args) {
        N = Int();
        //将记录数组初始化成-1，因为可能出现取的数为0的情况，如果直接判断f[x][y][x2][y2]!=0（见dfs第一行）
        //可能出现死循环而导致超时，细节问题
        for (int a = 0; a <= N; a++) {
            for (int b = 0; b <= N; b++) {
                for (int c = 0; c <= N; c++) {
                    Arrays.fill(memo[a][b][c], -1);
                }
            }
        }
        while (true) {//读入
            int t1 = Int(), t2 = Int(), t3 = Int();
            if (t1 == 0 && t2 == 0 && t3 == 0) break;
            mat[t1][t2] = t3;
        }
        int r = dfs(1, 1, 1, 1) + mat[1][1];//输出，因为dfs中没有考虑第一格，即s[1][1]，所以最后要加一下
        System.out.println(r);
    }

    static int[][][][] memo = new int[11][11][11][11];

    /**
     当第一种方案走到x,y,第二种方案走到x2,y2时 到终点可以取得的最大数
     */
    static int dfs(int x, int y, int x2, int y2) {
        if (memo[x][y][x2][y2] != -1) return memo[x][y][x2][y2];//记忆化
        if (x == N && y == N && x2 == N && y2 == N) return 0;//如果两种方案都走到了终点，返回结束
        int ans = 0;
        if (x < N && x2 < N) {// case 1: 都往下走
            int res = dfs(x + 1, y, x2 + 1, y2) + mat[x + 1][y] + mat[x2 + 1][y2];
            int repeat = mat[x + 1][y] * (x + 1 == x2 + 1 && y == y2 ? 1 : 0);//如果走到了同一格,需要减去重复值
            ans = Math.max(ans, res - repeat);
        }
        if (x < N && y2 < N) {//case 2: 第一种向下,第二种向右
            int res = dfs(x + 1, y, x2, y2 + 1) + mat[x + 1][y] + mat[x2][y2 + 1];
            int repeat = mat[x + 1][y] * (x + 1 == x2 && y == y2 + 1 ? 1 : 0);
            ans = Math.max(ans, res - repeat);
        }
        if (y < N && x2 < N) {// case 3: 第一种向右,第二种向下
            int res = dfs(x, y + 1, x2 + 1, y2) + mat[x][y + 1] + mat[x2 + 1][y2];
            int repeat = mat[x][y + 1] * (x == x2 + 1 && y + 1 == y2 ? 1 : 0);
            ans = Math.max(ans, res - repeat);
        }

        if (y < N && y2 < N) { // case 4: 都向右走
            int res = dfs(x, y + 1, x2, y2 + 1) + mat[x][y + 1] + mat[x2][y2 + 1];
            int repeat = mat[x][y + 1] * (x == x2 && y + 1 == y2 + 1 ? 1 : 0);
            ans = Math.max(ans, res - repeat);
        }
        return memo[x][y][x2][y2] = ans;//返回最大值
    }
}
