import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static int[] deli = {0, 1, -1, 0, 0};
	static int[] delj = {0, 0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		// T = 1;
		for (int tc = 0; tc < T; tc++) {
			List<List<Integer>> ary = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				String s = br.readLine();
				ary.add(new ArrayList<>());
				for (int j = 0; j < 3; j++) {
					char x = s.charAt(j);
					if (x == '*')
						ary.get(i).add(1);
					else
						ary.get(i).add(0);

				}
			}
			Set<List<List<Integer>>> set = new HashSet<>();

			Queue<List<List<Integer>>> q = new ArrayDeque<>();
			Queue<Integer> qcnt = new ArrayDeque<>();
			List<List<Integer>> start = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				start.add(new ArrayList<>());
				for (int j = 0; j < 3; j++)
					start.get(i).add(0);
			}

			q.add(start);
			qcnt.add(0);
			set.add(start);
			int answer = Integer.MAX_VALUE;
			while (!q.isEmpty()) {
				List<List<Integer>> now = q.poll();
				// printlist(now);
				int cnt = qcnt.poll();
				if (now.equals(ary)) {
					// System.out.println("found");
					// System.out.println(cnt);
					answer = Math.min(answer,cnt);
					continue;
				}

				for (int k = 0; k < 9; k++) {
					List<List<Integer>> temp = new ArrayList<>();
					for (int a = 0; a < 3; a++) {
						temp.add(new ArrayList<>());
						for (int b = 0; b < 3; b++) {
							temp.get(a).add(now.get(a).get(b));
						}
					}
					int i = k / 3;
					int j = k % 3;
					//상하좌우를 바꿔보자
					for (int idx = 0; idx < 5; idx++) {
						//상하좌우
						int di = i + deli[idx];
						int dj = j + delj[idx];
						// System.out.println(di + " " + dj);
						if (di >= 0 && dj >= 0 && di < 3 && dj < 3) {
							int bf = temp.get(di).get(dj);
							bf = (bf + 1)%2;
							temp.get(di).set(dj, bf);
						}
					}
					// printlist(temp);
					if (!set.contains(temp)) {
						set.add(temp);
						q.add(temp);
						qcnt.add(cnt + 1);
					}
				}

			}
			System.out.println(answer);
		}

	}

	private static void printlist(List<List<Integer>> now) {
		System.out.println("---");
		for(int a=0;a<3;a++){
			for(int b=0;b<3;b++){
				System.out.print(now.get(a).get(b));
			}
			System.out.println();
		}
		System.out.println(now.get(0).get(0));
	}

}