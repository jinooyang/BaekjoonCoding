import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

		@Override
		public String toString() {
			return "Node [i=" + i + ", j=" + j + "]";
		}

	}

	static class FireBall {
		Node node;
		int mass;
		int speed;
		int direction;

		public FireBall(Node node, int mass, int speed, int direction) {
			super();
			this.node = node;
			this.mass = mass;
			this.speed = speed;
			this.direction = direction;
		}

		@Override
		public String toString() {
			return "FireBall [node=" + node + ", mass=" + mass + ", speed=" + speed + ", direction=" + direction + "]";
		}

	}

	static int deli[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int delj[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static Map<Node, List<FireBall>> map;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// ary
		int M = Integer.parseInt(st.nextToken());// fireball
		int K = Integer.parseInt(st.nextToken());// command
		map = new HashMap<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());// row
			int c = Integer.parseInt(st.nextToken());// col
			int m = Integer.parseInt(st.nextToken());// mass
			int s = Integer.parseInt(st.nextToken());// speed
			int d = Integer.parseInt(st.nextToken());// direction
			Node newNode = new Node(r, c);
			FireBall newFireBall = new FireBall(newNode, m, s, d);
			// 처음부터 같은 노드에 파이어볼이 없다고 가정
			map.put(new Node(r, c), new ArrayList<>());
			map.get(newNode).add(newFireBall);

		}

		// 이동 명령을 K번 내린다
		for (int move = 0; move < K; move++) {
			//System.out.println("-------------------------");
			// System.out.println("move : " + move);
			// 1. move all fireballs
			moveAllFireBalls();
			// 2 .MergeFireBalls

			mergeFireBalls();
		}

		// add all mass
		int answer = 0;
		for (Node n : map.keySet()) {
			List<FireBall> fblist = map.get(n);
			for (int i = fblist.size() - 1; i >= 0; i--) {
				answer += fblist.get(i).mass;

			}
		}
		System.out.println(answer);
	}

	private static void mergeFireBalls() {

		// divide fireBalls
		// System.out.println("mapsize : " + map.size());
		for (Node n : map.keySet()) {
			// System.out.println(n);
			List<FireBall> fblist = map.get(n);
			if (fblist.size() > 1) {// 파이어볼이 여러개 있으면 머지한다
				mergeFirBall(fblist);
			}
//			for(FireBall f : fblist) {
//				System.out.println("!" + f);
//			}
		}
		// System.out.println("mapsize : " + map.size());
		// moveFBinMap();
		// System.out.println("mapsize : " + map.size());

	}

	private static void mergeFirBall(List<FireBall> fblist) {
		int totalMass = 0;
		int totalSpeed = 0;
		boolean isOdd = true;
		boolean isEven = true;
		int nodei = fblist.get(0).node.i;
		int nodej = fblist.get(0).node.j;
		int size = fblist.size();
		// 계산
		for (int i = 0; i < size; i++) {
			FireBall fb = fblist.get(i);
			totalMass += fb.mass;
			totalSpeed += fb.speed;
			if (fb.direction % 2 == 0) {
				isOdd = false;
			}
			if (fb.direction % 2 == 1) {
				isEven = false;
			}
		}
		// System.out.println(totalMass);
		totalMass = totalMass / 5;
		totalSpeed = totalSpeed / size;

		fblist.clear();
		// System.out.println("mergeFirBall" + fblist.size());
//		for(Node n : map.keySet()) {
//			System.out.println(map.get(n).size());
//		}
		if (totalMass == 0) {
			return;
		}
		for (int dir = 0; dir < 4; dir++) {

			if (isEven || isOdd) {// 모두 홀수 혹은 짝수 일때
				fblist.add(new FireBall(new Node(nodei, nodej), totalMass, totalSpeed, dir * 2));
			} else {
				fblist.add(new FireBall(new Node(nodei, nodej), totalMass, totalSpeed, dir * 2 + 1));
			}
		}
		//System.out.println("!" + fblist.size());

	}

	private static void moveAllFireBalls() {
		// move each fireballs
		// System.out.println("mapsize : " + map.size());
		for (Node n : map.keySet()) {
			List<FireBall> fblist = map.get(n);
			for (int i = fblist.size() - 1; i >= 0; i--) {
				FireBall fb = fblist.get(i);
				// System.out.println(n);
				moveFireBall(fb);
				// 파이어볼에게 새로운 node를 줌
			}
		}
		// System.out.println("---");
		// System.out.println("mapsize : " + map.size());
		// move to new place in map

		moveFBinMap();
//		for (Node n : map.keySet()) {
//			System.out.println(n);
//			System.out.println(map.get(n).size());
//		}
		// System.out.println("mapsize : " + map.size());
		return;
	}

	private static void moveFBinMap() {// 문제있음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// 새로운 맵 생성
		Map<Node, List<FireBall>> newMap = new HashMap<>();
		// 원래 맵의 노드를 순회한다
		for (Node n : map.keySet()) {
			// System.out.println(n);
			List<FireBall> fblist = map.get(n);
			int size = fblist.size();
			for (int i = size - 1; i >= 0; i--) {
				FireBall fb = fblist.get(i);
				// System.out.println(fb);
				//System.out.println(fb.node + " " + n);
				//if (!fb.node.equals(n)) {// 위치를 옮겨야함
				//	System.out.println("X");
					if (!newMap.containsKey(fb.node)) {
						//System.out.println("made new key");
						int nodei = fb.node.i;
						int nodej = fb.node.j;
						newMap.put(new Node(nodei, nodej), new ArrayList<>());
					}
					newMap.get(fb.node).add(fb);// 위치 옮김
					fblist.remove(i);
				//}

			}
		}
		map = new HashMap<>(newMap);
		// System.out.println("s : " + map.size());// 0??
	}

	private static void moveFireBall(FireBall fb) {
		// System.out.println("movefireball");
		int dir = fb.direction;
		int speed = fb.speed;
		int i = fb.node.i;
		int j = fb.node.j;
		// System.out.print("i : " + i + ", j : " + j+ " | " );
		i = i + deli[dir] * speed;
		j = j + delj[dir] * speed;
		// 범위 안쪽으로 이동
		// 파이어볼은 순환한다
		while (i > N) {
			i -= N;
		}
		while (j > N) {
			j -= N;
		}
		while (i < 1) {
			i += N;
		}
		while (j < 1) {
			j += N;
		}
		// System.out.println("i : " + i + ", j : " + j);
		fb.node.i = i;
		fb.node.j = j;

	}
}