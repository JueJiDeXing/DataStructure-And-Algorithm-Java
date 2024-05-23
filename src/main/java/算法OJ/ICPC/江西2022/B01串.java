package 算法OJ.ICPC.江西2022;

/**
 已AC(先模拟,全0或全1时求和)
 */
public class B01串 {
    /*
    t = int(input())
    for _ in range(t):
        n, k = map(int, input().split())
        s = input()
        ans = 0
        while k >= 0 and s != '':
            cnt = s.count('1')
            if len(s) == cnt:
                # cnt + cnt-1 + ... + cnt-k
                ans += (cnt + cnt - k) * (k + 1) // 2
                break
            elif cnt == 0:
                break
            ans += cnt
            newS = ''
            for i in range(len(s) - 1):
                newS = newS + max(s[i], s[i + 1])
            s = newS
            k -= 1
        print(ans)
     */


}
