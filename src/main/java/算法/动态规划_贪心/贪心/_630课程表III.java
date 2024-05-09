package 算法.动态规划_贪心.贪心;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class _630课程表III {
    /*
    这里有 n 门不同的在线课程，按从 1 到 n 编号。
    给你一个数组 courses ，其中 courses[i] = [durationi, lastDayi]
    表示第 i 门课将会 持续 上 durationi 天课，并且必须在不晚于 lastDayi 的时候完成。

    你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。
    返回你最多可以修读的课程数目。
     */
    //贪心,优先选择更早结课的课程
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(c -> c[1]));//按结束时间排序,优先选择结课最早的课程
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);//存储已选择的课程的持续时间
        int total = 0;// 优先队列中所有课程的总时间(当前时间)
        for (int[] course : courses) {
            int duration = course[0], lastDay = course[1];
            if (total + duration <= lastDay) {
                //能修完,入队
                total += duration;
                q.offer(duration);
            } else if (!q.isEmpty() && q.peek() > duration) {
                //修不完,但是这门课持续时间比上一门的短,那么总时间这门课更优
                total -= q.poll() - duration;//这门课入队,上一门课出队
                q.offer(duration);
            }
        }
        return q.size();
    }
}
