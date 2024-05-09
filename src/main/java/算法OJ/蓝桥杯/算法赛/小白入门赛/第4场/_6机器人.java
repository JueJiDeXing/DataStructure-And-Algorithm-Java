package 算法OJ.蓝桥杯.算法赛.小白入门赛.第4场;

public class _6机器人 {
    /*
    1~n的环,机器人初始在1号位置
    在i号位置有pi概率移动到下一个位置(i+1),1-pi停留在原位置 ( pi表示为2^-k形式 )
    求机器人停留在每一个位置的概率,以及排序大小结果
    概率使用最简分数表示后p/q,输出x, 其中x满足0<=x<MOD 且 x*q = p (% MOD)


    输入:
    n
    k1~kn

    输出:
    第一行输出第i个位置的概率(取模后)
    第二行输出第i个位置的概率(取模前)排第几大
    为了减少输出量
     假设原第一行组成数组A1,第二行组成数组A2
     第一行输出为sum{i*A1[i]}%MOD
     第二行输出为sum{i*A2[i]}%MOD
     */
    static int MOD = 998244353;

    public static void main(String[] args) {

    }
}
