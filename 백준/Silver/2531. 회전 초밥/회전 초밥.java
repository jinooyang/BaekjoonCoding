import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		List<Integer> list = new ArrayList<>();// 회전 초밥
		int sushiCnt[] = new int[d + 1];// 현재 셋에 들어있는 초밥의 수
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			list.add(x);
		}
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < k; i++) {// set에 k개 넣는다
			set.add(list.get(i));
			sushiCnt[list.get(i)]++;
		}

		int maxCntContainC = 0;
		int maxCntWithoutC = 0;

		for (int i = 0; i < n; i++) {
			//System.out.println(set);
			if (set.contains(c)) {
				if (set.size() > maxCntContainC)
					maxCntContainC = set.size();
			} else {
				if (set.size() > maxCntWithoutC)
					maxCntWithoutC = set.size();
			}

			sushiCnt[list.get(i)]--;// 리스트에서 제일 앞에꺼를 셋에서 뺀다
			if (sushiCnt[list.get(i)] == 0)
				set.remove(list.get(i));
			set.add(list.get((i + k) % n));
			sushiCnt[list.get((i + k) % n)]++;
		}
		// System.out.println(maxCntContainC);
		// System.out.println(maxCntWithoutC + 1);
		System.out.println(Math.max(maxCntContainC, maxCntWithoutC + 1));
	}
}