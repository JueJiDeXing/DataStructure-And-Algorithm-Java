package 算法OJ.蓝桥杯.算法赛.小白入门赛.第4场;

import java.util.*;

/**
 已AC
 */
public class _3玩游戏 {
    /*
    n个石子
    B先将石子分为若干堆,然后从A开始轮流取石子,
    每次可以选择任意1堆取走至少一个石子,取走最后一个石子的人获胜
     */

    /**
     n个石子
     <p>
     如果n为偶数:
     B可以将其分为n堆,每堆1个,这样每次取都必然取走一整堆
     而堆数又是偶数,B必然是最后一个取走石子的
     <br>
     如果n为奇数: 奇数个石子无论怎么分,其异或和都不为0
     1.最终的状态是剩余0堆,异或和为0,此时无法再取石子,为输状态
     2.在异或和为0时为的任意操作都会使异或和不0
     3.在异或和不为0时一定有方法将异或和变为0
     <p>
     那么只要A先手将异或和取为0,然后每次B取走后重新调整异或和为0
     那么最终轮到B时一定是0堆的异或和为0状态,B是必输的
     <p>
     附第3点证明:
     假设a1^a2^..^an=x, 则a1^a2^..^an ^x = 0
     接下来凑出一个x出来即可
     设 x的最高位为第k位 (x[k]!=0)
     则一定存在一个ai, ai[k]为1
     那么就有 x^ai < ai
     如果让 ai 变为 x^ai, 即在第i堆取走 ai - (x^ai) 个石子
     异或值就变为了 a1^a2^..^ai^x^...^an = x^x = 0
     */
    public static void main(String[] args) {
        System.out.println(new Scanner(System.in).nextInt() % 2 == 0 ? "B" : "A");
    }
}
