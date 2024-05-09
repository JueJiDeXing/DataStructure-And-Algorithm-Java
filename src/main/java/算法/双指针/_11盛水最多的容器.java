package 算法.双指针;

/**
 难度:中等↓
 */
public class _11盛水最多的容器 {
    /*
    给定一个长度为 n 的整数数组 height 。
    有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。

    找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

    返回容器可以储存的最大水量。

    说明：你不能倾斜容器。
     */


    /**
     假如在[left,right]中找最大容量<br>
     对于i=left,j=right,不妨设i为短柱<br>
     假如向内移动长柱指针j,因为短板效应,容器高度不变,而底边长度减小了,容量一定会变小<br>
     如果说i,j不是最大容量,因为i与其他的非j柱子容量会更小,那么最大容量一定不是由短柱i组成的<br>
     所以left应当向内移动,移动前记录此时容量,因为i,j可能是最大容量<br>
     <p>
     双指针,指向矮的柱子的指针向内移动
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int max = 0;
        while (left < right) {
            int area;
            if (height[left] < height[right]) {
                area = (right - left) * height[left];
                left++;
                // while(height[left]<height[left-1])left++;
            } else {
                area = (right - left) * height[right];
                right--;
                // while(height[right]<height[right+1])right--;
            }
            max = Math.max(max, area);
        }
        return max;
    }

    /**
     <h1>优化</h1>
     优化前双指针移动每轮都计算面积,非常耗时,所以优化面积计算次数<br>
     基础结论:矮柱指针应当向内移动<br>
     考虑移动之后:如果移动之后,柱子变矮或者不变,那么容量都变小,需要再次移动<br>
     考虑两柱一样长时:那么两个柱子的地位相等,如果他们不是最大容量,那么他们都不是最大容量的组成者,两个指针可以同时向内移动<br>
     所以可以用一个变量oldHeight记录每轮比较的短柱长度,两个指针都向内移动到大于oldHeight的柱子时才进行面积计算<br>
     */
    public int maxArea2(int[] height) {
        int max = 0, left = 0, right = height.length - 1, oldHigh = 0;
        while (left < right) {
            while (left < right && height[left] <= oldHigh) {
                left++;
            }
            while (left < right && height[right] <= oldHigh) {
                right--;
            }
            if (left < right) {
                oldHigh = Math.min(height[left], height[right]);
                max = Math.max(max, oldHigh * (right - left));
            }
        }
        return max;
    }
}
