import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Solution {
	static boolean visited[];
	static int n;
	static int answer = 0;
	static int ary[][];
	static Stack<Integer> stack;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		// int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			ary = new int[n + 2][2];
			visited = new boolean[n + 2];
			answer = Integer.MAX_VALUE;
			stack = new Stack<>();
			// 0,1은 회사와 집
			for (int i = 0; i < n + 2; i++) {
				ary[i][0] = Integer.parseInt(st.nextToken());
				ary[i][1] = Integer.parseInt(st.nextToken());
			}
			// 입력 완료
			// dfs로 시작점이 회사이고 끝점이 집인 경우를 모두 탐색하자
			visited[0] = true;
			dfs(0, 0, 0);
			System.out.println("#" + test_case + " " + answer);
		}
	}

	private static void dfs(int visitedCnt, int dist, int before) {
		if (visitedCnt == n) {
			// 집으로 가는 경로를 더하고 최단 경로를 저장하자
			int x = Math.abs(ary[before][0] - ary[1][0]) + Math.abs(ary[before][1] - ary[1][1]);
			int newDist = x + dist;
			if (newDist < answer)
				answer = newDist;
			return;
		}
		for (int i = 2; i < n + 2; i++) {
			if (!visited[i]) {
				int x = Math.abs(ary[before][0] - ary[i][0]) + Math.abs(ary[before][1] - ary[i][1]);
				int newDist = x + dist;
				if (newDist < answer) {
					visited[i] = true;
					dfs(visitedCnt + 1, newDist, i);
					visited[i] = false;
				}
			}
		}
	}
}