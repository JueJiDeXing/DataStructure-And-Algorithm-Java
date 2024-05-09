package 数据结构.树.树实现.线段树.线段树问题;

import java.util.*;

/**
 难度:中等↑
 */
public class _LCR058我的日程安排 {
    /*
    请实现一个 MyCalendar 类来存放你的日程安排。
    如果要添加的时间内没有其他安排，则可以存储这个新的日程安排。

    MyCalendar 有一个 book(int start, int end)方法。
    它意味着在 start 到 end 时间内增加一个日程安排，
    注意，这里的时间是半开区间，即 [start, end),
     实数 x 的范围为，  start <= x < end。

    当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生重复预订。

    每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true。
    否则，返回 false 并且不要将该日程安排添加到日历中。

    请按照以下步骤调用 MyCalendar 类:
    MyCalendar cal = new MyCalendar();
     MyCalendar.book(start, end)
     */
}

/**
 暴力解法
 存储所有区间的始终,每次添加日程时遍历所有区间判断是否重复
 */
class MyCalendar1 {

    List<int[]> booked;

    public MyCalendar1() {
        booked = new ArrayList<int[]>();
    }

    public boolean book(int start, int end) {
        //检查重复
        for (int[] arr : booked) {
            int l = arr[0], r = arr[1];
            if (l < end && start < r) {
                return false;
            }
        }
        //无重复,添加
        booked.add(new int[]{start, end});
        return true;
    }
}

/**
 平衡二叉树
 查询区间只需要找到距离start最近的两个区间判断是否有重复即可
 所以可以对start进行排序,每次查找最近的两个区间
 */
class MyCalendar2 {

    private TreeMap<Integer, Integer> map;//这里使用有序集合TreeMap对start进行排序

    public MyCalendar2() {
        this.map = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        //查询最近的两个区间
        Map.Entry<Integer, Integer> entry1 = map.floorEntry(start);
        Map.Entry<Integer, Integer> entry2 = map.ceilingEntry(start);
        if (entry1 != null && entry1.getValue() > start) {//判断是否有重复
            return false;
        }
        if (entry2 != null && entry2.getKey() < end) {
            return false;
        }
        //无重复,添加
        map.put(start, end);
        return true;
    }
}

/**
 线段树
 */
class MyCalendar3 {
    static final int CEILING = 1000000000;//最大查询上界
    Set<Integer> lazy;//懒标记
    Set<Integer> tree;

    public MyCalendar3() {
        lazy = new HashSet<>();
        tree = new HashSet<>();
    }

    public boolean book(int start, int end) {
        if (query(start, end - 1, 0, CEILING, 1)) {//查询[start,end]是否有重复
            return false;
        }
        //无重复,添加
        update(start, end - 1, 0, CEILING, 1);
        return true;
    }

    /**
     查询idx及其子树,idx对应区间为[l,r],判断日程任务[start,end]是否有重复<br>
     case1: idx的区间完全不在任务区间上,无重复<br>
     case2: idx上有懒标记,有重复(懒标记是update方法添加任务时区间被完全覆盖时添加的)<br>
     case3: idx的区间被完全覆盖,判断idx是否在日程上即可<br>
     case4: idx的区间与任务区间有交集,需要查询左右子树<br>
     */
    public boolean query(int start, int end, int l, int r, int idx) {
        if (start > r || end < l) return false;//不在该区间内,无重复
        if (lazy.contains(idx)) return true;// 如果该区间已被预订，有重复
        if (start <= l && r <= end) return tree.contains(idx);//区间被覆盖,只需要判断,idx是否在日程内即可
        //区间未完全覆盖
        int mid = (r - l) / 2 + l;
        if (end <= mid) {//都在左区间,左孩子索引2*idx,左区间[l,mid]
            return query(start, end, l, mid, 2 * idx);
        } else if (start > mid) {//都在右区间,右孩子索引2*idx+1,右区间[mid+1,r]
            return query(start, end, mid + 1, r, 2 * idx + 1);
        } else {//覆盖中间,两边都要查询
            return query(start, end, l, mid, 2 * idx) | query(start, end, mid + 1, r, 2 * idx + 1);
        }

    }

    /**
     为idx添加任务,idx对应区间为[l,r],要添加的日程任务区间为[start,end]<br>
     case1: idx的区间完全不在日程任务上,不添加<br>
     case2: idx的区间完全在日程任务上,添加idx,并为左右子树添加懒标记<br>
     case3: idx的区间部分在日程任务上,添加idx,并为左右子树添加任务<br>
     */
    public void update(int start, int end, int l, int r, int idx) {
        if (start > r || end < l) return;//idx节点完全不在任务区间上,不添加任务

        if (start <= l && r <= end) {//区间被覆盖
            lazy.add(idx);//不再下发日程任务,为下级区间添加懒标记
        } else {//区间不完全覆盖,为左右子树下发任务
            int mid = (r - l) / 2 + l;
            update(start, end, l, mid, 2 * idx);
            update(start, end, mid + 1, r, 2 * idx + 1);
        }
        tree.add(idx);//将idx节点添加到日程
    }
}

