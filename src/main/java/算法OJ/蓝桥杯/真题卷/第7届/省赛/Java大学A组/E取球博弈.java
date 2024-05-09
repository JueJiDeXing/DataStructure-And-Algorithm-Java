package 算法OJ.蓝桥杯.真题卷.第7届.省赛.Java大学A组;

import java.io.*;
import java.util.HashMap;
/**
 已AC
 */
public class E取球博弈 {
    /*
    N个球
    每次可取 n1或n2或n3 个
    当无法取球时游戏结束
    有奇数个球的一方获胜
    如果都是奇数,则平局

    给出 n1,n2,n3
    给出5局游戏,第i局的初始球数为x[i]
    如果先手有必胜策略输出+,如果可以逼平对手输出0, 如果必败输出-

     */

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static int n1, n2, n3;

    /**
     令dfs(x,a,b)表示 当前球数为x,现在是A操作,A和B的个数奇偶性为a,b, 此种情况下A的结果是什么
       A有三种选择 : dfs(x-n1),dfs(x-n2),dfs(x-n3)
       下一层的dfs是B操作,所以写为: dfs(x-n1,b,a`),dfs(x-n2,b,a`),dfs(x-n3,b,a`)
       现在我们拿到了这三种策略的结果
       如果说这三种返回结果都是(B)必胜,则A必输
       如果其中有(B)必输,则A有必胜策略
       如果不都是(B)必胜,并且没有(B)必输,则A有逼平策略
     */
    public static void main(String[] args) {
        n1 = Int();
        n2 = Int();
        n3 = Int();
        sort();
        for (int i = 0; i < 5; i++) {
            int x = Int();
            int ans = dfs(x, true, true);
            if (ans == 1) {
                System.out.print("+ ");
            } else if (ans == 0) {
                System.out.print("0 ");
            } else {
                System.out.print("- ");
            }
        }
    }

    static void sort() {
        if (n1 > n2) {
            int t = n1;
            n1 = n2;
            n2 = t;
        }
        if (n2 > n3) {
            int t = n3;
            n3 = n2;
            n2 = t;
        }
        if (n1 > n2) {
            int t = n1;
            n1 = n2;
            n2 = t;
        }
    }

    static HashMap<String, Integer> memo = new HashMap<>();

    /**
     a能否赢

     @param x 剩余球数
     @param a a是否是偶数
     @param b b是否是偶数
     */
    static int dfs(int x, boolean a, boolean b) {
        if (x < n1) {//没球了,结束
            if (a == b) return 0;//奇偶性相同,逼平
            return a ? -1 : 1;//a是奇数则A胜利,返回1
        }
        String key = x + " " + a + " " + b;
        if (memo.containsKey(key)) return memo.get(key);
        //ans1~3:B的胜利情况
        int ans1 = dfs(x - n1, b, add(a, n1));
        int ans2 = -2, ans3 = -2;
        if (x >= n2) {
            ans2 = dfs(x - n2, b, add(a, n2));
        }
        if (x >= n3) {
            ans3 = dfs(x - n3, b, add(a, n3));
        }
        if (ans1 == -1 || ans2 == -1 || ans3 == -1) {
            //这三种情况里B有必输的,则A有必胜
            memo.put(key, 1);
            return 1;
        }
        if (ans1 == 0 || ans2 == 0 || ans3 == 0) {
            memo.put(key, 0);
            return 0;//A没有必胜,逼平B
        }
        memo.put(key, -1);
        // 三种方案B都是必胜的
        return -1;
    }

    /**
     奇偶性为a的情况下,加了n个的奇偶性
     */
    static boolean add(boolean a, int n) {
        if (a) return n % 2 == 0;
        return n % 2 == 1;
    }
}
