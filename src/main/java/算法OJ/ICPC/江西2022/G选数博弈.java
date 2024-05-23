package 算法OJ.ICPC.江西2022;

import java.io.*;
import java.util.*; 

/**
 已AC(复杂)
 */
public class G选数博弈 {
    /*
    n个数, 每次A先选择1个数x, B需要选择1一个数字y, 然后从n个数中移除x和y
    y需要满足 |x-y|<=3 或 x%3==y%3
    当某人没数字可选时,他就输了
     */
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws Exception {
        int t = I();
        while (t-- > 0) {
            pw.println(solve());
        }
        pw.flush();
        pw.close();
    }

    /**
     case1: n为奇数, 无法两两配对, 输
     case2: 模3均为偶数, 组内直接配对, 赢
     case3: 奇 奇 偶, 块1存在1个可与块2匹配, 匹配后变为 偶 偶 偶 , 赢
     case4: 奇 奇 偶, 块1存在1个可与块3匹配, 块2存在1个可与块3匹配, 它们的匹配块应当不同, 匹配后变为 偶 偶 偶 , 赢
     其他情况: 输
     */
    private static String solve() throws IOException {
        List<Integer>[] list = new List[3];
        HashMap<Integer, Integer>[] cnt = new HashMap[3];
        Arrays.setAll(list, k -> new ArrayList<>());
        Arrays.setAll(cnt, k -> new HashMap<>());
        int n = I();
        for (int j = 0; j < n; j++) {
            int a = I();
            list[a % 3].add(a);
            cnt[a % 3].put(a, cnt[a % 3].getOrDefault(a, 0) + 1);
        }
        if (n % 2 != 0) return "His little girlfriend";//奇数个数,配不了
        if (allEven(list)) return "rqd"; // 每部分都是偶数个数, 它们两两都可以配对
        // 两奇一偶 -> 奇 奇 偶
        if (list[0].size() % 2 == 0) {
            swap(list, 0, 2);
            swap(cnt, 0, 2);
        } else if (list[1].size() % 2 == 0) {
            swap(list, 1, 2);
            swap(cnt, 1, 2);
        }
        for (List<Integer> l : list) l.sort(Integer::compareTo);
        // (1) 块1存在1个可与块2匹配, 匹配后变为 偶 偶 偶
        if (find(list[0], cnt[1]) != 0) return "rqd";
        // (2) 第一块内部配对后能剩余1个与第三块的某个配对, 然后第二块内部配对后也能与第三块的某个配对
        // 可能匹配同一个数
        // [4] [8] [6,9], 8-6无解, 4-6有解 -> 需要两次块先后匹配都找
        if (tryFind(list[0], list[1], cnt[2])) return "rqd";
        if (tryFind(list[1], list[0], cnt[2])) return "rqd";
        return "His little girlfriend";
    }

    /**
     先后查找,first先find,然后删除匹配项,再由second去find

     @return 是否两次都能find到
     */
    static boolean tryFind(List<Integer> first, List<Integer> second, HashMap<Integer, Integer> cnt) {
        int item = find(first, cnt);
        if (item != 0) {
            cnt.put(item, cnt.get(item) - 1);
            if (find(second, cnt) != 0) return true;
            cnt.put(item, cnt.get(item) + 1);// 回退删除操作
        }
        return false;
    }

    static boolean allEven(List<Integer>[] list) {
        for (List<Integer> list1 : list) {
            if (list1.size() % 2 != 0) return false;
        }
        return true;
    }

    static <T> void swap(T[] list, int i, int j) {
        T tmp = list[i];
        list[i] = list[j];
        list[j] = tmp;
    }

    /**
     检查是否能内部配对后剩余1个与另一块配对

     @param list 一组模3同余的元素, 它们两两可以配对, list长度为奇数
     @param cnt  另一组数, cnt和list中的一对数如果相差小于等于3,则可以配对
     @return 最后剩余的1个配对的数, 如果没有,返回0
     */
    static int find(List<Integer> list, HashMap<Integer, Integer> cnt) {
        for (int num : list) {
            // 对于每个数查找[num-3,num+3]在另一组是否有匹配项
            for (int k = -3; k <= 3; k++) {// 实际上-2~2即可, 另外的组不会有同余项的 
                int item = num + k;
                if (cnt.containsKey(item) && cnt.get(item) > 0) {
                    return item;
                }
            }
        }
        return 0;
    }
}
