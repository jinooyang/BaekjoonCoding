import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			long ary[][] = new long[n][2];
			long graph[][] = new long[n * n][3];
			for (int i = 0; i < n * n; i++) {
				Arrays.fill(graph[i], Long.MAX_VALUE);
			}
			long parent[] = new long[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
			st = new StringTokenizer(br.readLine());
			for (int in = 0; in < n; in++) {
				int x = Integer.parseInt(st.nextToken());
				ary[in][0] = x;
			}
			st = new StringTokenizer(br.readLine());
			for (int in = 0; in < n; in++) {
				int y = Integer.parseInt(st.nextToken());
				ary[in][1] = y;
			}
			st = new StringTokenizer(br.readLine());
			double E = Double.parseDouble(st.nextToken());
			// 입력 완료

			int idx = 0;
			for (int s = 0; s < n - 1; s++) {
				for (int e = s + 1; e < n; e++) {
					long dist = (long) (Math.pow(ary[s][0] - ary[e][0], 2) + Math.pow(ary[s][1] - ary[e][1], 2));
					graph[idx][0] = s;
					graph[idx][1] = e;
					graph[idx][2] = dist;
					idx++;

				}
			}

			Arrays.sort(graph, (o1, o2) -> Long.compare(o1[2], o2[2]));
			long answer = 0;
			for (int i = 0; i < idx; i++) {
				if (find(graph[i][0], parent) != find(graph[i][1], parent)) {
					answer += graph[i][2];
					union(graph[i][0], graph[i][1], parent);
				}
			}
			System.out.println("#" + tc + " " + (long) Math.round(answer * E));
		}
	}

	private static void union(long l, long m, long[] parent) {
		long a = find(l, parent);
		long b = find(m, parent);
		if (a < b)
			parent[(int) b] = a;
		else
			parent[(int) a] = b;

	}

	private static long find(long x, long[] parent) {
		if (parent[(int) x] == x)
			return x;
		else
			return parent[(int) x] = find(parent[(int) x], parent);
	}
}