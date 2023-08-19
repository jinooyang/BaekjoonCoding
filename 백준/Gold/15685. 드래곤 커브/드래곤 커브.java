import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
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
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

	}

	static int lastx;
	static int lasty;
	static int firstx;
	static int firsty;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());// 사실상 테케
		List<Set<Node>> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(new HashSet<>());
		}
		for (int i = 0; i < n; i++) {
			// init dragon curve
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());// 시작좌표 x
			int y = Integer.parseInt(st.nextToken());// 시작좌표 y
			int d = Integer.parseInt(st.nextToken());//
			int g = Integer.parseInt(st.nextToken());
			list.get(i).add(new Node(x, y));
			firstx = x;
			firsty = y;
			if (d == 0)
				x++;
			if (d == 1)
				y--;
			if (d == 2)
				x--;
			if (d == 3)
				y++;
			list.get(i).add(new Node(x, y));// 0,1두개 들어가있음
			lastx = x;
			lasty = y;

			// g는 드래곤 세대
			// 현재 0세대는 완성
			// g번 회전시키면 된다
			for (int dc = 0; dc < g; dc++) {
				dragonCurve(list.get(i));
			}

		}
		Set<Node> set = new HashSet<>();
		for (int i = 0; i < n; i++) {
			for (Node node : list.get(i)) {
				set.add(node);
			}
		}
		int answer = 0;
		int delx[] = { 0, 0, 1, 1 };
		int dely[] = { 0, 1, 0, 1 };
		for (Node node : set) {
			boolean isSquare = true;
			for (int idx = 0; idx < 4; idx++) {
				int dx = node.x + delx[idx];
				int dy = node.y + dely[idx];
				// 범위 안에 있고 set안에 있어야함
				if (!(dx >= 0 && dx <= 100 && dy >= 0 && dy <= 100 && set.contains(new Node(dx, dy)))) {
					isSquare = false;
					break;
				}
			}
			if (isSquare)
				answer++;
		}
		System.out.println(answer);

	}

	private static void dragonCurve(Set<Node> set) {

		int basex = lastx;
		int basey = lasty;
		List<Node> tempList = new ArrayList<>();
		for (Node n : set) {
			int dx = n.x - basex;
			int dy = n.y - basey;
			int temp = dx;
			dx = dy;
			dy = temp;
			tempList.add(new Node(basex - dx, basey + dy));
			if (n.x == firstx && n.y == firsty) {
				lastx = basex - dx;
				lasty = basey + dy;
			}
		}
		for (Node n : tempList) {
			set.add(n);
		}

	}
}