package 算法OJ.蓝桥杯.真题卷.第7届.国赛.Java大学A组;
/**
 已AC
 */
public class C棋子换位 {
    public static void main(String[] args) {
        char[] data = "AAAA.BBBB".toCharArray();
        f(data);
    }

    static void move(char[] data, int from, int to) {
        data[to] = data[from];
        data[from] = '.';
    }

    static boolean valid(char[] data, int k) {
        return k >= 0 && k < data.length;
    }

    static void f(char[] data) {
        while (true) {
            boolean tag = false;
            for (int i = 0; i < data.length; i++) {
                Integer dd = getDd(data, i);
                if (dd == null) continue;
                if (valid(data, i + dd) && valid(data, i + dd + dd)
                        && data[i + dd] != data[i] && data[i + dd + dd] == '.') {
                    // 如果能跳...
                    move(data, i, i + dd + dd);
                    System.out.println(new String(data));
                    tag = true;
                    break;
                }
            }
            if (tag) continue;
            for (int i = 0; i < data.length; i++) {
                Integer dd = getDd(data, i);
                if (dd == null) continue;
                if (valid(data, i + dd) && data[i + dd] == '.') {
                    // 如果能移动...
                    if (valid(data, i - dd) && valid(data, i + 2 * dd) && data[i - dd] == data[i + 2 * dd]) continue;

                    move(data, i, i + dd);
                    System.out.println(new String(data));
                    tag = true;
                    break;
                }
            }
            if (!tag) break;
        }
    }

    private static Integer getDd(char[] data, int i) {
        int dd = 0; // 移动方向
        if (data[i] == '.') return null;
        if (data[i] == 'A') dd = 1;
        if (data[i] == 'B') dd = -1;
        return dd;
    }
}
