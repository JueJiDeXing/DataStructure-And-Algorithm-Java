package 算法.算法基础.二分;

//已测试:src.java.数据结构与算法.算法.二分.BinarySearchTest
public class 二分查找_优化 {
    /**
     <h1>!!最推荐写法</h1>
     假设查找的区间范围是[0,n],该区间具有二段性,在[0,x]不满足要求,在[x+1,n]满足要求<br>
     求第一个满足要求的数<br>
     那么用left指向不满足要求区间,right指向满足要求的区间<br>
     最后二分查找后,left和right一定是相邻的(left+1==right)<br>
     此时right是第一个满足要求的数,left是最后一个满足要求的数<br>
     此方法编码简单不易错<br>
     (注意, 需要确保left=0位置不满足,right=n位置满足)
     */
    public void binarySearch() {
        int n = 100000;
        int left = 0, right = n;
        while (left + 1 != right) {
            int mid = (left + right) >>> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        System.out.println(right);
    }

    boolean check(int mid) {
        //doCheck
        return true;
    }

    /**
     <h1>二分:原始未优化版</h1>
     <p><strong>缺陷:</strong></p>
     <p>1.整型溢出</p>
     <p>2.未考虑重复值</p>
     <p>3.返回值的实用性不高</p>

     @param arr    待查找的升序数组
     @param target 在数组中查找的值
     @return 返回查找值在数组中的索引<br>未找到则返回-1
     */
    public int binarySearch_原(int[] arr, int target) {
        int left = 0, right = arr.length;//右指针不参与目标值比较
        while (left < right) {
            int middle = (left + right) / 2;
            if (target < arr[middle]) {// 目标在左边
                right = middle;
            } else if (arr[middle] < target) {// 目标在右边
                left = middle + 1;
            } else {//找到目标
                return middle;
            }
        }//未找到目标
        return -1;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>二分:优化</h1>

     @param arr    待查找的升序数组
     @param target 在数组中查找的值
     @return 返回查找值在数组中的索引<br>未找到则返回-插入点-1
     </div>
     */
    public int binarySearch(int[] arr, int target) {
        //时间复杂度:最坏情况logn,最好情况1,所以为O(logn)
        int left = 0;
        int right = arr.length;//右指针不参与目标值比较
        while (left < right) {
            int middle = (left + right) >>> 1;//使用位运算防止溢出问题
            if (target < arr[middle]) {// 目标在左边
                right = middle;
            } else if (arr[middle] < target) {// 目标在右边
                left = middle + 1;
            } else {//找到目标
                return middle;
            }
        }//未找到目标
        return -left - 1;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>二分:优化2</h1>
     <p>平衡if判断</p>
     <p>时间复杂度由O(logn)变为Θ(logn)</p>
     </div>

     @param arr    待查找的升序数组
     @param target 在数组中查找的值
     @return 返回查找值在数组中的索引<br>未找到则返回-插入点-1
     */
    public int binarySearch_balance(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (left + 1 < right) {//差距1时退出循环,因为右指针不参与比较,所以这时左指针要么找到目标,要么数组中没有目标
            int middle = (left + right) >>> 1;
            //System.out.println(left + "," + middle + "," + right);
            if (target < arr[middle]) {// 目标在左边
                right = middle;
            } else {//找到目标或目标在右边
                left = middle;//因为可能找到目标,所以不是移动到middle+1
            }
        }
        //把找到目标放在循环外,使循环内if判断平衡,if判断次数减少了
        //时间复杂度由Ο(logn)变为Θ(logn),最好与最坏情况能够同时表示
        if (arr[left] == target) {
            return left;
        } else {
            return -(left + 1) - 1;//-left-1等价于~left,取反运算
        }
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>二分:LeftMost(没有则返回-1)</h1>
     <p>有多个重复值,返回最左边的索引</p>
     </div>
     <hr>

     @param arr    待查找的升序数组
     @param target 在数组中查找的值
     @return 返回查找值在数组中的索引<br>未找到则返回-1
     */
    public int binarySearch_repeat(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int ans = -1;//记录候选位置,每次遇到更靠前的索引时更新
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (target < arr[middle]) {// 目标在左边
                right = middle - 1;
            } else if (arr[middle] < target) {//目标在右边
                left = middle + 1;
            } else {//相等
                ans = middle;//记录候选位置
                right = middle - 1;//如果要做查找最右的元素索引,这一步改为left=middle+1即可
            }
        }
        return ans;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>二分:LeftMost(大于等于)</h1>
     <p>例:[1,2,-?-,4,4,4,7,8]查找3,最终left=2,right=1退出循环,返回2</p>
     <p>例:[1,2,4,4,4,-?-,7,8]查找5,最终left=5,right=4退出循环,返回5</p>
     <p>应用场景1:求排名;<br>
     相同分数返回最左侧索引.注:返回值要加1</p>
     <p>应用场景2:求范围;<br>
     x<4 = 0~leftmost(4)-1,<br>
     x<=4 = 0~rightmost(4),<br>
     x>4 = right(4)+1~无穷,<br>
     x>=4 = leftmost(4)~无穷,</p>
     <hr>
     </div>

     @param arr    待查找的升序数组
     @param target 在数组中查找的值
     @return 返回≥target的,并且是最左侧的索引(leftMost)
     */
    public int binarySearch_leftMost(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (target <= arr[middle]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return left;//返回≥target的,并且是最左侧的索引(leftMost)
        //如果要返回比目标小的,并且是最右侧的索引,return left-1,并且target < arr[middle]
    }


    /**
     <div color=rgb(155,200,80)>
     <h1>二分:递归写法</h1>
     <hr>
     </div>

     @param arr    待查找的升序数组
     @param target 在数组中查找的值
     @param left   左区间起点
     @param right  右区间起点
     @return 返回查找到的索引, 没有找到返回-1
     */
    public int binarySearch_rec(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int middle = (left + right) >>> 1;
        if (target < arr[middle]) {
            return binarySearch_rec(arr, target, left, middle - 1);
        } else if (arr[middle] < target) {
            return binarySearch_rec(arr, target, middle + 1, right);
        } else {
            return middle;
        }
    }
}
