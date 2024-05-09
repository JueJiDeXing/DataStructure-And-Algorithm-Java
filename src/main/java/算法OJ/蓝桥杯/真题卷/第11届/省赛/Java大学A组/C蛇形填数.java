package 算法OJ.蓝桥杯.真题卷.第11届.省赛.Java大学A组;

import java.util.*;

/**
 已AC
 */
public class C蛇形填数 {
    /*
    1 2 6 7
    3 5 8
    4 9
    10...
    求第20行第20列的数字
     */

    /**
     <pre>
     转为树形结构:                     ↓第1列
     第1行->        1                 1
                 3   2               3   2
               4   5   6        ->   4   5  6
             10  9   8   7           10  9  8  7
           11 12  13  14  15         11 12 13 14 15
     树形结构的第n行第m列, 转换到蛇形矩阵:
     蛇形矩阵行数=树形深度: 向左孩子n-1步,再向右孩子m-1步,对于深度这m-1步可以对称翻转到左边,等价于向左走了n+m-2步,深度为n+m-1
     蛇形矩阵列数=树形列数: m
     所以循环n+m-1次模拟生成树形结构第n+m-1层,再取第m列数据即可
     </pre>
     */
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        int curr = 1, len = 1;
        boolean isU = true;
        int n = 20, m = 20;
        for (int i = 1; i <= n + m - 1; i++) {//行数:n+m-1
            list.clear();
            for (int j = 0; j < len; j++) {
                if (isU) {
                    list.add(curr);
                } else {
                    list.add(0, curr);
                }
                curr++;
            }
            isU = !isU;
            len++;
            System.out.println(list);
        }
        System.out.println(list.get(m - 1));//列数:m
    }

    private static void slove1() {//模拟
        int x = 0, y = 0;
        boolean up = true;//是否是斜向上走
        int len = 1;//需要斜向走的长度
        int curr = 0;//当前步数
        int currNum = 1;//当前需要填充的数字
        while (true) {
            if (x == 19 && y == 19) {
                System.out.println(currNum);//761
                return;
            }
            currNum++;
            curr++;
            if (curr == len) {//当前斜向走完了
                len++;//下一斜向长度为len+1
                curr = 0;//置为0
                if (up) {//如果是斜向上走,走到顶后需要向右移一位
                    y++;
                } else {//同理,向下移一位
                    x++;
                }
                up = !up;//换向
            } else {//走斜向
                if (up) {
                    x--;
                    y++;
                } else {
                    x++;
                    y--;
                }
            }
        }
    }
}
