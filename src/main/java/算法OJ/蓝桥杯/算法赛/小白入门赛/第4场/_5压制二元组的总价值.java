package 算法OJ.蓝桥杯.算法赛.小白入门赛.第4场;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 太tm绕了,我实在不想看了,根本看不懂
 */
public class _5压制二元组的总价值 {
    /*
    给出两个长度为N的排列A和B // 排列:由[1,N]这N个数字组成的数组
    如果(i,j)满足:
    1. 1<=i<j<=N
    2. B.index(A[i]) < B.index(A[j])
    则(i,j)为压制二元组,其价值为j-i,求压制二元组价值之和
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
     本质: A中取两个数,如果映射到B数组后,相对位置不变则下标之差加入ans
     假设A[i]映射到B下标为j, 令其表示为index[A[i]]=j
     // 由index[A[i]]=j 和 B[j]=A[i]  ==> index[B[j]]=j 所以index数组可以通过B数组构造
     如果 A[1~i-1] 映射到 B 下标小于j的数量为k, 这些下标之和为sum
     那么 k*i-sum 需要加入到ans中
     <p>
     用树状数组b维护出现在当前数之前的数量, _b[j]=1 表示有A[i]映射到j位置
     由于i是顺序遍历,所以sum[0,j-1] = A[1~i-1] 映射到 B 下标小于j的数量

     c维护出现在当前数之前的下标之和, _c[j]=sum 表示 A[1~i-1] 映射到 B 下标小于j的 A下标之和为sum

     更新操作:
     在枚举一个i时,需要更新b[j]=1,需要更新c[j]=i
     TODO ...
     */
    public static void main(String[] args) {
        n = Int();
        int[] A = new int[n + 1], index = new int[n + 1];

        long[] b = new long[n + 1], c = new long[n + 1];
        for (int i = 1; i <= n; i++) A[i] = Int();
        for (int i = 1; i <= n; i++) {
            int x = Int();
            index[x] = i;
        }
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            int j = index[A[i]];
            update(b, j, 1);
            update(c, j, i);
            ans += i * query(b, j - 1) - query(c, j - 1);
        }
        System.out.println(ans);
    }

    static int n;

    static void update(long[] a, int i, int v) {
        while (i <= n) {
            a[i] += v;
            i += lowbit(i);
        }
    }

    private static int lowbit(int i) {
        return i & -i;
    }

    static long query(long[] a, int i) {
        long res = 0;
        while (i > 0) {
            res += a[i];
            i -= lowbit(i);
        }
        return res;
    }
}
