package 算法.算法基础.贪心;

import java.util.Arrays;
import java.util.Comparator;

public class _452区间选点问题 {
    // 数轴上有n个区间, 问最少放置多少个点, 可以时每个区间都至少包含一个点

    /**
     ① 在放置之后点一定可以移动到区间的端点处 <br>
     ② 基于①, 点可以全部放置在左端点或者全部放置在右端点<br>
     ③ 假如全部放置在左端点, 并按左端点排序<br>
     此时考虑最后一个区间:<br>
        由于最后一个区间右边没有线段, 放置一个点在它的左端点是最优的<br>
        删去这个点覆盖到的区间, 剩余的区间又是一个子问题, 可以用相同的方法解决<br>
        即: 从右往左遍历, 如果上一个点不存在于当前区间, 则放置一个点在左端点<br>
     ④ 由于对称性, 可以按右排序, 从左往右遍历, 放置在右端点<br>
     @param points 区间
     @return 最少的防置点数
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(p -> p[1])); // 按照右端点从小到大排序
        int ans = 0;
        long preDot = Long.MIN_VALUE;
        for (int[] p : points) {
            if (preDot < p[0]) { // 上一个放的点在区间左边
                ans++;
                preDot = p[1]; // 在区间的最右边放一个点
            }
        }
        return ans;
    }

}
