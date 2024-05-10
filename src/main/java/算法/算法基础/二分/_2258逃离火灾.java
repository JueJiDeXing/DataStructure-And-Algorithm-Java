package 算法.算法基础.二分;

import java.util.*;

/**
 第 77 场双周赛 Q4
 难度分:2347
 */
public class _2258逃离火灾 {
    /*
    给你一个下标从 0 开始大小为 m x n 的二维整数数组 grid ，它表示一个网格图。每个格子为下面 3 个值之一：

    0 表示草地。
    1 表示着火的格子。
    2 表示一座墙，你跟火都不能通过这个格子。
    一开始你在最左上角的格子 (0, 0) ，你想要到达最右下角的安全屋格子 (m - 1, n - 1) 。每一分钟，你可以移动到 相邻 的草地格子。每次你移动 之后 ，着火的格子会扩散到所有不是墙的 相邻 格子。

    请你返回你在初始位置可以停留的 最多 分钟数，且停留完这段时间后你还能安全到达安全屋。如果无法实现，请你返回 -1 。如果不管你在初始位置停留多久，你 总是 能到达安全屋，请你返回 109 。

    注意，如果你到达安全屋后，火马上到了安全屋，这视为你能够安全到达安全屋。

    如果两个格子有共同边，那么它们为 相邻 格子。
     */
}

/**
 <h1>二分+BFS</h1>
 <ul>
 <li>二分:<br>
 对于时间t,如果停留t分钟后出发可以到达安全屋,则[0,t-1]的值都是成立的<br>
 所以t具有二段性,可以用二分查找的方式寻找最大的可停留时间
 </li>
 <li>BFS:<br>
 对于时间t,要判断能否到达安全屋,使用到bfs算法<br>
 1. 首先模拟t秒的火势蔓延,定义队列fire,将初始火入队,然后进行t次更新<br>
 2. 再模拟人火的行进,定义队列people,每次循环更新1次火,更新1次人<br>
 如果people空了(没有路可走了),说明t不行
 如果之前定义的布尔变量ok在更新队列后被置为了true,则说明t可以
 </li>
 </ul>
 */
class Solution1 {
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};//方向
    int n, m;//矩阵长宽
    boolean ok;//在check中,初始为false,如果在某次update后ok被置为true,则check返回true
    int[][] g, fg, pg;//g为原矩阵,fg为火,pg为人,fg[i][j]表示火蔓延到(i,j)的时间,pg[i][j]同理,0表示未到达

    public int maximumMinutes(int[][] grid) {
        g = grid;
        n = g.length;
        m = g[0].length;
        fg = new int[n][m];
        pg = new int[n][m];
        if (!check(0)) return -1;//如果不等待直接开始都无法达到安全屋,返回-1
        //二分检查时间t下能否通过
        int l = 0, r = n * m;//火蔓延所有格子的时间一定不超过n*m,否则火一定被墙围住了
        while (l < r) {
            int mid = l + r + 1 >> 1;//检查等待mid分钟后开始,能否到达安全屋
            if (check(mid)) l = mid;//能到达,找更大的停留时间
            else r = mid - 1;//不能到达,检查更小的停留时间
        }
        return r == m * n ? (int) 1e9 : r;//无论停留多久都能到达安全屋,返回1e9
    }

    private boolean check(int t) {
        ok = false;
        //初始化火源
        Deque<int[]> fire = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                fg[i][j] = pg[i][j] = 0;//初始化为0
                if (g[i][j] == 1) {//g为初始矩阵,1表示着火的格子
                    fg[i][j] = 1;//火源标记为1
                    fire.addLast(new int[]{i, j});//BFS,添加到队列
                }
            }
        }
        while (t-- > 0) update(fire, true, true);  // 先执行 t 秒的火势蔓延
        if (fg[0][0] != 0) return false;//火已经烧到(0,0)了
        //模拟人火行走
        Deque<int[]> people = new ArrayDeque<>();
        pg[0][0] = 1;//第一秒人开始在(0,0)
        people.addLast(new int[]{0, 0});
        while (!people.isEmpty()) {
            // 先火后人, 同步进行
            update(fire, true, false);
            update(people, false, false);
            //如果人到达安全屋,而 火没到 或 人火同时到, 则ok为true
            if (ok) return true;
        }
        return false;
    }

    private void update(Deque<int[]> deque, boolean isFire, boolean isBeforePeopleMove) {
        int sz = deque.size();
        while (sz-- > 0) {
            int[] info = deque.pollFirst();
            int x = info[0], y = info[1];
            for (int[] dir : dirs) {//搜索四个方向
                int nx = x + dir[0], ny = y + dir[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;//索引越界
                if (g[nx][ny] == 2) continue;//下一个位置是墙,不能走
                if (isFire) {
                    //火的相关操作
                    if (fg[nx][ny] != 0) continue;//这个地方不是草地,无法蔓延
                    fg[nx][ny] = fg[x][y] + (isBeforePeopleMove ? 0 : 1);//蔓延,fg存储火蔓延到此次的时间
                } else {
                    //人的相关操作
                    if (nx == n - 1 && ny == m - 1 && (fg[nx][ny] == 0 || fg[nx][ny] == pg[x][y] + 1)) {
                        // 人到达安全屋 并且 (火尚未到达 或 人火同时到达)
                        // 因为火是先更新的,所以人加1秒到达的话也是成立的
                        ok = true;
                    }
                    if (fg[nx][ny] != 0 || pg[nx][ny] != 0) continue;//不是草地 -> 是 有火的地方 或 人已经走过了
                    pg[nx][ny] = pg[x][y] + 1;//pg存储的是走的时间,人每次行动加1秒
                }
                deque.addLast(new int[]{nx, ny});
            }
        }
    }

}

