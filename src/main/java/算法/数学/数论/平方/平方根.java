package 算法.数学.数论.平方;

public class 平方根 {
    //求平方根的整数部分

    /**
     <h1>二分法</h1>
     */
    public int mySqrt1(int x) {
        int left = 1,right = x;
        int res = 0;
        while (left <= right) {//还有元素没有尝试
            int mid = (left + right) >>> 1;
            //long quad = (long) mid * mid;//大数平方越界
            if (mid <= x / mid) {//使用除法,防止越界
                res = mid;//用一个变量记录平方小于x的值
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;

    }

    /**
     <h1>对数转换法</h1>
     */
    public int mySqrt2(int x) {
        if (x == 0) return 0;

        int res = (int) Math.exp(0.5 * Math.log(x));//e^((lnx)/2)
        return (long) (res + 1) * (res + 1) <= x ? res + 1 : res;//小数计算有误差,判断res与res+1哪个才是正确的
    }
    int sqrt(int x){
        return (int)Math.sqrt(x);
    }
}
