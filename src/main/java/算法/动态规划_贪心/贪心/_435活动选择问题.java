package 算法.动态规划_贪心.贪心;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _435活动选择问题 {
    //无重叠区间
    /*
    在一个会议室举办n个活动,
    每个活动都有起始和结束时间,
    找出时间上互不冲突的组合,使举办次数最多
     */
    static class Activity {
        int id;//编号
        int start;
        @Getter
        int finish;

        @Override
        public String toString() {
            return "Activity(" + id + ')';
        }

        public Activity(int id, int start, int finish) {
            this.id = id;
            this.start = start;
            this.finish = finish;
        }
    }

    public static void main(String[] args) {
        Activity[] activities = new Activity[]{
                new Activity(0, 1, 3),
                new Activity(1, 2, 4),
                new Activity(2, 3, 5),
        };
        List<Activity> select = select(activities);
        for (Activity activity : select) {
            System.out.println(activity);
        }
    }

    /**
     <h1>贪心算法</h1>
     优先选择结束时间最靠前的活动
     */
    public static List<Activity> select(Activity[] activities) {
        //按结束时间排序
        Arrays.sort(activities, Comparator.comparingInt(Activity::getFinish));
        List<Activity> result = new ArrayList<>();
        Activity prev = activities[0];//上一个被选择的活动
        result.add(prev);
        for (int i = 1; i < activities.length; i++) {
            Activity current = activities[i];//当前活动
            if (current.start >= prev.finish) {//没有冲突
                result.add(current);
                prev = current;
            }
        }
        return result;
    }
}
