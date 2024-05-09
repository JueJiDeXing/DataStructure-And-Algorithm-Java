package 算法.动态规划_贪心.贪心;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class _1488避免洪水泛滥 {
    /*
    无数个湖泊
    rains[i]表示第i天第i个湖泊下雨
    rains[i]=0表示第i天晴天,可以抽干一个湖泊的水
    返回值:一个数组res[i]表示第i天的操作为{1:晴天但是不抽,-1:下雨所以不能做任何操作,others:抽干第others个湖泊的水}
     */
    public int[] avoidFlood(int[] rains) {
        int[] res = new int[rains.length];
        Arrays.fill(res, 1);
        TreeSet<Integer> set = new TreeSet<Integer>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < rains.length; i++) {
            if (rains[i] == 0) {//不下雨,加入晴天索引
                set.add(i);
            } else {//下雨
                res[i] = -1;//不能抽湖水
                if (map.containsKey(rains[i])) {//如果不是第一次下雨
                    Integer it = set.ceiling(map.get(rains[i]));//找到离上次该湖泊下雨后的最近一次晴天
                    if (it == null) {//上次该湖泊下雨到这次该湖泊下雨中间没有多余的晴天
                        return new int[0];
                    }
                    res[it] = rains[i];//有多余晴天,将湖水抽干
                    set.remove(it);//移除该晴天
                }
                map.put(rains[i], i);//第一次下雨,存储下雨的湖泊和天数(索引)
            }
        }
        return res;
    }
}
