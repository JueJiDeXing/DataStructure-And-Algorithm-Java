package 算法OJ.蓝桥杯.真题卷.第10届.国赛.Java大学A组;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashMap;

/**
 已AC
 */
public class D轨道炮 {
    /*
    N个敌人,第i个敌人的初始坐标(Xi,Yi),方向Di(U/D/L/R),速度Vi
    小明有轨道炮,可以 在非负整数时刻 平行坐标轴 的 任意直线 释放一次
     求最多消灭的敌人数量
     */


    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }

        return (int) st.nval;
    }

    static char Char() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return st.sval.charAt(0);
    }


    /**
     枚举点x,将其设为最终一定会消灭的敌人
     然后根据相对运动,给其余敌人加上x的反向速度
     分别计算X轴和Y轴的最大消灭数量
     <p>
     计算可以使用哈希表,时刻->该时刻敌人数量
     */
    public static void main(String[] args) {
        int n = Int();
        int[][] point = new int[n][4];
        for (int i = 0; i < n; i++) {
            int x = Int(), y = Int(), v = Int();
            char d = Char();
            int xv = 0, yv = 0;
            if (d == 'R') {
                xv = v;
            } else if (d == 'L') {
                xv = -v;
            } else if (d == 'U') {
                yv = v;
            } else {
                yv = -v;
            }
            point[i] = new int[]{x, y, xv, yv};
        }
        int ans = 0;
        HashMap<Integer, Integer> mapX = new HashMap<>();//time->到达x轴的敌人个数
        HashMap<Integer, Integer> mapY = new HashMap<>();//time->到达y轴的敌人个数

        for (int i = 0; i < n; i++) {
            mapX.clear();
            mapY.clear();
            int curX = point[i][0], curY = point[i][1];
            int curXv = point[i][2], curYv = point[i][3];

            for (int j = 0; j < n; j++) {
                int x = point[j][0] - curX, y = point[j][1] - curY;
                int xv = point[j][2] - curXv, yv = point[j][3] - curYv;
                tryArrive(mapY, x, xv);
                tryArrive(mapX, y, yv);
            }
            int ex = mapX.remove(-1), ey = mapY.remove(-1);//-1表示与当前敌人相对静止,在全部时刻都可以加
            ans = Math.max(ans, Math.max(ex, ey));
            for (int xCount : mapX.values()) {
                ans = Math.max(ans, xCount + ex);
            }
            for (int yCount : mapY.values()) {
                ans = Math.max(ans, yCount + ey);
            }
        }
        System.out.println(ans);
    }

    private static void tryArrive(HashMap<Integer, Integer> map, int c, int v) {

        if (c == 0) {
            if (v == 0) {//全时刻加
                map.put(-1, map.getOrDefault(-1, 0) + 1);
            } else {//仅在0时刻加,加完后会走
                map.put(0, map.getOrDefault(0, 0) + 1);
            }
            return;
        }
        if (v == 0 || (c < 0 && v < 0) || (c > 0 && v > 0)) return;//不能到达轴
        if (Math.abs(c) % Math.abs(v) != 0) return;//到达的不是整数时刻
        int time = Math.abs(c) / Math.abs(v);//在time时刻到达轴,time时刻敌人+1
        map.put(time, map.getOrDefault(time, 0) + 1);
    }
}
