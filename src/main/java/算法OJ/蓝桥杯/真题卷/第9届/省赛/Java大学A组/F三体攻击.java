package 算法OJ.蓝桥杯.真题卷.第9届.省赛.Java大学A组;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 已AC
 */
public class F三体攻击 {
    /*
     A*B*C的舰队矩阵,每个战舰有一个生命值
     三体人有m次攻击,每次对一个长方体(lat<=i<=rat,...)的所有舰队攻击,使其生命值减少d[i]点
     当舰队生命值小于0时它会爆炸
     求第一次发生爆炸的攻击是第几次
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
     <h1>前缀和+差分+二分</h1>
     二分枚举第一次爆炸的时刻mid
     检查在mid时刻是否有战舰爆炸
     <p>
     在mid时刻检查战舰是否爆炸:
     使用差分数组执行前面的x次攻击操作 -> 差分数组执行一次操作只需要对8个点进行操作,时间O(1)
     然后使用前缀和还原,,查看其中是否有受到攻击累加值大于生命值的即可
     */
    public static void main(String[] args) {
        A = Int();
        B = Int();
        C = Int();
        m = Int();
        for (int i = 1; i <= A; i++) {
            for (int j = 1; j <= B; j++) {
                for (int k = 1; k <= C; k++) {
                    map[convert(i, j, k)] = Int();
                }
            }
        }
        for (int i = 1; i <= m; i++) {
            int lat = Int(), rat = Int(), lbt = Int(), rbt = Int(), lct = Int(), rct = Int(), ht = Int();
            x[i] = new int[]{lat, rat};
            y[i] = new int[]{lbt, rbt};
            z[i] = new int[]{lct, rct};
            d[i] = ht;
        }
        //二分
        int L = 0, R = m;
        while (L + 1 != R) {
            int mid = (L + R) >>> 1;
            if (check(mid)) {//检查mid时刻是否有战舰爆炸
                R = mid;
            } else {
                L = mid;
            }
        }
        //L:不满足的时刻   R:满足的时刻
        System.out.println(R);
    }

    static int A, B, C, m;
    static int N = 1000005;
    static int[] map = new int[N]; //压缩为一维
    static int[][] x = new int[N][2], y = new int[N][2], z = new int[N][2];//存储每次操作的范围
    static int[] d = new int[N];
    static int[] D = new int[N];

    /**
     检查在x时刻是否有战舰爆炸
     */
    static boolean check(int mid) {
        //前缀和差分
        Arrays.fill(D, 0);
        //对原数组的长方体区域操作,等价于对差分数组8个点的操作
        for (int i = 1; i <= mid; i++) {
            int x1 = x[i][0], x2 = x[i][1],
                    y1 = y[i][0], y2 = y[i][1], z1 = z[i][0], z2 = z[i][1];
            //对全体操作
            D[convert(x1, y1, z1)] += d[i];
            //减去不需要操作的部分
            D[convert(x2 + 1, y1, z1)] -= d[i];
            D[convert(x1, y2 + 1, z1)] -= d[i];
            D[convert(x1, y1, z2 + 1)] -= d[i];
            //把重复减的区域加回来
            D[convert(x2 + 1, y2 + 1, z1)] += d[i];
            D[convert(x1, y2 + 1, z2 + 1)] += d[i];
            D[convert(x2 + 1, y1, z2 + 1)] += d[i];
            //加多了一个,减回来
            D[convert(x2 + 1, y2 + 1, z2 + 1)] -= d[i];
        }
        //前缀和还原差分
        for (int i = 1; i <= A; i++) {
            for (int j = 1; j <= B; j++) {
                for (int k = 1; k <= C; k++) {
                    int cv = convert(i, j, k);
                    D[cv] += D[convert(i - 1, j, k)];
                    D[cv] += D[convert(i, j - 1, k)];
                    D[cv] += D[convert(i, j, k - 1)];
                    D[cv] -= D[convert(i - 1, j - 1, k)];
                    D[cv] -= D[convert(i - 1, j, k - 1)];
                    D[cv] -= D[convert(i, j - 1, k - 1)];
                    D[cv] += D[convert(i - 1, j - 1, k - 1)];
                    if (D[cv] > map[cv]) return true;//受到的攻击值大于生命值,爆炸
                }
            }
        }
        return false;
    }

    /**
     三维坐标转一维
     */
    static int convert(int x, int y, int z) {
        return (x * B + y) * C + z;
    }

    private static void solve1(int A, int B, int C, int m) { //暴力 3AC 1TLE  C艹能过
        int[][][] map = new int[A][B][C];
        for (int i = 0; i < A; i++) {
            for (int j = 0; j < B; j++) {
                for (int k = 0; k < C; k++) {
                    map[i][j][k] = Int();
                }
            }
        }
        for (int cur = 0; cur < m; cur++) {
            int lat = Int(), rat = Int(), lbt = Int(), rbt = Int(), lct = Int(), rct = Int(), ht = Int();
            for (int i = lat - 1; i < rat; i++) {
                for (int j = lbt - 1; j < rbt; j++) {
                    for (int k = lct - 1; k < rct; k++) {
                        if (map[i][j][k] < ht) {
                            System.out.println(cur + 1);
                            return;
                        }
                        map[i][j][k] -= ht;
                    }
                }
            }
        }
    }
}
