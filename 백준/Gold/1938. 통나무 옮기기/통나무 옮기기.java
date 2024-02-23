import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int i;
		int j;

		public Node(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static int deli[] = {0, 0, 1, -1};
	public static int delj[] = {1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int ary[][] = new int[N][N];
		Node log[] = new Node[3];
		Node end[] = new Node[3];
		int logCnt = 0;
		int endCnt = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				char t = s.charAt(j);
				if (t == 'B') {
					log[logCnt] = new Node(i, j);
					logCnt++;
				} else if (t == 'E') {
					end[endCnt] = new Node(i, j);
					endCnt++;
				} else {
					ary[i][j] = t - '0';
				}
			}
		}

		Node start = new Node((log[0].i + log[1].i + log[2].i) / 3, (log[0].j + log[1].j + log[2].j) / 3);
		int direction = 0;
		if (log[0].j == log[1].j) {
			direction = 1;
		}
		Node finish = new Node((end[0].i + end[1].i + end[2].i) / 3, (end[0].j + end[1].j + end[2].j) / 3);
		int finDirection = 0;
		if (end[0].j == end[1].j) {
			finDirection = 1;
		}
		boolean visited[][][] = new boolean[N][N][2];//0가로, //1세로
		visited[start.i][start.j][direction] = true;
		Queue<Node> q = new ArrayDeque<>();
		Queue<Integer> qDir = new ArrayDeque<>();
		Queue<Integer> qCnt = new ArrayDeque<>();
		q.add(start);
		qDir.add(direction);
		qCnt.add(0);
		int answer = 0;
		//BFS
		while (!q.isEmpty()) {
			Node now = q.poll();
			int nowDir = qDir.poll();
			int cnt = qCnt.poll();
			//if now == finish 정답찾음
			if (now.i == finish.i && now.j == finish.j && nowDir == finDirection) {
				answer = cnt;
			}
			//회전
			int nextDir = (nowDir + 1) % 2;

			boolean possible = true;
			//방문한적이 없어야함
			if (visited[now.i][now.j][nextDir])
				possible = false;

			//9칸 내에 나무가 없어야한다
			for (int i = now.i - 1; i <= now.i + 1; i++) {
				for (int j = now.j - 1; j <= now.j + 1; j++) {
					//회전했을때 범위 밖으로 튀어 나가던가 9칸중에 나무가 있다면 false
					if (i < 0 || i >= N || j < 0 || j >= N || ary[i][j] == 1) {
						possible = false;
						break;
					}
				}
				if (!possible)
					break;
			}
			if (possible) {
				q.add(new Node(now.i, now.j));
				qDir.add(nextDir);
				qCnt.add(cnt + 1);
				visited[now.i][now.j][nextDir] = true;
			}

			//상하좌우
			for (int idx = 0; idx < 4; idx++) {
				int di = now.i + deli[idx];
				int dj = now.j + delj[idx];
				//범위 내에 있다
				if (di >= 0 && dj >= 0 && di < N && dj < N) {
					//방문한적이 있으면 불가능하다
					if (visited[di][dj][nowDir])
						continue;
					//가로일경우
					if (nowDir == 0) {
						// 좌우 끝은 불가능하다
						if (dj == 0 || dj == N - 1)
							continue;
						//세 위치 중 하나라도 나무가 있으면 이동 불가능하다
						if (ary[di][dj] == 1 || ary[di][dj - 1] == 1 || ary[di][dj + 1] == 1)
							continue;
					}
					//세로일 경우
					if (nowDir == 1) {
						// 상하 끝은 불가능하다
						if (di == 0 || di == N - 1)
							continue;
						//세 위치 중 하나라도 나무가 있으면 이동 불가능하다
						if (ary[di - 1][dj] == 1 || ary[di][dj] == 1 || ary[di + 1][dj] == 1)
							continue;
					}
					//방문한적없고 범위 내에 있고 나무도 없으니 움직일 수 있다
					q.add(new Node(di, dj));
					qDir.add(nowDir);
					qCnt.add(cnt + 1);
					visited[di][dj][nowDir] = true;

				}
			}
		}
		System.out.println(answer);
	}
}