import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		boolean isNumber[] = new boolean[1000001];
		int mul[] = new int[1000001];
		List<Integer> list = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(st.nextToken());
			isNumber[x] = true;
			list.add(x);
			map.put(i, x);
		}
		Collections.sort(list);
		int size = list.size();
		int answer[] = new int[1000001];
		for (int i = 1; i <= size; i++) {
			int now = list.get(i - 1);
			// 내 밑에 나를 나눌 수 있는 수들이 이만큼 있음
			// 그 수만큼 패배함
			int score = mul[now] * (-1);

			// now의 배수들을 모두 mul에 저장하자
			int m = 2;
			while (now * m <= 1000000) {
				mul[now * m]++;
				if (isNumber[now * m] == true) {
					score++;
				}
				m++;
			}
			answer[now] = score;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(answer[map.get(i)]).append(" ");
		}
		System.out.println(sb.toString());
	}
}