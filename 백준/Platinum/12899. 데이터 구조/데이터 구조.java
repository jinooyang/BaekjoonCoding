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

	// 0,숫자를 가진 노드로 빈 세그트리를 만든다
	private static Node segTree(int s, int e, int idx) {
		if (s == e)
			return seg[idx] = new Node(ary[s]);
		int mid = (s + e) / 2;
		Node left = segTree(s, mid, idx * 2);
		Node right = segTree(mid + 1, e, idx * 2 + 1);

		return seg[idx] = left.num > right.num ? new Node(left.cnt, left.num) : new Node(right.cnt, right.num);

	}

	// change : 숫자가 추가되면 해당 값을 포함한 노드의 카운트를 1증가
	// 감소하면 1 빼기
	// val=1값추가, val=-1값 삭제
	private static Node update(int s, int e, int idx, int change, int val) {
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

	// x번째 작은 수를 찾는다, 즉 cnt가 x인 노드의 num값을 찾는다
	// 핵심 코드
	private static Node findNum(int s, int e, int idx, int x) {
		// 현재 노드와 값을 비교하자

		// 찾고자 하는 정답이니 리턴한다
		// 카운트가 같다는 것은 x번째 작은 수라는 뜻이다.
		if (seg[idx].cnt == x) {
			return seg[idx];
		}
		// 더이상 자식이 없으니 리턴한다
		if (s == e)
			return seg[idx];
		int mid = (s + e) / 2;
		// 왼쪽 오른쪽 서브트리의 노드의 값에 따라 로직이 달라진다
		Node left = seg[idx * 2];
		Node right = seg[idx * 2 + 1];
		// 왼쪽 오른쪽 자식이 모두 카운트가 0이면 값이 없다는 뜻이이 리턴한다
		if (left.cnt == 0 && right.cnt == 0)
			return seg[idx];
		// 왼쪽 자식이 없으면 오른쪽가서 찾는다
		if (left.cnt == 0)
			return findNum(mid + 1, e, idx * 2 + 1, x - left.cnt);
		// 오른쪽 자식이 없으면 왼쪽 가서 찾는다
		if (right.cnt == 0)
			return findNum(s, mid, idx * 2, x);

		// 양 쪽 자식이 모두 있다면
		// x가 왼쪽 카운트보다 크면 왼쪽은 볼 필요가 없다. 오른쪽에 답이 있기 떄문이다.
		// 대신 왼쪽 서브트리에 값 만큼 x-left.cnt를 해준다
		// 왜냐하면 왼쪽에 5개 있고 6을 찾는다면 오른쪽에서 1번째로 작은 수를 찾으면 되니까.
		if (x > left.cnt)
			return findNum(mid + 1, e, idx * 2 + 1, x - left.cnt);
		else
			return findNum(s, mid, idx * 2, x);

	}

}