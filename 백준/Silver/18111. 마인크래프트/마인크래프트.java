import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		Integer ary[] = new Integer[N * M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				ary[i * M + j] = Integer.parseInt(st.nextToken());
			}
		}

		Arrays.sort(ary, Collections.reverseOrder());// 내림 차순 정렬
		int maxH = ary[0];
		int minH = ary[N * M - 1];
		if (minH == maxH) {
			System.out.println(0 + " " + maxH);
			return;
		}
		int minTime = Integer.MAX_VALUE;
		int maxLevel = 0;
		for (int level = maxH; level >= minH; level--) {// 최대 레밸 부터 최소 레벨 까지 보자
			// System.out.println("level : " + level);
			int bag = B;
			int time = 0;
			boolean possible = true;
			for (int i = 0; i < N * M; i++) {
				if (time > minTime) {
					possible = false;
					break;
				}
				// System.out.println(ary[i]);
				if (ary[i] > level) {
					// time+=2;
					int diff = ary[i] - level;// 차이 구함
					time += diff * 2;// 시간 누적
					bag += diff;// 가방에 블록을 넣는다
					// System.out.print("bag : " + bag);
				}
				if (ary[i] == level) {
					continue;
				}
				if (ary[i] < level) {
					// time +=1;
					int diff = level - ary[i];// 차이 구함
					// System.out.println("diff : " + diff + " " + bag);
					if (diff > bag) {// 가방에 블록이 모자름
						possible = false;
						break;
					}
					time += diff;// 시간 누적
					bag -= diff;// 가방에서 블록을 뺸다

				}
			}

			if (possible) {// 가능하다면
				// System.out.println("lv : " + level + " " + time);
				if (minTime > time) {// 시간이 더 적게 걸렸다면
					minTime = time;
					maxLevel = level;
				}
				// 시간이 같다면 앞에서 업데이트 했으니 무시한다
				// 시간이 더 오래 걸렸다면 무시해도 좋다
			}

		}
		System.out.println(minTime + " " + maxLevel);

	}
}