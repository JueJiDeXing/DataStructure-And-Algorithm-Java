package 基础数据结构算法.哈希表;

import java.util.*;

public class _447回旋镖的数量 {
    /*
    给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。
    回旋镖 是由点 (i, j, k) 表示的元组 ，
    其中 i 和 j 之间的距离和 i 和 k 之间的欧式距离相等（需要考虑元组的顺序）。

    返回平面上所有回旋镖的数量。
     */

    /**
     <h1>暴力</h1>
     枚举三点对ijk,统计数量
     */
    public int numberOfBoomerangs1(int[][] points) {
        int n = points.length;
        int ans = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                double dis_ij = dis(points, i, j);
                for (int k = j + 1; k < n; k++) {
                    double dis_ik = dis(points, i, k);
                    double dis_jk = dis(points, j, k);
                    if (dis_ij == dis_ik || dis_ij == dis_jk || dis_ik == dis_jk) {
                        ans += 2;
                    }
                }
            }
        }
        return ans;
    }

    private double dis(int[][] points, int i, int j) {
        int x1 = points[i][0], y1 = points[i][1];
        int x2 = points[j][0], y2 = points[j][1];
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    /**
     <h1>哈希+统计原理</h1>
     对于点i,枚举点j,并计算ij的距离dis<br>
     然后查找与i距离为dis的点数即可<br>
     <p>
     查找依赖于哈希表,在哈希表中存储距离为dis的点数,实现O(1)查找
     <p>
     设总共有n个点与点i距离为dis,所以res+=C{n}{2} <br>
     C{n}{2} = n*(n-1)/2 = 1+2+3+..+n-1 <br>
     所以当遍历到点j时(j与i距离为dis),假设当前有count个点与i距离为dis<br>
     那么res+=count即可,随后count+1更新哈希表
     */
    public int numberOfBoomerangs(int[][] points) {
        int len = points.length;
        if (len < 3) return 0;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            //对于点i,计算所有其他点与i的距离,存入哈希表
            //当遍历到点j的时候,在哈希表中查找ij的距离有多少个点(乘2,因为可以交换位置),即可构成多少对回旋镖
            map.clear();
            for (int j = 0; j < len; j++) {
                if (i == j) continue;
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                int dis = x * x + y * y;
                int count = map.getOrDefault(dis, 0);//当前有多少个点距离与ij距离相等
                res += count * 2;
                map.put(dis, count + 1);
            }
        }
        return res;
    }
}
