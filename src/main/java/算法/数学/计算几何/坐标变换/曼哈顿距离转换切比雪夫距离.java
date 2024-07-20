package 算法.数学.计算几何.坐标变换;

import java.util.TreeMap;

public class 曼哈顿距离转换切比雪夫距离 {
    /*
    对于点(x1,y1)和点(x2,y2)
    他们的曼哈顿距离为: |x1-x2| + |y1-y2|

    将点绕原点顺时钟旋转45度,再扩大sqrt(2)倍
    得到的新点 (x`,y`) = (x+y,y-x)
    原来的点的曼哈顿距离变为(x`,y`)的切比雪夫距离: max(x1`-x2`,y1`-y2`)

    这两种距离的不同点在于:
    曼哈顿距离是有绝对值,且带加法的,在很多时候不利于程序编写
    将其转换为切比雪夫距离,取max的操作更为方便
    */

    /**
     <h1>最小化曼哈顿距离</h1>
     第391场周赛 Q4<br>
     难度: 困难<br>
     <br>
     给你一个下标从 0 开始的数组 points , points[i] = [xi, yi] <br>
     两点之间的距离定义为它们的曼哈顿距离<br>
     请你恰好移除一个点，返回移除后任意两点之间的 最大 距离可能的 最小 值<br>
     */
    public static void main(String[] args) {
        int[][] points = {{4, 1}, {10, 7}, {5, 6}, {3, 2}, {10, 9}, {2, 9}, {2, 8}};
        System.out.println(minimumDistance(points));
    }

    public static int minimumDistance(int[][] points) {
        TreeMap<Integer, Integer> setX = new TreeMap<>(), setY = new TreeMap<>();//不能用TreeSet,点坐标是有重复的,如果用set会同时移除多个
        for (int[] p : points) {
            int x = p[0] + p[1], y = p[1] - p[0];
            setX.put(x, setX.getOrDefault(x, 0) + 1);
            setY.put(y, setY.getOrDefault(y, 0) + 1);
        }
        if (setX.size() == 1 && setY.size() == 1) {//点全部是重合的
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int[] p : points) {
            int x = p[0] + p[1], y = p[1] - p[0];
            if (setX.get(x) == 1) {
                setX.remove(x);
            } else {
                setX.put(x, setX.get(x) - 1);
            }
            if (setY.get(y) == 1) {
                setY.remove(y);
            } else {
                setY.put(y, setY.get(y) - 1);
            }
            ans = Math.min(ans, Math.max(setX.lastKey() - setX.firstKey(), setY.lastKey() - setY.firstKey()));
            setX.put(x, setX.getOrDefault(x, 0) + 1);
            setY.put(y, setY.getOrDefault(y, 0) + 1);
        }
        return ans;
    }

}
