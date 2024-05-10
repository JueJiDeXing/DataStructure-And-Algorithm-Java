package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学A组;

import java.util.Scanner;

/**
 已AC
 */
public class C内存空间 {
    /*
    输入变量声明赋值语句,输出内存大小单位GB,MB,KB,B,优先用大单位表示
    //int:4 byte; long:8 byte; string:len byte
    //int[]:cnt*4 byte; long[]:cnt*8 byte
    例如:
    int a=1,b=2; 输出8B
    long[] nums=new long[131072]; 输出1MB
    String s1="hello",s2="world";输出10B
     */
    public static void main(String[] args) {
        main_enter();
    }

    private static void main_enter() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        long totalByte = 0;
        for (int i = 0; i < T; i++) {
            totalByte += handle(sc.nextLine());
        }
        System.out.println(change(totalByte));
    }

    private static long handle(String statement) {
        if (statement.isEmpty()) return 0;
        int type = getType(statement);//方便判断类型
        if (type == 1 || type == 2) {//动态数组
            int cnt = 0;//数组长度之和
            String[] res = statement.split("[=,]");//按照"="和","切割,这样奇数项就是"new type[len]"的形式
            for (int j = 1; j < res.length; j += 2) {
                //截取每个数组长度c
                int idx1 = res[j].lastIndexOf("["), idx2 = res[j].lastIndexOf("]");
                int c = Integer.parseInt(res[j].substring(idx1 + 1, idx2));
                cnt += c;
            }
            return type == 1 ? cnt * 4L : cnt * 8L;
        }
        if (type == 5) {
            String[] res = statement.split("\"");
            long len = 0;//统计长度
            for (int j = 1; j < res.length; j += 2) {
                len += res[j].length();
            }
            return len;
        }
        int count = 0;//赋值语句个数
        for (char ch : statement.toCharArray()) {
            if (ch == '=') count++;
        }
        return type == 3 ? count * 4L : count * 8L;
    }

    private static int getType(String statement) {
        if (statement.startsWith("int[]")) return 1;
        if (statement.startsWith("long[]")) return 2;
        if (statement.startsWith("int")) return 3;//注意需要先处理数组
        if (statement.startsWith("long")) return 4;
        return 5;//String
    }

    /**
     将字节转换为aGBbMBcKBd形式的字符串
     */
    private static String change(long totalByte) {
        StringBuilder res = new StringBuilder();
        String[] units = new String[]{"B", "KB", "MB", "GB"};//4个单位
        //1024进制
        for (int i = 0; i < 4 && totalByte > 0; i++) {//单位只有4个,循环4次
            int t = (int) (totalByte % 1024);
            if (t != 0) res.insert(0, t + units[i]);//如果该位为0则不输出数字和单位
            totalByte /= 1024;
        }
        return res.toString();
    }
}
