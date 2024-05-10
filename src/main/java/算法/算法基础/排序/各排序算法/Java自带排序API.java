package 算法.算法基础.排序.各排序算法;

public class Java自带排序API {
    /*JDK7~13
    排序对象                            条件            采用算法

    int[] long[] float[] double[]    size<47        混合插入排序
                                     size<286       双基准点快排
                                     有序数高         归并排序
                                     有序数低         双基准点快排

    byte[]                          size>29         计数排序
                                    size<=29        插入排序

    char[] short[]                  size>3200       计数排序
                                    size<47         插入排序
                                    size<286        双基准点快排
                                    有序度高         归并排序
                                    有序度低         双基准点快排

    Object[]          useLegacyMergeSort=true      传统归并排序
                      useLegacyMergeSort=false     TimSort(归并+插入)
            //-Djava.util.Arrays.useLegacyMergeSort
     */

    //---------------------------------------------------------------------------------------------
    /*JDK14~20
    排序目标                            条件                  采用算法

    int[] long[] float[] double[]    size<65(并不是最左侧)   混合插入排序(pin)
                                     size<44(并不是最右侧)   插入排序
                                     递归次数>384           堆排序
                            非左侧,size>4096,或数组接近有序   归并排序
                                    其他情况                双基准点快排

    byte[]                          size>64               计数排序
                                    size<=64              插入排序
    char[] short[]                  size>1750             计数排序
                                    size<44               插入排序
                                    递归次数超过384         计数排序
                                    其他情况               双基准点快排
    Object[]          useLegacyMergeSort=true            传统归并排序
                      useLegacyMergeSort=false     TimSort(归并+插入)
     */
    //从JDK8开始支持Arrays.parallelSort并行排序(多线程)

