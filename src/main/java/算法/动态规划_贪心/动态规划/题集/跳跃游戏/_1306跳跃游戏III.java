package 算法.动态规划_贪心.动态规划.题集.跳跃游戏;

import java.util.LinkedList;
import java.util.Queue;

public class _1306跳跃游戏III {
    /*
    这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。
    当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。

    请你判断自己是否能够跳到对应元素值为 0 的 任一 下标处。

    注意，不管是什么情况下，你都无法跳到数组之外。
     */

    /**
     <h1>深度优先搜索</h1>
     */
    public boolean canReach1(int[] arr, int start) {
        return dfs(arr, start, new boolean[arr.length]);
    }

    /**
     从起点开始,能否到达arr中元素值为0的下标

     @param used 记录访问次数,0为未访问,1为已访问,大于1为
     */
    boolean dfs(int[] arr, int start, boolean[] used) {
        if (start < 0 || start >= arr.length) {//越界
            return false;
        }
        if (arr[start] == 0) {//到达arr中元素值为0的下标
            return true;
        }
        if (used[start]) {
            return false;
        }
        used[start] = true;
        return dfs(arr, start + arr[start], used) || dfs(arr, start - arr[start], used);
    }

    /**
     <h1>广度优先搜索</h1>
     从start开始,标记可到达的两个点,然后再分别将这两个点设为新的起点继续搜索,直到遇到0
     */
    public boolean canReach2(int[] arr, int start) {
        if (arr[start] == 0) return true;
        boolean[] isVisited = new boolean[arr.length];
        isVisited[start] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int j = queue.poll();
            int i1 = j + arr[j];
            if (i1 < arr.length && !isVisited[i1]) {
                if (arr[i1] == 0) return true;
                isVisited[i1] = true;
                queue.offer(i1);
            }
            int i2 = j - arr[j];
            if (i2 >= 0 && !isVisited[i2]) {
                if (arr[i2] == 0) return true;
                isVisited[i2] = true;
                queue.offer(i2);
            }
        }
        return false;
    }

    /**
     <h1>广度优先搜索:小改</h1>
     */
    public boolean canReach3(int[] arr, int start) {
        if (arr[start] == 0) return true;
        boolean[] isVisited = new boolean[arr.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int j = queue.poll();
                if (isVisited[j]) continue;
                if (arr[j] == 0) return true;
                isVisited[j] = true;
                if (j + arr[j] < arr.length) {
                    queue.offer(j + arr[j]);
                }
                if (j - arr[j] >= 0) {
                    queue.offer(j - arr[j]);
                }
            }
        }
        return false;
    }


}
