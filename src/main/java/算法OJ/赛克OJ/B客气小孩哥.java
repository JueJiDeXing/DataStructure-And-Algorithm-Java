package 算法OJ.赛克OJ;

import java.util.*;

/**
 已AC
 */
public class B客气小孩哥 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long A = sc.nextLong(), B = sc.nextLong();
        if (Math.abs(A - B) <= 1) {
            System.out.println("Brown");
        } else {
            System.out.println("Alice");
        }
    }
}
