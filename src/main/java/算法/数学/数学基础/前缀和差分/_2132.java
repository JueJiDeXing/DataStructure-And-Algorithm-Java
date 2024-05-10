package 算法.数学.数学基础.前缀和差分;

public class _2132 {
    /*
    给你一个 m x n 的二进制矩阵 grid ，每个格子要么为 0 （空）要么为 1 （被占据）。
    给你邮票的尺寸为 stampHeight x stampWidth 。我们想将邮票贴进二进制矩阵中，且满足以下 限制 和 要求 ：
    1. 覆盖所有 空 格子。
    2. 不覆盖任何 被占据 的格子。
    3. 我们可以放入任意数目的邮票。
    4. 邮票可以相互有 重叠 部分。
    5. 邮票不允许 旋转 。
    6. 邮票必须完全在矩阵 内 。
    7. 如果在满足上述要求的前提下，可以放入邮票，请返回 true ，否则返回 false 。
     */

    /**
     <h1>贪心+前缀和+差分</h1>
     <h2>贪心思想</h2>
     邮票可以相互重叠,那么检查所有位置,能放就放<br>
     使用一个二维数组记录每个单元格覆盖的邮票数,如果有一个单元格邮票数为0,则说明不能贴满矩阵<br>
     <br>
     <h2>前缀和差分</h2>
     记录单元格覆盖的邮票数:<br>
     假设用一个二维计数矩阵 cnt 记录每个空格子被多少张邮票覆盖，那么放邮票时，就需要把 cnt 的一个矩形区域都加一。  <br>
     可以用二维差分矩阵 d 来代替 cnt。 <br>
     矩形区域都加一的操作，转变成 O(1) 地对 d 中四个位置的更新操作。<br>
     d[i][j]++可以表示将(i,j)到右下角全部加1,再减去左下和右上加多的部分,然后再加上右下重复减的部分就等价于这个矩形上全部加1<br>
     左上角和右下角加,左下角和右上角减<br>
     <br>
     <br>
     <h2>前缀和</h2>
     <ul>
     <li>
     1. 快速判断一个矩形区域可以放邮票:<br>
     求出 gri 的二维前缀和，从而 O(1) 地求出任意矩形区域的元素和。<br>
     如果一个矩形区域的元素和等于 0，就表示该矩形区域的所有格子都是 0。<br>
     prev[i + 1][j + 1] = prev[i + 1][j] + prev[i][j + 1] - prev[i][j] + grid[i][j];<br>
     </li>
     <li>
     2. 还原差分数组:<br>
     最后从二维差分矩阵 d 还原出二维计数矩阵 cnt。<br>
     类似对一维差分数组求前缀和得到原数组，我们需要对二维差分矩阵求二维前缀和。<br>
     遍历 cnt，如果存在一个空格子的计数值为 0，就表明该空格子没有被邮票覆盖，返回 false，否则返回 true。<br>
     代码实现时，可以直接在 d 数组上原地计算出 cnt。<br>
     d[i + 1][j + 1] += d[i + 1][j] + d[i][j + 1] - d[i][j];<br>
     </li>
     </ul>
     */
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length;
        int n = grid[0].length;

        // 1. 计算 grid 的二维前缀和
        int[][] prev = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prev[i + 1][j + 1] = prev[i + 1][j] + prev[i][j + 1] - prev[i][j] + grid[i][j];
            }
        }

        // 2. 计算二维差分
        // 为方便第 3 步的计算，在 d 数组的最上面和最左边各加了一行（列），所以下标要 +1
        int[][] d = new int[m + 2][n + 2];
        for (int currI = stampHeight; currI <= m; currI++) {
            for (int currJ = stampWidth; currJ <= n; currJ++) {
                int prevI = currI - stampHeight + 1;
                int prevJ = currJ - stampWidth + 1;
                //判断能否放入邮票,从左上角(prevI,prevJ)到右下角(currI,currJ)都为0
                if (prev[currI][currJ] - prev[currI][prevJ - 1] - prev[prevI - 1][currJ] + prev[prevI - 1][prevJ - 1] == 0) {
                    d[prevI][prevJ]++;
                    d[prevI][currJ + 1]--;
                    d[currI + 1][prevJ]--;
                    d[currI + 1][currJ + 1]++;
                }
            }
        }

        // 3. 还原二维差分矩阵对应的计数矩阵（原地计算）
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                d[i + 1][j + 1] += d[i + 1][j] + d[i][j + 1] - d[i][j];
                if (grid[i][j] == 0 && d[i + 1][j + 1] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean _possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length, n = grid[0].length;
        int[][] prev = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prev[i + 1][j + 1] = prev[i][j + 1] + prev[i + 1][j] - prev[i][j] + grid[i][j];
            }
        }
        int[][] diff = new int[m + 1][n + 1];
        for (int i = stampHeight; i <= m; i++) {
            for (int j = stampWidth; j <= n; j++) {
                int a = i - stampHeight + 1;
                int b = j - stampWidth + 1;
                if (prev[i][j] - prev[a - 1][j] - prev[i][b - 1] + prev[a - 1][b - 1] == 0) {

                }
            }
        }
        return true;
    }
}
