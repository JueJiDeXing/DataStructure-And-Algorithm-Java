package 算法OJ.蓝桥杯.真题卷.第10届.省赛.Java大学A组;

import java.io.*;
import java.util.*;
/**
 已AC
 */
public class G外卖店优先级 {
    /*
    N家外卖店,编号1~N,初始0时刻,优先级都为0
    每过1秒,如果没有订单则优先级-1,最低为0,有订单则优先级加 2/每单
    如果某时刻优先级大于5,则加入优先缓存,小于等于3则移除优先缓存

    给定T时刻内的M条订单,求T时刻有多少外卖店在优先缓存中
    每条订单包含2个整数t,id,表示id的外卖店在t时刻有订单

     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     模拟
     将订单按时间排序,遍历订单模拟
     */
    public static void main(String[] args) {
        int N = Int(), M = Int(), T = Int();
        HashMap<Integer, Integer>[] dd = new HashMap[T + 1]; // 时间 -> 有订单的店 -> 店的订单个数
        Arrays.setAll(dd, k -> new HashMap<>());
        for (int i = 0; i < M; i++) {
            int tt = Int(), id = Int();
            HashMap<Integer, Integer> idToCount = dd[tt];// dd[tt]:在tt时刻的订单
            idToCount.put(id, idToCount.getOrDefault(id, 0) + 1);//id->订单个数
        }
        Set<Integer> set = new HashSet<>();  // 缓存
        long[] arr = new long[N + 1];  // 店家优先级记录
        //按时间顺序接单,模拟
        for (int tt = 1; tt <= T; tt++) {
            HashMap<Integer, Integer> idToCount = dd[tt];
            Set<Integer> idSet = idToCount.keySet();
            for (int id = 1; id <= N; id++) {
                if (idSet.contains(id)) continue;
                if (arr[id] > 0) {
                    arr[id]--;//没有订单的键
                    if (arr[id] <= 3) set.remove(id);
                }
            }
            for (Map.Entry<Integer, Integer> entry : idToCount.entrySet()) {
                int id = entry.getKey(), cnt = entry.getValue();
                arr[id] += cnt * 2L  ;
                if (arr[id] > 5) set.add(id);

            }
        }
        System.out.println(set.size());

    }


}
