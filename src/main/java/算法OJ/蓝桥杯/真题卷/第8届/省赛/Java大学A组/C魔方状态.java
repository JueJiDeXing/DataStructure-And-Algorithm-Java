package 算法OJ.蓝桥杯.真题卷.第8届.省赛.Java大学A组;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 已AC,非常ex的一题
 */
public class C魔方状态 {
    /*
    2阶魔方
    左右面为绿色,前面和下面为橙色,上面和后面为黄色,求魔方不同状态的方案数
     */
/*
from collections import deque
from copy import deepcopy


def process_cube(state) -> str:
    return ''.join([''.join(x) for x in state])


def u(state):  # 上面顺时针旋转一次
    def u_cell(cell):
        # 上面顺时针旋转一次对应的每个块的面编码变化
        cell[4], cell[5], cell[2], cell[0] = cell[0], cell[4], cell[5], cell[2]

    for i in [0, 1, 2, 3]:
        u_cell(state[i])
    state[0], state[1], state[2], state[3] = state[1], state[2], state[3], state[0]
    return state


def r(state):  # 右面顺时针旋转一次
    def r_cell(cell):
        # 右面顺时针旋转一次对应的每个块的面编码变化
        cell[0], cell[1], cell[5], cell[3] = cell[3], cell[0], cell[1], cell[5]

    for i in [1, 2, 5, 6]:
        r_cell(state[i])
    state[1], state[2], state[5], state[6] = state[5], state[1], state[6], state[2]
    return state


def f(state):  # 前面顺时针旋转一次
    def f_cell(cell):
        # 前面顺时针旋转一次对应的每个块的面编码变化
        cell[1], cell[2], cell[3], cell[4] = cell[4], cell[1], cell[2], cell[3]

    for i in [0, 1, 4, 5]:
        f_cell(state[i])
    state[0], state[1], state[4], state[5] = state[4], state[0], state[5], state[1]
    return state


def u_whole(state):  # 上+下面顺时针旋转一次
    def u_d_cell(cell):
        # 上+下面顺时针旋转一次对应的每个块的面编码变化
        cell[4], cell[5], cell[2], cell[0] = cell[0], cell[4], cell[5], cell[2]

    for i in range(8):
        u_d_cell(state[i])
    state[0], state[1], state[2], state[3] = state[1], state[2], state[3], state[0]
    state[4], state[5], state[6], state[7] = state[5], state[6], state[7], state[4]
    return state


def r_whole(state):  # 右+左面顺时针旋转一次
    def r_l_cell(cell):
        # 右+左面顺时针旋转一次对应的每个块的面编码变化
        cell[0], cell[1], cell[5], cell[3] = cell[3], cell[0], cell[1], cell[5]

    for i in range(8):
        r_l_cell(state[i])
    state[1], state[2], state[5], state[6] = state[5], state[1], state[6], state[2]
    state[0], state[3], state[4], state[7] = state[4], state[0], state[7], state[3]
    return state


def f_whole(state):  # 前+后面顺时针旋转一次
    def f_b_cell(cell):
        # 前+后面顺时针旋转一次对应的每个块的面编码变化
        cell[1], cell[2], cell[3], cell[4] = cell[4], cell[1], cell[2], cell[3]

    for i in range(8):
        f_b_cell(state[i])
    state[0], state[1], state[4], state[5] = state[4], state[0], state[5], state[1]
    state[3], state[2], state[7], state[6] = state[7], state[3], state[6], state[2]
    return state


def try_add(state):
    for _ in range(4):
        state = u_whole(deepcopy(state))
        for _ in range(4):
            state = r_whole(deepcopy(state))
            for _ in range(4):
                state = f_whole(deepcopy(state))
                if process_cube(state) in states:
                    return
    states.add(process_cube(state))
    queue.append(state)


if __name__ == '__main__':
    cube = [['o', 'y', 'x', 'x', 'g', 'x'],  # 一个魔方8个块, 每块6个面,其中的3个面不可见(x)
            ['o', 'y', 'g', 'x', 'x', 'x'],  # o,y,g分别为橙色,黄色,绿色
            ['x', 'y', 'g', 'x', 'x', 'y'],
            ['x', 'y', 'x', 'x', 'g', 'y'],
            ['o', 'x', 'x', 'o', 'g', 'x'],
            ['o', 'x', 'g', 'o', 'x', 'x'],
            ['x', 'x', 'g', 'o', 'x', 'y'],
            ['x', 'x', 'x', 'o', 'g', 'y']]
    queue, states = deque(), set()  # states:存储魔方的不同状态
    queue.append(cube)
    states.add(process_cube(cube))
    while queue:
        front = queue.popleft()
        # 二阶魔方只需要 顶层旋转,右层旋转,前面旋转 这三种操作就能获取到全部状态
        u_state = u(deepcopy(front))
        try_add(u_state)
        r_state = r(deepcopy(front))
        try_add(r_state)
        f_state = f(deepcopy(front))
        try_add(f_state)
    print(len(states))

 */

