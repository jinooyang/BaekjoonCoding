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

	static class Fish {
		int i;
		int j;
		int d;

		public Fish(int i, int j, int d) {
			super();
			this.i = i;
			this.j = j;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Fish [i=" + i + ", j=" + j + ", d=" + d + "]";
		}

	}

	static int deliF[] = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int deljF[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int deliS[] = { -1, 0, 1, 0 };
	static int deljS[] = { 0, -1, 0, 1 };
	static List<Integer> map[][] = new List[4][4];
	static int[][] smell = new int[4][4];
	static int sharkI;
	static int sharkJ;
	static int sharkMoveTestAry[] = new int[3];

	public static void main(String[] args) throws IOException {
//      System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		// map에는 물고기의 정보가 들어있다.
		// 각 map에는 물고기의 방향정보가 들어있다.
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			// 물고기 위치와 방향을 입력받는다
			st = new StringTokenizer(br.readLine());
			int fi = Integer.parseInt(st.nextToken()) - 1;// 위치도 인덱스에 맞춰 1 빼준다
			int fj = Integer.parseInt(st.nextToken()) - 1;
			int fd = Integer.parseInt(st.nextToken()) - 1;// 방향을 1 빼준다
			map[fi][fj].add(fd);
		}
		// 상어의 위치를 입력받는다
		st = new StringTokenizer(br.readLine());
		sharkI = Integer.parseInt(st.nextToken()) - 1;// 상어 위치도 인덱스에 맞춰 1 빼준다
		sharkJ = Integer.parseInt(st.nextToken()) - 1;
		// 연습횟수 만큼 반복한다
		for (int i = 0; i < S; i++) {
			// System.out.println("start : " + sharkI + " " + sharkJ);
			for (int a = 0; a < 4; a++) {
				for (int b = 0; b < 4; b++) {
					if (map[a][b].size() > 0) {
						// System.out.print(map[a][b].get(0) + " ");
//                  System.out.print(map[a][b].size()+" ");
					} else {
						// System.out.print(0 + " ");
					}
				}
				// System.out.println();
			}
			// 1. 복제할 물고기 목록을 저장한다
			List<Fish> copyList = getCopy();

			// 2.물고기를 한칸 이동한다
			// 물고기를 이동 시킬 위치를 List에 저장하고 map을 초기화 시킨다.
			// 물고기는 같은 칸에 여러마리 있을 수 있다.
			
			List<Fish> moveList = moveFish();
//			System.out.println("이동 전 크기 : " + copyList.size() + "이동 후 크기 : " + moveList.size());
			// moveList를 map에 적용한다
			// System.out.println(moveList);
			moveFishResult(moveList);
			// 물고기 이동이 완료했다.

			// 3. 상어를 연속 3칸이동하는데
			// 물고기를 제일 많이 먹을 수 있는 방향으로 이동한다
			// 그리고 최대 물고기를 먹을 수 있는 경우의 수가 여러가지라면 사전순으로 앞선 선택지를 선택한다
			moveShark();
			// System.out.println("shark location : " + sharkI + " " + sharkJ);
			// 4.냄새를 제거한다
			removeSmell();

			// 5.복제 물고기 생성한다
			copyFish(copyList);
//			System.out.println("shark : " + sharkI + " " + sharkJ);
//			for (int a = 0; a < 4; a++) {
//				for (int b = 0; b < 4; b++) {
//					if (map[a][b].size() > 0) {
////                  System.out.print(map[a][b].get(0) + " ");
//						System.out.print(map[a][b].size() + " ");
//					} else {
//						System.out.print(0 + " ");
//					}
//				}
//				System.out.println();
//			}
//			System.out.println("---------");
		}
		int ans = getResult();
		System.out.println(ans);
	}

	private static int getResult() {
		int ans = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				ans += map[i][j].size();
			}
		}
		return ans;
	}

	private static void copyFish(List<Fish> copyList) {
		for (int i = 0; i < copyList.size(); i++) {
			Fish f = copyList.get(i);
			map[f.i][f.j].add(f.d);
		}
	}

	private static void removeSmell() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (smell[i][j] > 0) {
					smell[i][j]--;
				}
			}
		}
	}

	private static void moveShark() {

		for (int i = 0; i < 3; i++) {
			sharkMoveTestAry[i] = 0;
		}
		int maxFish = -1;
		int bestRoute[] = new int[3];
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				for (int c = 0; c < 4; c++) {
					sharkMoveTestAry[0] = a;
					sharkMoveTestAry[1] = b;
					sharkMoveTestAry[2] = c;
					int FishEaten = tryMove();
					if (FishEaten != -1 && FishEaten > maxFish) {

						maxFish = FishEaten;
						bestRoute[0] = a;
						bestRoute[1] = b;
						bestRoute[2] = c;
						// System.out.println(FishEaten + "마리 먹고 : " + a + " " + b + " " + c);
					}

				}
			}

		}
