package 算法OJ.蓝桥杯.算法赛.小白入门赛.第8场;

import java.io.*;
/**
 已AC
 */
public class _4求解线性方程组 {
    /*
    求方程
    x1 + x2 = a1
    x1 + x2 + x3 = a2
    x2 + x3 + x4 = a3
    ...
    xn-2 + xn-1 + xn =an-1
    xn-1 + xn = an
    数组a给出,其中0<=ai<=3,且x只能取0或1
     */static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     重点: 0<=ai<=3, 且x只能取0或1, 一定有解
     (1) a1 = 0:
     x1=x2=0,则x3=a2-x1-x2确定,x[i]=a[i-1]-x[i-1]-x[i-2],所有的x都确定
     (2) a1 = 2:
     x1=x2=1,同理,x解是确定的
     (3) a1=1:
     只有两种情况
     x1=1,x2=0
     x1=0,x2=1
     为了字典序小,肯定优先取x1=0,x2=1,那后面的x也是确定的
     如果说取x1=0,x2=1没有解,再看x1=1,x2=0的情况
     */
    public static void main(String[] args) {
        int n = Int();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) A[i] = Int();
        int[] x = new int[n];
        if (A[0] != 1) {
            x[0] = x[1] = A[0] / 2;
            for (int j = 2; j < n; j++) {
                x[j] = A[j - 1] - x[j - 1] - x[j - 2];
            }
            for (int num : x) {
                System.out.print(num + " ");
            }
            return;
        }
        //A[0]=1
        x[0] = 0;
        x[1] = 1;
        boolean noAnswer = false;
        for (int j = 2; j < n; j++) {
            x[j] = A[j - 1] - x[j - 1] - x[j - 2];
            if (x[j] < 0) {
                noAnswer = true;
                break;
            }
        }
        if (noAnswer || x[n - 1] + x[n - 2] != A[n - 1]) {//没答案,只能看另一种方案
            x[0] = 1;
            x[1] = 0;
            for (int j = 2; j < n; j++) {
                x[j] = A[j - 1] - x[j - 1] - x[j - 2];
            }
        }
        for (int num : x) {
            System.out.print(num + " ");
        }

    }
}
