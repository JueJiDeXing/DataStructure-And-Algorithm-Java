package 算法OJ.ICPC.江西2022;

/**
 已AC(哈希表维护度数相同的点即可,但是Java写不了,然后必须链式前向星建图否则会爆内存)
 */
public class C图中删点 {
    /*
    #include<bits/stdc++.h>

using namespace std;
const int N = 2e6 + 1;
int n, m, degree[N];
set<int> s[N];
struct Edge {
    int v, nxt;
} e[N];
int en = 0, h[N];

void adde(int u, int v) {
    e[++en].v = v, e[en].nxt = h[u], h[u] = en;
}

int main() {
    ios::sync_with_stdio(0), cin.tie(0), cout.tie(0);
    cin >> n >> m;
    for (int i = 1; i <= m; i++) {
        int u, v;
        cin >> u >> v;
        adde(u, v);
        adde(v, u);
        degree[u]++, degree[v]++;
    }
    for (int i = 1; i <= n * 2; i++)s[degree[i]].insert(i);
    int maxD = m;
    while (maxD >= 0) {
        set<int> &list = s[maxD];
        if (list.size() < 2) {
            maxD--;
            continue;
        }
        int x = *list.begin();
        list.erase(x);
        int y = *list.begin();
        list.erase(y);
        cout << x << " " << y << "\n";
        degree[x] = degree[y] = 0;
        for (int i = h[x]; i; i = e[i].nxt) {
            int v = e[i].v;
            if (degree[v] > 0) {
                s[degree[v]].erase(v);
                degree[v]--;
                s[degree[v]].insert(v);
            }
        }
        for (int i = h[y]; i; i = e[i].nxt) {
            int v = e[i].v;
            if (degree[v] > 0) {
                s[degree[v]].erase(v);
                degree[v]--;
                s[degree[v]].insert(v);
            }
        }
    }
    return 0;
}
     */


}