//		String s = "";
//		for (int i = 0; i < 3; i++) {
//			int x = bestRoute[i];
//			if (x == 0) {
//				s += "상";
//			}
//			if (x == 1) {
//				s += "좌";
//			}
//			if (x == 2) {
//				s += "하";
//			}
//			if (x == 3) {
//				s += "우";
//			}
//		}
//		System.out.println(s);
		for (int idx = 0; idx < 3; idx++) {
			sharkI += deliS[bestRoute[idx]];
			sharkJ += deljS[bestRoute[idx]];
			// System.out.println("sharkMoveto : " + sharkI + " " + sharkJ);
			if (map[sharkI][sharkJ].size() > 0) {
				map[sharkI][sharkJ].clear();// 물고기를 모두 없앤다
				smell[sharkI][sharkJ] = 3;// 물고기 냄새를 기록한다
			}
		}
		// 상어 이동 완료

	}

	private static int tryMove() {
		int result = 0;
		Set<Node> set = new HashSet<>();
		int si = sharkI;
		int sj = sharkJ;
		for (int idx = 0; idx < 3; idx++) {
			si += deliS[sharkMoveTestAry[idx]];
			sj += deljS[sharkMoveTestAry[idx]];
			if (!isInsideMap(si, sj))
				return -1;
			if (set.contains(new Node(si, sj)))
				continue;
			else {
				result += map[si][sj].size();
				set.add(new Node(si, sj));
			}
		}
		return result;
	}

	private static void moveFishResult(List<Fish> moveList) {
		// map초기화한다
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j].clear();
			}
		}
		for (int i = 0; i < moveList.size(); i++) {
			Fish f = moveList.get(i);
			map[f.i][f.j].add(f.d);
		}
	}

	private static List<Fish> moveFish() {
		List<Fish> moveResult = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				List<Integer> now = map[i][j];
				for (Integer dir : now) {
					// 현재 위치에 있는 물고기의 이동방향을 알고있다.
					int di = 0;
					int dj = 0;
					int idx = dir;
					// System.out.println("idx:" + idx);
					for (int test = 0; test < 9; test++) {
						if (test == 8) {
							moveResult.add(new Fish(i, j, dir));
							break;
						}
						// 8방향중 되는 것을 찾기 위해 8번까지테스트 해본다
						di = i + deliF[idx];
						dj = j + deljF[idx];
						// 맵 안쪽이고
						if (isInsideMap(di, dj)) {
							// 상어 위치와 겹치고
							if (!(di == sharkI && dj == sharkJ)) {
								// 냄새가 나지 않는다
								if (smell[di][dj] == 0) {
									// 새로운 위치에 물고기가 생긴다
									moveResult.add(new Fish(di, dj, idx));
//									System.out
//											.println("물고기 이동" + idx + " : " + i + "," + j + "->" + " " + di + " " + dj);
									break;
								}
							}
						}

						// 찾기를 실패했으면 다음 방향으로 확인해본다

						idx--;
						if (idx < 0)
							idx += 8;
					}
				}
			}
		}
		// 새로운 물고기 위치를 반환한다.
		return moveResult;
	}

	private static boolean isInsideMap(int di, int dj) {
		return di >= 0 && dj >= 0 && di < 4 && dj < 4;
	}

	private static List<Fish> getCopy() {
		List<Fish> list = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				List<Integer> now = map[i][j];
				for (Integer dir : now) {
					list.add(new Fish(i, j, dir));
				}
			}
		}
		return list;
	}
}