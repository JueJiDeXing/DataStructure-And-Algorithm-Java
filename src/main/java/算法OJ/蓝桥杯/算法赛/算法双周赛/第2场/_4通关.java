package 算法OJ.蓝桥杯.算法赛.算法双周赛.第2场;

import java.util.*;
/**
 已AC
 */
public class _4通关 {

	public static void main(String[] aaaaa) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), p = sc.nextInt();
		List<Integer>[] graph = new ArrayList[n + 1];
		Arrays.setAll(graph, k -> new ArrayList<>());
		int[] s = new int[n + 1], k = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			int f = sc.nextInt(), ss = sc.nextInt(), kk = sc.nextInt();
			graph[f].add(i);
			s[i] = ss;
			k[i] = kk;
		}
		Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> k[o1] - k[o2]);// [需要经验,节点id]
		queue.offer(1);
		int sum = 0;
		while (!queue.isEmpty()) {
			int id = queue.poll();
			if (p < k[id])
				break;
			sum++;
			p += s[id];
			for (int i : graph[id])
				queue.offer(i);
		}
		System.out.println(sum);
	}
}
