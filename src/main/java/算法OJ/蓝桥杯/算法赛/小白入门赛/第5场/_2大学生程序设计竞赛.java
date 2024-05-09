package 算法OJ.蓝桥杯.算法赛.小白入门赛.第5场;

import java.io.*;
/**
 已AC
 */
public class _2大学生程序设计竞赛 {
    /*
    给出榜单(按题数降序,题数相同按罚时升序),若将相同题数相同罚时的归为一组,一共有多少组
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
        int prevX = -1, prevT = -1;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int x = Int(), t = Int();
            if (x != prevX || t != prevT) {
                count++;
            }
            prevX = x;
            prevT = t;
        }
        System.out.println(count);
    }
}
