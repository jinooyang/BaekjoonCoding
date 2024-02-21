import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int[] parent;
	static int[] order;

	static int[][] graph;
	static int N;
	static int M;

	static Map<Integer, int[]> map;

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		order = new int[5];
		parent = new int[N + 1];//부모를 저장할녀석
		graph = new int[M][3];
		map = new HashMap<>();
		// st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = st.nextToken().charAt(0) - 'A';//학교의 인덱스
			// System.out.println(u + " " + v + " " + w);

			graph[i][0] = u;
			graph[i][1] = v;
			graph[i][2] = w;
		}

		int[][] school = new int[5][3];
		//모든 경우의 수에 대한 MST그래프를 구한다

		getCombo(0);
		StringBuilder sb = new StringBuilder();
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 5; i++) {
				school[i][0] = Integer.parseInt(st.nextToken());
				school[i][1] = i;
			}
			//학교별 관리비를 순서대로 정렬한다
			Arrays.sort(school, (o1, o2) -> Integer.compare(o1[0], o2[0]));
			for (int i = 1; i <= 5; i++) {
				school[i-1][2] = i;
			}
			Arrays.sort(school, (o1, o2) -> Integer.compare(o1[1], o2[1]));
			// for(int i=0;i<5;i++){
			// 	System.out.println(school[i][0] + " "+school[i][1] +" "+school[i][2]);
			// }
			int key =
				school[0][2] * 10000 + school[1][2] * 1000 + school[2][2] * 100 + school[3][2] * 10 + school[4][2];
			// System.out.println(key);
			int[] ary = map.get(key);
			// System.out.println(Arrays.toString(ary));
			long ans = (long)school[0][0] * ary[0] + (long)school[1][0] * ary[1] + (long)school[2][0] * ary[2] + (long)school[3][0] * ary[3]
				+ (long)school[4][0] * ary[4];
			sb.append(ans).append("\n");
		}
		System.out.println(sb);

	}

	//모든 조합을 구한다 최개 5*4*3*2*1 = 120

	private static void getCombo(int used) {//비트마스킹으로 사용한 수를 저장한다 최대 31
		if (used == 31) {
			makeMST();
			return;
		}
		for (int i = 0; i < 5; i++) {

			if ((used & (1 << i)) == 0) {//사용이 안된 상태
				used |= (1 << i);
				// System.out.println(used);
				order[Integer.bitCount(used) - 1] = i + 1;
				getCombo(used);
				used &= ~(1 << i);//원상 복구
			}
		}
	}

	//해당 조합을 바탕으로 MST를 만든다
	//order배열을 바탕으로 MST를 만든다
	private static void makeMST() {
		//그래프를 정렬한다
		Arrays.sort(graph, (o1, o2) -> Integer.compare(order[o1[2]], order[o2[2]]));
		//parent배열을 초기화한다
		for (int i = 0; i < N + 1; i++)
			parent[i] = i;
		//간선을 짧은 순서대로 방문하며 unionFind를 통해 MST생성한다
		int[] answer = new int[5];//각 학교가 몇개의 도로를 관리하는지를 저장한다
		for (int i = 0; i < M; i++) {
			//부모가 다르다면 추가한다
			if (find(graph[i][0]) != find(graph[i][1])) {
				union(graph[i][0], graph[i][1]);
				answer[graph[i][2]]++;
			}
		}
		// System.out.println(Arrays.toString(answer));
		int key = order[0] * 10000 + order[1] * 1000 + order[2] * 100 + order[3] * 10 + order[4];
		// System.out.println(key);
		map.put(key, answer);
	}

	private static void union(int x, int y) {
		int a = find(x);
		int b = find(y);
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