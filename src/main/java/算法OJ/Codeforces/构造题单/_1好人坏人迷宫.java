package 算法OJ.Codeforces.构造题单;

import java.util.*;

/**
 已AC
 */
public class _1好人坏人迷宫 {
    /*
    n*m的迷宫
    .表示空 #表示墙壁
    G表示好人 B表示坏人
    (n,m)为迷宫出口
    除了墙壁格无法到达,G和B可以随意向相邻格移动
    现在可以让空格子变为墙壁,需要使G都能到达出口,B都不能到达出口
    如果可以则输入Yes,否则输出No

    t组询问,每次给出n和m以及迷宫,输出Yes或No
    t<=100, n,m<=50
    */
    static Scanner sc = new Scanner(System.in);

    /**
     <h1>贪心+bfs</h1>
     如果有某个B和G相邻,那么这个B一定是堵不住的
     因为B是可以走到G的位置上的,如果B堵住了,那么代表G也出不去

     如果没有B和G相邻,B构成的各连通块的周围都可以变为墙,这给G留下了最多的路径可能性,是最优的
     最优的堵B策略是,将B的周围的空格都变为墙
     然后从出口bfs,将能到达的点置为true
     然后检查G是否都能出去即可
     */
    public static void main(String[] args) {
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            System.out.println(solve() ? "Yes" : "No");
        }
    }

    static int[][] map = new int[60][60];
    static int[][] direction = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    static int n, m;
    static List<int[]> posG = new ArrayList<>(), posB = new ArrayList<>();

    private static boolean solve() {
        n = sc.nextInt();
        m = sc.nextInt();
        posB.clear();
        posG.clear();
        for (int i = 0; i < n; i++) {
            char[] s = sc.next().toCharArray();
            for (int j = 0; j < m; j++) {
                if (s[j] == '#') {
                    map[i][j] = 1;
                } else if (s[j] == 'B') {
                    posB.add(new int[]{i, j});
                    map[i][j] = 2;
                } else if (s[j] == 'G') {
                    posG.add(new int[]{i, j});
                    map[i][j] = 3;
                } else {
                    map[i][j] = 0;
                }
            }
        }
        if (map[n - 1][m - 1] == 2) return false;//B在出口
        for (int[] pos : posB) {
            int i = pos[0], j = pos[1];
            for (int[] d : direction) {
                int ni = i + d[0], nj = j + d[1];
                if (!isValid(ni, nj)) continue;
                if (map[ni][nj] == 3) return false;//BG相邻,一定堵不住B,堵BG肯定也出不去了
                if (map[ni][nj] == 0) map[ni][nj] = 1;//堵B
            }
        }
        if (map[n - 1][m - 1] == 1) return posG.isEmpty();//有G则G出不去,无G则B出不去满足要求

        for (boolean[] c : can) Arrays.fill(c, false);
        bfs();//求能到达的位置
        for (int[] pos : posG) {
            if (!can[pos[0]][pos[1]]) return false;//G无法到达
        }
        return true;
    }

    static boolean[][] can = new boolean[60][60];
    static boolean[][] isVisited = new boolean[60][60];
    static Queue<int[]> q = new LinkedList<>();

    static void bfs() {
        if (map[n - 1][m - 1] == 1) return;
        for (boolean[] isV : isVisited) Arrays.fill(isV, false);
        q.clear();
        q.offer(new int[]{n - 1, m - 1});
        isVisited[n - 1][m - 1] = true;
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int i = pos[0], j = pos[1];
            can[i][j] = true;
            for (int[] d : direction) {
                int ni = i + d[0], nj = j + d[1];
                if (!isValid(ni, nj)) continue;
                if (map[ni][nj] == 1) continue;
                if (isVisited[ni][nj]) continue;
                isVisited[ni][nj] = true;
                q.offer(new int[]{ni, nj});
            }
        }
    }
}
