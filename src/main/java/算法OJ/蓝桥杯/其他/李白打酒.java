package 算法OJ.蓝桥杯.其他;
/**
 已AC
 */
public class 李白打酒 {
    /*
    初始值为2
    遇到a共5次,b共10次
    每次遇到a,值*2; 遇到b,值-1
    求方案数
     */
    public static void main(String[] args) {
        System.out.println(dfs(2, 0, 0));
    }

    static int dfs(int x, int a, int b) {
        if (a == 5 && b == 10) {
            return x == 0 ? 1 : 0;
        }
        int ans = 0;
        if (a < 5 && b != 10) {//最后一次需要是b
            ans += dfs(x * 2, a + 1, b);
        }
        if (b < 10 && x > 0) {
            ans += dfs(x - 1, a, b + 1);
        }
        return ans;
    }

}
