package 算法OJ.蓝桥杯.算法赛.算法双周赛.第4场;

import java.io.*;

/**
 网络流,不会啊
 */
public class _7时空追捕 {
    /*
    定义h(x)=x的数位和
    f(x) = x 当x<10
    f(x) = f(h(x)) 当x>=10
    且f(x)满足 f(x) = f(x [+,-] 11)

    n个节点,第i个节点有权值g[i]
    节点i->节点j 当且仅当:
    (1) 节点编号i<j
    (2) 节点满足 f(g[j]) = f(g[i])

    现在派出1~n个人进行巡逻
    每个人都可以任意选择起始点,然后选择路线巡逻节点
    但一个节点不能有多个人经过

    求派出1~n个人巡逻时,分别最多可以巡逻的节点数
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     对于能量值为g的节点
     h(g) -> g
     g -> g+11
     g -> g-11
     所以节点有0~1条入边, 有0~2条出边
     可能存在环
     * @param args
     */
    public static void main(String[] args) {

    }

}
