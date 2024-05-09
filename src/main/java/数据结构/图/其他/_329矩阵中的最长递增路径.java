package 数据结构.图.其他;

import java.util.*;

public class _329矩阵中的最长递增路径 {
    /*
    给出一个m x n的整数矩阵matrix，找出其中最长递增路径的长度。
     */
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int node = n * m;
        List<Integer>[] graph = new List[node];
        Arrays.setAll(graph, k -> new ArrayList<>());
        int[] degree = new int[node];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int curr = matrix[i][j];
                int idx = toIdx(i, j, m);
                if (j != m - 1) {
                    int next = matrix[i][j + 1];
                    int nextIdx = toIdx(i, j + 1, m);
                    if (next > curr) {
                        graph[idx].add(nextIdx);
                        degree[nextIdx]++;
                    } else if (next < curr) {
                        graph[nextIdx].add(idx);
                        degree[idx]++;
                    }
                }
                if (i != n - 1) {
                    int next = matrix[i + 1][j];
                    int nextIdx = toIdx(i + 1, j, m);
                    if (next > curr) {
                        graph[idx].add(nextIdx);
                        degree[nextIdx]++;
                    } else if (next < curr) {
                        graph[nextIdx].add(idx);
                        degree[idx]++;
                    }
                }
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < node; i++) {
            if (degree[i] == 0) queue.offer(i);
        }
        int maxLen = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                for (int next : graph[curr]) {
                    degree[next]--;
                    if (degree[next] == 0) queue.offer(next);
                }
            }
            maxLen++;
        }
        return maxLen;
    }

    int toIdx(int i, int j, int m) {
        return i * m + j;
    }
}
