package 算法.数学.计算几何.扫描线;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 第 88 场周赛 Q4
 难度分:2236
 */
public class _850矩形面积 {
    /*
    给你一个轴对齐的二维数组 rectangles 。
     对于 rectangle[i] = [x1, y1, x2, y2]，其中（x1，y1）是矩形 i 左下角的坐标，
      (xi1, yi1) 是该矩形 左下角 的坐标， (xi2, yi2) 是该矩形 右上角 的坐标。
    计算平面中所有 rectangles 所覆盖的 总面积 。任何被两个或多个矩形覆盖的区域应只计算 一次 。
    返回 总面积 。因为答案可能太大，返回 109 + 7 的 模 。
     */

    int MOD = (int) 1e9 + 7;

    public int rectangleArea(int[][] rectangles) {
        //对所有的x排序
        List<Integer> list_x = new ArrayList<>();
        for (int[] info : rectangles) {
            list_x.add(info[0]);
            list_x.add(info[2]);
        }
        Collections.sort(list_x);
        long ans = 0;
        for (int i = 1; i < list_x.size(); i++) {
            int a = list_x.get(i - 1), b = list_x.get(i), len = b - a;//拿到x区间
            if (len == 0) continue;
            List<int[]> lines = new ArrayList<>();
            for (int[] rect : rectangles) {//添加在这个x区间上的块的y值
                if (rect[0] <= a && b <= rect[2]) {
                    lines.add(new int[]{rect[1], rect[3]});
                }
            }
            //对y值排序,优先排序底边,底边相同的块按顶边排序
            lines.sort((l1, l2) -> l1[0] != l2[0] ? l1[0] - l2[0] : l1[1] - l2[1]);

            long totalY = 0, low = -1, high = -1;
            for (int[] cur : lines) {
                if (cur[0] > high) {//当前块与上一块完全分离
                    totalY += high - low;
                    low = cur[0];
                    high = cur[1];
                } else if (cur[1] > high) {//当前块与上一块有重叠
                    high = cur[1];
                }
            }
            totalY += high - low;//加最后一块
            ans += totalY * len;//计算面积
            ans %= MOD;
        }
        return (int) ans;
    }
}