/**
 <h1>BFS+分类讨论</h1>
 fg和pg矩阵预处理出,到达格子(i,j)的最早时间; f=fg[n−1][m−1] p=pg[n-1][m-1];<br>
 <ul>
 <li>case 1: 如果p=0 (人与安全屋不连通) ,则返回-1</li>
 <li>case 2: 如果f=0 (p不等于0,火与安全屋不连通),则返回1e9</li>
 <li>case 3: f < p (火比人快), 返回-1</li>
 <li>* case 4: f>=p (人比火快)<br>
 因为人比火快,在人的路径上,不可能会半途碰到火,否则火会比人更快到达安全屋,但是可能半途同时到达的火<br>
 所以人可以停留 f-p-1(需要比火早一秒出发,错开同时到达) 或 f-p(安全屋特性,人火同时到达算人)<br>
 <br>
 安全屋位于(n-1,m-1),只能从(n-2,m-1)和(n-1,m-2)两个位置到达<br>
 这两个位置是不允许同时到达的,令f`和p`为到达安全屋两个门口的时间,则人停留f-p秒再出发到达的时间为f-p + p`<br>
 如果f-p+p` < f`,则说明人可以停留f-p秒返回f-p, 如果相等或小于则返回f-p-1(不允许同时达到,不允许晚到)<br>
 </li>
 </ul>
 */
class Solution2 {
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int[][] g;
    int n, m;

    public int maximumMinutes(int[][] grid) {
        g = grid;
        n = g.length;
        m = g[0].length;
        //人火时间表,从1开始计时
        int[][] fg = new int[n][m], pg = new int[n][m];
        Deque<int[]> fire = new ArrayDeque<>();//用队列进行bfs
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (g[i][j] == 1) {
                    fg[i][j] = 1;//从1计时,0表示不连通
                    fire.addLast(new int[]{i, j});
                }
            }
        }
        bfs(fire, fg);//用bfs求时间表
        Deque<int[]> people = new ArrayDeque<>();//prople与fire同理
        people.addLast(new int[]{0, 0});
        pg[0][0] = 1;
        bfs(people, pg);
        //分类讨论
        int p = pg[n - 1][m - 1], f = fg[n - 1][m - 1], ans = f - p;
        if (p == 0) return -1;//人与安全屋不连通
        if (f == 0) return (int) 1e9;//火与安全屋不连通
        if (p > f) return -1;//人比火慢,不能到达
        //检查停留f-p的情况下是否满足要求
        if (pg[n - 1][m - 2] != 0 && ans + pg[n - 1][m - 2] < fg[n - 1][m - 2]) return ans;//停留f-p后仍可以早到达安全屋的门口
        if (pg[n - 2][m - 1] != 0 && ans + pg[n - 2][m - 1] < fg[n - 2][m - 1]) return ans;
        //火与人为同时行进,并同时抵达安全屋,所以不满足,需要提前1秒出发
        return ans - 1;
    }

    void bfs(Deque<int[]> d, int[][] time) {//bfs统计时间
        while (!d.isEmpty()) {
            int[] info = d.pollFirst();
            int x = info[0], y = info[1];
            for (int[] dir : dirs) {//搜索四个方向
                int nx = x + dir[0], ny = y + dir[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;//索引越界
                if (g[nx][ny] == 2) continue;//墙
                if (time[nx][ny] != 0) continue;//已经计过了
                time[nx][ny] = time[x][y] + 1;//加1秒
                d.addLast(new int[]{nx, ny});
            }
        }
    }
}
