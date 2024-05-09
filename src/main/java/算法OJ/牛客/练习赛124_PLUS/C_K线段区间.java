package 算法OJ.牛客.练习赛124_PLUS;

import java.util.*;

/**
 已AC
 */
public class C_K线段区间 {
    public static void main(String[] args) {
        // 扫描线 - 端点离散化
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long m = sc.nextLong();
        int k = sc.nextInt();
        HashMap<Long, List<Long>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long l = sc.nextLong(), r = sc.nextLong();
            map.computeIfAbsent(r, x -> new ArrayList<>()).add(l);//右端点坐标->左端点坐标
        }
        Long[] R = map.keySet().toArray(new Long[0]);//按坐标升序排序
        Arrays.sort(R);
        //滑动窗口
        long usedL = 1;//[0,usedL)为已计算过的左端点
        long ans = 0;
        Queue<Long> L = new PriorityQueue<>();
        for (long r : R) {//遍历区间右端点
            for (long l : map.get(r)) L.offer(l); //r位置对应的左端点入队
            while (L.size() > k) L.poll(); //只存储k个线段,保留最小区间
            //此时L中存储了最小的可行区间[l,r], 左端点有[usedL,l]的选择,右端点有[r,m]的选择
            long l = L.peek();
            if (L.size() < k || l < usedL) continue;//未达到k条 或 超过已用左坐标,无可行解
            ans += (m - r + 1) * (l - usedL + 1);
            usedL = l + 1;//更新l的位置
        }
        System.out.println(ans);
    }
}

