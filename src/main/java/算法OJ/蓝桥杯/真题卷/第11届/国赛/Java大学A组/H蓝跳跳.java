package 算法OJ.蓝桥杯.真题卷.第11届.国赛.Java大学A组;

import java.util.*;

/**
 测试通过:4AC 6TLE
 */
public class H蓝跳跳 {
    /*
    从A到B,长度为L,最大跳跃能力为k,不能连续两次跳大于等于p长度
    求跳跃方案数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int p = sc.nextInt();
        long L = sc.nextLong();
        long dp0 = 1, dp1;
        Queue<Long> leftDown = new LinkedList<>(), rightUp = new LinkedList<>(), rightUpTemp = new LinkedList<>();
        leftDown.offer(1L);
        rightUpTemp.offer(1L);
        long leftDownSum = 1, rightUpSum = 0;
        for (int i = 1; i <= L; i++) {
            dp0 = (rightUpSum + leftDownSum) % mod;
            dp1 = leftDownSum;
            leftDown.offer(dp0);
            leftDownSum = (leftDownSum + dp0) % mod;
            if (leftDown.size() > p - 1) {
                leftDownSum = (leftDownSum - leftDown.poll() + mod) % mod;
            }
            rightUpTemp.offer(dp1);
            if (rightUpTemp.size() > p - 1) {
                Long poll = rightUpTemp.poll();
                rightUpSum = (rightUpSum + poll) % mod;
                rightUp.offer(poll);
                if (rightUp.size() > k - p + 1) {
                    rightUpSum = (rightUpSum - rightUp.poll() + mod) % mod;
                }
            }
        }
        System.out.println(dp0);
    }

    /**
     dfs(L,true) = sum{ dfs(L-i, i>=p) | 1<=i<=min(p-1,k)} = sum{ dfs(L-i, false)  | 1<=i<=min(p-1,k) }
     dfs(L,false) = sum{ dfs(L-i, i>=p) | 1<=i<=k}
     =>
     up = isP ? p-1 : k
     dfs(L,isP) = sum{ dfs(L-i, i>=p) | 1<=i<=up }
     */
    static int dfs(long L, boolean isP) {//3AC 7TLE
        if (L == 0) return 1;
        Args key = new Args(L, isP);
        if (memo.containsKey(key)) return memo.get(key);

        int ans = 0;
        int up = isP ? p - 1 : k;//如果上一步跳了大于等于p,这一步不能跳大于等于p
        for (int i = 1; i <= up && i <= L; i++) {
            ans = (ans + dfs(L - i, i >= p)) % mod;
        }
        memo.put(key, ans);
        return ans;
    }

    static int mod = 20201114;
    static int k, p;
    static HashMap<Args, Integer> memo = new HashMap<>();

    static class Args {
        long L;
        boolean isP;

        public Args(long l, boolean P) {
            L = l;
            isP = P;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Args args = (Args) o;
            return L == args.L && isP == args.isP;
        }

        @Override
        public int hashCode() {
            return Objects.hash(L, isP);
        }
    }
}
