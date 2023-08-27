import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int cnt;
		int num;

		public Node(int cnt, int num) {
			super();
			this.cnt = cnt;
			this.num = num;
		}

		public Node(Node n) {
			super();
			this.cnt = n.cnt;
			this.num = n.num;
		}

		@Override
		public String toString() {
			return "Node [cnt=" + cnt + ", num=" + num + "]";
		}

	}

	static final int end = 2_000_000;
	static Node seg[] = new Node[4 * (end + 1)];
	static Node ary[] = new Node[end + 1];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		init();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int flag = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			if (flag == 1) {// update segtree
				update(1, end, 1, x, 1);
			}
			if (flag == 2) {
				// for (int r = 0; r < 100; r++) {
				// System.out.print(seg[r] + " ");
				// }
				// System.out.println();
				Node found = findNum(1, end, 1, x);
				sb.append(found.num).append("\n");
				// System.out.println(found.num);
				// System.out.println("answer : " + found.cnt + " " + found.num);
				update(1, end, 1, found.num, -1);
			}

		}
		System.out.println(sb.toString());

	}

	private static void init() {
		for (int i = 1; i <= end; i++) {
			Node n = new Node(0, i);
			ary[i] = n;
		}
		segTree(1, end, 1);
	}

	// cnt의 구간 합을 기준으로 세그 트리를 만든다
	private static Node segTree(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = new Node(ary[s]);
		int mid = (s + e) / 2;
		Node left = segTree(s, mid, idx * 2);
		Node right = segTree(mid + 1, e, idx * 2 + 1);

		return seg[idx] = left.num > right.num ? new Node(left.cnt, left.num) : new Node(right.cnt, right.num);

	}

	private static Node update(int s, int e, int idx, int change, int val) {// val=1값추가, val=-1값 삭제
		// 현재노드가....
		// 범위 밖이면 리턴
		if (change < s || change > e)
			return seg[idx];

		// 범위 안쪽이면 값 +1
		seg[idx].cnt += val;
		// s==e이면 리프 노드니까 더이상 볼 필요 없다
		if (s == e)
			return seg[idx];

		// 현재 노드 처리 완료
		// 좌,우 서브트리 수정
		int mid = (s + e) / 2;
		Node left = update(s, mid, idx * 2, change, val);
		Node right = update(mid + 1, e, idx * 2 + 1, change, val);
		if (left.cnt != 0 && right.cnt != 0)
			seg[idx].num = Math.max(left.num, right.num);
		if (right.cnt == 0)
			seg[idx].num = left.num;
		if (left.cnt == 0)
			seg[idx].num = right.num;
		return seg[idx];

	}

	private static Node findNum(int s, int e, int idx, int x) {// x번째 작은 수를 찾는다, 즉 cnt가 x인 노드의 num값을 찾는다
		// 현재 노드와 값을 비교하자
		// System.out.println(seg[idx].cnt + " " + seg[idx].num + " x : " + x);
		if (x > seg[idx].cnt)
			return seg[idx];

		if (seg[idx].cnt == x) {
			return seg[idx];
		}
		if (s == e)// 같은 수 가 여러개 있는 경우도 생각해야한다?
			return seg[idx];
		int mid = (s + e) / 2;
		Node left = seg[idx * 2];
		// System.out.println(left);
		Node right = seg[idx * 2 + 1];
		if (left.cnt == 0 && right.cnt == 0)
			return seg[idx];
		if (left.cnt == 0)
			return findNum(mid + 1, e, idx * 2 + 1, x - left.cnt);
		if (right.cnt == 0)
			return findNum(s, mid, idx * 2, x);
		if (x > left.cnt)
			return findNum(mid + 1, e, idx * 2 + 1, x - left.cnt);
		else
			return findNum(s, mid, idx * 2, x);

		// find x
		// 왼쪽 서브트리의 num이 더 크다면 -> 왼쪽으로 이동
		// 오른쪽 서브트리의 num이 더 크다면 - >오른쪽으로 이동하는데 x에서 왼쪽 서브트리의 cnt를 뺸다

		// 현재 수cnt를 내린다
	}

}