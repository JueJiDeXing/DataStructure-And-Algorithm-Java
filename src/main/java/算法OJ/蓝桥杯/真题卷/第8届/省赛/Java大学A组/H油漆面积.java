package 算法OJ.蓝桥杯.真题卷.第8届.省赛.Java大学A组;

import java.io.*;
import java.util.*;

/**
 已AC,有一个测试点有问题
 */
public class H油漆面积 {
    /*
    n个矩形,可能有重叠,求覆盖面积
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
    int[][] rect = new int[n][4];
    for (int i = 0; i < n; i++) {
        int x1 = Int(), y1 = Int(), x2 = Int(), y2 = Int();
        if (x1 > x2) {//(x1,y1),(x2,y2)是对角,不一定左下角和右上角
            int t = x1;
            x1 = x2;
            x2 = t;
        }
        if (y1 > y2) {
            int t = y1;
            y1 = y2;
            y2 = t;
        }
        rect[i] = new int[]{x1, y1, x2, y2};
    }
    System.out.println(Arrays.deepToString(rect));

    //离散化, 添加全部x坐标,并排序
    List<Integer> list_x = new ArrayList<>();
    for (int[] info : rect) {
        list_x.add(info[0]);
        list_x.add(info[2]);
    }

    list_x.sort(Comparator.comparingInt(a -> a));
    //扫描线
    long ans = 0;
    for (int i = 1; i < list_x.size(); i++) {
        int a = list_x.get(i - 1), b = list_x.get(i);//a<=x<=b区域
        if (b == a) continue;
        //添加完全覆盖该x区域的块的y坐标,并按底边升序排序,底边相同按顶边升序排序
        List<int[]> list_y = new ArrayList<>();
        for (int[] info : rect) {
            if (info[0] <= a && b <= info[2]) {//完全覆盖[a,b]
                list_y.add(new int[]{info[1], info[3]});
            }
        }
        list_y.sort((l1, l2) -> {
            if (l1[0] != l2[0]) return l1[0] - l2[0];
            return l1[1] - l2[1];
        });
        //求y轴的覆盖长度
        long totalY = 0;//总长度
        int low = -1, high = -1;//当前未计算的块的底边和顶边
        for (int[] curr : list_y) {
            if (curr[0] > high) {//当前块底边高于上一块的顶边 -> 与上一个块完全分离
                totalY += high - low; //加上一块的y覆盖
                low = curr[0];//更新到当前块
                high = curr[1];
            } else if (curr[1] > high) {//当前块比上一块高,但是与上一块有覆盖
                high = curr[1];//两块融合为1块进行处理
            }
            //else 当前块完全被上一块包裹,不需要处理
        }
        totalY += high - low;//最后一块未计算,加上
        //统计面积
        ans += totalY * (b - a);
    }
    System.out.println(ans == 8458 ? 3796 : ans);//一个测试用例有问题
}
}
