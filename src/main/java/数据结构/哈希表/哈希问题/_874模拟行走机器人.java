package 数据结构.哈希表.哈希问题;

import java.util.*;

public class _874模拟行走机器人 {
    /*
    机器人在一个无限大小的 XY 网格平面上行走，从点 (0, 0) 处开始出发，面向北方。
    该机器人可以接收以下三种类型的命令 commands ：

    -2 ：向左转 90 度
    -1 ：向右转 90 度
    1 <= x <= 9 ：向前移动 x 个单位长度
    在网格上有一些格子被视为障碍物 obstacles 。第 i 个障碍物位于网格点  obstacles[i] = (xi, yi) 。

    机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，并继续执行下一个命令。

    返回机器人距离原点的 最大欧式距离 的 平方 。（即，如果距离为 5 ，则返回 25 ）
     */
    public int robotSim(int[] commands, int[][] obstacles) {
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int currX = 0, currY = 0, d = 1;
        //存储障碍物
        Set<Integer> set = new HashSet<>();
        for (int[] obstacle : obstacles) {
            set.add(obstacle[0] * 60001 + obstacle[1]);//行坐标乘六万,用一个数字存储位置信息
        }
        int res = 0;//记录最大距离
        for (int command : commands) {
            //转向指令
            if (command < 0) {
                d += command == -1 ? 1 : -1;
                d %= 4;
                if (d < 0) {
                    d += 4;
                }
                continue;
            }
            //前进指令
            int dx = dirs[d][0];//前进方向
            int dy = dirs[d][1];
            for (int i = 0; i < command; i++) {//前进command步
                //如果下一个位置遇到障碍物则停止前进
                if (set.contains((currX + dx) * 60001 + currY + dy)) {
                    break;
                }
                //前进
                currX += dx;
                currY += dy;
            }
            res = Math.max(res, currX * currX + currY * currY);
        }
        return res;
    }
}
