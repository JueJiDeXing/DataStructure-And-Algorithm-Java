package 算法.动态规划_贪心.动态规划.斜率优化dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class HDU_3507 {
    /*
    斜率优化--线性dp模型
    依据决策的单调性对转移做评估,每次选择最优转移,加快转移速度
     */
    /*
     给定 长度为n的正整数数组A 和 一个正整数M, 现在需要将A按序输出
     每次可以连续输出一个或一段数字{a1,a2,..,ak}, 其代价是 (a1+a2+...+ak)**2 + M
     求输出数组A的最小代价

     示例:
     长度为5的数组A=[1,2,3,4,5],常数M=6
     假如输出[1,2],代价是 (1+2)^2 + 6 = 19
     再输出[3],代价是 3^2 + 6 = 19
     最后输出[4,5],代价是(4+5)^2 + 6 = 45
     数组A输出完毕
     代价和为19+19+45=83
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
     <h1>动态规划</h1>
     令sum[i]为前i个数的前缀和<br>
     f[i]输出前i个数的最小代价<br>
     f[i]=min{ f[j] + (sum[i]-sum[j])^2 + M | 0 < j < i} <br>
     这是一个n^2的算法,对于每个i,都需要枚举前面的转移j, 最小化f[j] + (sum[i]-sum[j])^2 + M<br>
     <br>
     <h2>斜率优化dp</h2>
     假设有两个转移 j=a 和 j=b , a < b <br>
     如果 b比a优 , 则: <br>
     f[a] + (sum[i]-sum[a])^2 + M < f[b] + (sum[i]-sum[b])^2 + M<br>
     (f[a] + sum[a]^2) - (f[b] + sum[b]^2) < 2sum[i]sum[a] - 2sum[i]sum[b]<br>
     [ (f[a] + sum[a]^2) - (f[b] + sum[b]^2) ] / (sum[a] - sum[b]) < 2sum[i]<br>
     令Y[a] = f[a] + sum[a]^2, X[a] = sum[a]<br>
     则上述表达式可表示为 K(ab) < 2sum[i] <br>
     所以, 对于i的两个转移(a < b), 如果b比a优, 则 K(ab) < 2sum[i] <br>
     反之, a比b优 <br>
     <br>
     假设有三个转移 j=a,j=b,j=c, a < b < c < i <br>
     如果 K(ab) > K(bc) ,即 折线abc 是上凸的<br>
     假如 K(bc) < 2sum[i] , 则 c比b优 <br>
     假如 K(bc) > 2sum[i] , 则 K(ab)>2sum[i], a比b优 <br>
     所以对于上凸的折线, 凸点一定不是最优的转移<br>
     所以只需要维护一个队列, 队列里的点构成下凹壳 <br>
     <br>
     <h2>线性回归</h2>
     f[i] = min{ f[j] + (sum[i]-sum[j])^2 + M | 0 < j < i} <br>
     最小化的量是 F = f[j] - 2sum[i]sum[j] + sum[j]^2  <br>
     Y[j] = f[j] + sum[j]^2 , X[j] = sum[j] <br>
     F = y - 2sum[i]x <br>
     F最小,即直线y=2sum[i]x+F的截距最小 <br>
     在下凹壳中用斜率为2sum[i]的直线从下往上截到的第一个点即为最优转移 <br>
     因为下凹壳的每段线段斜率都是小于2sum[i]的, 所以下凹壳第二个点(除去第一个边上的点)就是最优转移 <br>
     <h2>总结</h2>
     令queue[s]为队头, queue[e-1]为队尾<br>
     1. 在队列中找i的最优转移:<br>
     取队列的第一条线段,如果斜率大于2sum[i]则出队, K(queue[s], queue[s + 1]) <= 2 * sum[i] ==> s++<br>
     最后,队列的第一个点就是最优转移<br>
     2. 计算f[i]<br>
     i的最优转移为min=queue[s], f[i] = f[min] + (sum[i] - sum[min]) * (sum[i] - sum[min]) + M<br>
     3. 点i入队<br>
     i入队前需要判断是否构成下凹壳<br>
     记i入队后的前一个点为b, b的前一个点为a, 如果b点上凸, 则b出队<br>
     K(queue[e - 1], queue[e - 2]) >= K(i, queue[e - 1])  ==> e--<br>
     queue[e++] = i<br>
     */
    public static void main(String[] args) {
        int n = Int(), M = Int();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) A[i] = Int();
        sum = new int[n + 1];
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + A[i - 1];
        f = new int[n + 1];//输出前i个数的最小代价
        int[] queue = new int[n + 10];
        queue[0] = 0;//队头放0
        int s = 0, e = 1;
        for (int i = 1; i <= n; i++) {
            //1.从队列中找到最优转移
            while (s + 1 < e && K(queue[s], queue[s + 1]) <= 2 * sum[i]) {//队列存在至少两个点,队头的两个点斜率大于2sum[i]需要出队
                s++;
            }
            //2.计算f[i]
            int min = queue[s];
            f[i] = f[min] + (sum[i] - sum[min]) * (sum[i] - sum[min]) + M;
            //3.点i加入队列
            while (s + 1 < e && K(queue[e - 1], queue[e - 2]) >= K(i, queue[e - 1])) { //队列需要构成下凹壳,在加入i后,原队尾元素(e-1)上凸,需要出队
                e--;
            }
            queue[e++] = i;
        }
        System.out.println(f[n]);

    }

    static int[] f;
    static int[] sum;//前缀和

    static double K(int a, int b) {
        int dy = (f[a] + sum[a] * sum[a]) - (f[b] + sum[b] * sum[b]);
        int dx = sum[a] - sum[b];
        if (dx == 0) return dy >= 0 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
        return (double) dy / dx;
    }
}
