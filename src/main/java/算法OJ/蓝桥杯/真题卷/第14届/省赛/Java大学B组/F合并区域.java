package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学B组;

import java.util.*;

/**
 已AC
 */
public class F合并区域 {
    static int[][] blockA, blockB;
    static boolean[][] isVisited;//bfs访问标记
    static int Map;
    static int offset, pos;

    public static void main(String[] args) {
        //开3*3范围的区块大小,一个区块放中间,一个区块在周围滑动/旋转
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        blockA = new int[n][n];
        blockB = new int[n][n];
        Map = 3 * n;
        isVisited = new boolean[Map][Map];
        for (int i = 0; i < n; i++) {//录入数据
            for (int j = 0; j < n; j++) {
                blockA[i][j] = scan.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blockB[i][j] = scan.nextInt();
            }
        }
        int ans = 0;
        for (int x = 0; x < 4; x++) {//每次转一圈
            int[][] newB = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    newB[i][j] = blockB[j][n - 1 - i];
                }
            }
            blockB = newB;
            for (pos = 0; pos < 4; pos++) {//换个位置
                for (offset = 0; offset <= Map - blockB.length; offset++) {
                    for (boolean[] p : isVisited) Arrays.fill(p, false);//初始化
                    for (int i = 0; i < Map; i++) {//查找最大的陆地块
                        for (int j = 0; j < Map; j++) {
                            ans = Math.max(ans, bfs(i, j));
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }

    /**
     寻找相连的块

     @param i
     @param j
     @return 当前已经找到的块数
     */
    static int bfs(int i, int j) {//用于寻找相连的最大数量
        if (isVisited[i][j] || !DNS(i, j)) {//已访问,或(i,j)不是土地
            isVisited[i][j] = true;
            return 0;
        }
        isVisited[i][j] = true;//更改记录
        int s = 1;
        s += bfs(i, (j + Map - 1) % Map);
        s += bfs(i, (j + 1) % Map);
        s += bfs((i + 1) % Map, j);
        s += bfs((i + Map - 1) % Map, j);
        return s;
    }

    /**
     DNS地址转换
     我这里用于合成两个土地块后的地址计算

     @return point[i][j]是否为土地
     */
    static boolean DNS(int i, int j) {
        //(i,j)是否落在块1
        int c = Map / 3;//块1在中间
        if (isValidIndex(i - c, j - c, blockA.length)) return blockA[i - c][j - c] == 1;//如果在块1 区域内，直接判定
        //不在块1,检查是否落在块2
        int newX = Map, newY = Map;//对应的块2中的地址
        if (pos == 0) {
            newX = i;
            newY = j - offset;
        } else if (pos == 1) {
            newX = i - offset;
            newY = j - 2 * c;
        } else if (pos == 2) {
            newX = i - 2 * c;
            newY = j - offset;
        } else if (pos == 3) {
            newX = i - offset;
            newY = j;
        }
        if (isValidIndex(newY, newX, blockB.length)) return blockB[newX][newY] == 1;

        return false;//不是土地
    }

    private static boolean isValidIndex(int i, int j, int len) {
        return i >= 0 && i < len && j >= 0 && j < len;
    }
}
