package 算法OJ.蓝桥杯.真题卷.第11届.国赛.Java大学A组;

import java.util.*;

/**
 已AC
 */
public class D游园安排 {
    /*
    给出x个人名,每个人首字母大小,其余小写,每个名字长度不超过10,名字可能重复
    名字A小于名字B的定义:
        如果前i个字符都一样,如果A和B都存在第i+1个字符,则比较字典序,字典序相同继续往后比较
        如果前i个字符都一样,如果A不存在第i+1个字符,而B存在,则A<B;
    需要在这些人里选择一个最长严格递增子序列
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String[] names = s.split("(?<!^)(?=[A-Z])");
        int len = names.length;
        if (len == 0) return;

        String[] lastString = new String[len];//lastString[i]:长度为i的最长严格递增子序列的最后一个字符串
        int[] path = new int[len];  //path[i],以第i个名字结尾的最长递增子序列长度
        path[0] = 0;
        lastString[0] = names[0];
        int now = 0;//最长的序列长度
        for (int i = 1; i < len; i++) {
            String name = names[i];
            int compare = name.compareTo(lastString[now]);
            if (compare > 0) {//比最后当前最长序列的最后一个大,可以拼接到最后一个序列
                now++;
                lastString[now] = name;
                path[i] = now;
            } else if (compare < 0) {//可以拼接到前面的序列里
                //二分查找大于name的第一个位置l
                int l = 0, r = now + 1;
                while (l <= r) {
                    int mid = l + (r - l) / 2;
                    if (lastString[mid].compareTo(name) >= 0) r = mid - 1;
                    else l = mid + 1;
                }
                //用name覆盖长度为l的序列的最后一个
                lastString[l] = name;
                path[i] = l;
            }
        }
        String MAX = "Zzzzzzzzzzzzzzzzzzz";
        String[] ans = new String[now + 1];
        for (int i = len - 1; i >= 0 && now >= 0; i--) {  //找出最优解,长度为now的序列
            String name = names[i];
            int iLen = path[i];//以name结尾的最长递增子序列长度
            if (iLen == now && name.compareTo(MAX) <= 0) {//name是要找的那个序列里的(长度匹配,且比后一项小)
                ans[now--] = name;
                MAX = name;
            }
        }
        for (String ss : ans) {
            System.out.print(ss);
        }
    }

    private static void solve1(String[] names) {//8AC 2TLE
        int n = names.length;
        List<List<String>> results = new ArrayList<>();//results[i]=arr:长度为i的子序列;arr中存储子序列具体人名
        results.add(Arrays.asList(names[0]));
        for (int i = 1; i < n; i++) {
            update(results, names[i]);
        }
        List<String> ans = results.get(results.size() - 1);
        for (String a : ans) {
            System.out.print(a);
        }
    }

    static void update(List<List<String>> results, String name) {
        //二分查找能在后面拼接name的子序列,子序列尽可能长
        int left = -1, right = results.size();
        while (left + 1 != right) {
            int mid = (left + right) >>> 1;
            List<String> line = results.get(mid);
            String tail = line.get(line.size() - 1);//当前一行的最后一个数字(当前子序列的末位)
            if (tail.compareTo(name) < 0) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (left == -1) {//name无法拼接到其他序列,它太小了
            results.set(0, Arrays.asList(name));
            return;
        }
        //行拼接num后覆盖下一行
        List<String> cover = new ArrayList<>(results.get(left));
        cover.add(name);
        if (left == results.size() - 1) {//拼接到最后一行
            results.add(cover);//添加一行
        } else {
            results.set(left + 1, cover);//相同长度下的更优解
        }
    }
}
