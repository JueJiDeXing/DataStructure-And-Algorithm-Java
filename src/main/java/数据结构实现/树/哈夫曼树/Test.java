package 数据结构实现.树.哈夫曼树;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception {
        HashMap<Character, Integer> cnt = getCntFromFile();
        test(cnt);
    }

    private static HashMap<Character, Integer> getCntFromFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("E:\\ideaProject\\Example\\src\\frep.txt"));
        HashMap<Character, Integer> cnt = new HashMap<>();
        while (sc.hasNext()) {
            String[] s = sc.nextLine().split(",");
            cnt.put(s[0].charAt(0), Integer.parseInt(s[1]));
        }
        return cnt;
    }

    private static HashMap<Character, Integer> getCntFromTerminal() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        HashMap<Character, Integer> cnt = new HashMap<>();
        System.out.print("请输入字符集大小:");
        int n = sc.nextInt();
        System.out.println("请输入" + n + "行,每行以'字符,频率'表示");
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().split(",");
            cnt.put(s[0].charAt(0), Integer.parseInt(s[1]));
        }
        return cnt;
    }

    private static void test(HashMap<Character, Integer> cnt) {
        HuffmanTree huffmanTree = new HuffmanTree(cnt);
        String encode = huffmanTree.encode("THIS IS JAVA TEST");
        System.out.println("编码结果:" + encode);
        String decode = huffmanTree.decode(encode);
        System.out.println("解码结果:" + decode);
    }

}
