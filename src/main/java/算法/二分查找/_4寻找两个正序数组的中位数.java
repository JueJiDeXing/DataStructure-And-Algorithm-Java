package 算法.二分查找;

/**
 难度:困难
 */
public class _4寻找两个正序数组的中位数 {
    /*
    给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。

    算法的时间复杂度应该为 O(log (m+n)) 。
    进阶O(log(n))
     */

    /**
     <h1>暴力解法</h1>
     合并两个数组,再查找中位数
     */
    public double _findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        //合并
        int len = m + n;
        int[] nums = new int[len];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                nums[k++] = nums1[i++];
            } else {
                nums[k++] = nums2[j++];
            }
        }
        if (i < m) System.arraycopy(nums1, i, nums, k, m - i);
        if (j < n) System.arraycopy(nums2, j, nums, k, n - j);
        //查找
        return len % 2 == 0 ? (nums[len / 2] + nums[len / 2 - 1]) / 2.0 : nums[len / 2];
    }

    /**
     <h1>暴力-优化</h1>
     要获取中位数,不需要合并数组,只要取到中位数即可
     */
    public double _findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int len = m + n;

        int prev = -1, curr = -1;
        int i = 0, j = 0;
        for (int k = 0; k <= len / 2; k++) {
            prev = curr;
            if (i < m && (j >= n || nums1[i] < nums2[j])) {
                curr = nums1[i++];
            } else {
                curr = nums2[j++];
            }
        }
        return (len & 1) == 0 ? (prev + curr) / 2.0 : curr;
    }

    /**
     <h1>求第k小数</h1>
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int left = (n + m + 1) / 2, right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, m - 1, nums2, 0, n - 1, left) +
                getKth(nums1, 0, m - 1, nums2, 0, n - 1, right)) / 2.0;
    }

    /**
     在两个升序数组的有效区间上寻找第k小的数
     nums1[start1,end1],nums2[start2,end2]
     将nums1和nums2的区间都进行二分,比较中间,小的一方,砍掉前半区间
     //四分查找
     */
    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1, len2 = end2 - start2 + 1; //保证len1<len2,简化代码
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        //1为空,返回2的第k个 // k为1,返回这两个数组的第一个的较小值(不考虑奇偶,奇数会求两遍一样的k,偶数会求k和k+1)
        if (len1 == 0) return nums2[start2 + k - 1];
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        //二分(四分): 划分区间,取当前有效区间的第k/2位置(排除一半查找区间)
        int i = start1 + Math.min(len1, k / 2) - 1;// min(len1, k / 2) -> 如果k/2位置越界,则取最后一个位置
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {//nums1的较大,将nums2的前半区间砍掉,第k小变为第 k-nums2的前半长度 小
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {//同理
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

    /**
     <h1>区间划分</h1>
     思路:<br>
     找到一对切分位置 i , j <br>
     其中 i 将 A 划分左右两边区域 left1 , right1  ;  j 划分 B 为区域 left2 , right2 <br>
     left1=A[0,i-1],right1=A[i,len1-1],left2=B[0,j-1],right2=B[j,len2-1]<br>
     并且 left1 、 left2 、 right1 、 right2  都为各自数组长度的一半 <br>
     <p>
     left=left1+left2,right=right1+right2<br>
     left和right的长度是两个数组总长度的一半<br>
     所以,如果 left 的最大值 小于 right 的最小值 ,那么就找到了中位数位置 <br>
     即: max ( A[i-1] , B[j-1] ) <= min ( A[i] , B[j] ) <br>
     因为 A 数组和 B 数组是有序的，所以 A[i-1] <= A[i] , B[i-1] <= B[i] 这是天然的<br>
     所以我们只需要保证 B[j-1] <= A[i] 和 A[i-1] <= B[j] <br>
     <br>
     边界情况:<br>
     <ul>
     <li>
     case1: B[j-1] > A[i],此时i需要增加,j需要减少 (切分位置 i 在右边)<br>
     并且为了不越界，要保证 j != 0，i != m<br>
     </li>
     <li>
     case2: A[i-1] > B[j],此时i需要减少,j需要增加 (切分位置 i 在左边)<br>
     并且为了不越界，要保证 i != 0，j != n<br>
     </li>
     <li>
     case3: i=0 或 j=0,切在了最前面<br>
     此时左半部分当 j=0 时，最大的值就是 A[i-1] ；当 i = 0 时 最大的值就是 B[j-1] <br>
     右半部分最小值和之前一样<br>
     </li>
     <li>
     case4: i=m 或 j=n,切在了最后面<br>
     此时左半部分最大值和之前一样。<br>
     右半部分当 j = n 时, 最小值就是 A[i] ; 当 i=m 时, 最小值就是B[j] <br>
     </li>
     </ul>
     <br>
     所以,在nums1中 二分查找切分位置 i <br>
     j = (m + n + 1) / 2 - i时划分一半长度,不需要枚举j
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m > n) return findMedianSortedArrays2(nums2, nums1); // 保证 m <= n

        int iMin = 0, iMax = m;//二分查找切分位置i,j
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = (m + n + 1) / 2 - i;//j的位置由i控制
            if (j != 0 && i != m && nums2[j - 1] > nums1[i]) { // nums2的左区间最大值大于nums1的右区间最小值,不是正确1切分位置,i 需要增大
                iMin = i + 1;
            } else if (i != 0 && j != n && nums1[i - 1] > nums2[j]) { // 同理,i 需要减小
                iMax = i - 1;
            } else { // 达到切分要求
                //求左区间的最大值和右区间的最小值. 左区间:A[0,i-1] & B[0,j-1] ; 右区间:A[i,len1-1] & B[j,len2-1]
                int maxLeft;
                if (i == 0) {//考虑边界
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((m + n) % 2 == 1) { // 长度为奇数的话,中位数可以直接取到,不需要右区间的最小值
                    return maxLeft;
                }

                int minRight;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums2[j], nums1[i]);
                }
                return (maxLeft + minRight) / 2.0; //如果是偶数的话返回平均数
            }
        }
        return 0.0;
    }
}
