package 算法OJ.蓝桥杯.真题卷.第7届.省赛.Java大学A组;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 已AC
 */
public class G压缩变换 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int n = Int();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) A[i] = Int();
        ArrayList<Integer> list = new ArrayList<>();//不重复的集合, list[idx]=A[pre]是上一个出现A[i], list从idx到数组尾是A(pre~i)上的数种类数
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int idx = list.indexOf(A[i]);//查看A[i]是否出现过
            if (idx == -1) {
                ans[i] = -A[i];//没出现,置为相反数
            } else {
                ans[i] = list.size() - idx - 1;// [idx+1,list.size-1]是不重复的数
                list.remove((Object) A[i]);//A[i]再次出现,放在list的最后面
            }
            list.add(A[i]);
        }
        for (int a : ans) {
            System.out.print(a + " ");
        }
    }

    private static void solve1(int n, int[] A) {//5AC 4TLE
        HashMap<Integer, Integer> map = new HashMap<>();//key:数字 val:该数上次出现的位置
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(A[i])) {
                ans[i] = -A[i];//第一次出现的数字变为相反数
            } else {
                //出现过
                Integer lastIdx = map.get(A[i]);
                //从上次的索引到当前位置一共有多少种数字
                HashSet<Integer> set = new HashSet<>();// 该部分需要优化
                for (int j = lastIdx + 1; j < i; j++) {
                    set.add(A[j]);
                }
                ans[i] = set.size();
            }
            map.put(A[i], i);
        }
        for (int a : ans) {
            System.out.print(a + " ");
        }
    }
}
