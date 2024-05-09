package 算法.排序.排序问题;

public class 根据另一个数组次序排序 {
    /*
    arr1根据arr2中元素出现顺序排序,其中arr2内元素不重复
    输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
    输出：[2,2,2,1,4,3,3,9,6,7,19]
    (arr2未出现的数字,升序放在arr1末尾)
     */

    //元素范围:[0,1000] 数组长度:[0,1000]
    public static int[] relativeSort(int[] arr1, int[] arr2) {
        int[] count = new int[1001];
        for (int num : arr1) {
            count[num]++;
        }
        int k = 0;
        for (int i : arr2) {
            //arr2的值映射到count索引,count索引映射到arr1的值
            while (count[i] > 0) {
                arr1[k++] = i;
                count[i]--;
            }
        }
        //处理arr2未出现的数字
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                arr1[k++] = i;
                count[i]--;
            }
        }
        return arr1;
    }
}
