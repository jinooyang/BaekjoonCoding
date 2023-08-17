import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Solution {
	static class Node {
		int i;
		int j;

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + i;
			result = prime * result + j;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (i != other.i)
				return false;
			if (j != other.j)
				return false;
			return true;
		}

	}

	static int BC[][];
	static int moveA[];
	static int moveB[];
	static int deli[] = { -1, 0, 1, 0 };
	static int delj[] = { 0, 1, 0, -1 };

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		// int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int moveCnt = Integer.parseInt(st.nextToken());// 총 이동시간 (20 ≤ M ≤ 100)
			int BCnum = Integer.parseInt(st.nextToken());// BC의 개수 (1 ≤ A ≤ 8)
			BC = new int[BCnum][4];
			moveA = new int[moveCnt + 1];
			moveB = new int[moveCnt + 1];
			// int map[][] = new int[11][11];
			moveA[moveCnt] = 0;
			moveB[moveCnt] = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < moveCnt; i++) {// 사용자 A의 이동 정보
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < moveCnt; i++) {// 사용자 B의 이동 정보
				moveB[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < BCnum; i++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {
					BC[i][k] = Integer.parseInt(st.nextToken());// 가로좌표,세로좌표, 충전범위,처리량
				}

			}

			// 입력 완료
			// set에 각 BC의 범위에 해당하는 좌표를 모두 넣자
			Set<Node> set[] = new HashSet[BCnum];
			for (int i = 0; i < BCnum; i++) {
				set[i] = new HashSet<>();
			}
			// 계산
			addRangeToSet(BCnum, BC, set);

			// map을 이동하면서 충전하자
			// 두 사람의 위치를 저장할 노드 두개
			Node nodeA = new Node(1, 1);
			Node nodeB = new Node(10, 10);
			// 충전량을 기록할 배열
			int answer = 0;// 0A 1B
			for (int i = 0; i < moveCnt + 1; i++) {// 이동을 하자
				// 현재위치에서 얼마나 충전할 수 있는지 계산하자
				List<Integer> listA = new ArrayList<>();// 현재 A가 속한 충전탑
				List<Integer> listB = new ArrayList<>();// 현재 B가 속한 충전탑

				for (int k = 0; k < BCnum; k++) {
					if (set[k].contains(nodeA)) {
						listA.add(k);
					}
					if (set[k].contains(nodeB)) {
						listB.add(k);
					}
				}

				answer += calc(listA, listB);

				// 다음 위치를 계산하자
				moveA(nodeA, i);
				moveB(nodeB, i);
			}
			System.out.println("#" + test_case + " " + answer);
		}
	}

	private static void moveA(Node node, int i) {
		if (moveA[i] == 0)
			return;
		int di = node.i + deli[moveA[i] - 1];
		int dj = node.j + delj[moveA[i] - 1];
		node.i = di;
		node.j = dj;
	}

	private static void moveB(Node node, int i) {
		if (moveB[i] == 0)
			return;
		int di = node.i + deli[moveB[i] - 1];
		int dj = node.j + delj[moveB[i] - 1];
		node.i = di;
		node.j = dj;
	}

	private static int calc(List<Integer> listA, List<Integer> listB) {
		int max = 0;
		if (listA.size() == 0 && listB.size() == 0)
			return max;
		if (listA.size() == 0) {

			for (int p = 0; p < listB.size(); p++) {
				int power = BC[listB.get(p)][3];
				if (power > max)
					max = power;
			}
			return max;
		}

		if (listB.size() == 0) {

			for (int p = 0; p < listA.size(); p++) {
				int power = BC[listA.get(p)][3];
				if (power > max)
					max = power;
			}
			return max;
		}

		for (int p = 0; p < listA.size(); p++) {
			for (int q = 0; q < listB.size(); q++) {
				int power = BC[listA.get(p)][3] + BC[listB.get(q)][3];
				if (listA.get(p) == listB.get(q))
					power = power / 2;
				if (power > max)
					max = power;
			}

		}

		return max;
	}

	private static void addRangeToSet(int BCnum, int[][] BC, Set<Node>[] set) {
		for (int BCnumber = 0; BCnumber < BCnum; BCnumber++) {// bfs를 이용해서 각 BC의 범위를 set에 저장하자

			Queue<Node> q = new ArrayDeque<>();
			Queue<Integer> qcnt = new ArrayDeque<>();
			boolean visited[][] = new boolean[11][11];
			q.add(new Node(BC[BCnumber][1], BC[BCnumber][0]));
			qcnt.add(0);
			visited[BC[BCnumber][1]][BC[BCnumber][0]] = true;

			while (!q.isEmpty()) {
				Node now = q.poll();
				int cnt = qcnt.poll();
				if (cnt > BC[BCnumber][2])
					break;//
				set[BCnumber].add(now);
				for (int idx = 0; idx < 4; idx++) {
					int di = deli[idx] + now.i;
					int dj = delj[idx] + now.j;
					if (di >= 0 && dj >= 0 && di < 11 && dj < 11 && !visited[di][dj]) {
						q.add(new Node(di, dj));
						visited[di][dj] = true;
						qcnt.add(cnt + 1);
					}

				}
			}
		}
	}
}