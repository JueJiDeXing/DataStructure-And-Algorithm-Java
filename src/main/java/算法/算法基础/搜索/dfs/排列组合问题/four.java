package 算法.算法基础.搜索.dfs.排列组合问题;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class four {
    public List<List<Integer>> threeSum(int[] nums) {
        return nSum(nums, 3, 0);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        return nSum(nums, 4, target);
    }

    List<List<Integer>> nSum(int[] a, int n, long target) {
        return new AbstractList<List<Integer>>() {
            final List<List<Integer>> res = new ArrayList<>();
            final List<Integer> path = new ArrayList<>();

            @Override
            public int size() {
                init();
                return res.size();
            }

            @Override
            public List<Integer> get(int index) {
                init();
                return res.get(index);
            }

            void init() {
                if (res.isEmpty()) {
                    Arrays.sort(a);
                    dfs(a, 0, a.length - 1, n, target);
                }
            }

            void dfs(int[] a, int i, int j, int n, long target) {
                if (n == 2) {
                    two(a, i, j, target);
                } else if (n > 2) {
                    hit(a, i, j, n, target);
                }
            }

            void two(int[] a, int i, int j, long target) {
                if (i >= j) {
                    return;
                }
                long max = 0;
                long min = 0;
                for (int k = 0; k < 2; k++) {
                    min += a[i + k];
                    max += a[j - k];
                }
                if (target < min || target > max) {
                    return;
                }
                while (j > i) {
                    long sum = a[i] + a[j];
                    if (sum < target) {
                        i++;
                    } else if (sum > target) {
                        j--;
                    } else {
                        path.add(a[i]);
                        path.add(a[j]);
                        res.add(new ArrayList<>(path));
                        path.remove(path.size() - 1);
                        path.remove(path.size() - 1);
                        while (j > i && a[i] == a[i + 1]) {
                            i++;
                        }
                        while (j > i && a[i] == a[j - 1]) {
                            j--;
                        }
                        i++;
                        j--;
                    }
                }
            }

            void hit(int[] a, int i, int j, int n, long target) {
                int begin = i;
                int end = j;
                if (i + n - 2 >= j) {
                    return;
                }
                long max = 0;
                long min = 0;
                for (int k = 0; k < n; k++) {
                    min += a[i + k];
                    max += a[j - k];
                }
                if (target < min || target > max) {
                    return;
                }
                while (j > i + n - 2) {
                    long sufMax = 0;
                    long preMin = 0;
                    for (int k = 0; k < n - 1; k++) {
                        preMin += a[i + k];
                        sufMax += a[j - k];
                    }
                    preMin += a[j];
                    sufMax += a[i];
                    if (sufMax < target) {
                        i++;
                    } else if (preMin > target) {
                        j--;
                    } else {
                        while (i != begin && j > i + n - 2 && a[i] == a[i - 1]) {
                            i++;
                        }
                        while (j != end && j > i + n - 2 && a[j] == a[j + 1]) {
                            j--;
                        }
                        path.add(a[i]);
                        dfs(a, i + 1, j, n - 1, target - a[i]);
                        path.remove(path.size() - 1);
                        i++;
                    }
                }
            }
        };
    }

}
