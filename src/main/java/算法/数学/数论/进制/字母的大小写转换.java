package 算法.数学.数论.进制;

public class  字母的大小写转换 {
    public static void main(String[] args) {
        //统一小写
        char a = 'a' | 32;
        char A_a = 'A' | 32;
        assert a == A_a;
        //统一大写
        char B = 'B' & 95;
        char b_B = 'b' & 95;
        assert B == b_B;
        //大小写互转
        char c = 'C' ^ 32;
        char C = 'c' ^ 32;
        assert c == C;
        /*
        原理:
        大写字母=64+字母序     A=1000001 B=1000002...
        小写字母=64+32+字母序  a=1100001 b=1100002...
        字母序不变,只改变第5位即可
         */
    }

    public void fun() {

    }
}
