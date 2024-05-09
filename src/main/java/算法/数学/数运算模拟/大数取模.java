package 算法.数学.数运算模拟;

public class 大数取模 {
    /**
     求a mod b
     其中a由字符串表示,a[0]表示a的最高位,a[n-1]表示a的最低位
     a[0,n-1] mod b
     = ( a[0,n-2]*10 + a[n-1] ) mod b
     = ( (a[0,n-2] * 10 mod b) + (a[n-1] mod b) ) mod b
     定义 dp[i] 表示a[0,i-1] mod b
     则有 dp[i] = (dp[i-1]*10 + a[i-1]) mod b
     从最高位0向后递推即可
     初始dp[0] = 0
     ans = dp[n]
     */
    public static int modString(String a, int b) {
        int ans = 0;
        for (char ch : a.toCharArray()) {
            ans = (ans * 10 + (ch - '0')) % b;
        }
        return ans;
    }
}
