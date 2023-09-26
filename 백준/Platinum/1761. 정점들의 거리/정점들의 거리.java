import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int number;
		int depth;

		public Node(int number, int depth) {
			super();
			this.number = number;
			this.depth = depth;
		}

	}

	static class Edge {
		int to;
		int dist;

		public Edge(int to, int dist) {
			super();
			this.to = to;
			this.dist = dist;
		}

	}

	static List<List<Edge>> list;
	static List<Node> ary;
	static Node seg[];
	static boolean visited[];
	static int dist[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			list.add(new ArrayList<>());
		}
		ary = new ArrayList<>();
		int N = 2 * n - 1;
		seg = new Node[4 * N];
		visited = new boolean[n + 1];
		dist = new int[n + 1];
		// tree input
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.get(a).add(new Edge(b, c));
			list.get(b).add(new Edge(a, c));
		}
		// 전위 순회 결과가...node번호와 depth로 나뉜다.
		visited[1] = true;
		dfs(1, 0, 0);

		// ary를 한바퀴 돌면서 인덱스 변환을 해준다
		// 세그 트리에서 어느 구간을 탐색할지 알기 위한 index변환
		int index[] = new int[n + 1];
		Arrays.fill(index, -1);
		for (int i = 0; i < ary.size(); i++) {
			int x = ary.get(i).number;
			if (index[x] == -1)
				index[x] = i;
		}

		// 전위 순회 결과를 바탕으로 세그트리를 생성한다.
		init(0, N - 1, 1);
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int l = index[x];
			int r = index[y];
			Node node = lca(0, N - 1, 1, Math.min(l, r), Math.max(l, r));
			long ans = dist[x] + dist[y] - (dist[node.number] * 2);
			sb.append(ans).append("\n");

		}
		System.out.println(sb.toString());
	}

	private static Node lca(int s, int e, int idx, int l, int r) {

		if (r < s || e < l) {
			return new Node(0, Integer.MAX_VALUE);
		}
		if (l <= s && e <= r) {
			return seg[idx];
		}
		int mid = (s + e) / 2;
		Node left = lca(s, mid, idx * 2, l, r);
		Node right = lca(mid + 1, e, idx * 2 + 1, l, r);
		if (left.depth < right.depth)
			return left;

		return right;
	}

	private static Node init(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = new Node(ary.get(s).number, ary.get(s).depth);
		int mid = (s + e) / 2;
		Node left = init(s, mid, idx * 2);
		Node right = init(mid + 1, e, idx * 2 + 1);
		if (left.depth < right.depth)
			return seg[idx] = new Node(left.number, left.depth);
		else
			return seg[idx] = new Node(right.number, right.depth);
	}

	private static void dfs(int i, int depth, int distToRoot) {
		ary.add(new Node(i, depth));
		dist[i] = distToRoot;
		List<Edge> e = list.get(i);
		for (Edge edge : e) {
			if (!visited[edge.to]) {
				visited[edge.to] = true;
				dfs(edge.to, depth + 1, distToRoot + edge.dist);
				ary.add(new Node(i, depth));
			}
		}
	}
}