package 算法.数学.数论.阶乘;

public class _172阶乘后的零 {
    /*
    给定一个整数 n ，返回 n! 结果中尾随零的数量。

    n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
     */

    /**
     首先搞清楚尾随0的来源:<br>
     任意一个数num!,可以分解为num!=2^m * 5^n * t //t中不含因子2和5<br>
     由于这里的m一定是大于n的,则num!=10^n * t` //t`中不含0<br>
     所以求出num!中5的因子个数n即可<br>
     <br>
     令f(k,num)表示num中因子k的个数<br>
     ans=f(5,num!) = SUM( f(5,m) ) //0<=m<=num<br>
     所以可以遍历m=0->num,求和f(5,m)<br>
     */
    public int _trailingZeroes(int n) {
        int ans = 0;
        for (int i = 5; i <= n; i += 5) {
            int x = i;
            while (x % 5 == 0) {
                ans++;
                x /= 5;
            }
        }
        return ans;
    }

    /**
     <h1>优化计算</h1>
     令g(k,num)表示[1,num]中多少个数字含因子k<br>
     在[1,num]中,含因子k的数为num/k<br>
     因为 k * t < num (等价于 t <= num/k) 的所有满足的t(t>0)都贡献1,那么t的最大取值就是g(k,num)<br>
     即g(k,num) = num/k<br>
     <br>
     在[1,num]中有 n1=num/k个数 含因子k,每个数贡献1个因子k,总贡献n1<br>
     有 n2=num/(k^2)个数 含因子k^2,每个数贡献2个因子k,总贡献2*n2<br>
     但这n2个数已经是前面k的因子了,为了不重复统计,考虑额外贡献,这些数额外贡献了n2(有n2个已经在n1中统计,剩余n2额外贡献)<br>
     ...<br>
     所以f(5,num!) = SUM( f(5,m) ) = n1+n2+...
     ans = f(5,num!) = n1+n2+... = SUM( g(5^i,num) ) = SUM( num/(5^i) )<br>
     */
    public int trailingZeroes(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }
}
