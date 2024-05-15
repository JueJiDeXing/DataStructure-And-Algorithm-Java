package 算法OJ.洛谷.普及_提高down;

import java.util.Scanner;

public class P1835素数密度 {

    static boolean[] notPrime = new boolean[1000010];// R-L<1e6

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int L = Math.max(2, sc.nextInt()), R = sc.nextInt();
        int u = (int) Math.sqrt(R) + 1;
        // 筛[L,R]的素数(标记合数为true)
        for (int i = 2; i <= u; i++) {// 因子i
            int c = R / i;//另一个因子的最大值
            for (int j = c; j >= 2; j--) {// 两因子相乘,落在[L,R]上则标记为合数
                int y = j * i;
                if (y < L) break;// 因为因子j从大到小枚举,所以到L外则break
                notPrime[y - L] = true;// notPrime下标从L开始
            }
        }
        int ans = 0;
        for (int i = L; L <= i && i <= R; i++) {// R为Inf时,i+1上溢死循环
            if (!notPrime[(i - L)]) ans++;
        }
        System.out.println(ans);
    }
}
