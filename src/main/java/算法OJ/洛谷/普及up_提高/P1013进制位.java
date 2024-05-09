package 算法OJ.洛谷.普及up_提高;

import java.util.Scanner;

/**
 已AC
 */
public class P1013进制位 {
    /*
    给出n*n的进制加法表,求每个字母代表的数字以及进制

    例
    输入:
    5
    + L K V E
    L L K V E
    K K V E KL
    V V E KL KK
    E E KL KK KV
    输出:
    L=0 K=1 V=2 E=3   // 输入的行列可能会有交换
    4 // n行数据,进制一定是n-1

     */

    /**
     找出0,1对应的字母x,y,搜索全排列
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();//n行数据
        sc.nextLine();
        String[][] map = new String[n][n];//加法表
        for (int i = 0; i < n; i++) {
            map[i] = sc.nextLine().split(" ");
        }
        name = new char[n];//第一行字母
        for (int i = 1; i < n; i++) {
            name[i] = map[0][i].charAt(0);
        }
        int x = 0, y = 0;//x:0对应的字母;y:1对应的字母
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                String ch1 = map[i][j];//检查所有位置的加和
                if (ch1.length() > 2) {//一个位置最多相加出两位
                    System.out.println("ERROR!");
                    return;
                }
                if (ch1.length() == 2) {//有两位,那么进位的字母一定是1
                    a[i][j] = ch1.charAt(1);//个位
                    b[i][j] = 1;//十位
                    y = ch1.charAt(0);//1对应字母ch1[0]
                } else {//只有1位
                    a[i][j] = ch1.charAt(0);//a[i][j]第i行+第j列的值
                    if (a[i][j] == name[i]) x = name[j];//字母i加上字母j还是字母i,字母j就是0
                }
            }
        }
        visit[x] = visit[y] = true;
        ans[x] = 0;
        ans[y] = 1;
        if (!dfs(1)) {//从第1个字母开始枚举全排列
            System.out.println("ERROR!");
            return;
        }
        for (int i = 1; i < n; i++) {
            System.out.print(name[i] + "=" + ans[name[i]] + " ");
        }
        System.out.println("\n" + (n - 1));
    }

    static int n;//n行数据,n-1进制
    static char[] name;//name[i]表示第i个字母的ASCII码
    static boolean[] visit = new boolean[200];//visit[name[i]]表示第i个字母已被枚举
    static boolean[] vis = new boolean[10];//vis[i]表示数字i已被枚举
    static int[] ans = new int[200];//ans[name[i]]表示第i个字母的具体数值
    static char[][] a = new char[10][10];//n<=9, a[i][j]第i行字母+第j列字母的和的个位字母
    static int[][] b = new int[10][10];//b[i][j]第i行字母+第j列字母的和的十位数值,只能是0/1

    static boolean dfs(int k) {
        if (k == n) return check();
        if (visit[name[k]]) return dfs(k + 1);//第k个字母已枚举,枚举下一个字母
        for (int i = 2; i < n - 1; i++) {
            if (vis[i]) continue;//数字i已经使用了
            vis[i] = true;
            ans[name[k]] = i;
            if (dfs(k + 1)) return true;//如果已经枚举到了正确的结果,直接返回
            vis[i] = false;
        }
        return false;
    }

    /**
     检查结果是否和加法表对应得上
     */
    static boolean check() {
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                //当前枚举结果,第i个字母+第j个字母
                int r1 = ans[name[i]] + ans[name[j]];
                //原结果,b[i][j]为原相加的十位, n-1进制
                // a[i][j]为原相加的个位字母, ans[a[i][j]]为原相加的个位
                int r2 = ans[a[i][j]] + b[i][j] * (n - 1);
                if (r1 != r2) return false;//不等说明枚举结果是错误的
            }
        }
        return true;
    }
}
