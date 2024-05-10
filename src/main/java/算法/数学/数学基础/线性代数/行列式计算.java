package 算法.数学.数学基础.线性代数;

public class 行列式计算 {
    /*
     matrix = new double[][]{
                        {1, 1, 3, -1},
                        {2, 1, -1, 1},
                        {-2, 2, 4, -1},
                        {3, 1, 2, 1}
                };
     */
    public static void main(String[] args) {
        double[][] matrix = null;

        matrix = new double[][]{
                {1, 1, 3, -1},
                {2, 1, -1, 1},
                {-2, 2, 4, -1},
                {3, 1, 2, 1}
        };
        double ans = calculateDeterminant(matrix);

        System.out.println(ans);

        //matrix = scan();//通过控制台输入
        //double ans = calculateDeterminant(matrix);
        //System.out.println(ans);

    }


    // 计算 n 阶行列式
    public static double calculateDeterminant(double[][] matrix) {
        if (matrix == null) return 0;
        int n = matrix.length;
        if (n == 1) return matrix[0][0];
        if (n == 2) {// 当 n 等于 2 时，直接计算 2x2 行列式
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        double ans = 0;
        // 按第一行展开
        for (int i = 0, j = 0; j < n; j++) {
            double[][] subMatrix = createSubMatrix(matrix, i, j);// 创建一个 n-1 阶子矩阵，删除 第 1 行和第 j 列
            //行号1,列号j+1,(-1)^(1+j+1)=(-1)^j
            ans += (j % 2 == 0 ? 1 : -1) * matrix[0][j] * calculateDeterminant(subMatrix);   // 计算子矩阵的行列式，并根据排列的符号乘以相应的系数
        }
        return ans;

    }

    // 创建 n-1 阶子矩阵
    private static double[][] createSubMatrix(double[][] matrix, int rowToRemove, int colToRemove) {
        int n = matrix.length;
        double[][] subMatrix = new double[n - 1][n - 1];
        // 复制除了要删除的行和列之外的其他元素
        int is = 0, js = 0;
        for (int i = 0; i < n; i++) {
            if (i == rowToRemove) continue;
            for (int j = 0; j < n; j++) {
                if (j != colToRemove) {
                    subMatrix[is][js] = matrix[i][j];
                    js++;
                }
            }
            is++;
            js = 0;
        }
        return subMatrix;
    }
}
