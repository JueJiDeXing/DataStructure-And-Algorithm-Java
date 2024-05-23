package 基础数据结构算法.栈.单调栈应用;

import 算法.算法基础.搜索.dfs._407接雨水II;

import java.util.LinkedList;

/**
 难度:困难
 */
public class _42接雨水 {
    static class Data {
        int height;
        int index;

        public Data(int height, int index) {
            this.height = height;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Data{" + "height=" + height + ", index=" + index + '}';
        }
    }

    /*
    给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     */

    /**
     <h1>单调递减栈</h1>
     维护一个单调递减栈,如果需要弹出,说明有凹陷柱,此时计算水容量
     计算水容量:
     令当前柱为右支撑柱,弹出的柱为凹陷柱,栈顶的柱为左支撑柱
     那么 宽=右支撑柱的index-左支撑柱的index-1 ; 高=左右支撑柱的最小值-凹陷柱高度
     所以栈存储时还需要存储索引
     */
    public int trap(int[] height) {
        LinkedList<Data> stack = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            Data right = new Data(height[i], i);//当前要放入的柱子,高度为0也算
            while (!stack.isEmpty() && stack.peek().height < right.height) {
                Data pop = stack.pop();//凹陷柱
                Data left = stack.peek();//盛水柱(左)
                if (left != null) {
                    int w = right.index - left.index - 1;//宽
                    int h = Math.min(left.height, right.height) - pop.height;//高
                    res += w * h;
                }
            }
            stack.push(right);
        }
        return res;
    }

    /**
     {@link _407接雨水II}
     */
    public int trapRainWater(int[][] heightMap) {
        return 0;
    }
}
