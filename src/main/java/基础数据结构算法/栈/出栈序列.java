package 基础数据结构算法.栈;

class 出栈序列 {
    /*
    元素以 1,2,...,n进栈 ,进栈的同时随时可以出栈,最后一个元素入栈后,全部出栈
    问出栈顺序有多少
     */

    /*
    卡特兰数应用
    C(n)= &sum i=0 → n-1  C(i)*C(n-1-i)<br>
    例:n=4
    右节点表示在之前出栈的个数,左节点表示在之后出栈的个数
        1最先出        1第二出        1第三出        1最后出
        /   \          /  \         /   \         /   \
      C(3)  C(0)     C(2) C(1)    C(1)  C(2)     C(0)  C(3)
     */
    public int outNum(int n) {
        int[] C = new int[n + 1];
        C[0] = C[1] = 1;
        for (int i = 2; i <= n; i++) {//递推求C(n)
            //C(i)=SUM( C(j) * C(i-j-1) )
            for (int j = 0; j < i; j++) {
                C[i] += C[j] * C[i - 1 - j];
            }

        }
        return C[n];
    }
}
