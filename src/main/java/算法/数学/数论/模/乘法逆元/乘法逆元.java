package 算法.数学.数论.模.乘法逆元;

public class 乘法逆元 {
    /*
    在计算 a1*a2*..*ak/x mod p 时,a=a1*a2*..*ak是多个数的相乘,需要逐步取模
    但 a / x mod p != (a mod p) / (x mod p) mod p , 除法不适用模性质
    a / x mod p = a * inv mod p = (a mod p) * (inv mod p) mod p 需要使用乘法逆元,将除法变为乘法
     */
    public static void main(String[] args) {
        //示例: 求 a(a+1)(2a+1)/6 % p 其中a非常大,它的乘法运算需要逐步取模
        long a = 999999999L;
        int x = 6, inv = 166666668;//6在MOD下的乘法逆元

        System.out.println(a * (a + 1) * (2 * a + 1) / x % MOD);//WA, 因为 a * (a + 1)* (2 * a + 1)溢出

        long t = a * (a + 1) % MOD * (2 * a + 1) % MOD;
        System.out.println(t / x % MOD);//WA, 因为除法不适用模性质

        System.out.println(t * inv % MOD);// AC, 使用乘法逆元,乘法代替除法,可拆分

        long kMOD = 6L * MOD;
        System.out.println(a * (a + 1) % kMOD * (2 * a + 1) % kMOD / 6);//AC, 使用模上的除法提取
    }

    static final int MOD = (int) 1e9 + 7;


}
