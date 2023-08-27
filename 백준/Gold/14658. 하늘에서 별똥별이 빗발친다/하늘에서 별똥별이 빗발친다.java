import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int i;
		int j;

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

		public Node(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}

	static Set<Node> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		set = new HashSet<>();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int length = Integer.parseInt(st.nextToken());// 트램폴린 가로세로
		int k = Integer.parseInt(st.nextToken());
		List<Integer> xlist = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			set.add(new Node(x, y));
			xlist.add(x);
		}
		Collections.sort(xlist);
		int maxStar = 0;
		int size = set.size();
		for (Node node : set) {// 100번
			// 이 노드에 트램폴린 왼쪽 위 모서리를 배치하자
			// 노드는 최대 100개임
			// 각 위치에서 별을 몇개 튕길수 있는지 최대값을 저장하자

			// int deflectCnt = 0;
			if (maxStar == size)
				break;
			for (int posx = 0; posx < xlist.size(); posx++) {// 10만번
				int x = xlist.get(posx);
				if (Math.abs(x - node.i) > length)
					continue;
				int deflectCnt = 0;
				for (Node star : set)// 100번
					if (star.i >= x && star.i <= x + length && star.j >= node.j && star.j <= node.j + length)
						deflectCnt++;

				if (deflectCnt > maxStar)
					maxStar = deflectCnt;

				if (maxStar == size)
					break;
			}
		}
		System.out.println(set.size() - maxStar);

	}
}