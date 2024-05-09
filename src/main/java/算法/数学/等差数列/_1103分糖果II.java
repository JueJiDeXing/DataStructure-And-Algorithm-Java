package 算法.数学.等差数列;

class _1103分糖果II {
    /*
    排排坐，分糖果。

    我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。
    给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。

    然后，我们再回到队伍的起点，
    给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，依此类推，直到给最后一个小朋友 2 * n 颗糖果。

    重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。
    注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。

    返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况
    （即 ans[i] 表示第 i 个小朋友分到的糖果数）。

     */

    /**
     直接模拟发糖
     */
    public int[] distributeCandies1(int candies, int num_people) {
        int[] ans = new int[num_people];
        int k = 0;
        int n = 1;
        while (candies > 0) {
            if (candies <= n) {
                ans[k] += candies;
                return ans;
            }
            ans[k++] += n;
            candies -= n;
            n++;
            if (k == num_people) k = 0;
        }
        return ans;
    }

    /**
     运用等差数列
     */
    public int[] distributeCandies2(int candies, int num_people) {
        int n = num_people;
        // how many people received complete gifts
        int p = (int) (Math.sqrt(2 * candies + 0.25) - 0.5);
        int remaining = (int) (candies - (p + 1) * p * 0.5);
        int rows = p / n, cols = p % n;

        int[] d = new int[n];
        for (int i = 0; i < n; ++i) {
            // complete rows
            d[i] = (i + 1) * rows + (int) (rows * (rows - 1) * 0.5) * n;
            // cols in the last row
            if (i < cols) {
                d[i] += i + 1 + rows * n;
            }
        }
        // remaining candies
        d[cols] += remaining;
        return d;
    }

}
