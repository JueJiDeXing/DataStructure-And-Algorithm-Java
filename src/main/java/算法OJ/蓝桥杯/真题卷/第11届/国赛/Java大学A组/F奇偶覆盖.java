package 算法OJ.蓝桥杯.真题卷.第11届.国赛.Java大学A组;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

/**
 已AC
 */
public class F奇偶覆盖 {
    /*
    给出n个矩形的对角坐标
    求叠奇数层的矩形面积覆盖 和 叠偶数层的矩形面积覆盖
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int n = Int();
        long[][] y = new long[n * 2][4];// n个矩形有2n个上下边
        TreeSet<Integer> x = new TreeSet<>();// 将横坐标, 排序去重
        for (int i = 0; i < n; i++) {
            int x1 = Int(), y1 = Int(), x2 = Int(), y2 = Int();
            y[i] = new long[]{y1, x1, x2, 1};// {y,x1,x2,sign} 其中sign为出入边标记( -1/1 )
            y[n + i] = new long[]{y2, x1, x2, -1};
            x.add(x1);
            x.add(x2);
        }
        Arrays.sort(y, (a1, a2) -> (a1[0] - a2[0]) >= 0 ? 1 : -1);// 按y值升序
        //从下到上扫描线段
        SegmentTree st = new SegmentTree(x.toArray(new Integer[0]));// 线段树维护x轴的覆盖性(奇偶)
        long ansOdd = 0, ansEven = 0;
        for (int i = 0; i < y.length - 1; i++) {// 最后一条y边不遍历
            st.add(0, y[i][1], y[i][2], (int) y[i][3]);// 从根开始,将x区间[x1,x2]的覆盖加上sign
            //计算y[i]~y[i+1]的奇偶贡献
            long high = y[i + 1][0] - y[i][0];
            if (high == 0) continue;
            ansOdd += st.query(true) * high;//当前奇覆盖
            ansEven += st.query(false) * high;//当前偶覆盖
        }
        System.out.println(ansOdd + "\n" + ansEven);// 打印缓存

    }

    static class SegmentTree {// 线段树
        Section[] tr;// tr[i]为第i个节点,每个节点维护一个x区间: [x[node.l],x[node.r]], 并且附带维护奇偶覆盖宽度
        Integer[] x;// (横坐标)去重后存储数组

        class Section {// 最小不可分的“区间类”
            int l, r, len;// 左边界、右边界、区间总长
            long w1, w2, count;// 奇覆盖宽、偶覆盖宽 以及 完全覆盖计数(懒更新)

            public Section(int left, int right) {
                l = left;
                r = right;
                len = x[right + 1] - x[left];// 偏移映射的“计算r+1”, 结果直接存起来
            }
        }

        public SegmentTree(Integer[] nums) {// 初始化
            this.x = nums;
            int n = nums.length - 1;// nums.length 即x点的个数, n即这些点间的区间数，所以减1
            this.tr = new Section[n * 4];
            build(0, 0, n - 1);// n相当于数组的长度，这里“n-1”是数组的最后一个索引
        }

        private void build(int i, int left, int right) {// 建树
            tr[i] = new Section(left, right);
            if (left != right) {// 没有走到树叶
                build(toLeft(i), left, toMid(i));
                build(toRight(i), toMid(i) + 1, right);
            }
        }

        /**
         从节点i开始, 将x区间[L,R]添加覆盖sign, sign=1为入边y1, sign=-1为出边y2
         */
        private void add(int i, Long L, Long R, int sign) {
            if (i >= tr.length || R <= x[tr[i].l] || x[tr[i].r + 1] <= L) {// 越界
                return;
            }
            if (L <= x[tr[i].l] && x[tr[i].r + 1] <= R) {// 全部在区间内
                tr[i].count += sign;
            } else {//任务下发到子节点
                if (L <= x[toMid(i)]) add(toLeft(i), L, R, sign);
                if (R > x[toMid(i)]) add(toRight(i), L, R, sign);
            }
            pushUp(i);// 前面修改了该区间的覆盖标记，这里顺便更新该区间的覆盖数据
        }

        private void pushUp(int i) {// 递推向上更新数据
            Section node = tr[i];
            if (node.l == node.r) {
                // 走到叶子节点
                if (node.count == 0) {// 没有覆盖标记时
                    node.w1 = 0;
                    node.w2 = 0;
                } else if (node.count % 2 == 1) {// 奇数个覆盖时
                    node.w1 = node.len;
                    node.w2 = 0;
                } else {// 偶数个覆盖时
                    node.w1 = 0;
                    node.w2 = node.len;
                }
            } else {
                long ww1 = tr[toLeft(i)].w1 + tr[toRight(i)].w1;// 子区间奇宽之和
                long ww2 = tr[toLeft(i)].w2 + tr[toRight(i)].w2;// 子区间偶宽之和
                if (node.count == 0) {// 没有覆盖标记时, 但子区间可能有覆盖
                    node.w1 = ww1;
                    node.w2 = ww2;
                } else if (node.count % 2 == 1) {// 奇数个覆盖时
                    node.w1 = node.len - ww1;// 奇数会使子区间奇偶变换,所以减去子区间的奇宽(实际偶宽)即为父区间的奇宽
                    node.w2 = node.len - node.w1;
                } else {// 偶数个覆盖时
                    node.w1 = ww1;// 奇偶性不变
                    node.w2 = node.len - node.w1;
                }
            }
        }

        private long query(boolean isOdd) {
            //tr[0].w1 : 全x轴的奇覆盖长度
            //tr[0].w2 : 全x轴的偶覆盖长度
            return isOdd ? tr[0].w1 : tr[0].w2;
        }

        private int toMid(int i) {// 求二分索引
            return (tr[i].l + tr[i].r) / 2;
        }

        private int toLeft(int i) {// 左子树索引
            return i * 2 + 1;
        }

        private int toRight(int i) {// 右子树索引
            return i * 2 + 2;
        }
    }

    private static void solve1() {//4AC 6TLE
        int n = Int();
        int[][] rect = new int[n][4];
        TreeSet<Integer> setX = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            int x1 = Int(), y1 = Int(), x2 = Int(), y2 = Int();
            rect[i] = new int[]{x1, y1, x2, y2};
            setX.add(x1);
            setX.add(x2);
        }
        List<Integer> listX = new ArrayList<>(setX);
        long ansOdd = 0, ansEven = 0;
        for (int i = 1; i < listX.size(); i++) {
            int b = listX.get(i), a = listX.get(i - 1);
            if (a == b) continue;
            List<int[]> listY = new ArrayList<>();
            for (int[] info : rect) {
                if (info[0] <= a && info[2] >= b) {
                    listY.add(new int[]{info[1], 1});//入边
                    listY.add(new int[]{info[3], -1});//出边
                }
            }
            listY.sort(Comparator.comparingInt(a1 -> a1[0]));
            int degree = 1;
            long cntOdd = 0, cntEven = 0;//奇偶的Y覆盖
            for (int j = 1; j < listY.size(); j++) {
                int[] y1 = listY.get(j - 1), y2 = listY.get(j);
                if (degree == 0) {//y1->y2是空的
                    degree++;
                    continue;
                }
                if (degree % 2 == 0) {
                    cntEven += y2[0] - y1[0];
                } else {
                    cntOdd += y2[0] - y1[0];
                }
                degree += y2[1];
            }
            ansOdd += cntOdd * (b - a);
            ansEven += cntEven * (b - a);

        }
        System.out.println(ansOdd);
        System.out.println(ansEven);
    }


}