    /**
     非对称操作有:
     前面顶层向右转;
     前面右层向上转;
     前面顺时针旋转;
     */
    static String process_cube(char[][] state) {
        StringBuilder ans = new StringBuilder();
        for (char[] s : state) {
            ans.append(new String(s));
        }
        return ans.toString();

    }

    static void rotate(char[] cell, int a, int b, int c, int d) {
        char temp = cell[a];
        cell[a] = cell[b];
        cell[b] = cell[c];
        cell[c] = cell[d];
        cell[d] = temp;
    }

    static void rotate(char[][] cell, int a, int b, int c, int d) {
        char[] temp = cell[a];
        cell[a] = cell[b];
        cell[b] = cell[c];
        cell[c] = cell[d];
        cell[d] = temp;
    }

    static void u_cell(char[] cell) {
        //上面顺时针旋转一次对应的每个块的面编码变化
        rotate(cell, 0, 2, 5, 4);
    }

    static char[][] u(char[][] state) {
        //上面顺时针旋转一次
        int[] list = new int[]{0, 1, 2, 3};
        for (int i : list) u_cell(state[i]);
        rotate(state, 0, 1, 2, 3);
        return state;
    }

    static void r_cell(char[] cell) {
        // 右面顺时针旋转一次对应的每个块的面编码变化
        rotate(cell, 0, 3, 5, 1);
    }

    static char[][] r(char[][] state) {
        // 右面顺时针旋转一次
        int[] list = new int[]{1, 2, 5, 6};
        for (int i : list) r_cell(state[i]);
        rotate(state, 1, 5, 6, 2);
        return state;
    }

    static void f_cell(char[] cell) {
        // 前面顺时针旋转一次对应的每个块的面编码变化
        rotate(cell, 1, 4, 3, 2);
    }

    static char[][] f(char[][] state) {
        // 前面顺时针旋转一次
        int[] list = new int[]{0, 1, 4, 5};
        for (int i : list) f_cell(state[i]);
        rotate(state, 0, 4, 5, 1);
        return state;
    }

    static void u_d_cell(char[] cell) {
        // 上+下面顺时针旋转一次对应的每个块的面编码变化
        rotate(cell, 0, 2, 5, 4);
    }

    static char[][] u_whole(char[][] state) {
        // 上+下面顺时针旋转一次
        int[] list = new int[]{0, 1, 2, 3, 4, 5, 6, 7};

        for (int i : list) u_d_cell(state[i]);
        rotate(state, 0, 1, 2, 3);
        rotate(state, 4, 5, 6, 7);
        return state;
    }

    static void r_l_cell(char[] cell) {
        // 右+左面顺时针旋转一次对应的每个块的面编码变化
        rotate(cell, 0, 3, 5, 1);
    }

    static char[][] r_whole(char[][] state) {
        // 右+左面顺时针旋转一次
        int[] list = new int[]{0, 1, 2, 3, 4, 5, 6, 7};

        for (int i : list) r_l_cell(state[i]);
        rotate(state, 1, 5, 6, 2);
        rotate(state, 0, 1, 7, 3);
        return state;
    }

    static void f_b_cell(char[] cell) {
        // 前+后面顺时针旋转一次对应的每个块的面编码变化
        rotate(cell, 1, 4, 3, 2);
    }


    static char[][] f_whole(char[][] state) {
        // 前+后面顺时针旋转一次
        int[] list = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
        for (int i : list) f_b_cell(state[i]);
        rotate(state, 0, 4, 5, 1);
        rotate(state, 2, 3, 7, 6);
        return state;
    }


    static void try_add(char[][] state) {
        if (check(state)) {//检查重复
            states.add(process_cube(state));
            queue.offer(state);
        }
    }

    private static boolean check(char[][] state) {
        for (int i = 0; i < 4; i++) {
            state = u_whole(state.clone());
            for (int j = 0; j < 4; j++) {
                state = r_whole(state.clone());
                for (int k = 0; k < 4; k++) {
                    state = f_whole(state.clone());
                    if (states.contains(process_cube(state))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static Queue<char[][]> queue = new LinkedList<>();
    static HashSet<String> states = new HashSet<>();

    public static void main(String[] args) {
        char[][] cube = {{'1', '2', '?', '?', '3', '?'},//8个块
                {'1', '2', '3', '?', '?', '?'},//每个块6个面
                {'?', '2', '3', '?', '?', '2'},//每个块有3个面是不可见的
                {'?', '2', '?', '?', '3', '2'}, {'1', '?', '?', '1', '3', '?'}, {'1', '?', '3', '1', '?', '?'}, {'?', '?', '3', '1', '?', '2'}, {'?', '?', '?', '1', '3', '2'}};
        queue.offer(cube);
        states.add(process_cube(cube));
        while (!queue.isEmpty()) {
            char[][] front = queue.poll();
            char[][] u_state = u(front.clone());
            try_add(u_state);
            char[][] r_state = r(front.clone());
            try_add(r_state);
            char[][] f_state = f(front.clone());
            try_add(f_state);
        }
        System.out.println(states.size());
    }
}
