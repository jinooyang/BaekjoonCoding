import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static List<List<Integer>> list;
	static int n;
	static int m;
	static int v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());// 정점
		m = Integer.parseInt(st.nextToken());// 간선
		v = Integer.parseInt(st.nextToken());// 시작
		list = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}
		for (int i = 1; i < n + 1; i++) {
			Collections.sort(list.get(i));
		}
		printdfs();
		System.out.println();
		printbfs();

	}

	private static void printdfs() {
		boolean visited[] = new boolean[n + 1];
		Stack<Integer> q = new Stack<>();
		q.add(v);
		visited[v] = true;

		DFS(visited, q);

	}

	private static void DFS(boolean[] visited, Stack<Integer> q) {
		while (!q.isEmpty()) {
			int now = q.pop();
			System.out.print(now + " ");
			for (int i = 0; i < list.get(now).size(); i++) {
				if (!visited[list.get(now).get(i)]) {
					visited[list.get(now).get(i)] = true;
					q.add(list.get(now).get(i));
					DFS(visited, q);
				}
			}
		}
	}

	private static void printbfs() {
		boolean visited[] = new boolean[n + 1];
		Queue<Integer> q = new ArrayDeque<>();
		q.add(v);
		visited[v] = true;

		while (!q.isEmpty()) {
			int now = q.poll();
			System.out.print(now + " ");
			for (int i = 0; i < list.get(now).size(); i++) {
				if (!visited[list.get(now).get(i)]) {
					q.add(list.get(now).get(i));
					visited[list.get(now).get(i)] = true;
				}
			}
		}
	}
}