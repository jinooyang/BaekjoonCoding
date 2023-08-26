import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static class Node {
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

	public static class Shark {
		Node node;
		int speed;
		int direction;
		int size;

		public Shark(Node node, int speed, int direction, int size) {
			super();
			this.node = node;
			this.speed = speed;
			this.direction = direction;
			this.size = size;
		}

	}

	static int deli[] = { 0, -1, 1, 0, 0 };// Up Down Right Left
	static int delj[] = { 0, 0, 0, 1, -1 };
	static List<Shark> sharkList;
	static int N;
	static int M;
	static int down[];
	static int right[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		down = new int[2 * N - 2];
		right = new int[2 * M - 2];
		init();

		int sharkNum = Integer.parseInt(st.nextToken());// for input
		sharkList = new ArrayList<>();
		for (int i = 0; i < sharkNum; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());// i
			int c = Integer.parseInt(st.nextToken());// j
			int s = Integer.parseInt(st.nextToken());// speed
			int d = Integer.parseInt(st.nextToken());// direction
			int z = Integer.parseInt(st.nextToken());// size
			Node newNode = new Node(r, c);
			Shark newShark = new Shark(newNode, s, d, z);
			sharkList.add(newShark);
		}

		// 1. move fishKing to right(o)
		int answer = 0;
		for (int fishKing = 1; fishKing <= M; fishKing++) {
			// 2. catch fish(o)
			answer += catchFish(fishKing);
		//	System.out.println(answer);
			// 3. moveAll sharks(o)
			moveAllSharks();
			// 4. sharkEatShark(o)
			sharkKillsShark();
		}
		System.out.println(answer);
	}

	private static void sharkKillsShark() {
		// add shark to map
		// key is Node of shark
		// value is the surviving shark obejct Shark
		// if shark dies in the process of adding to map
		// remove shark from list
		Map<Node, Shark> map = new HashMap<>();
		for (int i = 0; i < sharkList.size(); i++) {
			if (!map.containsKey(sharkList.get(i).node)) {
				map.put(sharkList.get(i).node, sharkList.get(i));
			} else {
				// if the next shark is bigger than the shark in map
				// replace to new shark
				if (map.get(sharkList.get(i).node).size < sharkList.get(i).size) {
					map.put(sharkList.get(i).node, sharkList.get(i));
				}
			}
		}
		// replace shark list with sharks in map

		sharkList.clear();
		for (Node node : map.keySet()) {
			sharkList.add(map.get(node));
		}
		//System.out.println("size : " + sharkList.size());
	}

	private static void init() {
		int idx = 0;
		for (int i = 1; i <= N; i++) {
			down[idx++] = i;
		}
		for (int i = N - 1; i >= 2; i--) {
			down[idx++] = i;
		}

		idx = 0;
		for (int i = 1; i <= M; i++) {
			right[idx++] = i;
		}
		for (int i = M - 1; i >= 2; i--) {
			right[idx++] = i;
		}

//		for (int i = 0; i < 2 * N - 2; i++) {
//			System.out.print(down[i] + " ");
//		}
//		System.out.println();
//		for (int i = 0; i < 2 * M - 2; i++) {
//			System.out.print(right[i] + " ");
//		}
	}

	private static void moveAllSharks() {
		// move one Shark
		for (int i = 0; i < sharkList.size(); i++) {
			moveOneShark(i);
		}

	}

	private static void moveOneShark(int x) {
		// move each shark according to their coordinates, direction and speed
		// need to know which shark to move
		// int x is sharList's index
		// can figure out which shark to move
		Shark shark = sharkList.get(x);// move this shark
		int dist = shark.speed;
		int i = shark.node.i;
		int j = shark.node.j;
		int direction = 0;
		if (shark.direction == 1) {// up
			int idx = (2 * N - 2 - (i - 1) + dist) % (2 * N - 2);
			i = down[idx];
			if (idx >= N)
				direction = 1;
			else
				direction = 2;
		}
		if (shark.direction == 2) {// down
			int idx = (dist + i - 1) % (2 * N - 2);
			i = down[idx];
			if (idx >= N)
				direction = 1;
			else
				direction = 2;

		}
		if (shark.direction == 3) {// right
			int idx = (dist + j - 1) % (2 * M - 2);
			j = right[idx];

			if (idx >= M)
				direction = 4;
			else
				direction = 3;

		}
		if (shark.direction == 4) {// left
			int idx = (2 * M - 2 - (j - 1) + dist) % (2 * M - 2);
			j = right[idx];
			if (idx >= M)
				direction = 4;
			else
				direction = 3;

		}

		shark.node.i = i;
		shark.node.j = j;
		shark.direction = direction;
		sharkList.set(x, shark);
		// sharkList.get(x).node.i = i;
		// sharkList.get(x).node.j = j;
	}

	private static int catchFish(int col) {
		// catch the closest shark in the current column;
		// find shark index in list
		// return the shark's size
		// remove the shark from list
		int closestSharkSize = 0;
		int closestSharkIIndex = Integer.MAX_VALUE;
		int closestSharkIndex = -1;// for removing shark from sharkList
		for (int i = 0; i < sharkList.size(); i++) {
			// if the shark is in the same column as the fishKing
			if (sharkList.get(i).node.j == col) {
				// if this shark is closer -> if node.i is smaller
				if (sharkList.get(i).node.i < closestSharkIIndex) {
					closestSharkIIndex = sharkList.get(i).node.i;
					closestSharkIndex = i;
					closestSharkSize = sharkList.get(i).size;
				}
			}
		}
		// remove shark from shark list if closestSharkIndex!=-1
		if (closestSharkIndex != -1) {
			sharkList.remove(closestSharkIndex);
		}

		return closestSharkSize;

	}
}