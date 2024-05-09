package 算法OJ.洛谷.普及down;

import java.io.*;

/**
 已AC
 */
public class P1003铺地毯 {
    /*
    n张地毯铺在第一象限,编号从1到n
    第i张地毯左下角坐标为(ai,bi),长(沿x轴方向)为gi,宽(沿y轴方向)为ki
    如果第i张地毯与第j张地毯有重叠,如果i<j,则j在i的上面
    求(x,y)点的地毯编号
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
     模拟?贪心√: 从最后一张地毯开始找,如果地毯覆盖了(x,y)即可停止,这张地毯就是最上面的地毯
     */
    public static void main(String[] args) {
        int n = Int();

        int[][] carpet = new int[n + 1][4];
        for (int i = 1; i <= n; i++) {
            carpet[i][0] = Int();
            carpet[i][1] = Int();
            carpet[i][2] = Int();
            carpet[i][3] = Int();
        }
        int x = Int(), y = Int();
        int id = -1;
        for (int i = n; i > 0; i--) {
            if (contain(x, y, carpet[i])) {
                id = i;
                break;
            }
        }
        System.out.println(id);

    }

    /**
     (x,y)是否在矩形内
     */
    static boolean contain(int x, int y, int[] rectangle) {
        int a = rectangle[0], b = rectangle[1];
        return a <= x && x <= a + rectangle[2] && b <= y && y <= b + rectangle[3];
    }
}
