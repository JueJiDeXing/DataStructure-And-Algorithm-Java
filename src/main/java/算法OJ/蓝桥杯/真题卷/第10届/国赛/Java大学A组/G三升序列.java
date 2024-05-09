package 算法OJ.蓝桥杯.真题卷.第10届.国赛.Java大学A组;

import java.util.Scanner;
/**
 已AC
 */
public class G三升序列 {
    /*
    如果在同一行或同一列或同一对角线的三个字母(不需要连续)
    是从左到右或从上到下升序的,则记为1个三升序列
    求下面的30行50列矩阵有多少个三升序列
VLPWJVVNNZSWFGHSFRBCOIJTPYNEURPIGKQGPSXUGNELGRVZAG
SDLLOVGRTWEYZKKXNKIRWGZWXWRHKXFASATDWZAPZRNHTNNGQF
ZGUGXVQDQAEAHOQEADMWWXFBXECKAVIGPTKTTQFWSWPKRPSMGA
BDGMGYHAOPPRRHKYZCMFZEDELCALTBSWNTAODXYVHQNDASUFRL
YVYWQZUTEPFSFXLTZBMBQETXGXFUEBHGMJKBPNIHMYOELYZIKH
ZYZHSLTCGNANNXTUJGBYKUOJMGOGRDPKEUGVHNZJZHDUNRERBU
XFPTZKTPVQPJEMBHNTUBSMIYEGXNWQSBZMHMDRZZMJPZQTCWLR
ZNXOKBITTPSHEXWHZXFLWEMPZTBVNKNYSHCIQRIKQHFRAYWOPG
MHJKFYYBQSDPOVJICWWGGCOZSBGLSOXOFDAADZYEOBKDDTMQPA
VIDPIGELBYMEVQLASLQRUKMXSEWGHRSFVXOMHSJWWXHIBCGVIF
GWRFRFLHAMYWYZOIQODBIHHRIIMWJWJGYPFAHZZWJKRGOISUJC
EKQKKPNEYCBWOQHTYFHHQZRLFNDOVXTWASSQWXKBIVTKTUIASK
PEKNJFIVBKOZUEPPHIWLUBFUDWPIDRJKAZVJKPBRHCRMGNMFWW
CGZAXHXPDELTACGUWBXWNNZNDQYYCIQRJCULIEBQBLLMJEUSZP
RWHHQMBIJWTQPUFNAESPZHAQARNIDUCRYQAZMNVRVZUJOZUDGS
PFGAYBDEECHUXFUZIKAXYDFWJNSAOPJYWUIEJSCORRBVQHCHMR
JNVIPVEMQSHCCAXMWEFSYIGFPIXNIDXOTXTNBCHSHUZGKXFECL
YZBAIIOTWLREPZISBGJLQDALKZUKEQMKLDIPXJEPENEIPWFDLP
HBQKWJFLSEXVILKYPNSWUZLDCRTAYUUPEITQJEITZRQMMAQNLN
DQDJGOWMBFKAIGWEAJOISPFPLULIWVVALLIIHBGEZLGRHRCKGF
LXYPCVPNUKSWCCGXEYTEBAWRLWDWNHHNNNWQNIIBUCGUJYMRYW
CZDKISKUSBPFHVGSAVJBDMNPSDKFRXVVPLVAQUGVUJEXSZFGFQ
IYIJGISUANRAXTGQLAVFMQTICKQAHLEBGHAVOVVPEXIMLFWIYI
ZIIFSOPCMAWCBPKWZBUQPQLGSNIBFADUUJJHPAIUVVNWNWKDZB
HGTEEIISFGIUEUOWXVTPJDVACYQYFQUCXOXOSSMXLZDQESHXKP
FEBZHJAGIFGXSMRDKGONGELOALLSYDVILRWAPXXBPOOSWZNEAS
VJGMAOFLGYIFLJTEKDNIWHJAABCASFMAKIENSYIZZSLRSUIPCJ
BMQGMPDRCPGWKTPLOTAINXZAAJWCPUJHPOUYWNWHZAKCDMZDSR
RRARTVHZYYCEDXJQNQAINQVDJCZCZLCQWQQIKUYMYMOVMNCBVY
ABTCRRUXVGYLZILFLOFYVWFFBZNFWDZOADRDCLIRFKBFBHMAXX
     */
    public static void main(String[] args) {
        map = new char[n][m];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            map[i] = sc.next().toCharArray();
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans += count(i, j);
            }
        }
        System.out.println(ans);//180414
    }

    static int n = 30, m = 50;
    static char[][] map;

    /**
     统计以(i,j)为头的位置有多少个三升序列
     */
    static int count(int i, int j) {
        return checkRow(i, j) + checkCol(i, j) + checkMain(i, j) + checkSub(i, j);
    }

    static int checkRow(int i, int j) {
        int count = 0;
        for (int k = j + 1; k < m; k++) {
            if (map[i][j] >= map[i][k]) continue;
            for (int l = k + 1; l < m; l++) {
                if (map[i][k] < map[i][l]) {
                    count++;
                }
            }
        }
        return count;
    }

    static int checkCol(int i, int j) {
        int count = 0;
        for (int k = i + 1; k < n; k++) {
            if (map[i][j] >= map[k][j]) continue;
            for (int l = k + 1; l < n; l++) {
                if (map[k][j] < map[l][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    static int checkMain(int i, int j) {
        int count = 0;
        for (int k = 1; !(i + k >= n || j + k >= m); k++) {
            if (map[i][j] >= map[i + k][j + k]) continue;
            for (int l = k + 1; !(i + l >= n || j + l >= m); l++) {
                if (map[i + k][j + k] < map[i + l][j + l]) {
                    count++;
                }
            }
        }
        return count;
    }

    static int checkSub(int i, int j) {
        //副对角线升序或降序都可
        int count = 0;
        for (int k = 1; !(i + k >= n || j - k < 0); k++) {
            for (int l = k + 1; !(i + l >= n || j - l < 0); l++) {
                if ((map[i][j] < map[i + k][j - k] && map[i + k][j - k] < map[i + l][j - l]) ||
                        (map[i][j] > map[i + k][j - k] && map[i + k][j - k] > map[i + l][j - l])) {
                    count++;
                }
            }
        }
        return count;
    }
}
