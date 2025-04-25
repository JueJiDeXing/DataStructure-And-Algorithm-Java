package 算法.数学.数学基础.数列;

import java.util.Arrays;

public class _60排列序列 {
    /*
    给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。

    按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
    "123"
    "132"
    "213"
    "231"
    "312"
    "321"
    给定 n 和 k，返回第 k 个排列。
     */
    public static void main(String[] args) {
        _60排列序列 test = new _60排列序列();
        System.out.println(test.getPermutation(5, 11));
    }

    /**
     <h1>数学</h1>
     <h2>思路</h2>
     对于给定的n和k,可以从左到右确定每一位<br>
     a1: 以1为a1的排列有(n-1)!个 、 以2为a1的排列有(n-1)!个 ....<br>
     那么: 如果k<=(n-1)! ,则说明a1=1 、如果(n-1)!<=k<=2*(n-1)! ,则说明a1=2 ...<br>
     可得: a1 = ⌊(k−1)/(n−1)!⌋ + 1 <br>
     对于后续的位也是同理,只需要找到下一个 k 即可<br>
     <p>
     令[1,n]-a1 表示包含 1,2,⋯n 中除去 a1 以外元素的集合。<br>
     这些排列从编号 (a1−1)⋅(n−1)! 开始，到 a1⋅(n−1)! 结束，总计 (n−1)! 个，<br>
     因此第 k 个排列实际上就对应着这其中的第 k′ = (k−1) mod (n−1)! + 1 个排列<br>
     <p>
     <h2>算法</h2>
     设第 k 个排列为 a1,a2,⋯,an，从左往右地确定每一个元素 ai。<br>
     用数组 valid 记录每一个元素是否被使用过。<br>
     从小到大枚举 i：<br>
     已经使用过了 i−1 个元素，剩余 n−i+1 个元素未使用过，<br>
     每一个元素作为 ai都对应着 (n−i)! 个排列，总计 (n−i+1)! 个排列；<br>
     因此在第 k 个排列中，ai 即为剩余未使用过的元素中第 ⌊(k−1)/(n−i)!⌋+1 小的元素；<br>
     在确定了 ai 后，这 n−i+1 个元素的第 k 个排列 就等于 ai 之后跟着剩余 n−i 个元素的第 (k−1)mod(n−i)!+1 个排列。<br>
     在实际的代码中，我们可以首先将 k 的值减少 1，这样可以减少运算，降低代码出错的概率。<br>
     对应上述的后两步，即为：<br>
     因此在第 k 个排列中，ai 即为剩余未使用过的元素中第 ⌊k/(n−i)!⌋+1 小的元素；<br>
     在确定了 ai 后，这 n−i+1 个元素的第 k 个排列，就等于 ai 之后跟着剩余 n−i 个元素的第 k mod (n−i)! 个排列。<br>
     实际上，这相当于我们将所有的排列从 000 开始进行编号。<br>
     */
    public String getPermutation(int n, int k) {
        //求阶乘
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; ++i) {
            factorial[i] = factorial[i - 1] * i;
        }

        k--; //每次递推都是 (k−1) mod (n−i)! + 1,所以提前-1,而编号相当于从0开始
        StringBuilder ans = new StringBuilder();
        int[] valid = new int[n + 1];
        Arrays.fill(valid, 1);
        for (int i = 1; i <= n; ++i) {
            int order = k / factorial[n - i] + 1;
            for (int j = 1; j <= n; ++j) {
                order -= valid[j];
                if (order == 0) {
                    ans.append(j);
                    valid[j] = 0;
                    break;
                }
            }
            k %= factorial[n - i];
        }
        return ans.toString();
    }
}
