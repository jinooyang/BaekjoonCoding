import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static int D(int x) {
		x *= 2;
		x %= 10000;
		return x;
	}

	static int S(int x) {
		if (x == 0)
			return 9999;
		else
			return x - 1;
	}

	static int L(int x) {
		x *= 10;
		int temp = x / 10000;
		x += temp;
		x %= 10000;
		return x;
	}

	static int R(int x) {
		int temp = x % 10;
		x = x / 10;
		x += temp * 1000;
		return x;
	}

	static int DSLR(int x, int i) {
		if (i == 1)
			return D(x);
		else if (i == 2)
			return S(x);
		else if (i == 3)
			return L(x);
		else
			return R(x);
	}

	static class Node {
		int i;
		String s;

		public Node(int i, String s) {
			super();
			this.i = i;
			this.s = s;
		}

	}

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			Set<Integer> v = new HashSet<>();
			Queue<Node> q = new ArrayDeque<>();
			v.add(A);
			q.add(new Node(A, ""));
			Node answer = null;
			while (!q.isEmpty()) {
				Node now = q.poll();
				if (now.i == B) {
					answer = now;
					break;
				}
				for (int i = 1; i <= 4; i++) {
					int child = DSLR(now.i, i);
					if (!v.contains(child)) {
						v.add(child);
						String ns = make(now.s, i);
						q.add(new Node(child, ns));
					}
				}

			}
			System.out.println(answer.s);
		}
	}

	private static String make(String ns, int i) {
		if (i == 1) {
			return ns + "D";
		}
		if (i == 2) {
			return ns + "S";
		}
		if (i == 3) {
			return ns + "L";
		}
		return ns + "R";
	}

}