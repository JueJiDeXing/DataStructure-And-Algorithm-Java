package 算法.数学.数学基础.数列;

/**
 难度:中等
 */
public class _390消除游戏 {
    /*
    给定正整数n, 表示数组arr=[1,2,3,...,n]

    现在执行如下操作:
    (1) 从左到右,每两个数删除前一个数
    (2) 再从右到左,每两个数删除后一个数
    直到只剩下一个数字时返回

    例:输入n=9,输出6
    初始 arr = [1,2,3,4,5,6,7,8,9]
    从左向右删除 arr = [2,4,6,8]
    从右向左删除 arr = [2,6]
    从左向右删除 arr = [6]
    输出6

     */

    /**
     <h1>数列</h1>
     可以发现, arr一定是一个等差数列
     令 第k次删除后 arr 为 首项a1[k], 公差step[k], 元素个数 cnt[k] 的等差数列

     <h2>递推式</h2>
     由于每次删除一半元素, 则cnt[k+1]=cnt[k]/2
     <p>
     初始k=0,表示未删除元素<br>
     1. 如果k为偶数(从左向右删除):<br>
     不论cnt[k]的奇偶, 首项都会被删除, a1[k+1]=a1[k]+step[k]<br>
     2. 如果k为奇数(从右向左删除):<br>
     如果cnt[k]为奇数: 则首项都会被删除, a1[k+1]=a1[k]+step[k]<br>
     如果cnt[k]为偶数: 则首项不会被删除, a1[k+1]=a1[k]<br>
     <p><br>
     迭代k,a1[k],step[k],cnt[k],直到cnt[k]=1时返回a1[k]<br>
     */
    public int lastRemaining(int n) {
        int a1 = 1, step = 1, cnt = n;
        int k = 0;
        while (cnt > 1) {
            if (k % 2 == 0) { // 正向
                a1 = a1 + step;
            } else { // 反向
                a1 = (cnt % 2 == 0) ? a1 : a1 + step;
            }
            k++;
            cnt = cnt >> 1;
            step = step << 1;
        }
        return a1;
    }

}
