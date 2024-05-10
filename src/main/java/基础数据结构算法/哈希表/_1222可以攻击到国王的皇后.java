package 基础数据结构算法.哈希表;

import java.util.*;

public class _1222可以攻击到国王的皇后 {
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        Set<Integer> queenPos = new HashSet<>();
        for (int[] queen : queens) {
            int x = queen[0], y = queen[1];
            queenPos.add(x * 8 + y);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                // 3*3 - 1 = 8 个方向
                if (dx == 0 && dy == 0) {
                    continue;
                }
                //搜索当前方向
                int kx = king[0] + dx, ky = king[1] + dy;
                while (kx >= 0 && kx < 8 && ky >= 0 && ky < 8) {
                    int pos = kx * 8 + ky;
                    if (queenPos.contains(pos)) {//如果遇到皇后则停止
                        //将皇后位置添加到ans
                        List<Integer> posList = new ArrayList<>();
                        posList.add(kx);
                        posList.add(ky);
                        ans.add(posList);
                        break;
                    }//否则继续向这个方向前进
                    kx += dx;
                    ky += dy;
                }
            }
        }
        return ans;
    }
}
