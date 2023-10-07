import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int deli[] = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int delj[] = { -1, -1, 0, 1, 1, 1, 0, -1 };

	static int copyi[] = { 1, 1, -1, -1 };
	static int copyj[] = { 1, -1, 1, -1 };

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

	static class Copy {
		int i;
		int j;
		int num;

		public Copy(int i, int j, int num) {
			super();
			this.i = i;
			this.j = j;
			this.num = num;
		}

	}

	static List<Node> cloud = new ArrayList<>();// 구름 위치 저장
	static Set<Node> delete = new HashSet<>();// 구름이 없어진 위치 저장
	static List<Copy> copyList = new ArrayList<>();
	static int ary[][];
	static int n;

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		ary = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(ary[i][j] + " ");
//			}
//			System.out.println();
//		}

		cloud.add(new Node(n - 1, 0));
		cloud.add(new Node(n - 1, 1));
		cloud.add(new Node(n - 2, 0));
		cloud.add(new Node(n - 2, 1));
		for (int q = 0; q < m; q++) {
//			System.out.println("-----");
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			// 1.모든 구름을 d방향으로 s만큼 이동한다
			move(d, s);
			// 2. 구름 위치에 물의 양 1 증가
			rain();
			// 3.delete cloud;
			delete();
			// 4. 물복사
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < n; j++) {
//					System.out.print(ary[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("copy");
			copyWater();
			// 5.새로운 구름 생성
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < n; j++) {
//					System.out.print(ary[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("makecloud");
			makeCloud();
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < n; j++) {
//					System.out.print(ary[i][j] + " ");
//				}
//				System.out.println();
//			}
		}
		long answer = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				answer += ary[i][j];
			}
		}
		System.out.println(answer);
	}

	private static void makeCloud() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (ary[i][j] >= 2) {
					Node newCloud = new Node(i, j);
					if (!delete.contains(newCloud)) {
						ary[i][j] -= 2;
						cloud.add(newCloud);
//						System.out.println("new Cloud : " + newCloud.i + " " + newCloud.j);
					}
				}
			}
		}
	}

	private static void copyWater() {
		copyList.clear();
		for (int c = 0; c < cloud.size(); c++) {
			Node now = cloud.get(c);
			Copy copy = new Copy(now.i, now.j, 0);
			int num = 0;
			for (int idx = 0; idx < 4; idx++) {
				int di = now.i + copyi[idx];
				int dj = now.j + copyj[idx];
				if (di >= 0 && dj >= 0 && di < n && dj < n && ary[di][dj] > 0) {
					num ++;
				}
			}
			copy.num = num;
			copyList.add(copy);
		}

		for (int i = 0; i < copyList.size(); i++) {
			Copy c = copyList.get(i);
			ary[c.i][c.j] += c.num;
		}

		cloud.clear();
	}

	private static void delete() {
		delete.clear();
		for (int i = 0; i < cloud.size(); i++) {
			delete.add(cloud.get(i));
		}
//		cloud.clear(); //물복사때사용하고 지운다
	}

	private static void rain() {
		for (int c = 0; c < cloud.size(); c++) {
			Node now = cloud.get(c);
			ary[now.i][now.j]++;
		}
	}

	private static void move(int d, int s) {
		for (int c = 0; c < cloud.size(); c++) {
			Node now = cloud.get(c);
			int di = now.i + deli[d] * s;
			int dj = now.j + delj[d] * s;
			di = intomap(di);
			dj = intomap(dj);
			now.i = di;
			now.j = dj;
		}
	}

	private static int intomap(int x) {
		if (x > 0)
			return x % n;

		else if (x < 0) {
			while (x < 0)
				return (x + n * 50) % n;
		} else
			return 0;
		return 0;
	}
}