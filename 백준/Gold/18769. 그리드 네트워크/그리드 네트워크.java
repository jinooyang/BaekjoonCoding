import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int graph[][];
	static int parent[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph = new int[10000][3];
			for (int i = 0; i < 10000; i++) {
				graph[i][2] = Integer.MAX_VALUE;
			}
			parent = new int[r * c];
			for (int i = 0; i < r * c; i++) {
				parent[i] = i;
			}
			int edge = 0;
			for (int i = 0; i < r; i++) {// c-1개의 입력
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < c - 1; j++) {
					int a = Integer.parseInt(st.nextToken());// 좌우
					graph[edge][0] = i * c + j;
					graph[edge][1] = i * c + j + 1;
					graph[edge][2] = a;
					edge++;
				}
			}
			for (int i = 0; i < r - 1; i++) {// c개의 입력
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < c; j++) {
					int a = Integer.parseInt(st.nextToken());// 상하
					graph[edge][0] = i * c + j;
					graph[edge][1] = (i + 1) * c + j;
					graph[edge][2] = a;
					edge++;
				}
			}
			// 입력 완료
			Arrays.sort(graph, (o1, o2) -> Integer.compare(o1[2], o2[2]));
//			for (int i = 0; i < edge; i++) {
//				System.out.println(graph[i][0] + " " + graph[i][1] + " " + graph[i][2]);
//			}
			int answer = 0;
			for (int i = 0; i < edge; i++) {
				if (find(graph[i][0]) != find(graph[i][1])) {
					answer += graph[i][2];
					union(graph[i][0], graph[i][1]);
				}
			}
			System.out.println(answer);

		}
	}

	private static void union(int i, int j) {
		int a = find(i);
		int b = find(j);
		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;

	}

	private static int find(int x) {
		if (x == parent[x])
			return x;
		else
			return parent[x] = find(parent[x]);
	}
}