     //---------------------------------------------------------------------------------------------
    //
    ///*
    // Arrays.sort源码追踪
    // */
    //public static void Arrays.sort(int[] a) {
    //    DualPivotQuicksort.sort(a, 0, 0, a.length);//1. 调用DualPivotQuicksort类去排序
    //}
    ///*
    //检查数据量,判断是否使用排序器
    // */
    //static void DualPivotQuicksort.sort(int[] a, int parallelism, int low, int high) {
    //    int size = high - low;
    //
    //    if (parallelism > 1 && size > MIN_PARALLEL_SORT_SIZE) {//2. 当数据量大于2^12,使用排序器
    //        int depth = getDepth(parallelism, size >> 12);
    //        int[] b = depth == 0 ? null : new int[size];
    //        new DualPivotQuicksort.Sorter(null, a, b, low, size, low, depth).invoke();//2.1创建一个内部类排序器进行排序
    //        //DualPivotQuicksort.Sorter extends CountedCompleter<Void> -> CountedCompleter<T> extends ForkJoinTask<T> -> invoke()
    //    } else {
    //        DualPivotQuicksort.sort(null, a, 0, low, high);//2.2不使用排序器进行排序
    //    }
    //}
    ///*
    //分支检查:对size和bits等进行检查,判断使用哪种排序方法
    // */
    //static void DualPivotQuicksort.sort(DualPivotQuicksort.Sorter sorter, int[] a, int bits, int low, int high) {
    //    while (true) {
    //        int end = high - 1, size = high - low;
    //        //bits – 递归深度和位标志的组合，其中右位“0”表示数组是最左边的部分
    //        if (size < MAX_MIXED_INSERTION_SORT_SIZE + bits && (bits & 1) > 0) {//1.size<65,且不是最左侧
    //            mixedInsertionSort(a, low, high - 3 * ((size >> 5) << 3), high);//使用混合插入排序
    //            return;
    //        }
    //        if (size < MAX_INSERTION_SORT_SIZE) {//2.size<44
    //            insertionSort(a, low, high);//使用插入排序
    //            return;
    //        }
    //        if ((bits == 0 || size > MIN_TRY_MERGE_SIZE && (bits & 1) > 0)//3.非左侧 或者 size>4096
    //                && tryMergeRuns(sorter, a, low, size)) {//尝试归并排序
    //            return;
    //        }
    //        if ((bits += DELTA) > MAX_RECURSION_DEPTH) {//4.最大递归深度大于384
    //            heapSort(a, low, high);//使用堆排序
    //            return;
    //        }
    //
    //        //5.其他情况
    //        int step = (size >> 3) * 3 + 3;
    //        int e1 = low + step;
    //        int e5 = end - step;
    //        int e3 = (e1 + e5) >>> 1;
    //        int e2 = (e1 + e3) >>> 1;
    //        int e4 = (e3 + e5) >>> 1;
    //        int a3 = a[e3];
    //        if (a[e5] < a[e2]) { int t = a[e5]; a[e5] = a[e2]; a[e2] = t; }//swap
    //        if (a[e4] < a[e1]) { int t = a[e4]; a[e4] = a[e1]; a[e1] = t; }
    //        if (a[e5] < a[e4]) { int t = a[e5]; a[e5] = a[e4]; a[e4] = t; }
    //        if (a[e2] < a[e1]) { int t = a[e2]; a[e2] = a[e1]; a[e1] = t; }
    //        if (a[e4] < a[e2]) { int t = a[e4]; a[e4] = a[e2]; a[e2] = t; }
    //
    //        if (a3 < a[e2]) {
    //            if (a3 < a[e1]) {
    //                a[e3] = a[e2]; a[e2] = a[e1]; a[e1] = a3;
    //            } else {
    //                a[e3] = a[e2]; a[e2] = a3;
    //            }
    //        } else if (a3 > a[e4]) {
    //            if (a3 > a[e5]) {
    //                a[e3] = a[e4]; a[e4] = a[e5]; a[e5] = a3;
    //            } else {
    //                a[e3] = a[e4]; a[e4] = a3;
    //            }
    //        }
    //        // Pointers
    //        int lower = low; // The index of the last element of the left part
    //        int upper = end; // The index of the first element of the right part
    //        if (a[e1] < a[e2] && a[e2] < a[e3] && a[e3] < a[e4] && a[e4] < a[e5]) {//双基准点快排
    //            int pivot1 = a[e1];
    //            int pivot2 = a[e5];
    //            a[e1] = a[lower];
    //            a[e5] = a[upper];
    //            while (a[++lower] < pivot1);
    //            while (a[--upper] > pivot2);
    //            for (int unused = --lower, k = ++upper; --k > lower; ) {
    //                int ak = a[k];
    //                if (ak < pivot1) { // Move a[k] to the left side
    //                    while (lower < k) {
    //                        if (a[++lower] >= pivot1) {
    //                            if (a[lower] > pivot2) {
    //                                a[k] = a[--upper];
    //                                a[upper] = a[lower];
    //                            } else {
    //                                a[k] = a[lower];
    //                            }
    //                            a[lower] = ak;
    //                            break;
    //                        }
    //                    }
    //                } else if (ak > pivot2) { // Move a[k] to the right side
    //                    a[k] = a[--upper];
    //                    a[upper] = ak;
    //                }
    //            }
    //            a[low] = a[lower]; a[lower] = pivot1;
    //            a[end] = a[upper]; a[upper] = pivot2;
    //            if (size > MIN_PARALLEL_SORT_SIZE && sorter != null) {
    //                sorter.forkSorter(bits | 1, lower + 1, upper);
    //                sorter.forkSorter(bits | 1, upper + 1, high);
    //            } else {
    //                sort(sorter, a, bits | 1, lower + 1, upper);
    //                sort(sorter, a, bits | 1, upper + 1, high);
    //            }
    //        } else { // 单基准点快排
    //            int pivot = a[e3];
    //            a[e3] = a[lower];
    //            for (int k = ++upper; --k > lower; ) {
    //                int ak = a[k];
    //                if (ak != pivot) {
    //                    a[k] = pivot;
    //                    if (ak < pivot) { // Move a[k] to the left side
    //                        while (a[++lower] < pivot);
    //                        if (a[lower] > pivot) {
    //                            a[--upper] = a[lower];
    //                        }
    //                        a[lower] = ak;
    //                    } else { // ak > pivot - Move a[k] to the right side
    //                        a[--upper] = ak;
    //                    }
    //                }
    //            }
    //            a[low] = a[lower]; a[lower] = pivot;
    //            if (size > MIN_PARALLEL_SORT_SIZE && sorter != null) {
    //                sorter.forkSorter(bits | 1, upper, high);
    //            } else {
    //                sort(sorter, a, bits | 1, upper, high);
    //            }
    //        }
    //        high = lower; // Iterate along the left part
    //    }
    //}
}
