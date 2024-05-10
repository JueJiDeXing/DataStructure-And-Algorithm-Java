package 基础数据结构算法.栈.单调栈应用;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class _2736最大和查询 {
    /*
    给你两个长度为 n 、下标从 0 开始的整数数组 nums1 和 nums2 ，
    另给你一个下标从 1 开始的二维数组 queries ，其中 queries[i] = [xi, yi] 。

    对于第 i 个查询，在所有满足 nums1[j] >= xi 且 nums2[j] >= yi 的下标 j (0 <= j < n) 中，
    找出 nums1[j] + nums2[j] 的 最大值 ，如果不存在满足条件的 j 则返回 -1 。

    返回数组 answer ，其中 answer[i] 是第 i 个查询的答案。
     */

    /**
     <h1>排序+单调栈+二分</h1>
     <h2>排序</h2>
     对nums1和nums2进行捆绑排序,按nums1从大到小,排序后的数组记为a<br>
     对查询数组排序,按xi从大到小排序<br>
     <h2>单调栈</h2>
     从j=0遍历捆绑排序后的数组a,并逐一回答询问,把满足nums1[j]>=xi的nums2[j]记录到栈中<br>
     在栈中查找>=yi的nums2[j],并求出nums1[j]+nums2[j]的最大值<br>
     <ul>对nums2[j]的大小进行分类讨论
     <li> case1: 如果nums2[j]比之前遍历到的nums2[j`]小,由于nums1[j]是降序排列,所以他们的和也更小,j位置无效 </li>
     <li> case2: 如果nums2[j]与之前遍历到的nums2[j`]相等,由于nums1[j]是降序排列,所以他们的和也更小,j位置无效 </li>
     <li> case3: 如果nums2[j]比之前遍历到的nums2[j`]大,j位置有效,可以入栈; </li>
     在入栈前可以把所有nums1[j`]+nums2[j`]小于当前和的出栈<br>
     因为更大的nums2[j]更能满足查询的需求,所以之前更小的nums1[j`]+nums2[j`]一定不会作为查询结果<br>
     </ul>
     <h2>二分</h2>
     根据单调栈,我们得到了 st[i]=[nums2[j],nums1[j]+nums2[j]] <br>
     从栈底到栈顶按nums2[j]升序,且按nums1[j]+nums2[j]降序(不是nums2[j]相等时降序,是整体降序) <br>
     查找nums2[j]>=yi的最大nums1[j]+nums2[j],等价于在栈st中找最小的索引j,使nums2[j]>=yi即可<br>
     */
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        //nums1、nums2数组重构为数对数组a
        int n = nums1.length;
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) {
            a[i][0] = nums1[i];
            a[i][1] = nums2[i];
        }
        Arrays.sort(a, (o1, o2) -> o2[0] - o1[0]);//按照nums1从大到小排序
        //按照查询数组queries的x值,从大到小生成索引数组qid
        Integer[] qid = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) qid[i] = i;
        Arrays.sort(qid, (o1, o2) -> queries[o2][0] - queries[o1][0]);
        //按x从大到小开始查询
        int[] ans = new int[queries.length];
        //单调栈,存储 {nums2,nums1+nums2}
        // 栈底到栈顶按nums2升序,nums1+nums2降序

        Stack<int[]> st = new Stack<>();
        //从j=0遍历a数组,并逐一回答询问,把满足nums1[j]>=xi的nums2[j]记录到栈中
        //在栈中查找>=yi的nums2[j],并求出nums1[j]+nums2[j]的最大值
        int j = 0;
        for (int i : qid) {
            int x = queries[i][0], y = queries[i][1];
            while (j < n && x <= a[j][0]) { // 下面只需关心 a[j][1]
                while (!st.isEmpty() && st.peek()[1] <= a[j][0] + a[j][1]) {//存储更大的和
                    st.pop();
                }
                if (st.isEmpty() || st.peek()[0] < a[j][1]) {//栈按nums2升序
                    st.push(new int[]{a[j][1], a[j][0] + a[j][1]});
                }
                j++;
            }
            int p = lowerBound(st, y);
            ans[i] = p < st.size() ? st.get(p)[1] : -1;
        }
        return ans;
    }

    /**
     在升序栈st中查找大于target的最小值<br>
     st[i]=[nums2[j],nums1[j]+nums2[j]],按nums2[j]升序,且按nums1[j]+nums2[j]降序
     */
    private int lowerBound(List<int[]> st, int target) {
        int left = -1, right = st.size(); // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            int mid = (left + right) >>> 1;
            if (st.get(mid)[0] >= target) {
                right = mid; // 范围缩小到 (left, mid)
            } else {
                left = mid; // 范围缩小到 (mid, right)
            }
        }
        return right;
    }
}
