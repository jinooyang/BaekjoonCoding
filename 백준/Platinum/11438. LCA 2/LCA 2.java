import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static List<List<Integer>> list;
	static List<Integer> ary;
	static List<Integer> ary2;
	static boolean visited[];
	static int seg[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		ary = new ArrayList<>();
		ary2 = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}
		visited = new boolean[n + 1];
		visited[1] = true;
		dfs(1);

		int myIndex = 0;
		Map<Integer, Integer> mapToMyNode = new HashMap<>();
		Map<Integer, Integer> mapFromMyNode = new HashMap<>();
		Queue<Integer> q = new ArrayDeque<>();
		boolean vq[] = new boolean[n + 1];
		q.add(1);
		vq[1] = true;
		int number = 1;

		// bfs로 매핑해준다
		while (!q.isEmpty()) {
			int now = q.poll();
			mapToMyNode.put(now, number);
			mapFromMyNode.put(number, now);
			number++;
			List<Integer> nowList = list.get(now);
			for (int i = 0; i < nowList.size(); i++) {
				if (vq[nowList.get(i)] == false) {
					vq[nowList.get(i)] = true;
					q.add(nowList.get(i));
				}
			}
		}

		int N = ary.size();

		for (int i = 0; i < N; i++) {
			int x = ary.get(i);
			int change = mapToMyNode.get(x);
			ary2.add(change);
		}

//		for (int i = 0; i < N; i++) {
//			System.out.print(ary.get(i) + " ");
//		}
//		System.out.println();
		seg = new int[4 * N];
		init(0, N - 1, 1);
//		System.out.println("seg");
//		for (int i = 0; i < 4 * N; i++) {
//			System.out.print(seg[i] + " " );
//		}

		int idx[] = new int[n + 1];
		Arrays.fill(idx, -1);
		for (int i = 0; i < ary2.size(); i++) {
			int x = ary2.get(i);
			if (idx[x] == -1)
				idx[x] = i;
		}
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			a = mapToMyNode.get(a);
			b = mapToMyNode.get(b);

			int startIndexA = idx[a];
			int startIndexB = idx[b];
//			System.out.println(idxa + " " + idxb);
			int lca = getMin(0, N - 1, 1, Math.min(startIndexA, startIndexB), Math.max(startIndexA, startIndexB));
			lca = mapFromMyNode.get(lca);
			sb.append(lca).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int getMin(int s, int e, int idx, int l, int r) {
		if (r < s || e < l)
			return Integer.MAX_VALUE;
		if (l <= s && e <= r) {
			return seg[idx];
		}
		int mid = (s + e) / 2;
		int left = getMin(s, mid, idx * 2, l, r);
		int right = getMin(mid + 1, e, idx * 2 + 1, l, r);
		return Math.min(left, right);
	}

	private static int init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = ary2.get(s);
		int mid = (s + e) / 2;
		return seg[idx] = Math.min(init(s, mid, idx * 2), init(mid + 1, e, idx * 2 + 1));
	}

	private static void dfs(int i) {
		ary.add(i);
		List<Integer> nowList = list.get(i);
		for (int k = 0; k < nowList.size(); k++) {
			int x = nowList.get(k);
			if (!visited[x]) {
				visited[x] = true;
				dfs(x);
				ary.add(i);
			}
		}
	}
}