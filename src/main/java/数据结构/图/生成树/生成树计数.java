package 数据结构.图.生成树;

import java.util.*;

/**
 不会
 */
public class 生成树计数 {
    static int M = 10007;
    static int[] inv = new int[M];

    static void init() {//求逆元
        inv[1] = 1;
        for (int i = 2; i < M; i++)
            inv[i] = (M - M / i) * inv[M % i] % M;
    }

    public static void main(String[] args) {
        init();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        //存节点
        int[][] point = new int[n][m];
        int s = 0;//节点编号
        for (int i = 0; i < n; i++) {
            char[] ss = sc.next().toCharArray();
            for (int j = 0; j < m; j++) {
                if (ss[j] == 'E') {//E表示存在节点
                    point[i][j] = s++;
                } else {
                    point[i][j] = -1;
                }
            }
        }
        //建图
        int p = n * m;
        int[][] graph = new int[s][s];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int id1 = point[i][j];
                if (id1 == -1) continue;
                //节点与相邻的节点相连
                if (i + 1 < n && point[i + 1][j] != -1) {
                    int id2 = point[i + 1][j];
                    graph[id1][id2] = 1;
                    graph[id2][id1] = 1;
                }
                if (j + 1 < m && point[i][j + 1] != -1) {
                    int id2 = point[i][j + 1];
                    graph[id1][id2] = 1;
                    graph[id2][id1] = 1;
                }
            }
        }
        System.out.println(count(graph));
    }

    static int count(int[][] graph) {
        int n = graph.length;
        int[][] matrix = getMatrix(graph);
        return det(matrix, n - 1);
    }

    static int det(int[][] c, int n) {//求矩阵c的n阶主子式的绝对值
        int i, j, k, ans = 1;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                c[i][j] = (c[i][j] % M + M) % M;
            }
        }
        for (i = 0; i != n; ++i) {
            for (j = i; j != n; ++j) if (c[i][j] == 0) break;// 找出第i行起第i列不为0的行
            if (i != j) swap(c, i, j);
            ans = ans * c[i][i] % M;
            for (j = i + 1; j != n; ++j) {// 第j行第i列变为0
                int d = c[j][i] * inv[c[i][i]] % M;// c[j][i]-d*c[i][i] = 0
                for (k = i; k != n; ++k) {// (c[j][i]->0同时其它c[j][k]减去d*c[i][k], k=i+1 也行,因为k=i没必要)
                    c[j][k] = (c[j][k] - d * c[i][k] % M + M) % M;
                }
            }
        }
        return ans;
    }

    static void swap(int[][] c, int i, int j) {
        int[] t = c[i];
        c[i] = c[j];
        c[j] = t;
    }

    static int[][] getMatrix(int[][] graph) {
        int n = graph.length;
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = -graph[i][j];
            }
        }
        int[] colSum = new int[n];
        for (int i = 0; i < n; i++) {
            for (int[] line : graph) {
                colSum[i] += line[i];
            }
        }

        for (int i = 0; i < n; i++) {
            matrix[i][i] = colSum[i];
        }
        return matrix;
    }
}
